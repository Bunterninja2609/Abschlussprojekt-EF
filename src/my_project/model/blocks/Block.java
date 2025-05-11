package my_project.model.blocks;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class Block extends GraphicalObject {
	public Block() {}
	@Override
	public void draw(DrawTool drawtool){
		drawtool.drawImage(getMyImage(),x, y);
	}
}
