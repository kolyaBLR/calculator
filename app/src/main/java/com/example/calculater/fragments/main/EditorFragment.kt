package com.example.calculater.fragments.main

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import com.example.calculater.R
import kotlinx.android.synthetic.main.fragment_editor.*
import kotlinx.android.synthetic.main.fragment_editor.view.*


class EditorFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_editor, container, false)!!
        view.conditionEditor.setOnTouchListener { v, event -> true }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.conditionEditor.showSoftInputOnFocus = false
        } else {
            val im = getSystemService(view.context, InputMethodManager::class.java)
            im?.hideSoftInputFromWindow(view.conditionEditor.windowToken, 0)
        }
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