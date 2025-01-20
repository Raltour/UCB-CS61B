package core;

import tileengine.TETile;
import tileengine.Tileset;

import static utils.RandomUtils.uniform;

import java.util.Random;

import static edu.princeton.cs.algs4.StdRandom.uniform;

public class World {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static final int BLOCK_SIZE = 10;//在随机生成room时用作一个参考的大小
    public static final int DEFAULT_SEED = 4321;
    public static final int ROOM_MAX_NUMBER = 10;
    public int roomNumber = 0;


    private TETile[][] myworld;
    private int seed;
    Random rand;

    private World(int seed) {
        myworld = new TETile[WIDTH][HEIGHT];
        fillWorldWithBlanck(myworld);
        this.seed = seed;
        rand = new Random(seed);
        this.randomGenerate();
    }



    private class Room {
        private int x1;
        private int y1;
        private int x2;
        private int y2;
        //0 1 2 3分别代表上下左右

        private Room(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        private boolean generateRoom(TETile[][] world) {
            if (this.x1 < 0 || this.x2 >= WIDTH || this.y1 < 0 || this.y2 >= HEIGHT
                    || roomNumber >= ROOM_MAX_NUMBER) {
                return false;
            }
            for (int i = x1; i <= x2; i ++) {
                for (int j = y1; j <= y2; j ++) {
                    if (world[i][j] == Tileset.FLOOR || world[i][j] == Tileset.WALL) {
                        return false;
                    }
                }
            }
            for (int i = x1; i <= x2; i++) {
                world[i][y1] = Tileset.WALL;
                world[i][y2] = Tileset.WALL;
            }
            for (int i = y1; i <= y2; i++) {
                world[x1][i] = Tileset.WALL;
                world[x2][i] = Tileset.WALL;
            }
            for (int i = x1 + 1; i < x2; i++) {
                for (int j = y1 + 1; j < y2; j++) {
                    world[i][j] = Tileset.FLOOR;
                }
            }
            roomNumber++;
            return true;
        }

        private void generateNextRoom(TETile[][] world) {
            for (int i = 0; i < 40; i++) {
                int p = uniform(rand, 0, 5);
                if (p == 0) {
                    //向右生成横向走廊
                    int x = x2 + uniform(3, 10);
                    int y = y1 + uniform(rand, 1, y2 - y1);
                    int height = uniform(rand, 4, BLOCK_SIZE);
                    int y11 = uniform(rand, y - height + 2, y);
                    Room nextRoom = new Room(x, y11,
                            x + uniform(rand, 3, BLOCK_SIZE), y11 + height - 1);
                    if (nextRoom.generateRoom(world)) {
                        Hallway hw = new Hallway(x2, y, x, y);
                        hw.generateHallway(world);
                        nextRoom.generateNextRoom(world);
                    }
                } else if(p == 1) {
                    //向左生成横向走廊
                    int x = x1 - uniform(3, 10);
                    int y = y1 + uniform(rand, 1, y2 - y1);
                    int height = uniform(rand, 4, BLOCK_SIZE);
                    int y11 = uniform(rand, y - height + 2, y);
                    Room nextRoom = new Room(x - uniform(rand, 3, BLOCK_SIZE), y11,
                            x, y11 + height - 1);
                    if (nextRoom.generateRoom(world)) {
                        Hallway hw = new Hallway(x, y, x1, y);
                        hw.generateHallway(world);
                        nextRoom.generateNextRoom(world);
                    }
                } else if(p == 2) {
                    //向上生成纵向走廊
                    int x = x1 + uniform(rand, 1, x2 - x1);
                    int y = y2 + uniform(rand, 3, 10);
                    int width = uniform(rand, 4, BLOCK_SIZE);
                    int x11 = uniform(rand, x - width + 2, x);
                    Room nextRoom = new Room(x11, y,
                            x11 + width - 1, y + uniform(rand, 3, BLOCK_SIZE));
                    if (nextRoom.generateRoom(world)) {
                        Hallway hw = new Hallway(x , y2, x , y);
                        hw.generateHallway(world);
                        nextRoom.generateNextRoom(world);
                    }
                } else if(p == 3) {
                    //向下生成纵向走廊
                    int x = x1 + uniform(rand, 1, x2 - x1);
                    int y = y2 - uniform(rand, 3, 10);
                    int width = uniform(rand, 4, BLOCK_SIZE);
                    int x11 = uniform(rand, x - width + 2, x);
                    Room nextRoom = new Room(x11, y - uniform(rand, 3, BLOCK_SIZE),
                            x11 + width - 1, y);
                    if (nextRoom.generateRoom(world)) {
                        Hallway hw = new Hallway(x , y, x, y1);
                        hw.generateHallway(world);
                        nextRoom.generateNextRoom(world);
                    }
                }
            }
        }
    }



    private class Hallway {
        private int x1;
        private int y1;
        private int x2;
        private int y2;

        private Hallway(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        private void generateHallway(TETile[][] world) {
            if (y1 == y2) {
                for (int i = x1; i <= x2; i++) {
                    if (world[i][y1 - 1] == Tileset.FLOOR
                            && world[i][y1] == Tileset.FLOOR
                            && world[i][y1 + 1] == Tileset.FLOOR) {
                        continue;
                    }
                    world[i][y1 - 1] = Tileset.WALL;
                    world[i][y1] = Tileset.FLOOR;
                    world[i][y1 + 1] = Tileset.WALL;
                }
            } if (x1 == x2) {
                for (int i = y1; i <= y2; i++) {
                    if (world[x1 - 1][i] == Tileset.FLOOR
                            && world[x1][i] == Tileset.FLOOR
                            && world[x1 + 1][i] == Tileset.FLOOR) {
                        continue;
                    }
                    world[x1 - 1][i] = Tileset.WALL;
                    world[x1][i] = Tileset.FLOOR;
                    world[x1 + 1][i] = Tileset.WALL;
                }
            }
        }
    }



    private void fillWorldWithBlanck(TETile[][] myworld) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                myworld[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static World createWorld(int seed) {
        return new World(seed);
    }

    public TETile[][] getWorld() {
        return myworld;
    }


    private void randomGenerate() {
        int x1 = uniform(rand, 0, 60);
        int y1 = uniform(rand, 0, 20);
        int x2 = x1 + BLOCK_SIZE - uniform(rand, 1, 4);
        int y2 = y1 + BLOCK_SIZE - uniform(rand, 1, 4);

        Room room = new Room(x1, y1, x2, y2);
        room.generateRoom(myworld);
        room.generateNextRoom(myworld);
    }


}
