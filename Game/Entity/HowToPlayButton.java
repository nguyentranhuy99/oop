package Entity;

import Main.GamePanel;
import Main.MouseHandle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HowToPlayButton extends Entity{
    GamePanel gamePanel;
    MouseHandle mouseHandle;
    BufferedImage image;
    public boolean click = false;

    // Constructor
    public HowToPlayButton(GamePanel gamePanel, MouseHandle mouseHandle){
        this.gamePanel = gamePanel;
        this.mouseHandle = mouseHandle;
        setDefautValues();
        getNewGameButtonImage();
        image = howToPlay1;
    }

    // Set possition
    public void setDefautValues(){
        x = 6 * gamePanel.tileSize;
        y = 9 * gamePanel.tileSize;
    }

    // Get image
    public void getNewGameButtonImage(){
        try{
            howToPlay1 = ImageIO.read((getClass().getResourceAsStream("/tiles/guide1.png")));
            howToPlay2 = ImageIO.read((getClass().getResourceAsStream("/tiles/guide2.png")));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update
    public void update(){
        if (mouseHandle.enter) {
            if (image == howToPlay1) {
                image = howToPlay2;
            }
            mouseHandle.enter = false;

        }

        if (mouseHandle.exit) {
            if (image == howToPlay2) {
                image = howToPlay1;
            }
            mouseHandle.exit = false;
        }

        if (mouseHandle.click) {
            System.out.println("Hello");
            click = true;
            mouseHandle.click = false;
        }
    }

    // Draw
    public void draw(Graphics2D g2D){
        g2D.drawImage(image, x, y, 4* gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
