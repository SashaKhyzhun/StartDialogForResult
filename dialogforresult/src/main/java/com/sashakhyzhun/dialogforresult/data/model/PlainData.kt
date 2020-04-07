package com.sashakhyzhun.dialogforresult.data.model

import com.sashakhyzhun.dialogforresult.data.DisplayableItem

data class PlainData(
    var id: Int,
    var title: String?,
    var description: String?,
    var icon: Int?
): DisplayableItem