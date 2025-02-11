package core;

import static core.Main.stateMachine;

public interface GameState {
    StateMachine mach = stateMachine;

    void update(int pressed);

    void render();

    //每次进入这个函数时完成对整个状态的初始化，
    //并且生成世界时在这里区分是新建世界还是加载旧世界，可以按不同函数参数来重载一下？
    void enter();

    void exit();
}
