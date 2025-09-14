package my_project.uI;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.control.ProgramManager;
import my_project.control.Mouse;
import my_project.model.Texture;


public class Button {
    private Vec2d position;
    private int widthButton;
    private int heightButton;
    private boolean isPressed;
    private Texture texture;
    private Texture textureDown;
    private int buttonType;
    private Mouse mouse;

    public Button(double x, double y, int width, String texturePath, String texturePathdown ,int type){
        position = new Vec2d(x, y);
        widthButton = width;
        isPressed = false;
        texture = new Texture(texturePath);
        textureDown = new Texture(texturePathdown);
        buttonType = type;

    }
    public void draw(DrawTool drawTool) {
        //spiel starten
        if (buttonType == 1) {

            if(ProgramManager.getMousePos().x > position.x && ProgramManager.getMousePos().x < position.x + widthButton && ProgramManager.getMousePos().y > position.y && ProgramManager.getMousePos().y < position.y + texture.getHeightRelativeToWidth(widthButton)){
                textureDown.drawToWidth(drawTool, position.x, position.y, widthButton);
            }else{
                texture.drawToWidth(drawTool, position.x, position.y, widthButton);
            }
        }
        //Credits
        if (buttonType == 2) {
            if(ProgramManager.getMousePos().x > position.x && ProgramManager.getMousePos().x < position.x + widthButton && ProgramManager.getMousePos().y > position.y && ProgramManager.getMousePos().y < position.y + texture.getHeightRelativeToWidth(widthButton)){
                textureDown.drawToWidth(drawTool, position.x, position.y, widthButton);
            }else{
                texture.drawToWidth(drawTool, position.x, position.y, widthButton);
            }
        }
        //Main menu
        if (buttonType == 3) {
            if(ProgramManager.getMousePos().x > position.x && ProgramManager.getMousePos().x < position.x + widthButton && ProgramManager.getMousePos().y > position.y && ProgramManager.getMousePos().y < position.y + texture.getHeightRelativeToWidth(widthButton)){
                textureDown.drawToWidth(drawTool, position.x, position.y, widthButton);
            }else{
                texture.drawToWidth(drawTool, position.x, position.y, widthButton);
            }
        }
        // Endscreen
        if (buttonType == 4) {
            if(ProgramManager.getMousePos().x > position.x && ProgramManager.getMousePos().x < position.x + widthButton && ProgramManager.getMousePos().y > position.y && ProgramManager.getMousePos().y < position.y + texture.getHeightRelativeToWidth(widthButton)){
                textureDown.drawToWidth(drawTool, position.x, position.y, widthButton);
            }else{
                texture.drawToWidth(drawTool, position.x, position.y, widthButton);
            }
        //Spiel beenden
        }
        if (buttonType == 5) {
            if(ProgramManager.getMousePos().x > position.x && ProgramManager.getMousePos().x < position.x + widthButton && ProgramManager.getMousePos().y > position.y && ProgramManager.getMousePos().y < position.y + texture.getHeightRelativeToWidth(widthButton)){
                textureDown.drawToWidth(drawTool, position.x, position.y, widthButton);
            }else{
                texture.drawToWidth(drawTool, position.x, position.y, widthButton);
            }
        }
        //Menu (Settings)
        if (buttonType == 6) {
            if(ProgramManager.getMousePos().x > position.x && ProgramManager.getMousePos().x < position.x + widthButton && ProgramManager.getMousePos().y > position.y && ProgramManager.getMousePos().y < position.y + texture.getHeightRelativeToWidth(widthButton)){
                textureDown.drawToWidth(drawTool, position.x, position.y, widthButton);
            }else{
                texture.drawToWidth(drawTool, position.x, position.y, widthButton);
            }
        }

    }
    public void update(double dt) {
        if(ProgramManager.getMousePos().x > position.x && ProgramManager.getMousePos().x < position.x + widthButton && ProgramManager.getMousePos().y > position.y && ProgramManager.getMousePos().y < position.y + texture.getHeightRelativeToWidth(widthButton) && Mouse.isDown(1)){
            isPressed = true;
        }else {
            isPressed = false;
        }
        if(buttonType == 1 ){
            if(isPressed) {
               ProgramManager.setScene(1);
            }
        }
        if(buttonType == 2 ){
            if(isPressed) {
                ProgramManager.setScene(4);
            }
        }
        if(buttonType == 3 ){
            if(isPressed) {
                ProgramManager.setScene(0);
            }
        }
        if(buttonType == 4 ){
            if(isPressed) {
                ProgramManager.setScene(3);
            }
        }

        if(buttonType == 5 ){
            if(isPressed) {
                ProgramManager.setScene(2);
            }
        }
        if(buttonType == 6 ){
            if(isPressed) {
                System.exit(0);
            }
        }
    }
}

