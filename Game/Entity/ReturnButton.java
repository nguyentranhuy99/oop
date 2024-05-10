package Entity;

import Main.GamePanel;
import Main.MouseHandle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ReturnButton extends Entity {
    GamePanel gamePanel;
    BufferedImage image;
    public MouseHandle mouseHandle;
    public boolean click = false;

    // Constructor
    public ReturnButton(GamePanel gamePanel,MouseHandle mouseHandle) {
        this.gamePanel = gamePanel;
        this.mouseHandle = mouseHandle;
        setDefautValues();
        getPauseButtonImage();
        image = return1;
    }

    // Set possition
    public void setDefautValues() {
        x = 0;
        y = 0;
    }

    // Get image
    public void getPauseButtonImage() {
        try {
            return1 = ImageIO.read((getClass().getResourceAsStream("/tiles/return1.png")));
            return2 = ImageIO.read((getClass().getResourceAsStream("/tiles/return2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update
    public void update() {
        if (mouseHandle.enter) {
            if (image == return1) {
                image = return2;
            }
            mouseHandle.enter = false;
        }

        if (mouseHandle.exit) {
            if (image == return2) {
                image = return1;
            }
            mouseHandle.exit = false;
        }

        if (mouseHandle.click) {
            click = true;
            mouseHandle.click = false;
        }
    }

    // Draw
    public void draw(Graphics2D g2D) {
        g2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
