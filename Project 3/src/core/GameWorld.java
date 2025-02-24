package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;

import static core.World.HEIGHT;
import static core.World.WIDTH;

public class GameWorld implements GameState{
    private TERenderer ter;
    private World myWorld;


    public GameWorld() {}

    @Override
    public void update(int pressed) {
        if (pressed == 'q' || pressed == 'Q') {
            System.exit(0);
        } else if (pressed == 'w' || pressed == 'W') {
            myWorld.userAvater.moveUp();
        } else if (pressed == 's' || pressed == 'S') {
            myWorld.userAvater.moveDown();
        } else if (pressed == 'd' || pressed == 'D') {
            myWorld.userAvater.moveRight();
        } else if (pressed == 'a' || pressed == 'A') {
            myWorld.userAvater.moveLeft();
        } else if (pressed == 'o' || pressed == 'O') {
            myWorld.save();
        } else if (pressed == 'n' || pressed == 'N') {
            mach.changeState("EnterSeed");
        }
    }

    @Override
    public void render() {
        ter.renderFrame(myWorld.getWorld());
//        double mX = mouseX();
//        double mY = mouseY();
//        StringBuilder text = new StringBuilder();
//        text.append("Tile: ");
//        text.append(mX + "\t" + mY);
//        if (myWorld.getWorld()[mX][mY] == Tileset.WALL) {
//            text.append("Wall");
//        } else if (myWorld.getWorld()[mX][mY] == Tileset.FLOOR) {
//            text.append("Floor");
//        } else if (myWorld.getWorld()[mX][mY] == Tileset.AVATAR) {
//            text.append("Avatar");
//        } else if (myWorld.getWorld()[mX][mY] == Tileset.NOTHING) {
//            text.append("Nothing");
//        }
//        StdDraw.text(0.5, 0.5, text.toString());
    }

    @Override
    public void enter() {
        ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        if (mach.state.equals("Load")) {
            myWorld = World.loadWorld();
        } else {
            myWorld = World.createWorld(Integer.parseInt(mach.state));
        }
        ter.renderFrame(myWorld.getWorld());
    }

    @Override
    public void exit() {
        StdDraw.clear();
    }
}
