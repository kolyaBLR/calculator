package com.example.calculater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculater.fragments.EditorFragment
import com.example.calculater.fragments.OtherOperatorsFragment
import com.example.calculater.fragments.KeyboardFragment
import com.example.calculater.fragments.MemoryFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.editorContainer, EditorFragment())
                .add(R.id.keyboardContainer, KeyboardFragment())
                .add(R.id.otherContainer, OtherOperatorsFragment())
                .add(R.id.memoryContainer, MemoryFragment())
                .commit()
        }
    }
}
