package Main;

import javax.swing.*;

public class GameFrame extends JFrame {
    GamePanel gamePanel;

    // Constructor
    GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("One Hundered Ancient Mystery Coins");

        gamePanel =new GamePanel(this);

        this.add(gamePanel);
        this.pack(); //This make the frame get the equal size with the panel
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        gamePanel.startGameThread();
    }

    // Game restart
    public void restartGame() {
        this.remove(gamePanel);
        gamePanel = new GamePanel(this);
        this.add(gamePanel);
        gamePanel.startGameThread();
        this.revalidate(); // This will refresh the JFrame
        this.repaint(); // This will repaint the JFrame
        gamePanel.requestFocusInWindow();
    }
}
