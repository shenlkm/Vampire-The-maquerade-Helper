package com.example.vampiremasterhelper.model

data class PointGroupSetModel(var title: String, var items: MutableList<PointGroupModel>, var locked: Boolean = false)