package com.sashakhyzhun.dialogforresult.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.sashakhyzhun.dialogforresult.R
import com.sashakhyzhun.dialogforresult.data.DialogForResultCallback
import com.sashakhyzhun.dialogforresult.data.DisplayableItem
import com.sashakhyzhun.dialogforresult.data.model.PlainData
import com.sashakhyzhun.dialogforresult.ui.adapter.LocationAdapter
import com.sashakhyzhun.dialogforresult.ui.adapter.decorator.LinearDecorator
import com.sashakhyzhun.dialogforresult.ui.base.BaseDialogFragment
import com.sashakhyzhun.dialogforresult.utils.dp
import org.jetbrains.anko.support.v4.ctx

/**
 * Created by Alexander Khyzhun on 05 February 2020.
 * Copyright (c) 2020 Sphere. All rights reserved.
 */
class LocationDialog private constructor(
    private val callback: DialogForResultCallback?,
    private val data: List<PlainData>?,
    private val title: String?,
    private val hint: String?
) : BaseDialogFragment() {


    data class Builder(
        private var callback: DialogForResultCallback? = null,
        private var data: List<PlainData>? = null,
        private var title: String? = null,
        private var hint: String? = null
    ) {
        fun callback(callback: DialogForResultCallback) = apply { this.callback = callback }
        fun data(data: List<PlainData>) = apply { this.data = data }
        fun title(title: String?) = apply { this.title = title }
        fun hint(hint: String?) = apply { this.hint = hint }

        fun build() = LocationDialog(callback, data, title, hint)
    }

    companion object {
        const val TAG = "LocationDialog"
    }

    private lateinit var et: EditText

    private val locationAdapter by lazy {
        LocationAdapter(click = ::onClickLocation)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater
        val layout = inflater?.inflate(R.layout.dialog_location, null)

        layout?.let { view ->
            et = view.findViewById(R.id.dialog_select_address_et_input)
            val tvTitle = view.findViewById<AppCompatTextView>(R.id.dialog_select_address_tv_title)
            val page = view.findViewById<View>(R.id.dialog_select_address_layout_parent)
            val rv = view.findViewById<RecyclerView>(R.id.dialog_select_address_rv_suggestions)

            title?.let {
                tvTitle.text = title
            }
            hint?.let {
                et.hint = hint
            }

            /**
             * Behaviour and Slide Listeners.
             */
            with(BottomSheetBehavior.from(page)) {
                this.state = BottomSheetBehavior.STATE_EXPANDED
                this.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(bottomSheet: View, slideOffset: Float) {}
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                            BottomSheetBehavior.from(page).state = BottomSheetBehavior.STATE_HIDDEN

                            callback?.onResultNeutral("Dialog has been closed manually")
                            dismiss()
                        }
                    }
                })
            }

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
                        8.dp(ctx),
                        16.dp(ctx),
                        8.dp(ctx),
                        16.dp(ctx)
                    )
                )
            }

            /*
             * Preload the data
             */
            data?.let {
                locationAdapter.updateLocationData(it)
            }

            /*
             * Listener for user query and RV updating
             */
            et.addTextChangedListener {
                it?.let { query ->
                    val actualData: List<PlainData>? = data?.filter { item ->
                        item.title?.contains(query, true) ?: false || item.description?.contains(query, true) ?: false
                    }
                    when (actualData) {
                        null -> callback?.onResultFailed(Exception("Error while fetching locations"))
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

        callback?.onResultSuccess(item as PlainData)
        dismiss()
    }


}