package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.DrawTool;
import my_project.model.entities.Entity;
import my_project.model.entities.*;

import java.util.ArrayList;

public class EntityManager {

    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private ViewController viewController;

    public EntityManager(ViewController viewController) {
        this.viewController = viewController;
        this.addEntity(new Player(9));
    }

    public void draw(DrawTool drawTool) {
        for (Entity entity : entities){
            //TODO Add Simulation Distance detection
            entity.draw(drawTool);
        }
    }

    public void update(double dt) {
        for (Entity entity : entities){
            //TODO Add Simulation Distance detection
            entity.update(dt);
        }
    }
    public void addEntity(Entity entity){
        entities.add(entity);
        if (entity instanceof Player){
            viewController.register(entity);
        }
    }
    public Entity getEntity(String type) {
        try {
            Class<?> entityClass = Class.forName(type);
            for (Entity entity : entities) {
                if (entityClass.isInstance(entity)) {
                    return entity;
                }
            }
        } catch (ClassNotFoundException e) {

            System.out.println("Class not found: " + type);
            return null;
        }
        return null;
    }
    public Player getPlayer() {
        for (Entity entity : entities) {
            if (entity instanceof Player) {
                return (Player) entity;
            }
        }
        return null;
    }
}
