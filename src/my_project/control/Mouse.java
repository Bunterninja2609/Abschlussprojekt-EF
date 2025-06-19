package my_project.control;

import KAGO_framework.model.InteractiveGraphicalObject;
import com.sun.javafx.geom.Vec2d;

import java.awt.event.MouseEvent;

public class Mouse extends InteractiveGraphicalObject {
    private static Vec2d position;
    private static boolean[] button;
    public Mouse() {
        position = new Vec2d();
        button = new boolean[6];
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        position.x = e.getX();
        position.y = e.getY();
    }
    @Override
    public void mousePressed(MouseEvent e) {
        button[e.getButton()] = true;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        button[e.getButton()] = false;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        position.x = e.getX();
        position.y = e.getY();
    }
    public Vec2d getPosition() {
        return position;
    }
    public static boolean isDown(int b) {
        return button[b];
    }
}
