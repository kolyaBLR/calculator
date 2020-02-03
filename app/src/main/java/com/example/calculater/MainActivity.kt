package com.example.calculater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculater.fragments.EditorFragment
import com.example.calculater.fragments.KeyboardFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        if (savedInstanceState == null) {
            val editorFragment = EditorFragment()
            val keyboardFragment = KeyboardFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.editorContainer, editorFragment, EDITOR_FRAGMENT_TAG)
                .add(R.id.keyboardContainer, keyboardFragment, KEYBOARD_FRAGMENT_TAG)
                .commit()
        }
    }

    private fun findKeybaordFragment() =
        supportFragmentManager.findFragmentByTag(KEYBOARD_FRAGMENT_TAG) as? KeyboardFragment

    private fun findEditorFragment() =
        supportFragmentManager.findFragmentByTag(EDITOR_FRAGMENT_TAG) as? EditorFragment

    companion object {
        private const val EDITOR_FRAGMENT_TAG = "EDITOR_FRAGMENT_TAG"
        private const val KEYBOARD_FRAGMENT_TAG = "KEYBOARD_FRAGMENT_TAG"
    }
}
