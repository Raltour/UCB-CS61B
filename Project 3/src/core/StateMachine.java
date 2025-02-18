package core;

import java.util.HashMap;

public class StateMachine {
    private HashMap<String, GameState> gameStates;
    private String curr;

    public StateMachine() {
        gameStates = new HashMap<>();
        gameStates.put("StartMenu", new StartMenu());
        gameStates.put("EnterSeed", new EnterSeed());
        gameStates.put("GameWorld", new GameWorld());
        curr = "StartMenu";
        gameStates.get(curr).enter("Start");
    }

    public void update(int pressed) {
        gameStates.get(curr).update(pressed);
    }

    public void render() {
        gameStates.get(curr).render();
    }

    public void changeState(String to) {
        String str = gameStates.get(curr).exit();
        curr = to;
        gameStates.get(to).enter(str);
    }
}
