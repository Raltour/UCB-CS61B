package core;

public interface GameState {
//    StateMachine mach = main.stateMachine;

    void update(int pressed);

    void render();

    void enter();

    void exit();
}
