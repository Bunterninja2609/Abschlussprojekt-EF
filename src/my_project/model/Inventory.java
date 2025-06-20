package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.model.blocks.*;
import my_project.model.items.Item;

import java.awt.*;
import java.util.Objects;

public class Inventory extends GraphicalObject {
    private double highlitedSlot;
    private int size;
    private Item[] items;

    public Inventory(int size) {
        this.size = size;
        items = new Item[size];
        highlitedSlot = 0;
    }

    @Override
    public void draw(DrawTool drawTool) {
        for (int i = 0; i < size; i++) {
            if (i != highlitedSlot) {
                drawTool.setCurrentColor(new Color(131, 131, 131, 100));
            }else{
                drawTool.setCurrentColor(new Color(255, 255, 255, 100));
            }

            drawTool.drawFilledRectangle(20 + 55*i,20,50,50);
            if (items[i] != null) {
                drawTool.setCurrentColor(0,0,0,255);
                items[i].getTexture().drawToWidth(drawTool,30 + 55*i, 30, 30);
                //drawTool.drawFilledRectangle(30 + 55*i, 30, 30, 30);
                drawTool.setCurrentColor(new Color(219, 226, 230));
                drawTool.drawText(32 + 55*i, 56, String.valueOf(items[i].getAmount()));
            }
        }
    }

    public void addItem(Block block) {
        String name = "";
        boolean added = false;
        if (block instanceof Stone) {
            name = "Stone";
        }else if (block instanceof Dirt || block instanceof Grass) {
            name = "Dirt";
        }
        for (Item item : items) {
            if (item == null) {
                continue;
            }
            if (name.equals(item.getName())) {
                item.changeAmount(1);
                added = true;
            }
        }
        if (!added) {
            for (int i = 0; i < items.length; i++) {
                if (items[i] == null) {
                    if(name.equals("Stone")) {
                        items[i] = new my_project.model.items.Stone();
                    }else if (name.equals("Dirt")) {
                        items[i] = new my_project.model.items.Dirt();
                    }
                    break;
                }
            }
        }
    }
    public void addItem(Item item) {}
    /*
    public void addItem(String itemName,int amount) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {

        } throw new ClassNotFoundException() | new InstantiationException() | new IllegalAccessException(){

        }
        Object obj = Class.forName("my_project.model.blocks."+itemName).newInstance();
    }
    */

    public void removeItem(Block block){

    }

    public Item getItem(int slot){
        return null;
    }
    public void highlitedSlot(int slot) {
        highlitedSlot = slot;
    }
}
