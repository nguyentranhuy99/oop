package Entity;

import Main.GamePanel;
import Main.MouseHandle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PrevButton extends Entity{
    GamePanel gamePanel;
    BufferedImage image;
    MouseHandle mouseHandle;
    public boolean click = false;

    // Constructor
    public PrevButton(GamePanel gamePanel,MouseHandle mouseHandle) {
        this.gamePanel = gamePanel;
        this.mouseHandle = mouseHandle;
        setDefautValues();
        getPauseButtonImage();
        image = prev1;
    }

    // Set possition
    public void setDefautValues() {
        x = 4 * gamePanel.tileSize;
        y = 6 * gamePanel.tileSize;
    }

    // Get image
    public void getPauseButtonImage() {
        try {
            prev1= ImageIO.read((getClass().getResourceAsStream("/tiles/prev1.png")));
            prev2 = ImageIO.read((getClass().getResourceAsStream("/tiles/prev2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update
    public void update() {
        if (mouseHandle.enter) {
            if (image == prev1 ) {
                image = prev2;
            }
            mouseHandle.enter = false;
        }

        if (mouseHandle.exit) {
            if (image == prev2) {
                image = prev1;
            }
            mouseHandle.exit = false;
        }

        if (mouseHandle.click) {
            click = true;
            // Draw for nextButton if character_number max
            gamePanel.nextButton.image = gamePanel.nextButton.next1;
            mouseHandle.click = false;
        }

        // Set image when character_number = 0
        if(gamePanel.character_number == 0) {
            image = prev2;
        }
    }

    // Draw
    public void draw(Graphics2D g2D) {
        g2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
