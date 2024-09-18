package com.crimsom.snakegame.view.cavas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.crimsom.snakegame.model.Stage

class Canvas(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var model : Stage = Stage();
    private val paint : Paint = Paint();

    private var CELL_SIZE : Float = 100f;

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.RED;
        canvas.drawRect(100f, 40f, 200f, 100f, paint);

        CELL_SIZE = width*2 / model.width.toFloat();

        drawGameBoard(canvas);
        model.processGame(0.1f);

        postInvalidateDelayed(100)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false;

        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                println("Touch at ${event.x}, ${event.y}")
                invalidate();
            }
        }

        return true;
    }

    private fun drawGameBoard(canvas: Canvas){
        for (i in 0 until model.height){
            for (j in 0 until model.width){
                paint.color = Color.BLACK;
                canvas.drawRect(i * CELL_SIZE, j * CELL_SIZE, i * CELL_SIZE + CELL_SIZE, j * CELL_SIZE + CELL_SIZE, paint)

                if(model.gameMap[i][j] == 2){
                    paint.color = Color.RED;
                    canvas.drawRect(i * CELL_SIZE, j * CELL_SIZE, i * CELL_SIZE + CELL_SIZE, j * CELL_SIZE + CELL_SIZE, paint)
                }

                if(model.gameMap[i][j] == 1){
                    paint.color = Color.GREEN;
                    canvas.drawRect(i * CELL_SIZE, j * CELL_SIZE, i * CELL_SIZE + CELL_SIZE, j * CELL_SIZE + CELL_SIZE, paint)
                }
            }
        }
    }

}