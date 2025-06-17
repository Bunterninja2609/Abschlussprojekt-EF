package my_project.model;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.BlockSpace;
import my_project.control.ProgramController;
import my_project.control.Renderer;
import my_project.model.blocks.Block;

public class Chunk {
    Terrain terrain;

    Vec2d position;
    Vec2d chunkGridPosition;//Position in the Chunks array
    Vec2d blockGridPosition;//Position in the Block grid

    boolean loaded = false;

    static Vec2d SIZE = new Vec2d(16, 16); // wie viele blöcke ein chunk enthält
    BlockSpace[][] blockSpaces; // empty spaces for blocks
    public Chunk(Vec2d chunkGridPosition, Terrain terrain) {
        this.terrain = terrain;

        blockSpaces = new BlockSpace[(int)(SIZE.x)][(int)(SIZE.y)];
        this.chunkGridPosition = chunkGridPosition;
        this.blockGridPosition = new Vec2d(this.chunkGridPosition.x * SIZE.x, this.chunkGridPosition.y * SIZE.y);
        this.position = new Vec2d(blockGridPosition.x * Block.getSIZE().x, blockGridPosition.y * Block.getSIZE().y);

        //generate base structure
        for (int x = 0; x < SIZE.x; x++) {
            for (int y = 0; y < SIZE.y; y++) {
                Vec2d blockPosition = new Vec2d((blockGridPosition.x + x), (blockGridPosition.y + y));
                Block generatedBlock = terrain.generate(blockPosition.x, blockPosition.y);
                System.out.println(blockPosition.x + " " + blockPosition.y);
                blockSpaces[x][y] = new BlockSpace( blockPosition, generatedBlock);
            }
        }
    }
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(0,0,0, 255);
        for(BlockSpace[] row : blockSpaces) {
            for (BlockSpace blockSpace : row) {
                blockSpace.getBlock().draw(drawTool);
            }
        }

        drawTool.setCurrentColor(0, 0, 255, 255);


        drawTool.drawRectangle(Renderer.translateAndScaleX(position.x), Renderer.translateAndScaleY(position.y), SIZE.x * Block.getSIZE().x * Renderer.getSCALE(), SIZE.y * Block.getSIZE().y * Renderer.getSCALE());
        loaded = false;
    }
    public void drawBorder(DrawTool drawTool) {
        drawTool.setCurrentColor(0,0,0, 255);
        for(BlockSpace[] row : blockSpaces) {
            for (BlockSpace blockSpace : row) {
                blockSpace.getBlock().drawBorder(drawTool);
            }
        }
    }
    public void update(double dt) {
        for(BlockSpace[] row : blockSpaces) {
            for (BlockSpace blockSpace : row) {
                blockSpace.getBlock().update(dt);
            }
        }
    }

    public boolean isLoaded() {
        return loaded;
    }
    public static Vec2d getSIZE() {
        return SIZE;
    }
    public void setLoaded(boolean loaded) {

        if (loaded && !this.loaded) {
            terrain.loadChunk(this);
        }else if(!loaded && this.loaded) {
            terrain.unloadChunk(this);
        }

        this.loaded = loaded;
    }
    public Block getBlockByPosition(double x, double y) {
        int blockX = (int) ProgramController.clamp( 0, blockSpaces.length - 1,x / (Block.getSIZE().x));
        int blockY = (int) ProgramController.clamp( 0, blockSpaces[0].length - 1,y / (Block.getSIZE().y));
        return blockSpaces[blockX][blockY].getBlock();
    }
    public Block getBlockByBlockGridPosition(int x, int y) {
        x = (int)(x - blockGridPosition.x);
        y = (int)(y - blockGridPosition.y);
        //System.out.println("min:"+0+" max:"+blockSpaces.length+" x:"+x);
        //System.out.println("Result: "+ProgramController.clamp( 0, blockSpaces.length - 1,x));
        //System.out.println("min:"+0+" max:"+blockSpaces[0].length+" y:"+y);
        int Bx = (int)ProgramController.clamp(0, blockSpaces.length - 1, x);
        int By = (int)ProgramController.clamp(0, blockSpaces[0].length - 1, y);
        return blockSpaces[Bx][By].getBlock();
    }
    public BlockSpace getBlockSpaceByPosition(double x, double y) {
        int blockX = (int) ProgramController.clamp( 0, blockSpaces.length - 1,x / (Block.getSIZE().x));
        int blockY = (int) ProgramController.clamp( 0, blockSpaces[0].length - 1,y / (Block.getSIZE().y));
        return blockSpaces[blockX][blockY];
    }
    public BlockSpace getBlockSpaceByBlockGridPosition(double x, double y) {
        x = (int)(x - blockGridPosition.x);
        y = (int)(y - blockGridPosition.y);
        //System.out.println("min:"+0+" max:"+blockSpaces.length+" x:"+x);
        //System.out.println("Result: "+ProgramController.clamp( 0, blockSpaces.length - 1,x));
        //System.out.println("min:"+0+" max:"+blockSpaces[0].length+" y:"+y);
        int Bx = (int)ProgramController.clamp(0, blockSpaces.length - 1, x);
        int By = (int)ProgramController.clamp(0, blockSpaces[0].length - 1, y);
        return blockSpaces[Bx][By];
    }
}
