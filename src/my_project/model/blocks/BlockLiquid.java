package my_project.model.blocks;

import KAGO_framework.view.DrawTool;
import com.sun.javafx.geom.Vec2d;
import my_project.control.ProgramController;
import my_project.control.ProgramManager;
import my_project.model.BlockSpace;
import my_project.model.textureContainers.BlockTextures;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BlockLiquid extends Block {
    protected double volume;
    protected double minimumVolume = 0.05;
    protected int viscosity = 1;
    public BlockLiquid(Vec2d gridPosition, double volume) {
        super(gridPosition, true);
        this.volume = volume;
    }
    @Override
    public void update(double dt) {
        super.update(dt);

        if (volume <= minimumVolume) {
            this.destroy();
        }
    }
    @Override
    public void updateOnTick(){
        super.updateOnTick();
        for (int i = 0; i < viscosity; i++) {
            calculateFlow();
        }

    }
    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(255, 98, 0));
        drawTool.drawFilledRectangle(ProgramManager.translateAndScaleX(x), ProgramManager.translateAndScaleY(y + (SIZE.y - SIZE.y*volume) ), ProgramManager.scale(SIZE.x), ProgramManager.scale(SIZE.y)*volume);
    }
    public double getVolume() {
        return volume;
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }
    private void calculateFlow(){
        try {
            Constructor<? extends Block> constructor = this.getClass().getDeclaredConstructor(Vec2d.class, double.class);
            constructor.setAccessible(true);
            BlockSpace blockSpaceDown = getAdjacentBlockSpace("down");
            BlockSpace blockSpaceUp = getAdjacentBlockSpace("up");
            BlockSpace blockSpaceLeft = getAdjacentBlockSpace("left");
            BlockSpace blockSpaceRight = getAdjacentBlockSpace("right");

            if(blockSpaceDown.getBlock().getClass() == BlockAir.class){
                //drop down
                move(0, 1, "swap");
                System.out.println("down");
                System.out.println("Blockspace: " + this.getBlockSpace());
                System.out.println("Y: " + this.getY());
                System.out.println("Y: " + this.getBlockSpace().getY());///fehler

            }else if(blockSpaceDown.getBlock().getClass() == this.getClass()){
                //combine with below

                BlockLiquid liquidBelow = (BlockLiquid)blockSpaceDown.getBlock();
                combineWith(liquidBelow, "push");
                BlockLiquid liquidDown = (BlockLiquid)blockSpaceDown.getBlock();
                if (liquidDown.getVolume() >= 1){
                    if (this.volume > minimumVolume) {
                        flowToSides();
                        //System.out.println("flow1");
                    }
                    combineToSides();
                    //System.out.println("combine1");
                }
            }else{
                //spread
                if (this.volume > minimumVolume) {
                    flowToSides();
                   // System.out.println("flow2");
                }
                combineToSides();
                //System.out.println("Combine2");
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if(volume <= 0){
            destroy();
        }
    }
    private void flowToSides(){
        BlockSpace blockSpaceLeft = getAdjacentBlockSpace("left");
        BlockSpace blockSpaceRight = getAdjacentBlockSpace("right");
        if (blockSpaceLeft.getBlock().getClass() == BlockAir.class && blockSpaceRight.getBlock().getClass() == BlockAir.class) {

            flow(blockSpaceLeft, blockSpaceRight);
        } else if (blockSpaceLeft.getBlock().getClass() == BlockAir.class) {
            flow(blockSpaceLeft);
        } else if (blockSpaceRight.getBlock().getClass() == BlockAir.class) {
            flow(blockSpaceRight);
        }
    }
    private void combineDown(){

    }
    private void combineToSides(){
        Block blockLeft = getAdjacentBlock("left");
        Block blockRight = getAdjacentBlock("right");
        if (blockLeft.getClass() == this.getClass() && blockRight.getClass() == this.getClass()) {
            combineWith((BlockLiquid) blockLeft, (BlockLiquid) blockRight, "balance");
        } else if (blockLeft.getClass() == this.getClass()) {
            combineWith((BlockLiquid) blockLeft, "balance");
        } else if (blockRight.getClass() == this.getClass()) {
            combineWith((BlockLiquid) blockRight, "balance");
        }
    }
    private void combineWith(BlockLiquid liquid, String combineType){
        double difference = liquid.getVolume() - volume;
        switch (combineType){
            case "balance":
                double balance = (liquid.getVolume()+this.getVolume())/2;
                liquid.setVolume(balance);
                this.volume = balance;
                break;
            case "force-push":
                liquid.setVolume(liquid.getVolume() + this.getVolume());
                this.volume = 0;
                break;
            case "force-pull":
                liquid.setVolume(0);
                this.volume = liquid.getVolume() + this.getVolume();
                break;
            case "push":
                if (liquid.getVolume() < 1) {
                    //fill up bottom Block
                    if(1-liquid.getVolume() >= this.getVolume()){
                        liquid.setVolume(liquid.getVolume() + this.getVolume());
                        this.setVolume(0);
                    }else {
                        this.volume = this.getVolume() - (1-liquid.getVolume());
                        liquid.setVolume(1);
                    }
                }
                break;
            case "pull":
                if (this.volume < 1) {
                    //fill up bottom Block
                    this.volume = liquid.getVolume() + this.getVolume();
                    if (this.volume > 1) {
                        liquid.setVolume(this.volume -1);
                        volume = 1;
                    }else{
                        liquid.setVolume(0);
                    }
                }

        }
    }
    private void combineWith(BlockLiquid liquid1, BlockLiquid liquid2,String combineType){
        switch (combineType){
            case "balance":
                double balance = (this.volume+liquid1.getVolume()+liquid2.getVolume())/3;
                liquid1.setVolume(balance);
                liquid2.setVolume(balance);
                this.volume = balance;
            break;

        }
    }
    private void flowDown(){
        /*
        BlockSpace blockSpaceDown = getAdjacentBlockSpace("down");
        if (blockSpaceDown.getBlock().getClass() == BlockAir.class) {
            blockSpaceDown.setBlock(constructor.newInstance(new Vec2d(0,0), 1));
            volume = 0;
            remove();
        }
        */

    }
    private void flow(BlockSpace blockSpace){
        try {
            Constructor<? extends Block> constructor = this.getClass().getDeclaredConstructor(Vec2d.class, double.class);
            constructor.setAccessible(true);

            blockSpace.setBlock(constructor.newInstance(new Vec2d(0, 0), this.volume/2));
            this.volume = this.volume/2;

        }catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
    private void flow(BlockSpace blockSpace1, BlockSpace blockSpace2){
        try {
            Constructor<? extends Block> constructor = this.getClass().getDeclaredConstructor(Vec2d.class, double.class);
            constructor.setAccessible(true);

            blockSpace1.setBlock(constructor.newInstance(new Vec2d(0, 0), this.volume/3));
            blockSpace2.setBlock(constructor.newInstance(new Vec2d(0, 0), this.volume/3));
            this.volume = this.volume/3;

        }catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String showData(){
        return "Volume: " + Math.ceil(volume*1000)/1000;
    }

}

