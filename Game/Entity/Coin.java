package Entity;

import Main.GameFrame;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;
public class Coin extends Entity {

    GamePanel gamePanel;
    Random rand = new Random();

    // Random location
    int randomX;
    int randomY;

    // Contructer
    public Coin (GamePanel gamePanel){
        this.gamePanel = gamePanel;

        // Random location
        randomX = rand.nextInt(gamePanel.screenWidth - gamePanel.tileSize);
        randomY = 4 * gamePanel.tileSize + rand.nextInt(gamePanel.screenHeight - 9 * gamePanel.tileSize + 1);

        setDefautValues();

        getCoinImage();
    }
    public Coin (int x, int y, GamePanel gamePanel){
        this.gamePanel = gamePanel;
        this.x = x;
        this.y = y;
        getCoinImage();
    }

    // Set location
    public void setDefautValues(){
        x = randomX;
        y = randomY;
    }

    // Get image
    public void getCoinImage(){
        try {
            coin1 = ImageIO.read((getClass().getResourceAsStream("/tiles/coin_1.png")));
            coin2 = ImageIO.read((getClass().getResourceAsStream("/tiles/coin_2.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Check if player earn the coin
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

    // Coin update
    public void update(){
        // Change the frame
        spriteCount++;
        if (spriteCount % 10 == 0) {
            spriteNum = spriteNum * -1;
            spriteCount=0;
        }
    }

    // Coin draw
    public void draw(Graphics2D g2D) {
        if (gamePanel.score < 100) {
            BufferedImage image = null;
            if (spriteNum == 1) {
                image = coin1;
            } else if (spriteNum == -1) {
                image = coin2;
            }
            g2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
