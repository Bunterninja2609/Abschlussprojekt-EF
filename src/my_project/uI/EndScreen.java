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
    protected boolean visible;

    protected RespawnButton respawnButton;
    protected MenuButton menuButton;
    private Texture texture;

    private final int[][] stars;
    private final Random random = new Random();
    private double xOffset = 0;

    private Button startButton02 = new Button(Config.WINDOW_WIDTH/2-60,Config.WINDOW_HEIGHT/2+300, 50,"src/my_project/resources/Creditsbutton.png", "src/my_project/resources/Creditsbutton_down.png" ,5);

    public EndScreen(int screenWidth, int screenHeight, String texturePath) {

        int numberOfStars = 160;
        stars = new int[numberOfStars][3];
        for (int i = 0; i < numberOfStars; i++) {
            stars[i][0] = random.nextInt(screenWidth);   // x
            stars[i][1] = random.nextInt(screenHeight);  // y
            stars[i][2] = 1 + random.nextInt(6);   // radius
        }
        texture = new Texture(texturePath);


        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.visible = false;

        int buttonWidth = 200;
        int buttonHeight = 50;
        int centerX = screenWidth/2 - buttonWidth/2;

        //respawnButton = new RespawnButton(centerX, screenHeight / 2, buttonWidth, buttonHeight, false, 1);
        //menuButton = new MenuButton(centerX , screenHeight / 2 + 70, buttonWidth, buttonHeight, false, 2);
    }

    public void setVisible(boolean visible) {
        //Muss noch ergänzt werden, wenn der spieler stirbt blablabla
    }

    @Override
    public void draw(DrawTool drawTool){
        texture.drawToWidth(drawTool, 0, 0, Config.WINDOW_WIDTH);
        drawTool.setCurrentColor(0, 0, 0, 180);
        drawTool.drawFilledRectangle(0, 0, screenWidth, screenHeight);
        if (Renderer.getSCENE() == 3) {

            drawTool.setCurrentColor(Color.RED);
            for (int[] star : stars) {
                int x = (int) ((star[0] + xOffset) % screenWidth);
                int y = star[1];
                drawTool.drawFilledCircle(x, y, star[2]);
            }




            drawTool.setCurrentColor(Color.RED);
            drawTool.formatText("monospaced",3,45);
            drawTool.drawText(screenWidth / 2 - 140, screenHeight / 2 - 60, "Game Over");

            startButton02.draw(drawTool);
            //respawnButton.draw(drawTool);
            //menuButton.draw(drawTool);
        }
    }


    //keine ahnung wie ich mouse event mache
    public void update(double dt) {
        if (Renderer.getSCENE() == 3) {

            xOffset += 20*dt;
            if (xOffset > screenWidth) {
                xOffset = 0;
            }


            startButton02.update(dt);
            //respawnButton.update(dt);
            //menuButton.update(dt);
        }
    }
}
