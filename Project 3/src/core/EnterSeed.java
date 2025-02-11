package core;

import edu.princeton.cs.algs4.StdDraw;

public class EnterSeed implements GameState {
    private StringBuilder seed;

    public EnterSeed() {
        seed = new StringBuilder();
    }

    @Override
    public void update(int pressed) {

    }

    @Override
    public void render() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.text(0.2, 0.4, "Enter your Seed (enter to end) : ");
        StdDraw.textLeft(0.4, 0.4, seed.toString());
    }

    @Override
    public void enter() {
        seed = new StringBuilder();
        StdDraw.clear(StdDraw.BLACK);
        render();
    }

    @Override
    public void exit() {
        StdDraw.clear();
    }
}
