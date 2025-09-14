package my_project.model.blocks;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.model.BlockSpace;
import my_project.Config;
import my_project.control.ProgramManager;
import my_project.model.textureContainers.BlockTextures;
import my_project.model.Terrain;
import my_project.model.Texture;
import my_project.model.entities.Entity;
import my_project.model.items.Item;

import java.awt.*;

public abstract class Block extends GraphicalObject {
	protected Texture texture;
	static Vec2d SIZE = new Vec2d(16, 16);
	private BlockSpace blockSpace;
	double illumination;
	protected Vec2d gridPosition;
	protected boolean isTransparent;
	protected boolean highlighted = false;
	protected double hitpoints = 0;
	protected boolean colliderInside;
	private double damageOverlayCooldown = 0.3;
	private double damageOverlayCooldownTimer = damageOverlayCooldown;

	private boolean updated = true;
	private boolean tickUpdated = true;

	private double timer = 0;

	protected Item drop;

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
		updated = true;
		timer += dt;
		calculateIllumination();
		if (hitpoints <= 0){
			destroy();
		}
		if(colliderInside)setColliderInside(false);
		updateOverlayTimer(dt);
	}

	public void updateOnTick(){
		tickUpdated = true;
	}

	protected void drawTexture(DrawTool drawtool){
		boolean onscreen = (ProgramManager.translateAndScaleX(x) >= ProgramManager.scale(-SIZE.x) && ProgramManager.translateAndScaleY(y) >= ProgramManager.scale(-SIZE.y)) && (ProgramManager.translateAndScaleX(x) < Config.WINDOW_WIDTH && ProgramManager.translateAndScaleY(y) < Config.WINDOW_HEIGHT);
		if (onscreen) {
			//drawtool.drawFilledRectangle(Renderer.translateAndScaleX(x), Renderer.translateAndScaleY(y), Renderer.scale(SIZE.x), Renderer.scale(SIZE.y));

			Entity player = ProgramManager.getEntityRenderer().getPlayer();
			if (player != null) {

				if (illumination <= 0.1){
					drawtool.setCurrentColor(Color.BLACK);
					drawtool.drawFilledRectangle(ProgramManager.translateAndScaleX(x), ProgramManager.translateAndScaleY(y), ProgramManager.scale(SIZE.x), ProgramManager.scale(SIZE.y));
				} else {
					if(texture.getMyImage() != null ) {
						texture.autoDraw(drawtool, x, y, SIZE.x);
					}
					drawtool.setCurrentColor(new Color(0, 0, 0, (int)(255 - 255*illumination)));
					drawtool.drawFilledRectangle(ProgramManager.translateAndScaleX(x), ProgramManager.translateAndScaleY(y), ProgramManager.scale(SIZE.x), ProgramManager.scale(SIZE.y));
				}
			}
			if (highlighted) {
				//System.out.println("highlighted :D");
				drawtool.setCurrentColor(Color.WHITE);

				BlockTextures.getTexture("highlight").autoDraw(drawtool, x, y, SIZE.x);
				highlighted = false;
			}
			double overlayFactor = damageOverlayCooldownTimer/damageOverlayCooldown;
			if (overlayFactor > 0) {
				drawtool.setCurrentColor(new Color(255, 255, 255, (int) (255 * overlayFactor)));
				drawtool.drawFilledRectangle(ProgramManager.translateAndScaleX(x), ProgramManager.translateAndScaleY(y), ProgramManager.scale(SIZE.x), ProgramManager.scale(SIZE.y));
			}
		}
	}
	public void drawBorder(DrawTool drawtool){
		boolean onscreen = (ProgramManager.translateAndScaleX(x) >= ProgramManager.scale(-SIZE.x) && ProgramManager.translateAndScaleY(y) >= ProgramManager.scale(-SIZE.y)) && (ProgramManager.translateAndScaleX(x) < Config.WINDOW_WIDTH && ProgramManager.translateAndScaleY(y) < Config.WINDOW_HEIGHT);
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
		damageOverlayCooldownTimer = damageOverlayCooldown;
	}
	public void destroy(){
		ProgramManager.getEntityRenderer().getPlayer().getInventory().addItem(drop);
		remove();
	}
	public void remove(){
		ProgramManager.getBlockRenderer().getTerrain().getBlockSpaceByBlockGrid((int)gridPosition.x, (int)gridPosition.y).setBlock(new BlockAir((gridPosition)));

	}
	public void calculateIllumination(){
		if (isTransparent) {

		} else {
			Block topBlock = ProgramManager.getBlockRenderer().getTerrain().getBlockByBlockGrid((int)gridPosition.x, (int)gridPosition.y-1);
			Block bottomBlock = ProgramManager.getBlockRenderer().getTerrain().getBlockByBlockGrid((int)gridPosition.x, (int)gridPosition.y+1);
			Block leftBlock = ProgramManager.getBlockRenderer().getTerrain().getBlockByBlockGrid((int)gridPosition.x-1, (int)gridPosition.y);
			Block rightBlock = ProgramManager.getBlockRenderer().getTerrain().getBlockByBlockGrid((int)gridPosition.x+1, (int)gridPosition.y);
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
	public boolean isColliderInside() {
		return colliderInside;
	}
	public void setColliderInside(boolean colliderInside) {
		this.colliderInside = colliderInside;
	}
	private void updateOverlayTimer(double dt) {
		if(damageOverlayCooldownTimer < 0) {
			damageOverlayCooldownTimer = 0;
		} else if (damageOverlayCooldownTimer > 0) {
			damageOverlayCooldownTimer -= dt;
		}
	}
	public void move(double ox, double oy, String type) {
		moveTo(gridPosition.x+ox, gridPosition.y+oy, type);
	}
	public void moveTo(double x, double y, String type) {

		BlockSpace targetSpace = ProgramManager.getBlockRenderer().getTerrain().getBlockSpaceByBlockGrid((int)x, (int)y);
		BlockSpace originSpace = this.getBlockSpace();
		Block targetBlock = targetSpace.getBlock();
		if(originSpace != null && targetBlock != null) {
			switch (type) {
				case "replace":
					targetSpace.setBlock(this);
					originSpace.setNewBlock(BlockAir.class);
					break;
				case "swap":
					targetSpace.setBlock(this);
					originSpace.setBlock(targetBlock);
					break;
				case "place":
					targetSpace.setBlock(this);
					break;
			}
		}


	}

	public BlockSpace getAdjacentBlockSpace(int ox, int oy) {
		return ProgramManager.getBlockRenderer().getTerrain().getBlockSpaceByBlockGrid((int)gridPosition.x + ox, (int)gridPosition.y + oy);
	}
	public Block getAdjacentBlock(int ox, int oy) {
		return getAdjacentBlockSpace(ox, oy).getBlock();
	}
	public BlockSpace getAdjacentBlockSpace(String direction, int offset) {
		if (direction.equals("up")||direction.equals("top")) {
			return getAdjacentBlockSpace(0, -offset);
		}else if (direction.equals("down")||direction.equals("bottom")) {
			return getAdjacentBlockSpace(0, offset);
		}else if (direction.equals("left")||direction.equals("socialism")) {
			return getAdjacentBlockSpace(-offset, 0);
		}else if (direction.equals("right")||direction.equals("fascism")) {
			return getAdjacentBlockSpace(offset, 0);
		}
		return getAdjacentBlockSpace(0, 0);
	}
	public Block getAdjacentBlock(String direction, int offset) {
		return getAdjacentBlockSpace(direction, offset).getBlock();
	}
	public BlockSpace getAdjacentBlockSpace(String direction) {
		return getAdjacentBlockSpace(direction, 1);
	}
	public Block getAdjacentBlock(String direction) {
		return getAdjacentBlockSpace(direction).getBlock();
	}
	public String showData(){
		return "";
	}
	public void setBlockSpace(BlockSpace blockSpace) {
		this.blockSpace = blockSpace;
		if (blockSpace != null) {
			setGridPosition(blockSpace.getGridPosition());
		}
	}
	public BlockSpace getBlockSpace() {
		return blockSpace;
	}
	public boolean isUpdated(){
		return updated;
	}

	public boolean isTickUpdated() {
		return tickUpdated;
	}

	public void setTickUpdated(boolean tickUpdated) {
		this.tickUpdated = tickUpdated;
	}
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}
}
