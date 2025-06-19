package my_project.model.blocks;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.BlockSpace;
import my_project.Config;
import my_project.control.Renderer;
import my_project.model.BlockTextures;
import my_project.model.Terrain;
import my_project.model.Texture;
import my_project.model.entities.Entity;
import my_project.model.entities.Player;

import java.awt.*;

public abstract class Block extends GraphicalObject {
	protected Texture texture;
	static Vec2d SIZE = new Vec2d(16, 16);
	double illumination;
	protected Vec2d gridPosition;
	protected boolean isTransparent;
	protected boolean highlighted = false;
	double hitpoints = 0;
	BlockSpace blockSpace;
	public Block(Vec2d gridPosition, boolean isTransparent) {
		this.gridPosition = gridPosition;
		x = Terrain.convertBlockGridToPosition((int)gridPosition.x, (int)gridPosition.y).x;
		y = Terrain.convertBlockGridToPosition((int)gridPosition.x, (int)gridPosition.y).y;

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
		calculateIllumination();
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

				if (illumination <= 0.1){
					drawtool.setCurrentColor(Color.BLACK);
					drawtool.drawFilledRectangle(Renderer.translateAndScaleX(x), Renderer.translateAndScaleY(y), Renderer.scale(SIZE.x), Renderer.scale(SIZE.y));
				} else {
					if(texture.getMyImage() != null ) {
						texture.autoDraw(drawtool, x, y, SIZE.x);
					}
					drawtool.setCurrentColor(new Color(0, 0, 0, (int)(255 - 255*illumination)));
					drawtool.drawFilledRectangle(Renderer.translateAndScaleX(x), Renderer.translateAndScaleY(y), Renderer.scale(SIZE.x), Renderer.scale(SIZE.y));
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
		Renderer.getEntityRenderer().getPlayer().getInventory().addItem(this);
		Renderer.getBlockRenderer().getTerrain().getBlockSpaceByBlockGrid((int)gridPosition.x, (int)gridPosition.y).setBlock(new Air((gridPosition)));
	}
	public void calculateIllumination(){
		if (isTransparent) {

		} else {
			Block topBlock = Renderer.getBlockRenderer().getTerrain().getBlockByBlockGrid((int)gridPosition.x, (int)gridPosition.y-1);
			Block bottomBlock = Renderer.getBlockRenderer().getTerrain().getBlockByBlockGrid((int)gridPosition.x, (int)gridPosition.y+1);
			Block leftBlock = Renderer.getBlockRenderer().getTerrain().getBlockByBlockGrid((int)gridPosition.x-1, (int)gridPosition.y);
			Block rightBlock = Renderer.getBlockRenderer().getTerrain().getBlockByBlockGrid((int)gridPosition.x+1, (int)gridPosition.y);
			if (topBlock.getTransparent() || bottomBlock.getTransparent() || leftBlock.getTransparent() || rightBlock.getTransparent()) {
				illumination = 1;
			} else {
				illumination = Math.max(Math.max(topBlock.getIllumination(), bottomBlock.getIllumination()), Math.max(leftBlock.getIllumination(), rightBlock.getIllumination()))*0.5;
			}
		}
	}
	public double getIllumination(){
		return illumination;
	}
	public void setGridPosition(Vec2d gridPosition) {
		this.gridPosition = gridPosition;
		x = Terrain.convertBlockGridToPosition((int)gridPosition.x, (int)gridPosition.y).x;
		y = Terrain.convertBlockGridToPosition((int)gridPosition.x, (int)gridPosition.y).y;
	}
}
