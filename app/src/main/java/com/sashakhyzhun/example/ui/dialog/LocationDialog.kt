package com.sashakhyzhun.example.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.sashakhyzhun.dialogforresult.BaseDialogFragment
import com.sashakhyzhun.dialogforresult.DialogForResultCallback
import com.sashakhyzhun.dialogforresult.DisplayableItem
import com.sashakhyzhun.example.R
import com.sashakhyzhun.example.model.Location
import com.sashakhyzhun.example.ui.adapter.LocationAdapter
import com.sashakhyzhun.example.ui.decorator.LinearDecorator
import com.sashakhyzhun.example.utils.dp

/**
 * Created by Alexander Khyzhun on 05 February 2020.
 * Copyright (c) 2020 Sphere. All rights reserved.
 */
class LocationDialog(
    private val callback: DialogForResultCallback,
    private val locations: List<Location>
) : BaseDialogFragment() {

    private lateinit var et: EditText

    private val locationAdapter by lazy {
        LocationAdapter(click = ::onClickLocation)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater
        val layout = inflater?.inflate(R.layout.dialog_location, null)


        layout?.let { view ->
            val page = view.findViewById<View>(R.id.dialog_select_address_layout_parent)
            et = view.findViewById(R.id.dialog_select_address_et_input)
            val rv = view.findViewById<RecyclerView>(R.id.dialog_select_address_rv_suggestions)


            /**
             * Behaviour and Slide Listeners.
             */
//            with(BottomSheetBehavior.from(page)) {
//                this.state = BottomSheetBehavior.STATE_EXPANDED
//                this.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//                    override fun onSlide(bottomSheet: View, slideOffset: Float) {}
//                    override fun onStateChanged(bottomSheet: View, newState: Int) {
//                        if (newState == BottomSheetBehavior.STATE_HIDDEN) {
//                            BottomSheetBehavior.from(page).state = BottomSheetBehavior.STATE_HIDDEN
//
//                            callback.onResultNeutral("Dialog has been closed manually")
//                            dismiss()
//                        }
//                    }
//                })
//            }

            /*
             * manual keyboard opening
             */
            et.requestFocus()
            showKeyboard()

            /*
             * initiation of recycler view
             */
            with(rv) {
                adapter = locationAdapter
                layoutManager = LinearLayoutManager(
                    context,
                    RecyclerView.VERTICAL,
                    false
                )
                addItemDecoration(
                    LinearDecorator(
                        8.dp(),
                        16.dp(),
                        8.dp(),
                        16.dp()
                    )
                )
            }

            /*
             * Listener for user query and RV updating
             */
            et.addTextChangedListener {
                it?.let { query ->
                    val actualData: List<Location>? = locations.filter { location ->
                        location.city.contains(query, true) ||
                                location.country.contains(query, true)
                    }
                    when (actualData) {
                        null -> callback.onResultFailed(Exception("Error while fetching locations"))
                        else -> locationAdapter.updateLocationData(actualData)
                    }
                }
            }


        }

        builder.setCancelable(true)
        builder.setView(layout)
        return builder.create()

    }

    private fun onClickLocation(item: DisplayableItem) {
        et.clearFocus()
        hideKeyboard()

        callback.onResultSuccess(item as Location)
        dismiss()
    }


    companion object {
        const val TAG = "LocationDialog"
    }

}