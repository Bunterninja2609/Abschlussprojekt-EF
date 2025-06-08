package my_project.model.blocks;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.Config;
import my_project.control.Renderer;
import my_project.model.BlockTextures;
import my_project.model.Texture;

import java.awt.*;

public abstract class Block extends GraphicalObject {
	protected Texture texture;
	static Vec2d SIZE = new Vec2d(16, 16);
	protected Vec2d gridPosition;
	protected boolean isTransparent;
	protected boolean highlighted = false;
	public Block(Vec2d gridPosition, boolean isTransparent) {
		this.gridPosition = gridPosition;
		x = gridPosition.x * SIZE.x;
		y = gridPosition.y * SIZE.y;
		this.isTransparent = isTransparent;

	}
	@Override
	public void draw(DrawTool drawtool){
		drawTexture(drawtool);
	}
	protected void drawTexture(DrawTool drawtool){
		//TODO Fix Scale
		boolean onscreen = (Renderer.translateAndScaleX(x) >= Renderer.scale(-SIZE.x) && Renderer.translateAndScaleY(y) >= Renderer.scale(-SIZE.y)) && (Renderer.translateAndScaleX(x) < Config.WINDOW_WIDTH && Renderer.translateAndScaleY(y) < Config.WINDOW_HEIGHT);
		if (onscreen) {
			drawtool.drawFilledRectangle(Renderer.translateAndScaleX(x), Renderer.translateAndScaleY(y), Renderer.scale(SIZE.x), Renderer.scale(SIZE.y));
			if(texture.getMyImage() != null) {
				texture.autoDraw(drawtool, x, y, SIZE.x);
			}
			if (highlighted) {
				System.out.println("highlighted :D");
				drawtool.setCurrentColor(Color.WHITE);

				BlockTextures.getTexture("highlight").autoDraw(drawtool, x, y, SIZE.x);
				highlighted = false;
			}
		}
	}

	public static Vec2d getSIZE() {
		return SIZE;
	}
	public boolean getTransparent(){
		return isTransparent;
	}
	public void highlight() {
		highlighted = true;
	}
}
