package Entity;

import Main.GamePanel;
import Main.MouseHandle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ChooseButton extends Entity{
    GamePanel gamePanel;
    MouseHandle mouseHandle;
    BufferedImage image;
    public boolean click = false;

    // Constructor
    public ChooseButton(GamePanel gamePanel, MouseHandle mouseHandle){
        this.gamePanel = gamePanel;
        this.mouseHandle = mouseHandle;
        setDefautValues();
        getNewGameButtonImage();
        image = choose1;
    }

    // Set possition
    public void setDefautValues(){
        x = 6 * gamePanel.tileSize;
        y = 9 * gamePanel.tileSize;
    }

    // Get image
    public void getNewGameButtonImage(){
        try{
            choose1 = ImageIO.read((getClass().getResourceAsStream("/tiles/choose1.png")));
            choose2 = ImageIO.read((getClass().getResourceAsStream("/tiles/choose2.png")));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update
    public void update(){
        if (mouseHandle.enter) {
            if (image == choose1) {
                image = choose2;
            }
            mouseHandle.enter = false;

        }

        if (mouseHandle.exit) {
            if (image == choose2) {
                image = choose1;
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
