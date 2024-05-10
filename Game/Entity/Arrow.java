package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;

public class Arrow extends Entity{
    GamePanel gamePanel;
    Random rand = new Random();
    int random=rand.nextInt(5);
    int random1;

    // Nose of the arrow
    int xCheck;
    int yCheck;

    // Ramdon location around the player possition
    public int aroundPossition(){
        if (random <= 2){
            return random;
        }
        else if(random==3){
            return -1;
        }
        else{
            return -2;
        }
    }

    // Check if arrow ouside the frame
    boolean isDead =false;
    public boolean dead(){
        return isDead;
    }

    // Constructer
    public Arrow(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        // random1 to keep the even or odd of random + possiton
        random1 = random + gamePanel.player.x/gamePanel.tileSize;

        setDefautValues();

        // Speed difficult
        speed = speed + gamePanel.speed;

        getArrowImage();
    }

    // Set location and speed of arrow
    public void setDefautValues(){
        // Set X possition
        x = gamePanel.tileSize*((gamePanel.player.x/gamePanel.tileSize)+aroundPossition());
        xCheck = x + (gamePanel.tileSize/2);

        // Set Y possition
        // Above
        if (random1%2==0){
            y = 2 * gamePanel.tileSize;
            yCheck = y + gamePanel.tileSize - 2;
        }
        // Under
        else{
            y = gamePanel.screenHeight - 3 * gamePanel.tileSize;
            yCheck = y;
        }

        speed = 4;

    }

    // Get image
    public void getArrowImage(){

        try {
            up = ImageIO.read((getClass().getResourceAsStream("/tiles/arrow1.png")));
            down = ImageIO.read((getClass().getResourceAsStream("/tiles/arrow2.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Check if arrow hit player
    public boolean check(){
        Point point = new Point(xCheck,yCheck);
        if(gamePanel.player.solidArea.contains(point)){
            return true;
        }
        return false;
    }

    // Arrow update
    public void update(){

        // Above
        if(random1 % 2==0){
            y = y + speed;
            yCheck = y + gamePanel.tileSize-2;
            if(y > gamePanel.screenHeight + gamePanel.tileSize){
                isDead = true;
            }
        }
        // Under
        else{
            y = y - speed;
            yCheck = y;
            if(x < 2 * gamePanel.tileSize) {
                if (y < gamePanel.tileSize) {
                    isDead = true;
                }
            }
            else{
                if (y < 0) {
                    isDead = true;
                }
            }
        }
    }

    // Arrow draw
    public void draw(Graphics2D g2D){
        BufferedImage image;
        // Above
        if(random1 % 2 == 0){
            image = down;
        }

        // Under
        else {
            image = up;
        }
        g2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
