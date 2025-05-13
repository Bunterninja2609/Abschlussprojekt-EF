package my_project.model.blocks;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.control.Renderer;

public class Block extends GraphicalObject {
	static Vec2d SIZE = new Vec2d(16, 16);
	protected Vec2d gridPosition;
	public Block(Vec2d gridPosition) {
		this.gridPosition = gridPosition;
		x = gridPosition.x * SIZE.x;
		y = gridPosition.y * SIZE.y;
	}
	@Override
	public void draw(DrawTool drawtool){
		drawHitbox(drawtool);
	}
	protected void drawHitbox(DrawTool drawtool){
		//TODO Fix Scale
		drawtool.drawFilledRectangle(Renderer.translateAndScaleX(x), Renderer.translateAndScaleY(y), Renderer.scale(SIZE.x), Renderer.scale(SIZE.y));
	}

	public static Vec2d getSIZE() {
		return SIZE;
	}
}
