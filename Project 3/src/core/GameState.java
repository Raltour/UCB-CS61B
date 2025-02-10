package core;

import static core.Main.stateMachine;

public interface GameState {
    StateMachine mach = stateMachine;

    void update(int pressed);

    void render();

    void enter();

    void exit();
}
