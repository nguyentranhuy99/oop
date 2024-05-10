package Main;

import Entity.Entity;
import Entity.PauseButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

public class MouseHandle implements MouseListener, MouseMotionListener {
    public boolean click,enter,exit;
    Rectangle2D button;

    // Constructor
    public MouseHandle(Rectangle2D button){
        this.button = button;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if( button.contains(e.getPoint())){
            click = true;
            //System.out.println("Clicked");
        }
        else{
            click = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    // Mouse enter and exit
    @Override
    public void mouseMoved(MouseEvent e) {
        if( button.contains(e.getPoint())){
            enter = true;
            exit  = false;
            //System.out.println("Enter");
        }
        else{
            enter = false;
            exit = true;
        }
    }
}
