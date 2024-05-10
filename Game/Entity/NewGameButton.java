package Entity;

import Main.GamePanel;
import Main.MouseHandle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NewGameButton extends Entity {
    GamePanel gamePanel;
    MouseHandle mouseHandle;
    BufferedImage image;
    public boolean title = true;

    // Contructor
    public NewGameButton(GamePanel gamePanel, MouseHandle mouseHandle){
        this.gamePanel = gamePanel;
        this.mouseHandle = mouseHandle;
        setDefautValues();
        getNewGameButtonImage();
        image = newGame1;
    }

    // Set possition
    public void setDefautValues(){
        x = 6 * gamePanel.tileSize;
        y = 5 * gamePanel.tileSize;
    }

    // Get image
    public void getNewGameButtonImage(){
        try{
            newGame1 = ImageIO.read((getClass().getResourceAsStream("/tiles/new_game1.png")));
            newGame2 = ImageIO.read((getClass().getResourceAsStream("/tiles/new_game2.png")));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update
    public void update(){
        if (mouseHandle.enter) {
            if (image == newGame1) {
                image = newGame2;
            }
            mouseHandle.enter = false;

        }

        if (mouseHandle.exit) {
            if (image == newGame2) {
                image = newGame1;
            }
            mouseHandle.exit = false;
        }

        if (mouseHandle.click) {
            System.out.println("Hello");
            if (image == newGame2) {
                image = pause2;
            }
            title = false;
            mouseHandle.click = false;
        }
    }

    // Draw
    public void draw(Graphics2D g2D){
        g2D.drawImage(image, x, y, 4* gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
