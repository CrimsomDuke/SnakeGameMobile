package com.crimsom.snakegame.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TouchPanel(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var onDirChangedAction : () -> Unit = {}

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false;

        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                println("Touch at ${event.x}, ${event.y}")

                onDirChangedAction()

                invalidate();
            }
        }

        return true;
    }

    fun setOnDirectionChangedListener(action : () -> Unit){
        onDirChangedAction = action
    }

}