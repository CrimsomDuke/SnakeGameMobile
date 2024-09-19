package com.crimsom.snakegame.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.crimsom.snakegame.R
import com.crimsom.snakegame.databinding.ActivityMainBinding
import com.crimsom.snakegame.model.Direction
import com.crimsom.snakegame.view.RetryGameActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding;

    companion object{
        const val DELAY = 200L;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupTouchPanels();
        binding.canvas.setOnFinishedAction {
            val intent = Intent(this, RetryGameActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupTouchPanels(){
        binding.touchPanelUP.setOnDirectionChangedListener {
            binding.canvas.changeSnakeDirection(Direction.UP)
        }

        binding.touchPanelDOWN.setOnDirectionChangedListener {
            binding.canvas.changeSnakeDirection(Direction.DOWN)
        }

        binding.touchPanelLEFT.setOnDirectionChangedListener {
            binding.canvas.changeSnakeDirection(Direction.LEFT)
        }

        binding.touchPanelRIGHT.setOnDirectionChangedListener {
            binding.canvas.changeSnakeDirection(Direction.RIGHT)
        }
    }
}
