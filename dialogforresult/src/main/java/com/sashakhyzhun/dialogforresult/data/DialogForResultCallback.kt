package com.sashakhyzhun.dialogforresult.data

import java.lang.Exception

/**
 * The callback to handle selected item.
 * It works like startActivityForResults() but with dialog and mine interface.
 */
interface DialogForResultCallback {
    /**
     * Uses for success use-case. Basically this function should be called when user pressed on
     * something from the suggested list in your Dialog. But different logic might be applied.
     *
     * @param item - this is any POJO class we gonna filtering for. Item class should implement
     * DisplayableItem;
     * @see DisplayableItem - this is an interface to mark POJO classes we wanna use within our
     * dialogs and make it filterable;
     */
    fun onResultSuccess(item: DisplayableItem)
    /**
     * Uses for error use-case. Basically this function should be called inside your try-catch block
     * when you are doing some logic like filtering, calculating or data mapping.
     * But different logic might be applied.
     *
     * @exception ex - this is any type of error you might faced while working with data inside your
     * Dialog. Be sure handling it with try-catch.
     */
    fun onResultFailed(ex: Exception)
    /**
     * Uses for neutral use-case. Basically this function should be called when you didn't get data
     * you are required for or user closed Dialog manually without getting any of error or successful
     * result. Feel free to implement different logic might be applied.
     *
     * @param item - this might be as any type of the data you want to transfer to. Pay attention that
     * this is Nullable object
     */
    fun onResultNeutral(item: Any?)

}