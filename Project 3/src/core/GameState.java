package core;

public interface GameState {
//    StateMachine mach;

    void update(int pressed);

    void render();

    void enter();

    void exit();
}
