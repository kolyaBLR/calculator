package com.example.calculater

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.calculater.fragments.main.EditorFragment
import com.example.calculater.fragments.main.OtherOperatorsFragment
import com.example.calculater.fragments.main.KeyboardFragment
import com.example.calculater.fragments.main.MemoryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        getCoreViewModel().errorText = getString(R.string.error)

        if (savedInstanceState == null) {
            if (getSessionViewModel().isAuth()) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.editorContainer, EditorFragment())
                    .add(
                        R.id.keyboardContainer,
                        KeyboardFragment()
                    )
                    .add(
                        R.id.otherContainer,
                        OtherOperatorsFragment()
                    )
                    .add(R.id.memoryContainer, MemoryFragment())
                    .commit()
            } else {
                showAuthActivity()
            }
        }

        signOut.setOnClickListener {
            AlertDialog.Builder(this, R.style.AlertDarkDialog).setTitle(R.string.sign_up)
                .setPositiveButton(R.string.sign_out) { _, _ ->
                    signOut()
                    showAuthActivity()
                }
                .setNegativeButton(R.string.cancel) { _, _ ->
                }.create().show()
        }
    }

    private fun signOut() {
        getSessionViewModel().auth(null)
    }

    private fun showAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
