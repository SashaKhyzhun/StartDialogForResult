package com.sashakhyzhun.example.model

import com.sashakhyzhun.dialogforresult.DisplayableItem

data class Location(
    var id: Int,
    var country: String,
    var city: String,
    var title: String = "$country, $city"
): DisplayableItem {

    override fun toString() = "Location[" +
            "id=$id," +
            "title=$title," +
            "country=$country," +
            "city=$city," +
    "]"
}