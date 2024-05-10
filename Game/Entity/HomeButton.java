package Entity;

import Main.GamePanel;
import Main.MouseHandle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HomeButton extends Entity{
    GamePanel gamePanel;
    BufferedImage image;
    MouseHandle mouseHandle;
    public boolean home = false;

    // Constructor
    public HomeButton(GamePanel gamePanel,MouseHandle mouseHandle) {
        this.gamePanel = gamePanel;
        this.mouseHandle = mouseHandle;
        setDefautValues();
        getPauseButtonImage();
        image = home1;
    }

    // Set possition
    public void setDefautValues() {
        x = 4 * gamePanel.tileSize;
        y = 0;
    }

    // Get image
    public void getPauseButtonImage() {
        try {
            home1 = ImageIO.read((getClass().getResourceAsStream("/tiles/home1.png")));
            home2 = ImageIO.read((getClass().getResourceAsStream("/tiles/home2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update
    public void update() {
        if (mouseHandle.enter) {
            if (image == home1) {
                image = home2;
            }
            mouseHandle.enter = false;
        }

        if (mouseHandle.exit) {
            if (image == home2) {
                image = home1;
            }
            mouseHandle.exit = false;
        }

        if (mouseHandle.click) {
            home = true;
            mouseHandle.click = false;
        }
    }

    // Draw
    public void draw(Graphics2D g2D) {
        g2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
