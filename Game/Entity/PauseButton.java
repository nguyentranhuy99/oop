package Entity;

import Main.GamePanel;
import Main.MouseHandle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PauseButton extends Entity {
    GamePanel gamePanel;
    MouseHandle mouseHandle;
    BufferedImage image;
    public boolean pause = true;

    // Construtor
    public PauseButton(GamePanel gamePanel, MouseHandle mouseHandle){
        this.gamePanel = gamePanel;
        this.mouseHandle = mouseHandle;
        setDefautValues();
        getPauseButtonImage();
        image = pause1;
    }

    // Set possition
    public void setDefautValues(){
        x = 0;
        y = 0;
    }

    // Get image
    public void getPauseButtonImage(){
        try{
            pause1 = ImageIO.read((getClass().getResourceAsStream("/tiles/pause_button1.png")));
            pause2 = ImageIO.read((getClass().getResourceAsStream("/tiles/pause_button2.png")));
            play1 = ImageIO.read((getClass().getResourceAsStream("/tiles/play_button1.png")));
            play2 = ImageIO.read((getClass().getResourceAsStream("/tiles/play_button2.png")));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update
    public void update() {
        if (mouseHandle.enter) {
            if (image == play1) {
                image = play2;
            } else if (image == pause1) {
                image = pause2;
            }
            mouseHandle.enter = false;
        }

        if (mouseHandle.exit) {
            if (image == play2) {
                image = play1;
            } else if (image == pause2) {
                image = pause1;
            }
            mouseHandle.exit = false;
        }

        if (mouseHandle.click) {
            System.out.println("Hello");
            if (pause == false) {
                pause = true;
                image = pause2;
            } else {
                pause = false;
                image = play2;
            }
            mouseHandle.click = false;
        }
    }

    // Draw
    public void draw(Graphics2D g2D){
        g2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
