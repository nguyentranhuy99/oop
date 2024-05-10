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
    String name;

    // Constructer
    public Player(GamePanel gamePanel, KeyHandle keyHandle, String name){
        this.gamePanel = gamePanel;
        this.keyHandle = keyHandle;
        this.name = name;

        setDefautValues();

        // Collision
        solidArea = new Rectangle(x+gamePanel.tileSize/6,y+gamePanel.tileSize/3,(gamePanel.tileSize)/2,(gamePanel.tileSize)/2);

        getPlayerImage();
    }

    // Set possision and speed
    public void setDefautValues(){
        // Set player default possition
        x = 0;
        y = 320;
        speed = 3;
        direction = "down";
    }

    // Get image
    public void getPlayerImage(){
        try {
            front1 = ImageIO.read((getClass().getResourceAsStream("/player/" + name + "_down1.png")));
            front2 = ImageIO.read((getClass().getResourceAsStream("/player/"+ name + "_down2.png")));
            behide1 = ImageIO.read((getClass().getResourceAsStream("/player/" + name + "_up1.png")));
            behide2 = ImageIO.read((getClass().getResourceAsStream("/player/" + name + "_up2.png")));
            left1 = ImageIO.read((getClass().getResourceAsStream("/player/" + name + "_left1.png")));
            left2 = ImageIO.read((getClass().getResourceAsStream("/player/" + name + "_left2.png")));
            right1 = ImageIO.read((getClass().getResourceAsStream("/player/" + name + "_right1.png")));
            right2 = ImageIO.read((getClass().getResourceAsStream("/player/" + name + "_right2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Player update
    public void update(){

        // Update the location by the key pressed
        if(keyHandle.check()) {
            if (keyHandle.up == true && y - speed >= 3 * gamePanel.tileSize) {
                direction = "up";
                y = y - speed;
            }
            if (keyHandle.down == true && y + speed <= gamePanel.screenHeight - 4 * gamePanel.tileSize) {
                direction = "down";
                y = y + speed;
            }
            if (keyHandle.left == true && x - speed >= 0) {
                direction = "left";
                x = x - speed;
            }
            if (keyHandle.right == true && x + speed <= gamePanel.screenWidth - gamePanel.tileSize) {
                direction = "right";
                x = x + speed;
            }

            // Up date the solidArea
            solidArea = new Rectangle(x+(gamePanel.tileSize/3),y+(gamePanel.tileSize/3),(gamePanel.tileSize)/3,(gamePanel.tileSize)/3);

            // Update the frame
            spriteCount++;
            if (spriteCount % 15 == 0) {
                spriteNum = spriteNum * -1;
                spriteCount = 0;
            }
        }
    }

    // Player draw
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
