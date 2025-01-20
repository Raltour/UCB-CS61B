package core;

import tileengine.TERenderer;

import static core.World.HEIGHT;
import static core.World.WIDTH;
import static core.World.DEFAULT_SEED;

public class Main {
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);




        World myWorld = World.createWorld(DEFAULT_SEED);
        ter.renderFrame(myWorld.getWorld());
    }
}
