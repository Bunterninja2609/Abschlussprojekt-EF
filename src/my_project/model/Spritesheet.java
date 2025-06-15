package my_project.model;

import KAGO_framework.view.DrawTool;
import my_project.control.Renderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Spritesheet {
    private BufferedImage[][] image;
    private double timerX;
    private double timerY;
    private double cooldownX;
    private double cooldownY;
    private int frameX;
    private int frameY;
    public Spritesheet(BufferedImage[][] image, double cooldownX, double cooldownY) {
        this.image = image;
        cooldownX = cooldownX;
        cooldownY = cooldownY;
        timerX = 0;
        timerY = 0;
        frameX = 0;
        frameY = 0;
    }
    public Spritesheet(String directory, int frameNumX, int frameNumY, double cooldownX, double cooldownY) {
        setNewImage(directory, frameNumX, frameNumY);
        cooldownX = cooldownX;
        cooldownY = cooldownY;
        timerX = 0;
        timerY = 0;
        frameX = 0;
        frameY = 0;
    }
    public void setNewImage(String directory, int frameNumX, int frameNumY) {
        for (int fx = 0; fx < frameNumX; fx++) {
            for (int fy = 0; fy < frameNumY; fy++) {
                try {
                    image[fx][fy] = ImageIO.read(new File(directory + fx + "|" + fy));
                } catch (Exception e) {
                    System.err.println("Error adding frame: " + directory + fx + "|" + fy);
                    e.printStackTrace();
                }
            }
        }
    }
    public void updateX(double dt){
        timerX += dt;
        if (timerX > cooldownX){
            int framesSkipped = (int)(cooldownX/timerX);
            timerX %= cooldownX;
            frameX += framesSkipped;
            frameX %=image.length-1;
        }
    }
    public void updateY(double dt){
        timerY += dt;
        if (timerY > cooldownY){
            int framesSkipped = (int)(cooldownY/timerY);
            timerY %= cooldownY;
            frameY += framesSkipped;
            frameY %=image[0].length-1;
        }
    }
    public void draw(DrawTool drawTool, double x, double y, double scale) {
        drawTool.drawTransformedImage(image[frameX][frameY], x, y, 0, scale);
    }
    public void autoDraw(DrawTool drawTool, double x, double y, double width) {
        drawTool.drawTransformedImage(image[frameX][frameY], Renderer.translateAndScaleX(x), Renderer.translateAndScaleY(y), 0, Renderer.scale(width/image[frameX][frameY].getWidth()));
    }
}
