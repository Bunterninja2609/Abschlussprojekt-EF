package my_project.model;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.blocks.Block;
import my_project.model.items.BrickItem;
import my_project.model.items.DirtItem;
import my_project.model.items.Item;
import my_project.model.items.StoneItem;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

public class Crafter {
    private static ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    public Crafter() {
        recipes = new ArrayList<Recipe>();
        Class<? extends Item>[] requirements = new Class[]{StoneItem.class};
        recipes.add(new Recipe(requirements, BrickItem.class));
    }
    private class Recipe{
        private Class<? extends Item>[] requirements; //damit wird verglichen
        private Class<? extends Item> result;
        public Recipe(Class<? extends Item>[] requirements, Class<? extends Item> result) {
            this.requirements = requirements;
            this.result = result;
        }
        public Class<? extends Item>[] getRequirements() {
            return requirements;
        }
        public Class<? extends Item> getResult() {
            return result;
        }
    }
    public static boolean hasRequirements(Inventory inventory, Recipe recipe) {
        for (Class<? extends Item> requirement : recipe.getRequirements()) {
            if (!inventory.includesItem(requirement, 0)){
                return false;
            }
        }
        return true;
    }
    public static void consumeReqirements(Inventory inventory, Recipe recipe) {
        System.out.println("Consuming requirements");

        for (Class<? extends Item> requirement : recipe.getRequirements()) {
            inventory.getItem(requirement).changeAmount(-1);
        }
    }
    public static void craft(Inventory inventory, Recipe recipe) {
        if (hasRequirements(inventory, recipe)) {
            try {
                Constructor<? extends Item> constructor = recipe.getResult().getDeclaredConstructor(int.class);
                constructor.setAccessible(true);
                consumeReqirements(inventory, recipe);
                inventory.addItem(constructor.newInstance(1));
            }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
                e.printStackTrace();
            }
        }
    }
    public static void craft(Inventory inventory, Class<? extends Item> item) {
        boolean isCrafted = false;
        for (Recipe recipe : recipes) {
            if (recipe.getResult().equals(item)) {
                craft(inventory, recipe);
                isCrafted = true;
            }
            if (isCrafted) {
                break;
            }
        }
    }
    public static void draw(DrawTool drawTool, Inventory inventory){
        ArrayList<Recipe> craftableRecipes = new ArrayList<Recipe>();
        for (Recipe recipe : recipes) {
            if (hasRequirements(inventory, recipe)) {

                craftableRecipes.add(recipe);
            }
        }
        for (int i = 0; i < craftableRecipes.size(); i++) {

        }
    }
}
