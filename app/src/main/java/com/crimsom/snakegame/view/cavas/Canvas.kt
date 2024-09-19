package com.crimsom.snakegame.view.cavas

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.crimsom.snakegame.model.Direction
import com.crimsom.snakegame.model.Stage
import com.crimsom.snakegame.view.RetryGameActivity
import com.crimsom.snakegame.view.activities.MainActivity

class Canvas(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var model : Stage = Stage();
    private val paint : Paint = Paint();

    private var CELL_SIZE : Float = 100f;

    public var onFinishAction : () -> Unit = {}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.RED;
        canvas.drawRect(100f, 40f, 200f, 100f, paint);

        CELL_SIZE = width*2 / model.width.toFloat();

        drawGameBoard(canvas);
        if(model.inGame){
            model.processGame(0.1f);
        }else{
            onFinishAction();
            return;
        }

        invalidate()

    }

    public fun setOnFinishedAction(action : () -> Unit){
        onFinishAction = action;
    }

    public fun changeSnakeDirection(direction: Direction){

        //Opposing directions
        if(model.snake.currentDir == Direction.UP && direction == Direction.DOWN) return;
        if(model.snake.currentDir == Direction.DOWN && direction == Direction.UP) return;
        if(model.snake.currentDir == Direction.LEFT && direction == Direction.RIGHT) return;
        if(model.snake.currentDir == Direction.RIGHT && direction == Direction.LEFT) return;

        model.snake.currentDir = direction;
    }

    private fun drawGameBoard(canvas: Canvas){

        //Draw inverted (height <-> widht)
        for (i in 0 until model.height){
            for (j in 0 until model.width){
                paint.color = Color.BLACK;
                canvas.drawRect(i * CELL_SIZE, j * CELL_SIZE, i * CELL_SIZE + CELL_SIZE, j * CELL_SIZE + CELL_SIZE, paint)

                if(model.gameMap[i][j] == 2){
                    paint.color = Color.RED;
                    canvas.drawCircle(i * CELL_SIZE + (CELL_SIZE/2), j * CELL_SIZE + (CELL_SIZE/2), CELL_SIZE/2, paint)
                }else if(model.gameMap[i][j] == 1){
                    paint.color = Color.GREEN;
                    canvas.drawRect(i * CELL_SIZE, j * CELL_SIZE, i * CELL_SIZE + CELL_SIZE, j * CELL_SIZE + CELL_SIZE, paint)
                }
            }
        }
        //draw the head at the end to avoid problems
        drawSnakeHead(canvas);
    }

    private fun drawSnakeHead(canvas: Canvas){
        //for snake's head
        paint.color = Color.GREEN;

        canvas.drawCircle(model.snake.head.x * CELL_SIZE + (CELL_SIZE/2),
            model.snake.head.y * CELL_SIZE + (CELL_SIZE/2),
            CELL_SIZE * 0.6f,
            paint)
    }

}