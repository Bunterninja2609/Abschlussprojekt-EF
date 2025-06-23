package my_project.uI;
import my_project.Config;

import KAGO_framework.model.GraphicalObject;
import my_project.control.Renderer;
import my_project.model.Texture;
import my_project.uI.RespawnButton;
import my_project.uI.MenuButton;
import KAGO_framework.view.DrawTool;
import java.util.Random;

import java.awt.*;



public class EndScreen extends GraphicalObject {

    protected int screenWidth;
    protected int screenHeight;

    protected RespawnButton respawnButton;
    protected MenuButton menuButton;
    private Texture texture;

    private final int[][] blood;
    private final Random random = new Random();
    private double xOffset = 0;

    private Button startButton02 = new Button(Config.WINDOW_WIDTH/2-60,Config.WINDOW_HEIGHT/2+300, 50, 40,"src/my_project/resources/Creditsbutton.png", "src/my_project/resources/Creditsbutton_down.png" ,5);

    public EndScreen(int screenWidth, int screenHeight, String texturePath) {

        int numberOfStars = 160;
        blood = new int[numberOfStars][3];
        for (int i = 0; i < numberOfStars; i++) {
            blood[i][0] = random.nextInt(screenWidth);   // x
            blood[i][1] = random.nextInt(screenHeight);  // y
            blood[i][2] = 1 + random.nextInt(6);   // radius
        }
        texture = new Texture(texturePath);


        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        int buttonWidth = 200;
        int buttonHeight = 50;
        int centerX = screenWidth/2 - buttonWidth/2;

    }


    @Override
    public void draw(DrawTool drawTool){
        texture.drawToWidth(drawTool, 0, 0, Config.WINDOW_WIDTH);
        drawTool.setCurrentColor(0, 0, 0, 180);
        drawTool.drawFilledRectangle(0, 0, screenWidth, screenHeight);
        if (Renderer.getSCENE() == 3) {

            drawTool.setCurrentColor(Color.RED);
            for (int[] blut : blood) {
                int x = (int) ((blut[0] + xOffset) % screenWidth);
                int y = blut[1];
                drawTool.drawFilledCircle(x, y, blut[2]);
            }




            drawTool.setCurrentColor(Color.RED);
            drawTool.formatText("monospaced",3,45);
            drawTool.drawText(screenWidth / 2 - 160, screenHeight / 2 - 60, "Game Over");

            startButton02.draw(drawTool);
        }
    }



    public void update(double dt) {
        if (Renderer.getSCENE() == 3) {

            xOffset += 20*dt;
            if (xOffset > screenWidth) {
                xOffset = 0;
            }
            startButton02.update(dt);
        }
    }
}
