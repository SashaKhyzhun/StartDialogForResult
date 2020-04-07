package com.sashakhyzhun.dialogforresult.utils

import android.content.Context
import com.sashakhyzhun.dialogforresult.R
import com.sashakhyzhun.dialogforresult.data.model.PlainData


fun Int.dp(ctx: Context): Int {
    if (this == 0) {
        return 0
    }
    val metrics = ctx.resources?.displayMetrics
    require(metrics != null) { "Metrics is null" }
    return (this * metrics.density).toInt()
}

fun createMockData() = listOf(
    PlainData(0, "Paris", "Is the capital of France", R.drawable.ic_map_pin),
    PlainData(1, "Kiev", "Is the capital of Ukraine", R.drawable.ic_map_pin),
    PlainData(2, "New York", "Is the capital of USA", R.drawable.ic_map_pin),
    PlainData(3, "Athens", "Is the capital of Greece", R.drawable.ic_map_pin),
    PlainData(4, "Budapest", "Is the capital of Hungary", R.drawable.ic_map_pin),
    PlainData(5, "Minks", "Is the capital of Belarus", R.drawable.ic_map_pin),
    PlainData(6, "Havana", "Is the capital of Cuba", R.drawable.ic_map_pin),
    PlainData(7, "London", "Is the capital of England", R.drawable.ic_map_pin),
    PlainData(8, "Helsinki", "Is the capital of Finland", R.drawable.ic_map_pin),
    PlainData(9, "Tokyo", "Is the capital of Japan", R.drawable.ic_map_pin)
)