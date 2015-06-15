package game_logic;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{
    public static final String gamename = "Super Bowser: "
                                        + "The Quest to Defend the Castle";
    public static final int menu = 0;
    public static final int worldTemp = 10;
    public static final int backpack = 9;
    public static final int worldOne = 1;   
    public static final int worldTwo = 2;  
    public static final int test01 = 901;  
    public Game(String gamename){
        super(gamename);
        this.addState(new Menu(menu));
        this.addState(new WorldTemplate(worldTemp));
        this.addState(new Backpack(backpack));
        this.addState(new WorldOne(worldOne));
        this.addState(new WorldTwo(worldTwo));
    }
    public void initStatesList(GameContainer gc) throws SlickException{
        this.getState(menu).init(gc, this);
        this.getState(worldTemp).init(gc, this);
        this.getState(backpack).init(gc, this);
        this.getState(worldOne).init(gc, this);
        this.enterState(menu);

    }
    public static void main(String[] args) {
        AppGameContainer appgc;
        try {
            appgc = new AppGameContainer(new Game(gamename));
            appgc.setDisplayMode(800, 640, false);
            appgc.setTargetFrameRate(60);
            appgc.start();
        } catch(SlickException e) {
                e.printStackTrace();
        }
    }
}





