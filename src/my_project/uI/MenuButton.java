// === MenuButton.java ===
package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.model.GameButton;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MenuButton extends GameButton {
    public MenuButton(int x, int y, int width, int height, Runnable onClick) {
        super(x, y, width, height, "Startbildschirm", onClick);
    }
}
