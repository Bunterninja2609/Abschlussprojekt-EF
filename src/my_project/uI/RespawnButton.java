// === RespawnButton.java ===
package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.model.GameButton;
import java.awt.*;
import java.awt.event.MouseEvent;

public class RespawnButton extends GameButton {
    public RespawnButton(int x, int y, int width, int height, Runnable onClick) {
        super(x, y, width, height, "Respawn", onClick);
    }
}