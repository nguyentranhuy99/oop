package Tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;

    // Constructor
    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        getTileImage();
    }

    // Get Image
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/monster1.png"));
            tile[0].collision = false;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/arrow1.png"));
            tile[1].collision = false;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/arrow2.png"));
            tile[2].collision = false;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/monster2.png"));
            tile[3].collision = false;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ancient_brick.png"));
            tile[4].collision = false;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Draw
    public void draw(Graphics2D g2D){
        // Back ground
        for (int i = 0; i <= gamePanel.screenWidth; i = i + gamePanel.tileSize){
            for (int j = gamePanel.tileSize; j < gamePanel.screenHeight - gamePanel.tileSize; j = j + gamePanel.tileSize){
                g2D.drawImage(tile[4].image, i, j,gamePanel.tileSize,gamePanel.tileSize,null);
            }
        }

        // Above monster and arrow
        for (int i = 0; i <= gamePanel.screenWidth; i = i + 2 * gamePanel.tileSize) {
            g2D.drawImage(tile[0].image, i, gamePanel.tileSize, gamePanel.tileSize, gamePanel.tileSize, null);
            g2D.drawImage(tile[2].image, i, 2*gamePanel.tileSize,gamePanel.tileSize,gamePanel.tileSize,null);
        }

        // Under monster and arrow
        for (int i = gamePanel.tileSize; i<= gamePanel.screenWidth; i = i+2 * gamePanel.tileSize){
            g2D.drawImage(tile[3].image, i, gamePanel.screenHeight- 2 * gamePanel.tileSize, gamePanel.tileSize, gamePanel.tileSize, null);
            g2D.drawImage(tile[1].image, i, gamePanel.screenHeight- 3 * gamePanel.tileSize,gamePanel.tileSize,gamePanel.tileSize,null);
        }
    }
}
