package my_project.uI;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.Config;
import my_project.control.Renderer;
import my_project.control.Mouse;
import my_project.model.Texture;

import java.awt.*;


public class Button {
    private Vec2d position;
    private int widthButton;
    private int heightButton;
    private boolean isPressed;
    private Texture texture;
    private Texture textureDown;
    private int buttonType;
    private Mouse mouse;

    public Button(double x, double y, int width, int height, String texturePath, String texturePathdown ,int type){
        position = new Vec2d(x, y);
        widthButton = width;
        heightButton = height;
        isPressed = false;
        texture = new Texture(texturePath);
        textureDown = new Texture(texturePathdown);
        buttonType = type;

    }
    public void draw(DrawTool drawTool) {
        //spiel starten
        if (buttonType == 1) {

            if(Renderer.getMousePos().x > position.x && Renderer.getMousePos().x < position.x + widthButton && Renderer.getMousePos().y > position.y && Renderer.getMousePos().y < position.y + texture.getHeightRelativeToWidth(widthButton)){
                textureDown.drawToWidth(drawTool, position.x, position.y, widthButton);
            }else{
                texture.drawToWidth(drawTool, position.x, position.y, widthButton);
            }
        }
        //Credits
        if (buttonType == 2) {
            if(Renderer.getMousePos().x > position.x && Renderer.getMousePos().x < position.x + widthButton && Renderer.getMousePos().y > position.y && Renderer.getMousePos().y < position.y + texture.getHeightRelativeToWidth(widthButton)){
                textureDown.drawToWidth(drawTool, position.x, position.y, widthButton);
            }else{
                texture.drawToWidth(drawTool, position.x, position.y, widthButton);
            }
        }
        //Main menu
        if (buttonType == 3) {
            if(Renderer.getMousePos().x > position.x && Renderer.getMousePos().x < position.x + widthButton && Renderer.getMousePos().y > position.y && Renderer.getMousePos().y < position.y + texture.getHeightRelativeToWidth(widthButton)){
                textureDown.drawToWidth(drawTool, position.x, position.y, widthButton);
            }else{
                texture.drawToWidth(drawTool, position.x, position.y, widthButton);
            }
        }
        // Endscreen
        if (buttonType == 4) {
            if(Renderer.getMousePos().x > position.x && Renderer.getMousePos().x < position.x + widthButton && Renderer.getMousePos().y > position.y && Renderer.getMousePos().y < position.y + texture.getHeightRelativeToWidth(widthButton)){
                textureDown.drawToWidth(drawTool, position.x, position.y, widthButton);
            }else{
                texture.drawToWidth(drawTool, position.x, position.y, widthButton);
            }
        //Spiel beenden
        }
        if (buttonType == 5) {
            if(Renderer.getMousePos().x > position.x && Renderer.getMousePos().x < position.x + widthButton && Renderer.getMousePos().y > position.y && Renderer.getMousePos().y < position.y + texture.getHeightRelativeToWidth(widthButton)){
                textureDown.drawToWidth(drawTool, position.x, position.y, widthButton);
            }else{
                texture.drawToWidth(drawTool, position.x, position.y, widthButton);
            }
        }
        //Menu (Settings)
        if (buttonType == 6) {
            if(Renderer.getMousePos().x > position.x && Renderer.getMousePos().x < position.x + widthButton && Renderer.getMousePos().y > position.y && Renderer.getMousePos().y < position.y + texture.getHeightRelativeToWidth(widthButton)){
                textureDown.drawToWidth(drawTool, position.x, position.y, widthButton);
            }else{
                texture.drawToWidth(drawTool, position.x, position.y, widthButton);
            }
        }

    }
    public void update(double dt) {
        if(Renderer.getMousePos().x > position.x && Renderer.getMousePos().x < position.x + widthButton && Renderer.getMousePos().y > position.y && Renderer.getMousePos().y < position.y + texture.getHeightRelativeToWidth(widthButton) && Mouse.isDown(1)){
            isPressed = true;
        }else {
            isPressed = false;
        }
        if(buttonType == 1 ){
            if(isPressed) {
               Renderer.setScene(1);
            }
        }
        if(buttonType == 2 ){
            if(isPressed) {
                Renderer.setScene(4);
            }
        }
        if(buttonType == 3 ){
            if(isPressed) {
                Renderer.setScene(0);
            }
        }
        if(buttonType == 4 ){
            if(isPressed) {
                Renderer.setScene(3);
            }
        }
        if(buttonType == 5 ){
            if(isPressed) {
                System.exit(0);
            }
        }
        if(buttonType == 6 ){
            if(isPressed) {
                Renderer.setScene(2);
            }
        }
    }
}

