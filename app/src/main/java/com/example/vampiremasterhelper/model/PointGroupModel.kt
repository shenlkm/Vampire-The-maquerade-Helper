package com.example.vampiremasterhelper.model

data class PointGroupModel(var title: String, var items: List<PointItemModel>, var totalPoints: Int = 0, var locked: Boolean = false)
