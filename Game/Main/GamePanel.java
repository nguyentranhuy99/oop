package Main;

import Entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Sceen setting
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public int tileSize = originalTileSize*scale;
    final int maxScreenCol = 16;
    final int getMaxScreenRow = 12;
    public int screenWidth = tileSize*maxScreenCol;
    public int screenHeight= tileSize*getMaxScreenRow;

    //FPS
    final int fps =60;

    // Set player default possition
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    Thread gameThread;

    KeyHandle keyHandle = new KeyHandle();

    Player player = new Player(this,keyHandle);
    public int count =0;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.CYAN);
        this.setDoubleBuffered(true); // This make game smoother
        this.addKeyListener(keyHandle);
        this.setFocusable(true); // This make GamePanel focus to receive key input
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null){
            if (keyHandle.check()==true){
                // 1 UPDATE : update information such as character positions
                update();

                // 2 DRAW : draw the screen with the information
                repaint(); // Call the paintComponent

                // Sleep
            }
            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        player.update();
        count++;
    }
    public void paintComponent(Graphics g){ // One of the standard methods to draw things on JPanel
        super.paintComponent(g);
        Graphics2D g2D= (Graphics2D) g;
        player.draw(g2D);
        g2D.dispose();
    }
}

