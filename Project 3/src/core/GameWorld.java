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
        }
    }

    @Override
    public void render() {
        ter.renderFrame(myWorld.getWorld());
    }

    @Override
    public void enter(String str) {
        ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        myWorld = World.createWorld(Integer.parseInt(str.toString()));
        ter.renderFrame(myWorld.getWorld());
    }

    @Override
    public String exit() {
        StdDraw.clear();
        return "Exit";
    }
}
