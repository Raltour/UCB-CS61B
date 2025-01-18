package core;

import tileengine.TERenderer;

import static core.World.HEIGHT;
import static core.World.WIDTH;

public class Main {
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        World myWorld = World.createWorld();


        ter.renderFrame(myWorld.getWorld());
    }
}
