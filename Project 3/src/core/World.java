package core;

import tileengine.TETile;
import tileengine.Tileset;

public class World {
    public static final int WIDTH = 60;
    public static final int HEIGHT = 30;

    private TETile[][] myworld;
    private int width;
    private int height;

    public World() {
        width = WIDTH;
        height = HEIGHT;
        myworld = new TETile[width][height];
        fillWorldWithBlanck(myworld);
    }

    private void fillWorldWithBlanck(TETile[][] myworld) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                myworld[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static World createWorld() {
        return new World();
    }

    public TETile[][] getWorld() {
        return myworld;
    }


}
