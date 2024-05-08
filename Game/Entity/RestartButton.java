package Entity;

import Main.GamePanel;
import Main.MouseHandle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RestartButton extends Entity{
    GamePanel gamePanel;
    MouseHandle mouseHandle;
    BufferedImage image;
    public boolean restart = false;
    int count = 0;

    public RestartButton(GamePanel gamePanel, MouseHandle mouseHandle){
        this.gamePanel = gamePanel;
        this.mouseHandle = mouseHandle;
        setDefautValues();
        getPauseButtonImage();
        image = restart1;
    }

    public void setDefautValues(){
        x = 2* gamePanel.tileSize;
        y = 0;
    }

    public void getPauseButtonImage(){
        try{
            restart1 = ImageIO.read((getClass().getResourceAsStream("/tiles/restart_button1.png")));
            restart2 = ImageIO.read((getClass().getResourceAsStream("/tiles/restart_button2.png")));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        if (mouseHandle.enter) {
            if (image == restart1) {
                image = restart2;
            }
            mouseHandle.enter = false;
        }

        if (mouseHandle.exit) {
            if (image == restart2) {
                image = restart1;
            }
            mouseHandle.exit = false;
        }

        if (mouseHandle.click) {
            restart = true;
            mouseHandle.click = false;
        }
        count++;
        if (count == 2){
            restart = false;
            count = 0;
        }
    }

    public void draw(Graphics2D g2D){
        g2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
