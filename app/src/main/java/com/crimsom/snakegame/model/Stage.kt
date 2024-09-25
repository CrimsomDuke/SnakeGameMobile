package com.crimsom.snakegame.model

import com.crimsom.snakegame.view.activities.MainActivity

class Stage(var width: Int = 20, var height: Int = 10) {

    public lateinit var gameMap : Array<Array<Int>>;

    public lateinit var snake : Snake;

    public var inGame : Boolean = true

    init{
        //INVERTED, Xs are width and Ys are height
        gameMap = Array(height) { Array(width) { 0 } }

        this.snake = Snake()
        setupMatrix()
    }

    public fun processGame(delta : Float) {
        moveSnake()
        //printMap()
        Thread.sleep(MainActivity.DELAY)
    }

    public fun moveSnake(){
        var nextCell = snake.getNextCell(gameMap)

        if(nextCell.value == 1){
            inGame = false
            return
        }

        if (nextCell.value == 2) {
            snake.nodes.addFirst(SnakeNode(nextCell.x, nextCell.y))
            gameMap[nextCell.x][nextCell.y] = 0
            generateRandomFruit()
        } else {
            snake.nodes.addFirst(SnakeNode(nextCell.x, nextCell.y))
            snake.nodes.removeLast()
        }

        snake.head = snake.nodes.first()
        snake.tail = snake.nodes.last()

        updateMap()
    }

    public fun generateRandomFruit(){

        //columns are rows and rows are columns

        var randX = (0 until height).random()
        var randY = (0 until width).random()

        if(gameMap[randX][randY] != 1){
            gameMap[randX][randY] = 2
            return;
        }

        //if the snake is there try again
        generateRandomFruit()
    }

    public fun updateMap(){
        for (i in gameMap.indices) {
            for (j in gameMap[i].indices) {
                //if it's not a fruit, refresh
                if(gameMap[i][j] != 2) gameMap[i][j] = 0
            }
        }
        //draw the SOLID SNAKE
        for (node in snake.nodes) {
            gameMap[node.x][node.y] = 1
        }
    }

    private fun setupMatrix() {

        for (i in gameMap.indices) {
            for (j in gameMap[i].indices) {
                gameMap[i][j] = 0
            }
        }

        generateRandomFruit()
        generateRandomFruit()
        generateRandomFruit()
    }

    public fun setRandomDir(){
        var random = (0..3).random()
        var newDir = Direction.RIGHT;
        when(random){
            0 -> newDir = Direction.UP
            1 -> newDir = Direction.DOWN
            2 -> newDir = Direction.LEFT
            3 -> newDir = Direction.RIGHT
            else -> newDir = Direction.RIGHT
        }

        if(snake.currentDir == Direction.UP && newDir == Direction.DOWN) return
        if(snake.currentDir == Direction.DOWN && newDir == Direction.UP) return

        if(snake.currentDir == Direction.LEFT && newDir == Direction.RIGHT) return
        if(snake.currentDir == Direction.RIGHT && newDir == Direction.LEFT) return

        snake.currentDir = newDir
    }

    public fun printMap() {
        //draw inverted, given that columns are rows and rows are columns
        for (i in gameMap[0].indices) {
            for (j in gameMap.indices) {
                if(gameMap[j][i] == 0) print(" ")
                if(gameMap[j][i] == 1) print("X")
                if(gameMap[j][i] == 2) print("O")
                print(" ")
            }
            println()
        }

        println("--------------------------------------------")
    }

}