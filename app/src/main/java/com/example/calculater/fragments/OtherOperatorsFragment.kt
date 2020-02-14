package com.example.calculater.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculater.R
import kotlinx.android.synthetic.main.fragment_other_operators.*

class OtherOperatorsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_other_operators, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sin.setOnClickListener { getViewModel()?.onSymbolClick('S') }
        cos.setOnClickListener { getViewModel()?.onSymbolClick('C') }
    }
}