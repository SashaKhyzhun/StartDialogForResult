package com.sashakhyzhun.example.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sashakhyzhun.dialogforresult.data.DialogForResultCallback
import com.sashakhyzhun.dialogforresult.data.DisplayableItem
import com.sashakhyzhun.example.R
import com.sashakhyzhun.dialogforresult.data.model.PlainData
import com.sashakhyzhun.dialogforresult.ui.dialog.LocationDialog
import com.sashakhyzhun.dialogforresult.utils.createMockData
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.Exception

/**
 * Created by Alexander Khyzhun on 27 March 2020.
 * Copyright (c) 2020 Sphere. All rights reserved.
 */
class MainFragment : Fragment(),
    DialogForResultCallback {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_text_input_layout_et.setOnClickListener {
            LocationDialog.Builder()
                .callback(this)
                .data(createMockData())
                .title("Select your city")
                .hint("Start typing: Par..")
                .build()
                .show(childFragmentManager, LocationDialog.TAG)

        }
    }

    override fun onResultSuccess(item: DisplayableItem) {
        // manage your code here

        (item as PlainData).title?.let {
            fragment_text_input_layout_et.setText(it)

            if (fragment_text_input_layout_et.text.isNullOrEmpty().not()) {
                fragment_text_input_layout.isHelperTextEnabled = false
            }
        }
    }

    override fun onResultFailed(ex: Exception) {
        // manage your code here
    }

    override fun onResultNeutral(item: Any?) {
        // manage your code here
    }

}