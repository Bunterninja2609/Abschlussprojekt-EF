package my_project.model;

import my_project.control.Item;

public class Inventory {

    private int size;
    private Item[] items;

    public Inventory(int size) {
        this.size = size;
        items = new Item[size];
    }

    public void addItem(int slot){

    }

    public Item getItem(int slot){
        return null;
    }
}
