import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        ImageIcon background =new ImageIcon("background.jpg");

        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setLayout(null);

        JLabel backGround=new JLabel();
        frame.add(backGround);

        frame.pack();
        frame.setVisible(true);

        backGround.setBounds(0,0,frame.getWidth(),frame.getHeight());
        int newWidth = frame.getWidth();
        int newHeight =frame.getHeight();
        ImageIcon newBackground = new ImageIcon(background.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
        backGround.setIcon(newBackground);
        frame.revalidate();
        frame.repaint();
    }
}