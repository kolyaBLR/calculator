package com.example.calculater.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.calculater.view.models.CoreViewModel

abstract class BaseFragment: Fragment() {

    protected fun getViewModel(): CoreViewModel? {
        return activity?.let { ViewModelProviders.of(it).get(CoreViewModel::class.java) }
    }
}