package Main;

import Entity.*;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    GameFrame window;

    // Sceen setting
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public int tileSize = originalTileSize*scale;
    final int maxScreenCol = 16;
    final int getMaxScreenRow = 14;
    public int screenWidth = tileSize*maxScreenCol;
    public int screenHeight= tileSize*getMaxScreenRow;

    // Game state
    public boolean gameOver = false;
    public boolean gamePaused = true;
    public boolean gameRestart = false;

    // Hard
    int hard = 20;
    int difficult = 125;
    public int speed = 0;

    // Score
    public int score = 0;

    // FPS
    final int fps = 60;

    // Thred
    public Thread gameThread;

    // Win
    public boolean win = false;

    // Rectangle
    Rectangle pauseArea1 = new Rectangle(0,0,tileSize,tileSize);
    Rectangle2D pauseArea = (Rectangle2D) pauseArea1;

    Rectangle restartArea1 = new Rectangle(2 * tileSize,0 ,tileSize,tileSize);
    Rectangle2D restartArea = (Rectangle2D) restartArea1;

    //KeyListener
    public KeyHandle keyHandle = new KeyHandle();

    // MouseListener
    public MouseHandle mouseHandle1 = new MouseHandle(pauseArea);
    public MouseHandle mouseHandle2 = new MouseHandle(restartArea);

    // Sound
    Sound sound = new Sound();

    // Timer
    int timer = 0;

    // Tiles
    public Player player = new Player(this,keyHandle);
    Queue<Arrow>arrows;
    Coin coin = new Coin(this);
    Treasure treasure = new Treasure(this);

    //public CollisionChecker collisionChecker = new CollisionChecker(this);

    //Buttons
    PauseButton pauseButton;
    RestartButton restartButton;

    //UI
    UI ui = new UI(this);

    // Random
    Random rand = new Random();

    // Background
    TileManager tileManager = new TileManager(this);

    // Constructor
    public GamePanel(GameFrame window){
        this.window = window;
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.GRAY);
        this.setLayout(null);
        this.setDoubleBuffered(true); // This make game smoother
        this.addKeyListener(keyHandle);
        this.addMouseListener(mouseHandle1);
        this.addMouseListener(mouseHandle2);
        pauseButton = new PauseButton(this,mouseHandle1);
        restartButton = new RestartButton(this,mouseHandle2);
        this.setFocusable(true); // This make GamePanel focus to receive key input
        this.arrows=new LinkedList<>();
    }

    // Game thread
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Running game
    @Override
    public void run() {
        playMusic(0);
        while (gameThread != null){
            // Restart game
            if (gameRestart == true){
                stopMusic();
                gameThread = null;
                window.restartGame();
            }
            if(gameOver == false) {
                if (gamePaused == false) {
                    // 1 UPDATE : update information such as character positions
                    update();

                    // 2 DRAW : draw the screen with the information
                    repaint(); // Call the paintComponent

                    // Sleep
                    try {
                        Thread.sleep(1000 / fps);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Pause button update
                    pauseButton.update();
                    gamePaused = pauseButton.pause;
                    if (gamePaused == true) {
                        ui.showMessange("Paused");
                    }
                    restartButton.update();
                    gameRestart = restartButton.restart;
                    repaint();
                    // Sleep
                    try {
                        Thread.sleep(1000 / fps);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                // Restart button update
                restartButton.update();
                gameRestart = restartButton.restart;
                repaint();
                // Sleep
                try {
                    Thread.sleep(1000 / fps);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Update
    public void update(){
        // Player update
        player.update();

        // Arrow update
        int random = rand.nextInt(hard);
        if(random == 1 && score <100) {
            Arrow arrow1 = new Arrow(this);
            arrows.add(arrow1);
        }
        Iterator<Arrow> iterator = arrows.iterator();
        while (iterator.hasNext()) {
            Arrow arrow = iterator.next();
            arrow.update();
            if (arrow.dead()) {
                iterator.remove();
            }
            if (arrow.check()){
                System.out.println("You Lose!");
                ui.showMessange("Game over!");
                gameOver = true;
                eventSound(4);
                stopMusic();
                //gameThread = null;
            }
        }

        // Coin update
        if(coin.check()){
            if(score <100) {
                eventSound(2);
            }
            ui.showMessange("You earned a coin!");
            if(score < 98) {
                score++;
                coin = new Coin(this);
            }
            // Win stange
            else{
                coin = new Coin(0,320,this);
                if(score<100) {
                    score++;
                }
                else{
                    Iterator<Arrow> iterator1 = arrows.iterator();
                    while (iterator.hasNext()) {
                        Arrow arrow = iterator1.next();
                        if(!arrow.dead()) {
                            iterator1.remove();
                        }
                    }
                }
            }

            if((score + 1) % 30 == 0){
                hard = hard*100/difficult;
                ui.showMessange("More arrows will be shot!!!");
            }
            if((score + 1) % 40 == 0){
                speed = speed + 2;
                ui.showMessange("The arrow will faster!!");
            }
            System.out.println(score);
        }
        coin.update();

        // Treasure update
        if(treasure.check()) {
            if(win == false && score == 100) {
                eventSound(5);
                stopMusic();
                playMusic(1);
                score = score + 999999899;
            }
            win = true;
        }

        // Timer update
        if(keyHandle.check1() == false ){
            if(score<100) {
                timer++;
            }
            if(timer == 40){
                score--;
                ui.showMessange("You loose a coin!");
                System.out.println(score);
                eventSound(3);
                timer = 0;
            }
        }
        else{
            timer = 0;
        }

        // Pause button update
        pauseButton.update();
        gamePaused = pauseButton.pause;
        if (gamePaused == true) {
            ui.showMessange("Paused");
        }

        // Restart button update
        restartButton.update();
        gameRestart = restartButton.restart;
    }

    // Draw
    public void paintComponent(Graphics g){ // One of the standard methods to draw things on JPanel
        super.paintComponent(g);
        Graphics2D g2D= (Graphics2D) g;

        // Draw background
        tileManager.draw(g2D);

        // Draw button
        pauseButton.draw(g2D);
        restartButton.draw(g2D);

        // Draw player
        player.draw(g2D);

        // Draw arrows
        for (Arrow arrow : arrows) {
            arrow.draw(g2D);
        }

        // Draw coin
        coin.draw(g2D);

        // Draw coin bar
        g2D.setColor(Color.RED);
        g2D.fillRect(0,screenHeight - tileSize,(score*screenWidth)/100,tileSize);

        // Draw time bar
        g2D.setColor(Color.CYAN);
        g2D.fillRect((11 * tileSize * (85 + timer))/85,0, 5 * tileSize,tileSize);
        g2D.setColor(Color.BLACK);
        g2D.setStroke(new BasicStroke(2));
        g2D.drawRect(11 * tileSize,0, 5 *tileSize, tileSize);

        // Draw treasure
        treasure.draw(g2D);

        // Draw UI
        ui.draw(g2D);

        g2D.dispose();
    }

    // Play sound
    public void playMusic(int i){
        sound.setFile(i);
        if(i>1) {
            sound.play();
            sound.loop();
        }
        else{
            sound.playBackground();
            sound.loopBackground();
        }
    }

    // Stop sound
    public void stopMusic(){
        sound.stopBackground();
        //sound.stop();
    }

    // Play sound when have event
    public void eventSound(int i){
        sound.setFile(i);
        if(i>1) {
            sound.play();
        }
        else{
            sound.playBackground();
        }
    }
}

