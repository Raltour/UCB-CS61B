package core;

import edu.princeton.cs.algs4.StdDraw;

import static java.lang.Character.getNumericValue;

public class EnterSeed implements GameState {
    private StringBuilder seed;

//    public static int getSeed() {
//        return seed;
//    }

    public EnterSeed() {
        seed = new StringBuilder();
    }

    @Override
    public void update(int pressed) {
        if (pressed == '\n') {
            mach.changeState("GameWorld");
        } else if (pressed >= '0' && pressed <= '9') {
            int keyNUm = getNumericValue(pressed);
            seed.append(keyNUm);
        }
    }

    @Override
    public void render() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.text(0.2, 0.4, "Enter your Seed (enter to end) : ");
        StdDraw.textLeft(0.4, 0.4, seed.toString());
    }

    @Override
    public void enter() {
        StdDraw.clear(StdDraw.BLACK);
        render();
    }

    @Override
    public void exit() {
        StdDraw.clear();
        mach.state = seed.toString();
    }
}
