package my_project.model;

import KAGO_framework.view.DrawTool;
import my_project.control.ProgramManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    public Spritesheet(String directory, String fileType, int frameNumX, int frameNumY, double cooldownX, double cooldownY) {
        setNewImage(directory, fileType, frameNumX, frameNumY);
        this.cooldownX = cooldownX;
        this.cooldownY = cooldownY;
        timerX = 0;
        timerY = 0;
        frameX = 0;
        frameY = 0;
    }
    public void setNewImage(String directory, String fileType, int frameNumX, int frameNumY) {
        image = new BufferedImage[frameNumX][frameNumY];
        for (int fx = 0; fx < frameNumX; fx++) {
            for (int fy = 0; fy < frameNumY; fy++) {
                try {
                    //System.out.println("adding frame " + fx + "-" + fy);
                    image[fx][fy] = ImageIO.read(new File(directory + fx + "-" + fy + fileType));
                    //System.out.println("Successfully added frame " + fx + "-" + fy);
                } catch (IOException e) {
                    System.err.println("Error adding frame: " + directory + fx + "-" + fy + fileType);
                    e.printStackTrace();
                }
            }
        }
    }
    public void updateX(double dt){
        timerX += dt;
        if (timerX > cooldownX){
            int framesSkipped = (int)(timerX/cooldownX);
            //System.out.println(framesSkipped);

            timerX %= cooldownX;
            frameX += framesSkipped;
            frameX %=image.length;

        }
    }
    public void updateY(double dt){
        timerY += dt;
        if (timerY > cooldownY){
            int framesSkipped = (int)(timerY/cooldownY);
            timerY %= cooldownY;
            frameY += framesSkipped;
            frameY %=image[0].length;
        }
    }
    public void draw(DrawTool drawTool, double x, double y, double scale) {
        drawTool.drawTransformedImage(image[frameX][frameY], x, y, 0, scale);
    }
    public void autoDraw(DrawTool drawTool, double x, double y, double width) {
        drawTool.drawTransformedImage(image[frameX][frameY], ProgramManager.translateAndScaleX(x), ProgramManager.translateAndScaleY(y), 0, ProgramManager.scale(width/image[frameX][frameY].getWidth()));
    }
    public void setFrameX(int frameX) {
        this.frameX = frameX;
    }
    public void setFrameY(int frameY) {
        this.frameY = frameY;
    }
    public int getFrameX() {
        return frameX;
    }
    public int getFrameY() {
        return frameY;
    }
}
