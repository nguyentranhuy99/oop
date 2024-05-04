package Entity;
import Main.GamePanel;
import Main.KeyHandle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandle keyHandle;

    public Player(GamePanel gamePanel, KeyHandle keyHandle){
        this.gamePanel=gamePanel;
        this.keyHandle=keyHandle;
        setDefautValues();
        getPlayerImage();
    }

    public void setDefautValues(){
        // Set player default possition
        x = 0;
        y = 0;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try {
            front1 = ImageIO.read((getClass().getResourceAsStream("/player/front1.png")));
            front2 = ImageIO.read((getClass().getResourceAsStream("/player/front2.png")));
            behide1 = ImageIO.read((getClass().getResourceAsStream("/player/behide1.png")));
            behide2 = ImageIO.read((getClass().getResourceAsStream("/player/behide2.png")));
            left1 = ImageIO.read((getClass().getResourceAsStream("/player/left1.png")));
            left2 = ImageIO.read((getClass().getResourceAsStream("/player/left2.png")));
            right1 = ImageIO.read((getClass().getResourceAsStream("/player/right1.png")));
            right2 = ImageIO.read((getClass().getResourceAsStream("/player/right2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        if (keyHandle.up == true && y - speed >=0){
            direction = "up";
            y = y - speed;
        }
        if (keyHandle.down == true && y + speed <= gamePanel.screenHeight - gamePanel.tileSize){
            direction = "down";
            y = y + speed;
        }
        if (keyHandle.left == true && x - speed >= 0){
            direction = "left";
            x = x - speed;
        }
        if (keyHandle.right == true && x + speed <= gamePanel.screenWidth - gamePanel.tileSize){
            direction = "right";
            x = x + speed;
        }
        spriteCount++;
        if (spriteCount % 10 == 0){
            spriteNum = spriteNum * -1;
        }
    }
    public void draw(Graphics2D g2D){
        BufferedImage image =null;
        switch (direction){
            case "up":
                if(spriteNum == 1) {
                    image = behide1;
                }
                else if(spriteNum == -1) {
                    image = behide2;
                }
                break;

            case "down":
                if(spriteNum == 1) {
                    image = front1;
                }
                else if (spriteNum == -1){
                    image = front2;
                }
                break;

            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                else if (spriteNum == -1){
                    image = left2;
                }
                break;

            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                else if(spriteNum == -1){
                    image = right2;
                }
                break;
        }
        g2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
