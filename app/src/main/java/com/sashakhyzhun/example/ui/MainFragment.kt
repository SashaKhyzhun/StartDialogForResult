package com.sashakhyzhun.example.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sashakhyzhun.dialogforresult.DialogForResultCallback
import com.sashakhyzhun.dialogforresult.DisplayableItem
import com.sashakhyzhun.example.R
import com.sashakhyzhun.example.model.Location
import com.sashakhyzhun.example.ui.dialog.LocationDialog
import com.sashakhyzhun.example.utils.createMockLocationData
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.Exception

/**
 * Created by Alexander Khyzhun on 27 March 2020.
 * Copyright (c) 2020 Sphere. All rights reserved.
 */
class MainFragment : Fragment(), DialogForResultCallback {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_text_input_layout_et.setOnClickListener {
            LocationDialog(callback = this, locations = createMockLocationData())
                .show(childFragmentManager, LocationDialog.TAG)
        }
    }

    override fun onResultSuccess(item: DisplayableItem) {
        // manage your code here

        fragment_text_input_layout_et.setText((item as Location).title)
        if (fragment_text_input_layout_et.text.isNullOrEmpty().not()) {
            fragment_text_input_layout.isHelperTextEnabled = false
        }
    }

    override fun onResultFailed(ex: Exception) {
        // manage your code here
    }

    override fun onResultNeutral(item: Any?) {
        // manage your code here
    }

}