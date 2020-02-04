package com.example.calculater.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.calculater.R
import kotlinx.android.synthetic.main.fragment_keyboard.*

class KeyboardFragment : BaseFragment(), View.OnClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_keyboard, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val views = listOf(
            number0, number1, number2, number3, number4,
            number5, number6, number7, number8, number9,
            dot, plus, minus, multiply, divide
        )

        equal.setOnClickListener { getViewModel()?.onEqualsClick() }

        delete.setOnClickListener { getViewModel()?.onDeleteClick() }
        delete.setOnLongClickListener {
            getViewModel()?.onDeleteLongClick()
            true
        }

        views.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(v: View?) {
        val item = (v as? TextView)?.text?.toString() ?: return
        val formattedSymbol = when (item) {
            getString(R.string.symbol_divide) -> '/'
            getString(R.string.symbol_minus) -> '-'
            getString(R.string.symbol_plus) -> '+'
            getString(R.string.symbol_multiply) -> '*'
            "," -> '.'
            else -> item.first()
        }
        getViewModel()?.onSymbolClick(formattedSymbol)
    }
}