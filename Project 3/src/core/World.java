package core;

import tileengine.TETile;
import tileengine.Tileset;

import static utils.RandomUtils.uniform;
import static utils.FileUtils.writeFile;
import static utils.FileUtils.readFile;
import static utils.FileUtils.fileExists;

import java.util.Random;

import static edu.princeton.cs.algs4.StdRandom.uniform;

public class World {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    public static final int BLOCK_SIZE = 10;//在随机生成room时用作一个参考的大小
    public static final int ROOM_MAX_NUMBER = 50;
    public static final int minHallway = 5;
    public static final int maxHallway = 20;
    public static final int generateTry = 25;
    public int roomNumber = 0;
    public avatar userAvater;
    public static final String SAVE_WORLD_FILE = "save.txt";



    private TETile[][] myworld;
    private int seed;
    Random rand;

    private World(int seed) {
        myworld = new TETile[WIDTH][HEIGHT];
        fillWorldWithBlanck(myworld);
        this.seed = seed;
        rand = new Random(seed);
        this.randomGenerate();
        this.userAvater = new avatar();
    }

    public World(String fileName) {
        if (fileExists(fileName)) {
            this.myworld = new TETile[WIDTH][HEIGHT];
            fillWorldWithBlanck(myworld);
            String save = readFile(SAVE_WORLD_FILE);
            String[] lines = save.split("\r?\n");
            for (int y = 0; y < HEIGHT; y++) {
                String[] line = lines[y].split(" ");
                for (int x = 0; x < WIDTH; x++) {
                    if (line[x].equals("0")) {
                        myworld[x][y] = Tileset.NOTHING;
                    } else if (line[x].equals("1")) {
                        myworld[x][y] = Tileset.WALL;
                    } else if (line[x].equals("2")) {
                        myworld[x][y] = Tileset.FLOOR;
                    } else if (line[x].equals("3")) {
                        myworld[x][y] = Tileset.AVATAR;
                        userAvater = new avatar(x, y);
                    }
                }
            }
        }
    }

    public class avatar {
        int x;
        int y;

        public avatar() {
            while (true) {
                int xx = uniform(rand, WIDTH);
                int yy = uniform(rand, HEIGHT);
                if (myworld[xx][yy] == Tileset.FLOOR) {
                    myworld[xx][yy] = Tileset.AVATAR;
                    this.x = xx;
                    this.y = yy;
                    break;
                }
            }
        }

        public avatar(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void moveUp() {
            if (myworld[x][y + 1] != Tileset.WALL) {
                myworld[x][y] = Tileset.FLOOR;
                y++;
                myworld[x][y] = Tileset.AVATAR;
            }
        }

        public void moveDown() {
            if (myworld[x][y - 1] != Tileset.WALL) {
                myworld[x][y] = Tileset.FLOOR;
                y--;
                myworld[x][y] = Tileset.AVATAR;
            }
        }

        public void moveLeft() {
            if (myworld[x - 1][y] != Tileset.WALL) {
                myworld[x][y] = Tileset.FLOOR;
                x--;
                myworld[x][y] = Tileset.AVATAR;
            }
        }

        public void moveRight() {
            if (myworld[x + 1][y] != Tileset.WALL) {
                myworld[x][y] = Tileset.FLOOR;
                x++;
                myworld[x][y] = Tileset.AVATAR;
            }
        }
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
            for (int i = 0; i < generateTry; i++) {
                int p = uniform(rand, 0, 5);
                if (p == 0) {
                    //向右生成横向走廊
                    int x = x2 + uniform(minHallway, maxHallway);
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
                    int x = x1 - uniform(minHallway, maxHallway);
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
                    int y = y2 + uniform(rand, minHallway, maxHallway);
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
                    int y = y2 - uniform(rand, minHallway, maxHallway);
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

    public static World loadWorld() {
        return new World(SAVE_WORLD_FILE);
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

    public void save() {
        StringBuilder text = new StringBuilder();

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (myworld[x][y] == Tileset.NOTHING) {
                    text.append('0'  + ' ');
                } else if (myworld[x][y] == Tileset.WALL) {
                    text.append('1' + ' ');
                } else if (myworld[x][y] == Tileset.FLOOR) {
                    text.append('2');
                } else if (myworld[x][y] == Tileset.AVATAR) {
                    text.append('3');
                }
            }
            text.append('\n');
        }

        writeFile(SAVE_WORLD_FILE, text.toString());
    }


}
