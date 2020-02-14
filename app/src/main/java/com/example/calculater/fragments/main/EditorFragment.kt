package com.example.calculater.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.calculater.R
import kotlinx.android.synthetic.main.fragment_editor.*
import kotlinx.android.synthetic.main.fragment_editor.view.*

class EditorFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_editor, container, false)!!
        view.conditionEditor.setOnTouchListener { v, event -> true }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel()?.let { viewModel ->
            viewModel.condition.observe(viewLifecycleOwner, Observer {
                conditionEditor.setText(it.replace(".", ","))
                if (it.isNotEmpty()) {
                    conditionEditor.setSelection(it.length)
                }
            })
            viewModel.preview.observe(viewLifecycleOwner, Observer {
                previewText.text = it.replace(".", ",")
            })
        }
    }
}