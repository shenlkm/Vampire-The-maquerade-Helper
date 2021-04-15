package com.example.vampiremasterhelper.model

data class PointGroupModel(var title: String, var items: Array<PointItemModel>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PointGroupModel

        if (title != other.title) return false
        if (!items.contentEquals(other.items)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + items.contentHashCode()
        return result
    }
}
