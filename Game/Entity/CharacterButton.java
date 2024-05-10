package Entity;

import Main.GamePanel;
import Main.MouseHandle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CharacterButton extends Entity{
    GamePanel gamePanel;
    public MouseHandle mouseHandle;
    BufferedImage image;
    public boolean click = false;

    // Constructor
    public CharacterButton(GamePanel gamePanel, MouseHandle mouseHandle){
        this.gamePanel = gamePanel;
        this.mouseHandle = mouseHandle;
        setDefautValues();
        getNewGameButtonImage();
        image = character1;
    }

    // Set posssition
    public void setDefautValues(){
        x = 6 * gamePanel.tileSize;
        y = 7 * gamePanel.tileSize;
    }

    // Get image
    public void getNewGameButtonImage(){
        try{
            character1 = ImageIO.read((getClass().getResourceAsStream("/tiles/character1.png")));
            character2 = ImageIO.read((getClass().getResourceAsStream("/tiles/character2.png")));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update
    public void update(){
        if (mouseHandle.enter) {
            if (image == character1) {
                image = character2;
            }
            mouseHandle.enter = false;

        }

        if (mouseHandle.exit) {
            if (image == character2) {
                image = character1;
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
