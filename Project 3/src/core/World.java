package core;

import tileengine.TETile;
import tileengine.Tileset;

import static utils.RandomUtils.uniform;

import java.util.Random;

import static edu.princeton.cs.algs4.StdRandom.uniform;

public class World {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static final int BLOCK_SIZE = 10;

    private TETile[][] myworld;
    private int width;
    private int height;
    private int seed;
    Random rand;

    public World() {
        width = WIDTH;
        height = HEIGHT;
        myworld = new TETile[width][height];
        fillWorldWithBlanck(myworld);
        seed = 1234;
        rand = new Random(seed);
        this.generateRandomRoom();
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

    public void generateRandomRoom() {
        for (int x = 0; x < width / BLOCK_SIZE; x++) {
            for (int y = 0; y < height / BLOCK_SIZE; y++) {
                this.blockRandomRoom(x, y);
            }
        }
    }

    private void blockRandomRoom(int x, int y) {
        int x_axis = x * BLOCK_SIZE;
        int y_axis = y * BLOCK_SIZE;

        int x1_axis = x_axis + uniform(rand, 0, 5);
        int y1_axis = y_axis + uniform(rand, 0, 5);
        int x2_axis = x_axis + BLOCK_SIZE - uniform(rand, 1, 4);
        int y2_axis = y_axis + BLOCK_SIZE - uniform(rand, 1, 4);

        for (int i = x1_axis; i <= x2_axis; i++) {
            this.myworld[i][y1_axis] = Tileset.WALL;
            this.myworld[i][y2_axis] = Tileset.WALL;
        }
        for (int i = y1_axis; i <= y2_axis; i++) {
            this.myworld[x1_axis][i] = Tileset.WALL;
            this.myworld[x2_axis][i] = Tileset.WALL;
        }
        for (int i = x1_axis + 1; i < x2_axis; i++) {
            for (int j = y1_axis + 1; j < y2_axis; j++) {
                this.myworld[i][j] = Tileset.FLOOR;
            }
        }
    }


}
