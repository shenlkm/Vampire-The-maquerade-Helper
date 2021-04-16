package com.example.vampiremasterhelper.model

data class PointItemModel(
    var label: String,
    var filled: Int = 0,
    var temporal: Int = 0,
    var locked: Boolean = false
)
