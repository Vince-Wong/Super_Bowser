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
    public static final int worldThree = 3;
    public static final int worldFour = 4;
    public static final int worldFive = 5;
    public static final int youWin = 6;
    public static final int room = 7;
    public static final int room1 = 20; //t = 20
    public static final int gameOver = 666; 
    public static WorldTemplate prevState = null;
    
    public Game(String gamename){
        super(gamename);
        this.addState(new Menu(menu));
        this.addState(new WorldTemplate(worldTemp));
        this.addState(new Backpack(backpack));
        this.addState(new WorldOne(worldOne));
        this.addState(new WorldTwo(worldTwo));
        this.addState(new WorldThree(worldThree));
        this.addState(new WorldFour(worldFour));
        this.addState(new WorldFive(worldFive));
        this.addState(new YouWin(youWin));
        this.addState(new Room(room));
        this.addState(new Room1(room1));
        this.addState(new GameOver(gameOver));
    }
    public void initStatesList(GameContainer gc) throws SlickException{
        this.getState(menu).init(gc, this);
        this.getState(worldTemp).init(gc, this);
        this.getState(backpack).init(gc, this);
        this.getState(worldOne).init(gc, this);
        this.getState(worldTwo).init(gc, this);
        this.getState(worldThree).init(gc, this);
        this.getState(room1).init(gc, this);
        this.getState(worldFour).init(gc, this);
        this.getState(worldFive).init(gc, this);
        this.getState(room).init(gc, this);
        this.getState(room1).init(gc, this);
        this.getState(youWin).init(gc, this);
        this.getState(gameOver).init(gc, this);
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





