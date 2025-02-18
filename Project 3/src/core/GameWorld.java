package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;

import static core.World.HEIGHT;
import static core.World.WIDTH;

public class GameWorld implements GameState{
    private TERenderer ter;
    private World myWorld;


    @Override
    public void update(int pressed) {

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
    }
}
