package com.example.calculater.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.calculater.R
import kotlinx.android.synthetic.main.fragment_editor.*

class EditorFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_editor, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel()?.let { viewModel ->
            viewModel.condition.observe(viewLifecycleOwner, Observer {
                conditionEditor.setText(it)
            })
            viewModel.preview.observe(viewLifecycleOwner, Observer {
                previewText.text = it
            })
        }
    }
}