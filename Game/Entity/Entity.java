package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    // Possition and speed
    public int x,y;
    public int speed;

    // Image for each character
    public BufferedImage front1, front2, behide1, behide2, left1, left2, right1, right2,up,down,coin1,coin2,treasure,treasure1;
    // Image for button in game
    public BufferedImage pause1, pause2, play1, play2, restart1, restart2, home1, home2 ,return1, return2;
    //Image for button in menu
    public BufferedImage newGame1, newGame2, character1, character2, howToPlay1, howToPlay2;
    //Image for button in character menu
    public BufferedImage choose1, choose2, next1, next2, prev1, prev2;

    // Direction
    public String direction;

    // Count number to make character move
    public int spriteCount = 0;
    public int spriteNum = 1;

    // Collision
    public Rectangle solidArea;
    public boolean collisionOn =false;
}
