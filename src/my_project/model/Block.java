package my_project.model;

import KAGO_framework.model.GraphicalObject;

public class Block extends GraphicalObject {
	@Override
	public void draw(Drawtool drawtool){
		drawtool.drawImage(x, y, getImage())
	}
}
