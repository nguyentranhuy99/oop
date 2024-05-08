package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Treasure extends Entity{
    GamePanel gamePanel;

    // Constructor
    public Treasure (GamePanel gamePanel){
        this.gamePanel = gamePanel;
        setDefautValues();
        getTreasureImage();
    }

    // Set possition
    public void setDefautValues(){
        x = gamePanel.screenWidth - gamePanel.tileSize;
        y = 320;
    }

    // Get image
    public void getTreasureImage(){
        try {
            treasure = ImageIO.read((getClass().getResourceAsStream("/tiles/treasure.png")));
            treasure1 = ImageIO.read((getClass().getResourceAsStream("/tiles/treasure_open.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Check if player hit the treasure
    public boolean check(){
        if(gamePanel.player.x <= x + gamePanel.tileSize/2 && gamePanel.player.x >= x - gamePanel.tileSize/2){
            if(gamePanel.player.y <= y + gamePanel.tileSize/2 && gamePanel.player.y >= y - gamePanel.tileSize/2) {
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    // Treasure draw
    public void draw(Graphics2D g2D) {
        if (gamePanel.score == 100) {
            BufferedImage image = null;
            if(gamePanel.win){
                image = treasure1;
            }
            else{
                image = treasure;
            }
            g2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
