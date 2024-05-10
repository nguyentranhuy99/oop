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
    public boolean gamePaused = false;
    public boolean gameRestart = false;

    // Tile state
    public boolean tile_state;
    public boolean guide_state = false;
    public boolean character_state = false;

    // Hard
    int hard = 20;
    int difficult = 125;
    public int speed = 0;

    // Score
    public int score = 0;
    public int penaltyScore = 0;

    // FPS
    final int fps = 60;

    // Thred
    public Thread gameThread;

    // Time
    long startTime = System.currentTimeMillis() /1000;
    long endTime;


    // Win
    public boolean win = false;

    // Character number
    public int character_number;

    // Rectangle
    Rectangle pauseArea1 = new Rectangle(0,0,tileSize,tileSize);
    Rectangle2D pauseArea = (Rectangle2D) pauseArea1;

    Rectangle restartArea1 = new Rectangle(2 * tileSize,0 ,tileSize,tileSize);
    Rectangle2D restartArea = (Rectangle2D) restartArea1;

    Rectangle newGameArea1 = new Rectangle(6 * tileSize,5 * tileSize ,4 * tileSize, tileSize);
    Rectangle2D newGameArea = (Rectangle2D) newGameArea1;

    Rectangle characterArea1 = new Rectangle(6 * tileSize,7 * tileSize ,4 * tileSize, tileSize);
    Rectangle2D characterArea = (Rectangle2D) characterArea1;

    Rectangle howToPlayArea1 = new Rectangle(6 * tileSize,9 * tileSize ,4 * tileSize, tileSize);
    Rectangle2D howToPlayArea = (Rectangle2D) howToPlayArea1;

    Rectangle homeArea1 = new Rectangle(4 * tileSize,0 ,tileSize,tileSize);
    Rectangle2D homeArea = (Rectangle2D) homeArea1;

    Rectangle nextArea1 = new Rectangle(11 * tileSize,6 * tileSize ,tileSize,tileSize);
    Rectangle2D nextArea = (Rectangle2D) nextArea1;

    Rectangle prevArea1 = new Rectangle(4 * tileSize,6 * tileSize ,tileSize,tileSize);
    Rectangle2D prevArea = (Rectangle2D) prevArea1;

    //KeyListener
    public KeyHandle keyHandle = new KeyHandle();

    // MouseListener
    public MouseHandle mouseHandle1 = new MouseHandle(pauseArea);
    public MouseHandle mouseHandle2 = new MouseHandle(restartArea);
    public MouseHandle mouseHandle3 = new MouseHandle(newGameArea);
    public MouseHandle mouseHandle4 = new MouseHandle(characterArea);
    public MouseHandle mouseHandle5 = new MouseHandle(howToPlayArea);
    public MouseHandle mouseHandle6 = new MouseHandle(homeArea);
    public MouseHandle mouseHandle7 = new MouseHandle(nextArea);
    public MouseHandle mouseHandle8 = new MouseHandle(prevArea);

    // Sound
    Sound sound = new Sound();

    // Timer
    int timer = 0;

    // Tiles
    public Player player;
    Queue<Arrow>arrows;
    Coin coin = new Coin(this);
    Treasure treasure = new Treasure(this);

    //Buttons
    PauseButton pauseButton;
    RestartButton restartButton;
    NewGameButton newGameButton;
    CharacterButton characterButton;
    HowToPlayButton howToPlayButton;
    HomeButton homeButton;
    ReturnButton returnButton;
    ChooseButton chooseButton;
    public NextButton nextButton;
    public PrevButton prevButton;

    //UI
    UI ui = new UI(this);

    // Random
    Random rand = new Random();

    // Background
    TileManager tileManager = new TileManager(this);

    // Constructor
    public GamePanel(GameFrame window, boolean tile_state ,int character_number){
        // To restart game
        this.window = window;

        // To keep character when restart
        this.character_number = character_number;
        player = new Player(this,keyHandle,ui.name[character_number]);

        // To hide the menu when restart
        this.tile_state = tile_state;

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.GRAY);
        this.setLayout(null);
        this.setDoubleBuffered(true); // This make game smoother

        // Add key listener
        this.addKeyListener(keyHandle);
        this.setFocusable(true); // This make GamePanel focus to receive key input

        // Add mouse listener
        this.addMouseListener(mouseHandle1);
        this.addMouseListener(mouseHandle2);
        this.addMouseListener(mouseHandle3);
        this.addMouseListener(mouseHandle4);
        this.addMouseListener(mouseHandle5);
        this.addMouseListener(mouseHandle6);
        this.addMouseListener(mouseHandle7);
        this.addMouseListener(mouseHandle8);
        this.addMouseMotionListener(mouseHandle1);
        this.addMouseMotionListener(mouseHandle2);
        this.addMouseMotionListener(mouseHandle3);
        this.addMouseMotionListener(mouseHandle4);
        this.addMouseMotionListener(mouseHandle5);
        this.addMouseMotionListener(mouseHandle6);
        this.addMouseMotionListener(mouseHandle7);
        this.addMouseMotionListener(mouseHandle8);

        // Set the button
        pauseButton = new PauseButton(this,mouseHandle1);
        restartButton = new RestartButton(this,mouseHandle2);
        newGameButton = new NewGameButton(this, mouseHandle3);
        characterButton = new CharacterButton(this,mouseHandle4);
        howToPlayButton = new HowToPlayButton(this,mouseHandle5);
        homeButton = new HomeButton(this,mouseHandle6);
        returnButton = new ReturnButton(this, mouseHandle1);
        chooseButton = new ChooseButton(this,mouseHandle5);
        nextButton = new NextButton(this, mouseHandle7);
        prevButton = new PrevButton(this, mouseHandle8);

        // Arrow
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
        //Background music
        playMusic(0);

        while (gameThread != null){
            // Restart game
            if (gameRestart == true){
                window.character_number = character_number;
                System.out.println(character_number);
                try {
                    stopMusic();
                }
                catch (NullPointerException e){
                    e.printStackTrace();
                }
                gameThread = null;
                window.restartGame(false);
            }

            // Game running
            if (tile_state == false) {
                // 1 UPDATE : update information such as character positions

                update();

                // Pause button update
                if(gameOver == false) {
                    pauseButton.update();
                    gamePaused = !pauseButton.pause;
                    if (gamePaused == true) {
                        ui.showMessange("Paused");
                    }
                }

                // Restart button update
                restartButton.update();
                gameRestart = restartButton.restart; // gameRestart will false after restart so don't need to set resterButton false

                // Home button update
                homeButton.update();
                if (homeButton.home) {
                    // Stop the game
                    try {
                        stopMusic();
                    }
                    catch (NullPointerException e){
                        e.printStackTrace();
                    }
                    gameThread = null;

                    // Create new game with menu
                    window.restartGame(true);
                }
            }

            // Update menu
            else{
                menuUpdate();
            }

            // 2 DRAW : draw the screen with the information
            repaint(); // Call the paintComponent

            // Sleep
            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // In-game update
    public void update(){
        if ( gameOver == false && gamePaused == false ) {
            // Player update
            player.update();

            // Arrow update
            int random = rand.nextInt(hard);
            if (random == 1 && score < 100) {
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
                // Check if arrow hit the player
                if (arrow.check()) {
                    ui.showMessange("Game over!");
                    // Game over
                    gameOver = true;
                    eventSound(4);
                    stopMusic();
                    eventSound(4);
                }
            }

            // Coin update
            if (coin.check()) {
                if (score < 100) {
                    eventSound(2);
                    ui.showMessange("You earned a coin!");

                    // Hard level
                    if ((score + 1) % 30 == 0) {
                        hard = hard * 100 / difficult;
                        ui.showMessange("More arrows will be shot!!!");
                    }
                    if ((score + 1) % 40 == 0) {
                        speed = speed + 2;
                        ui.showMessange("The arrow will faster!!");
                    }
                }

                // Random coin
                if (score < 98) {
                    score++;
                    coin = new Coin(this);
                }

                // Win stage
                else {
                    coin = new Coin(0, 320, this);
                    if (score < 100) {
                        score++;
                    }
                }
            }
            coin.update();

            // Treasure update
            if (treasure.check() && score == 100) {
                // Check if player hit the treasure
                if (win == false && score == 100) {
                    stopMusic();
                    eventSound(5);
                    playMusic(1);
                    score = score + 999999899;
                }
                win = true;
                // Count the time end
                endTime = System.currentTimeMillis()/1000;
            }

            // Timer update
            if (keyHandle.check1() == false) {
                if (score < 100) {
                    timer++;
                }
                if (timer == 40) {
                    score--;
                    // Game over when score < -100
                    if (score < -100){
                        gameOver = true;
                        eventSound(4);
                        stopMusic();
                        eventSound(4);
                    }
                    penaltyScore++;
                    ui.showMessange("You loose a coin!");
                    //System.out.println(score);
                    eventSound(3);
                    timer = 0;
                }
            } else {
                timer = 0;
            }
        }
    }

    // Menu update
    public void menuUpdate(){
        // Background
        if (guide_state == false && character_state == false) {
            newGameButton.update();
            tile_state = newGameButton.title;
        }

        // Update character button
        if (guide_state == false) {
            characterButton.update();
            character_state = characterButton.click;
            // Set click_state for chooseButton
            chooseButton.click = false;

            // Update click-state of characterButton
            if (character_state == true) {
                chooseButton.update();
                if (chooseButton.click == true) {
                    // Return to menu
                    characterButton.click = false;
                    // New player
                    player = new Player(this,keyHandle,ui.name[character_number]);
                }
                // Create restrict for character_number
                if (character_number < 4) {
                nextButton.update();
                    if (nextButton.click == true){
                        character_number++;
                    }
                    nextButton.click = false; //Set to false to exit click-state
                }
                prevButton.update();
                if( character_number > 0) {
                    if (prevButton.click == true) {
                        character_number--;
                    }
                }
                prevButton.click = false;

                // UI for character menu
                ui.update();
            }
        }

        // Update how to play button
        if (character_state == false) {
            howToPlayButton.update();
            guide_state = howToPlayButton.click;
            returnButton.click = false; // Set to false
            if (guide_state == true) {
                returnButton.update();
                if (returnButton.click == true){
                    // Exit how to play state
                    howToPlayButton.click =false;
                }
            }
        }

    }
    // Draw
    public void paintComponent(Graphics g){ // One of the standard methods to draw things on JPanel
        super.paintComponent(g);
        Graphics2D g2D= (Graphics2D) g;
        g2D.setColor(Color.WHITE);

        // In-game draw
        if (tile_state == false) {
            // Draw background
            tileManager.draw(g2D);

            // Draw button
            pauseButton.draw(g2D);
            restartButton.draw(g2D);
            homeButton.draw(g2D);

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
            g2D.fillRect(0, screenHeight - tileSize, (score * screenWidth) / 100, tileSize);

            // Draw time bar
            g2D.setColor(Color.CYAN);
            g2D.fillRect((11 * tileSize * (85 + timer)) / 85, 0, 5 * tileSize, tileSize);
            g2D.setColor(Color.BLACK);
            g2D.setStroke(new BasicStroke(2));
            g2D.drawRect(11 * tileSize, 0, 5 * tileSize, tileSize);

            // Draw treasure
            treasure.draw(g2D);
        }

        // Draw menu
        else{
            // Draw background
            tileManager.draw(g2D);

            // Draw buttons in menu
            if (guide_state == false && character_state == false){
                newGameButton.draw(g2D);
                characterButton.draw(g2D);
                howToPlayButton.draw(g2D);
            }

            // Draw return button in how to play state
            else if (guide_state == true && character_state == false){
                returnButton.draw(g2D);
            }

            // Draw buttons in character_state
            else if (character_state == true && guide_state == false){
                nextButton.draw(g2D);
                prevButton.draw(g2D);
                chooseButton.draw(g2D);
            }
        }

        // Draw UI
        ui.draw(g2D);

        g2D.dispose();
    }

    // Play background
    public void playMusic(int i){
        sound.setFile(i);
        sound.playBackground();
        sound.loopBackground();

    }

    // Stop sound
    public void stopMusic(){
        sound.stopBackground();
        sound.stop();
    }

    // Play sound when have event
    public void eventSound(int i){
        sound.setFile(i);
        sound.play();
    }
}

