package com.crimsom.snakegame.model

import com.crimsom.snakegame.App

class Snake {

    var nodes : ArrayDeque<SnakeNode> = ArrayDeque()

    public lateinit var head : SnakeNode;
    public lateinit var tail : SnakeNode;

    public var currentDir : Direction = Direction.RIGHT;

    init {
        nodes.add(SnakeNode(0, 0))
        nodes.addFirst(SnakeNode(1, 0))
    }

    constructor() {
        head = nodes.first()
        tail = nodes.last()
    }

    public fun getNextCell(map : Array<Array<Int>>) : Cell{

        var nextX = head.x
        var nextY = head.y

        when(currentDir){
            Direction.UP -> nextY--
            Direction.DOWN -> nextY++
            Direction.LEFT -> nextX--
            Direction.RIGHT -> nextX++
        }

        //if it's on borders
        if(nextX < 0) nextX = map.size - 1
        if(nextX >= map.size) nextX = 0
        if(nextY < 0) nextY = map[0].size - 1
        if(nextY >= map[0].size) nextY = 0

        return Cell(nextX, nextY, map[nextX][nextY])
    }

}

public enum class Direction {
    UP, DOWN, LEFT, RIGHT
}