package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class Inventory extends GraphicalObject {

    public int size;
    public Item[] items;

    public Inventory(int size) {
        this.size = size;
        items = new Item[size];
    }

    @Override
    public void draw(DrawTool drawTool) {
        for (int i = 0; i < size; i++) {
            drawTool.setCurrentColor(new Color(131, 131, 131, 100));
            drawTool.drawFilledRectangle(20+ 55*i,20,50,50);
        }
    }

    public void addItem(int slot){

    }

    public void removeItem(int slot){

    }

    public Item getItem(int slot){
        return null;
    }
}
