package com.sashakhyzhun.example.utils

import com.sashakhyzhun.example.ExampleApp
import com.sashakhyzhun.example.model.Location

fun Int.dp(): Int {
    if (this == 0) {
        return 0
    }
    val metrics = ExampleApp.context.resources?.displayMetrics
    require(metrics != null) { "Metrics is null" }
    return (this * metrics.density).toInt()
}

fun createMockLocationData() = listOf(
    Location(0, "France", "Paris"),
    Location(1, "Kyiv", "Ukraine"),
    Location(2, "New York", "USA"),
    Location(3, "Athens", "Greece"),
    Location(4, "Hungary", "Budapest"),
    Location(5, "Minks", "BELARUS"),
    Location(6, "Cuba", "Havana"),
    Location(7, "England", "London"),
    Location(8, "Finland", "Helsinki"),
    Location(9, "Japan", "Tokyo")
)