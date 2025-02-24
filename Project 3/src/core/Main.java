package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.Tileset;

import static core.World.HEIGHT;
import static core.World.WIDTH;
import static edu.princeton.cs.algs4.StdDraw.*;
import static java.lang.Character.getNumericValue;

public class Main {
    public static StateMachine stateMachine;

    public static void gameLoop() {
        stateMachine = new StateMachine();
        while (true) {
            int pressed = getKeyInput();
            stateMachine.update(pressed);
            stateMachine.render();
        }
    }

    public static int getKeyInput() {
        while (true) {
            if (hasNextKeyTyped()) {
                return nextKeyTyped();
            }
        }
    }

    public static void main(String[] args) {
        gameLoop();
    }





    public static void runNewWorld() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.text(0.2, 0.4, "Enter your Seed (enter to end) : ");
        StringBuilder seed = new StringBuilder();
        while (true) {
            if (hasNextKeyTyped()) {
                int key = nextKeyTyped();
                if (key == '\n') {
                    break;
                }
                int keyNum = getNumericValue(key);
                if (keyNum < 0 || keyNum > 9) {
                    continue;
                }
                seed.append(getNumericValue(key));
                StdDraw.clear(StdDraw.BLACK);
                StdDraw.text(0.2, 0.4, "Enter your Seed (enter to end) : ");
                StdDraw.textLeft(0.4, 0.4, seed.toString());
            }
        }
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        World myWorld = World.createWorld(Integer.parseInt(seed.toString()));
        ter.renderFrame(myWorld.getWorld());
        while (true) {
            if (hasNextKeyTyped()) {
                char key = nextKeyTyped();
                if (key == 'q' || key == 'Q') {
                    System.exit(0);
                } else if (key == 'w' || key == 'W') {
                    myWorld.userAvater.moveUp();
                } else if (key == 's' || key == 'S') {
                    myWorld.userAvater.moveDown();
                } else if (key == 'd' || key == 'D') {
                    myWorld.userAvater.moveRight();
                } else if (key == 'a' || key == 'A') {
                    myWorld.userAvater.moveLeft();
                }
            }
            ter.renderFrame(myWorld.getWorld());
            int mX = (int) mouseX();
            int mY = (int) mouseY();
            StringBuilder text = new StringBuilder();
            text.append("Tile: ");
            if (myWorld.getWorld()[mX][mY] == Tileset.WALL) {
                text.append("Wall");
            } else if (myWorld.getWorld()[mX][mY] == Tileset.FLOOR) {
                text.append("Floor");
            } else if (myWorld.getWorld()[mX][mY] == Tileset.AVATAR) {
                text.append("Avatar");
            } else if (myWorld.getWorld()[mX][mY] == Tileset.NOTHING) {
                text.append("Nothing");
            }
            textLeft(0.1, 0.9, text.toString());

        }



    }



}
