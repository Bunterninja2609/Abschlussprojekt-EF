package my_project.control;

import KAGO_framework.control.ViewController;
import my_project.model.Crafter;

import java.awt.event.MouseEvent;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Attribute


    // Referenzen
    private ViewController viewController;  // diese Referenz soll auf ein Objekt der Klasse viewController zeigen. Über dieses Objekt wird das Fenster gesteuert.
    private ProgramManager programManager;
    private BlockManager blockManager;
    private EntityManager entityManager;
    private UserInterfaceManager userInterfaceManager;
    private Keyboard keyboard;
    private Mouse mouse;
    private Crafter crafter;

    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    public void startProgram() {
        keyboard = new Keyboard();
        mouse = new Mouse();
        crafter = new Crafter();
        viewController.register(keyboard);
        viewController.register(mouse);

        blockManager = new BlockManager();
        entityManager = new EntityManager(viewController);
        userInterfaceManager = new UserInterfaceManager();
        programManager = new ProgramManager(blockManager, entityManager, userInterfaceManager);
        CollisionHandler.setRenderer(programManager, true);
        viewController.draw(programManager);
        viewController.register(programManager);

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
