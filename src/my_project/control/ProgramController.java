package my_project.control;

import KAGO_framework.control.ViewController;
import com.sun.javafx.geom.Vec2d;

import java.awt.event.MouseEvent;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Attribute


    // Referenzen
    private ViewController viewController;  // diese Referenz soll auf ein Objekt der Klasse viewController zeigen. Über dieses Objekt wird das Fenster gesteuert.
    private Renderer renderer;
    private BlockRenderer blockRenderer;
    private EntityRenderer entityRenderer;
    private UIRenderer uiRenderer;
    private Keyboard keyboard;

    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    public void startProgram() {
        keyboard = new Keyboard();
        viewController.register(keyboard);
        blockRenderer = new BlockRenderer();
        entityRenderer = new EntityRenderer(viewController);
        uiRenderer = new UIRenderer();
        renderer = new Renderer(blockRenderer, entityRenderer, uiRenderer);
        CollisionHandler.setRenderer(renderer, true);
        viewController.draw(renderer);
        viewController.register(renderer);

    }

    public void updateProgram(double dt){

    }

    public void mouseClicked(MouseEvent e){

    }
    public static double clamp(double min, double max, double value){
        if(value > max){
            return max;
        }
        return Math.max(value, min);
    }
    public static double extrapolate(double min, double max, double value, String type){
        double difference = max - min;
        value = ProgramController.clamp(0,1, value);
        double formular = min + difference*value;
        if(type.equals("linear")){
            //linear ist standart
        }else if(type.equals("parabola")){
            formular = Math.pow(value, 2)*difference + min;
        }else if(type.equals("sine")){
            formular = ((Math.sin((value - 0.5)*Math.PI)+1)/(2))*difference+min;
        }
        return formular;
    }
    public static boolean isAt(double goal, double fluctuation, double value){
        return goal+fluctuation >= value && goal-fluctuation <= value;
    }
    public static boolean isBetween(double min, double max, double value){
        return value>=min && value<=max;
    }


}
