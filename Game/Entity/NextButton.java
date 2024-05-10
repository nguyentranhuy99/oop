package Entity;

import Main.GamePanel;
import Main.MouseHandle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NextButton extends Entity{
    GamePanel gamePanel;
    BufferedImage image;
    MouseHandle mouseHandle;
    public boolean click = false;

    // Constructor
    public NextButton(GamePanel gamePanel,MouseHandle mouseHandle) {
        this.gamePanel = gamePanel;
        this.mouseHandle = mouseHandle;
        setDefautValues();
        getPauseButtonImage();
        image = next1;
    }

    // Set possition
    public void setDefautValues() {
        x = 11 * gamePanel.tileSize;
        y = 6 * gamePanel.tileSize;
    }

    // Get image
    public void getPauseButtonImage() {
        try {
            next1 = ImageIO.read((getClass().getResourceAsStream("/tiles/next1.png")));
            next2 = ImageIO.read((getClass().getResourceAsStream("/tiles/next2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update
    public void update() {
        if (mouseHandle.enter) {
            if (image == next1) {
                image = next2;
            }
            mouseHandle.enter = false;
        }

        if (mouseHandle.exit) {
            if (image == next2) {
                image = next1;
            }
            mouseHandle.exit = false;
        }

        if (mouseHandle.click) {
            click = true;
            // Set prevButton to prev1 if character_number = 0
            gamePanel.prevButton.image = gamePanel.prevButton.prev1;
            mouseHandle.click = false;
        }

        if(gamePanel.character_number == 4) {
            image = next2;
        }
    }

    // Draw
    public void draw(Graphics2D g2D) {
        g2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
