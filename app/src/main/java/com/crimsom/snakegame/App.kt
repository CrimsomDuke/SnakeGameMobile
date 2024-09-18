package com.crimsom.snakegame

import com.crimsom.snakegame.model.Stage

public class App{
    public companion object{
        var stage : Stage = Stage();
    }
}

public fun main(args : Array<String>) {

    App.stage.printMap();

    while(App.stage.inGame){
        App.stage.processGame(0.1f);
    }
}