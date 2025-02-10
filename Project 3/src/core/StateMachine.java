package core;

import java.util.HashMap;

public class StateMachine {
    private HashMap<String, GameState> gameStates;
    private String curr;
    private String StartMenu;
    private StartMenu startMenu;

    public StateMachine() {
        gameStates = new HashMap<>();
        startMenu = new StartMenu();
        gameStates.put(StartMenu, startMenu);
        curr = "StartMenu";
        startMenu.enter();
    }

    public void update(int pressed) {
        gameStates.get(curr).update(pressed);
    }

    public void render() {
        gameStates.get(curr).render();
    }

    public void changeState(String to) {
        gameStates.get(curr).exit();
        gameStates.get(to).enter();
    }
}
