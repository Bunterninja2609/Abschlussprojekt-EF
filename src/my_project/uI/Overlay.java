package my_project.uI;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.ProgramManager;


public class Overlay {

    private Button startButton01 = new Button(Config.WINDOW_WIDTH-70,10, 50, "src/my_project/resources/Settingsbutton.png", "src/my_project/resources/Settingsbutton_down.png" , 5);

    public Overlay() {

    }
    public void draw(DrawTool drawTool){
        //drawTool.setCurrentColor(new Color(148, 148, 148, 43));
        //drawTool.drawFilledRectangle(0,0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        if(ProgramManager.getSCENE() == 1) {
        startButton01.draw(drawTool);
        }

    }
    public void update(double dt){
        if(ProgramManager.getSCENE() == 1) {
            startButton01.update(dt);
        }

    }
}
