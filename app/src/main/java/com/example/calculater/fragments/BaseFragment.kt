package com.example.calculater.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.calculater.view.models.MainViewModel

abstract class BaseFragment: Fragment() {

    protected fun getViewModel(): MainViewModel? {
        return activity?.let { ViewModelProviders.of(it).get(MainViewModel::class.java) }
    }
}