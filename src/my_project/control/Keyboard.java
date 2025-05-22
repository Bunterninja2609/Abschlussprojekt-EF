package my_project.control;

import KAGO_framework.model.InteractiveGraphicalObject;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Keyboard extends InteractiveGraphicalObject {
    public static boolean[] keys = new boolean[65489];

    public Keyboard() {}

    public static boolean isPressed(int key) {
        return keys[key];
    }
    public static boolean isPressed(String key) {
        return keys[KeyEvent.getExtendedKeyCodeForChar(key.charAt(0))];
    }
    @Override
    public void keyPressed(int key) {
        keys[key] = true;
    }
    @Override
    public void keyReleased(int key) {
        keys[key] = false;
    }


}
