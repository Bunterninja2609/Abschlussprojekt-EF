package my_project.control;

import KAGO_framework.control.ViewController;

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

    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    public void startProgram() {
        blockRenderer = new BlockRenderer();
        entityRenderer = new EntityRenderer(viewController);
        renderer = new Renderer(blockRenderer, entityRenderer, uiRenderer);
        viewController.draw(renderer);
        viewController.register(renderer);
    }

    public void updateProgram(double dt){

    }

    public void mouseClicked(MouseEvent e){

    }
}
