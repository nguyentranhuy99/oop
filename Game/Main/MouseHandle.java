package Main;

import Entity.Entity;
import Entity.PauseButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

public class MouseHandle implements MouseListener {
    public boolean click,enter,exit;
    Rectangle2D button;

    // Constructor
    MouseHandle(Rectangle2D button){
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
        if( button.contains(e.getPoint())){
            enter = true;
            //System.out.println("Enter");
        }
        else{
            enter = false;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if( button.contains(e.getPoint()) == false) {
            exit = true;
            //System.out.println("Exit");
        }
        else{
            exit = false;
        }
    }

    public boolean check(){
        return enter || click || exit ;
    }
}
