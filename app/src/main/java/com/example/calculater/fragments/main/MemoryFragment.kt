package com.example.calculater.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.calculater.R
import kotlinx.android.synthetic.main.fragment_memory.*

class MemoryFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_memory, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttons = listOf(memoryRead, memoryClear, memorySave, memoryPlus, memoryMinus)

        buttons.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(view: View?) {
        val text = (view as? TextView)?.text?.toString() ?: return
        val symbol = when (text) {
            getString(R.string.m_plus) -> '+'
            getString(R.string.m_minus) -> '-'
            getString(R.string.mc) -> 'C'
            getString(R.string.mr) -> 'R'
            getString(R.string.ms) -> 'S'
            else -> return
        }
        getViewModel()?.onMemoryClick(symbol)
    }
}
