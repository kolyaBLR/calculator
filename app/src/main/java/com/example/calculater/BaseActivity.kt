package com.example.calculater

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.calculater.view.models.AuthViewModel
import com.example.calculater.view.models.SessionViewModel

abstract class BaseActivity: AppCompatActivity() {

    protected fun getAuthViewModel(): AuthViewModel {
        return ViewModelProviders.of(this).get(AuthViewModel::class.java)
    }

    protected fun getSessionViewModel(): SessionViewModel {
        return ViewModelProviders.of(this).get(SessionViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAuthViewModel().init(this)
        getSessionViewModel().init(this)
    }
}