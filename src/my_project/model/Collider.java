package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.CollisionHandler;
import my_project.control.Renderer;

import java.awt.*;

public class Collider extends GraphicalObject {

    private Cage cage;
    private String direction;
    private double distance;
    private double thickness;
    private boolean collides;

    public Collider(Cage cage, String direction, double distance, double thickness) {
        this.cage = cage;
        this.direction = direction;
        this.distance = distance;
        this.thickness = thickness;
    }

    @Override
    public void draw(DrawTool drawTool) {
        if (collides) {
            drawTool.setCurrentColor(new Color(255, 0, 0));
        } else {
            drawTool.setCurrentColor(new Color(255, 255, 255));
        }
        drawTool.drawFilledRectangle(Renderer.translateAndScaleX(x),Renderer.translateAndScaleY(y),Renderer.scale(width),Renderer.scale(height));
    }

    @Override
    public void update(double dt) {

        //DO NOT USE DT//
        //-------------//
        //System.out.println("Collider position: "+x+"|"+y);
        updatePosition();
        collides = false;
    }
    public Cage getCage() {
        return cage;
    }
    public void setCollides(boolean collides) {
        this.collides = collides;
    }
    public boolean collides() {
        return collides;
    }
    public void updatePosition() {
        if(direction.equals("up") ||direction.equals("down")) {
            width = cage.getWidth();
            height = thickness;
            x = cage.getX();
            if(direction.equals("up")) {
                y = cage.getY() - distance - thickness;
            } else { //down
                y = cage.getY() + distance + cage.getHeight();
            }
        }else{
            height = cage.getHeight();
            width = thickness;
            y = cage.getY();
            if(direction.equals("left")) {
                x = cage.getX() - distance - thickness;
            }else{ //right
                x = cage.getX() + distance + cage.getWidth();
            }
        }
    }
}
