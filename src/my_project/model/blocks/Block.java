package my_project.model.blocks;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.BlockSpace;
import my_project.Config;
import my_project.control.Renderer;
import my_project.model.BlockTextures;
import my_project.model.Texture;
import my_project.model.entities.Entity;
import my_project.model.entities.Player;

import java.awt.*;

public abstract class Block extends GraphicalObject {
	protected Texture texture;
	static Vec2d SIZE = new Vec2d(16, 16);
	protected Vec2d gridPosition;
	protected boolean isTransparent;
	protected boolean highlighted = false;
	double hitpoints = 0;
	BlockSpace blockSpace;
	public Block(Vec2d gridPosition, boolean isTransparent) {
		this.gridPosition = gridPosition;
		x = gridPosition.x * SIZE.x;
		y = gridPosition.y * SIZE.y;
		width = SIZE.x;
		height = SIZE.y;
		this.isTransparent = isTransparent;
	}
	@Override
	public void draw(DrawTool drawtool){
		drawTexture(drawtool);
	}
	@Override
	public void update(double dt){
		if (hitpoints <= 0){
			destroy();
		}
	}
	protected void drawTexture(DrawTool drawtool){
		boolean onscreen = (Renderer.translateAndScaleX(x) >= Renderer.scale(-SIZE.x) && Renderer.translateAndScaleY(y) >= Renderer.scale(-SIZE.y)) && (Renderer.translateAndScaleX(x) < Config.WINDOW_WIDTH && Renderer.translateAndScaleY(y) < Config.WINDOW_HEIGHT);
		if (onscreen) {
			//drawtool.drawFilledRectangle(Renderer.translateAndScaleX(x), Renderer.translateAndScaleY(y), Renderer.scale(SIZE.x), Renderer.scale(SIZE.y));

			Entity player = Renderer.getEntityRenderer().getPlayer();
			if (player != null) {

				if (!Renderer.raycast(new Vec2d(x, y), new Vec2d(player.getX(), player.getY()), 15000, 1)){
					drawtool.setCurrentColor(Color.BLACK);
					drawtool.drawFilledRectangle(Renderer.translateAndScaleX(x), Renderer.translateAndScaleY(y), Renderer.scale(SIZE.x), Renderer.scale(SIZE.y));
				} else {
					if(texture.getMyImage() != null ) {
						texture.autoDraw(drawtool, x, y, SIZE.x);
					}
				}
			}
			if (highlighted) {
				//System.out.println("highlighted :D");
				drawtool.setCurrentColor(Color.WHITE);

				BlockTextures.getTexture("highlight").autoDraw(drawtool, x, y, SIZE.x);
				highlighted = false;
			}
		}
	}
	public void drawBorder(DrawTool drawtool){
		boolean onscreen = (Renderer.translateAndScaleX(x) >= Renderer.scale(-SIZE.x) && Renderer.translateAndScaleY(y) >= Renderer.scale(-SIZE.y)) && (Renderer.translateAndScaleX(x) < Config.WINDOW_WIDTH && Renderer.translateAndScaleY(y) < Config.WINDOW_HEIGHT);
		if (onscreen) {
			if (!isTransparent) {
				BlockTextures.getTexture("solidBorder").autoDraw(drawtool, x - 1, y - 1, SIZE.x + 2);
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
	public void damage(double damage){
		this.hitpoints += damage;
	}
	public void destroy(){
		Renderer.getBlockRenderer().getTerrain().getBlockSpaceByBlockGrid((int)gridPosition.x, (int)gridPosition.y).setBlock(new Air((gridPosition)));
	}
}
