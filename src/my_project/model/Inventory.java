package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.model.blocks.*;
import my_project.model.items.DirtItem;
import my_project.model.items.Item;
import my_project.model.items.StoneItem;

import java.awt.*;

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
            if (items[i] != null && items[i].getAmount()<= 0) {
                items[i] = null;
            }
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
                        items[i] = new StoneItem(1);
                    }else if (name.equals("Dirt")) {
                        items[i] = new DirtItem(1);
                    }
                    break;
                }
            }
        }
    }
    public void addItem(Item item) {
        if (item == null) {
            return;
        }
        boolean added = false;
        for (Item nItem : items) {
            if (nItem == null) {
                continue;
            }
            if (nItem.getClass() == item.getClass()) {
                nItem.changeAmount(item.getAmount());
                added = true;
                break;
            }
        }
        if (!added) {
            for (int i = 0; i < items.length; i++) {
                if (items[i] == null) {
                    items[i] = item;
                    break;
                }
            }
        }
    }
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
    public boolean includesItem(Class<? extends Item> item, int amount) {
        for (Item nItem : items) {
            if(nItem.getClass() == item) {
                if(nItem.getAmount() >= amount) {
                    return true;
                }else {
                    return false;
                }
            }
        }
        return false;
    }

    public Item getItem(int slot){
        if (slot < 0 || slot >= items.length) {
            return null;
        }
        return items[slot];
    }
    public Item getItem(Class<? extends Item> item) {
        for (Item nItem : items) {
            if(nItem.getClass() == item) {
                System.out.println("Item found");
                return nItem;

            }
        }
        System.out.println("Item not found");
        return null;
    }
    public Item[] getItems() {
        return items;
    }
    public void highlitedSlot(int slot) {
        highlitedSlot = slot;
    }
}
