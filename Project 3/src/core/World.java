package core;

import tileengine.TETile;
import tileengine.Tileset;

import static utils.RandomUtils.bernoulli;
import static utils.RandomUtils.uniform;

import java.util.Random;

import static edu.princeton.cs.algs4.StdRandom.uniform;

public class World {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static final int BLOCK_SIZE = 10;
    public int BLOCK_WIDTH = WIDTH / BLOCK_SIZE;
    public int BLOCK_HEIGHT = HEIGHT / BLOCK_SIZE;
    public double CONNECT_PROBABILITY = 0.8;

    private TETile[][] myworld;
    private int seed;
    Random rand;

    public World() {
        myworld = new TETile[WIDTH][HEIGHT];
        fillWorldWithBlanck(myworld);
        seed = 1234;
        rand = new Random(seed);
        this.generateRandomRooms();
        this.connectRooms();
    }

    private void fillWorldWithBlanck(TETile[][] myworld) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
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

    public void generateRandomRooms() {
        for (int x = 0; x < BLOCK_WIDTH; x++) {
            for (int y = 0; y < BLOCK_HEIGHT; y++) {
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

    public void connectRooms() {
        for (int x = 0; x < BLOCK_WIDTH; x++) {
            for (int y = 0; y < BLOCK_HEIGHT - 1; y++) {
                if (bernoulli(rand, CONNECT_PROBABILITY)) {
                    roomConnect(x, y, 0);
                    //0 代表竖着，1 代表横着
                }
            }
        }
        for (int y = 0; y < BLOCK_HEIGHT; y++) {
            for (int x = 0; x < BLOCK_WIDTH - 1; x++) {
                if (bernoulli(rand, CONNECT_PROBABILITY)) {
                    roomConnect(x, y, 1);
                    //0 代表竖着，1 代表横着
                }
            }
        }
    }

    private void roomConnect(int x, int y, int z) {
        if (z == 0) {
            int line = uniform(rand, 1, 8) + x * BLOCK_SIZE;
            int canConnect = 0;
            for (int i = y * BLOCK_SIZE; i < (y + 2) * BLOCK_SIZE - 1; i++) {
                if (this.myworld[line][i] == Tileset.WALL) {
                    canConnect++;
                }
            }
            if (canConnect == 2) {
                blockConnect(x, y, z, line);
            }
        }
    }

    private void blockConnect(int x, int y, int z, int line) {
        if (z == 0) {
            for (int i = y * BLOCK_SIZE + 7; i < y * BLOCK_SIZE + 14; i++) {
                if (this.myworld[line][i] == Tileset.WALL) {
                    this.myworld[line][i] = Tileset.FLOOR;
                } else if (this.myworld[line][i] == Tileset.NOTHING) {
                    this.myworld[line][i] = Tileset.FLOOR;
                    this.myworld[line - 1][i] = Tileset.WALL;
                    this.myworld[line + 1][i] = Tileset.WALL;
                }
            }
        }
    }


}
