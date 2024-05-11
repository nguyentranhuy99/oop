package Main;

import Entity.Coin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
    GamePanel gamePanel;
    // Set font
    Font arial_40;
    Font arial_80B;

    // Images
    BufferedImage image;
    BufferedImage image2;
    BufferedImage image3, image4;
    BufferedImage vietnam;

    // Character name
    String name[] = {"police","player","student","alien","blueboy"};
    String name2[] = {"Bing","Code","Huy","Gogool","Blue Boy (RyiSnow)"};

    // Messange notification
    public boolean messangeOn = false;
    public String messange = "";
    int messangeCounter = 0;
    int spriteCount = 0;

    // Time
    long time;
    long hours;
    long minute;
    long second;


    // Constructor
    UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);
        UIGetImage();
    }

    // Get image
    public void UIGetImage() {
        try {
            image = ImageIO.read((getClass().getResourceAsStream("/tiles/coin_1.png")));
            image2 = ImageIO.read((getClass().getResourceAsStream("/tiles/clock.png")));
            image3 = ImageIO.read((getClass().getResourceAsStream("/player/" + name[0] + "_down1.png")));
            image4 = ImageIO.read((getClass().getResourceAsStream("/player/" + name[0] + "_down2.png")));
            vietnam = ImageIO.read((getClass().getResourceAsStream("/tiles/vietnam.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessange(String text){
        messange = text;
        messangeOn = true;
    }

    // UI update
    public void update(){
        try {
            image3 = ImageIO.read((getClass().getResourceAsStream("/player/" + name[gamePanel.character_number] + "_down1.png")));
            image4 = ImageIO.read((getClass().getResourceAsStream("/player/" + name[gamePanel.character_number] + "_down2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Draw UI
    public void draw(Graphics2D g2D){
        g2D.setFont(arial_40);
        // Game UI
        if (gamePanel.tile_state == false) {
            // Coin bar text and image
            g2D.drawImage(image, (5 * gamePanel.tileSize) / 2, gamePanel.screenHeight - gamePanel.tileSize, gamePanel.tileSize, gamePanel.tileSize, null);
            if (gamePanel.win == false) {
                g2D.drawImage(image, (26 * gamePanel.tileSize) / 2, gamePanel.screenHeight - gamePanel.tileSize, gamePanel.tileSize, gamePanel.tileSize, null);
            }
            g2D.setColor(Color.WHITE);
            g2D.drawString("Coin earned: " + gamePanel.score + " / 100", (9 * gamePanel.tileSize) / 2, gamePanel.screenHeight - 10);

            // Time bar image
            g2D.drawImage(image2, (11 * gamePanel.tileSize), 0, gamePanel.tileSize, gamePanel.tileSize, null);
        }
        // Menu UI
        else{
            // Menu
            if (gamePanel.guide_state == false && gamePanel.character_state == false) {
                g2D.setFont(arial_80B);
                g2D.setFont(g2D.getFont().deriveFont(50F));
                g2D.setColor(Color.GRAY);
                g2D.drawString("100 Mythical Ancient Coins", ((5 * gamePanel.tileSize) / 4) + 3, 4 * gamePanel.tileSize + 3);
                g2D.setColor(Color.ORANGE);
                g2D.drawString("100 Mythical Ancient Coins", (5 * gamePanel.tileSize) / 4, 4 * gamePanel.tileSize);
                g2D.setFont(g2D.getFont().deriveFont(20F));
                g2D.setColor(Color.WHITE);
//                g2D.drawString("Created by Nguyễn Trần Huy",0,gamePanel.tileSize - 15);
//                g2D.drawImage(vietnam, 6 * gamePanel.tileSize,  6,(3 * (gamePanel.tileSize -10))/2 ,gamePanel.tileSize - 10,null);
            }

            // How to play
            else if (gamePanel.guide_state == true && gamePanel.character_state == false){
                // Table
                g2D.setColor(new Color(0,0,0,80));
                g2D.fillRoundRect(0, 3 * gamePanel.tileSize, gamePanel.screenWidth , 8 * gamePanel.tileSize, 35,35);
                g2D.setColor(Color.WHITE);
                g2D.setStroke(new BasicStroke(3));
                g2D.drawRoundRect(3,3 * gamePanel.tileSize + 3 , gamePanel.screenWidth - 6,8 * gamePanel.tileSize - 6,27,27);

                // Text
                g2D.setFont(arial_40);
                g2D.setColor(Color.WHITE);
                g2D.setFont(g2D.getFont().deriveFont(20F));
                String text = "Hello, this is the game instructions:\n" +
                        "You can move your character up, down, right, left to collect coins and avoid the \nmonster's arrow by pressing the W, A ,S ,D keys in that order.\n" +
                        "There is a target rectangle in your character’s center. If the arrowhead touches this \narea, the game will be over. However you can go through the rest of the arrow.\n" +
                        "You can complete the game by collect 100 coins. The coin bar in the bottom will \nshow your progress. If your score below -100, the game will be over too!!!\n" +
                        "Noctice: The notification will appear on the top center when a event occur!\n" +
                        "The time bar in the top right corner of the screen will gradually decrease if the \nhorizontal position of your character does not change. If it is empty one of your coin \nwill disappear. \nWhy? \nBecause they are mythical ancient coins…\nHave a nice time playing! " +
                        "\n" +
                        "\n";
                int y =4 * gamePanel.tileSize - 20;
                // Separate string
                for (String line : text.split("\n")){
                    g2D.drawString(line, 10,y);
                    y = y + 26;
                }
            }

            // Character state
            else if (gamePanel.character_state == true && gamePanel.guide_state == false) {
                // Image of character
                spriteCount ++;
                if (spriteCount <= 10) {
                    g2D.drawImage(image3, 6 * gamePanel.tileSize, 4 * gamePanel.tileSize, 4 * gamePanel.tileSize, 4 * gamePanel.tileSize, null);
                }
                else if (spriteCount > 10 && spriteCount <= 20){
                    g2D.drawImage(image4, 6 * gamePanel.tileSize, 4 * gamePanel.tileSize, 4 * gamePanel.tileSize, 4 * gamePanel.tileSize, null);
                    if (spriteCount == 20) {
                        spriteCount = 0;
                    }
                }

                //Character name
                g2D.setFont(g2D.getFont().deriveFont(30F));
                // Place name in the center
                FontMetrics metrics = g2D.getFontMetrics();
                int x = (gamePanel.screenWidth - metrics.stringWidth(name2[gamePanel.character_number])) / 2;
                g2D.drawString(name2[gamePanel.character_number], x, 9 * gamePanel.tileSize -13);
            }

        }

        // Game over
        g2D.setFont(g2D.getFont().deriveFont(30F));
        if(gamePanel.gameOver) {
            g2D.drawString("Game Over!", 6 * gamePanel.tileSize, 35);
            messangeOn = false;
        }

        // Messange
        if(messangeOn == true){
            g2D.setFont(g2D.getFont().deriveFont(20F));
            g2D.drawString(messange,6*gamePanel.tileSize,32);
            messangeCounter++;
            if(messangeCounter == 30){
                messangeOn = false;
                messangeCounter = 0;
            }
        }

        // Win state
        if (gamePanel.win == true){
            time = gamePanel.endTime - gamePanel.startTime;
            hours = time /3600;
            minute = (time - hours * 3600)/60;
            second = time - hours * 3600 - minute * 60;

            // Cogratulation
            if (messangeCounter <= 1500) {
                g2D.setFont(g2D.getFont().deriveFont(20F));
                if (messangeCounter <= 300){
                    g2D.drawString("You earned infinity coin!",6 * gamePanel.tileSize,32);
                }
                g2D.setFont(g2D.getFont().deriveFont(30F));
                g2D.drawString("You complete the game", 5 * gamePanel.tileSize, 10 * gamePanel.tileSize);
                g2D.setFont(arial_80B);
                g2D.setColor(Color.GRAY);
                g2D.drawString("Congratulation!", 2 * gamePanel.tileSize + 3, 5 * gamePanel.tileSize + 3);
                g2D.setColor(Color.YELLOW);
                g2D.drawString("Congratulation!", 2 * gamePanel.tileSize, 5 * gamePanel.tileSize);
                messangeCounter++;
            }

            // Summary
            if (messangeCounter <= 3000 && messangeCounter >=1500){
                // Table
                g2D.setColor(new Color(0,0,0,80));
                g2D.fillRoundRect(gamePanel.tileSize,3 * gamePanel.tileSize, (19 *gamePanel.tileSize)/2, (7 * gamePanel.tileSize)/2,35,35);
                g2D.setColor(Color.WHITE);
                g2D.setStroke(new BasicStroke(2));
                g2D.drawRoundRect(gamePanel.tileSize +2,3 * gamePanel.tileSize +2 , ((19 * gamePanel.tileSize)/2) - 4,((7 * gamePanel.tileSize)/2) - 4,27,27);

                // Text and image
                g2D.setFont(arial_40);
                g2D.setColor(Color.YELLOW);
                g2D.setFont(g2D.getFont().deriveFont(30F));
                g2D.drawString("Total coins earned: " + (100 + gamePanel.penaltyScore), 2 * gamePanel.tileSize, 4 * gamePanel.tileSize);
                g2D.drawImage(image, ((17 * gamePanel.tileSize)/2) + 5, 3 * gamePanel.tileSize + 10, gamePanel.tileSize, gamePanel.tileSize, null);
                g2D.drawString("Total penalty coins: " + (gamePanel.penaltyScore), 2 * gamePanel.tileSize, 5 * gamePanel.tileSize);
                g2D.drawImage(image, ((16 * gamePanel.tileSize)/2), 4 * gamePanel.tileSize + 10, gamePanel.tileSize, gamePanel.tileSize, null);
                g2D.drawString("Play time: " + hours + " h " + minute + " m " +second+ " s ", 2 * gamePanel.tileSize, 6 * gamePanel.tileSize);
                g2D.drawImage(image2, ((16 * gamePanel.tileSize)/2) + 21 , 5 * gamePanel.tileSize + 10, gamePanel.tileSize, gamePanel.tileSize, null);
                messangeCounter++;
            }

            // Thank you
            if (messangeCounter <= 4000 && messangeCounter >=3000) {
                g2D.setFont(arial_40);
                g2D.setColor(Color.CYAN);
                g2D.setFont(g2D.getFont().deriveFont(30F));
                g2D.drawString("Thank you for playing my game", 2 * gamePanel.tileSize, 4 * gamePanel.tileSize);
                g2D.drawString("I hope you had an exciting experience.", 2 * gamePanel.tileSize, 5 * gamePanel.tileSize);
                g2D.drawString("Congratulation, again...", 2 * gamePanel.tileSize, 6 * gamePanel.tileSize);
                g2D.drawString("Have a nice day!", 2 * gamePanel.tileSize, 7 * gamePanel.tileSize);
                messangeCounter++;
            }
        }
    }
}
