package com.shalaga44.algorithms.graphtheory.Utils.DataTypes.Edges

import com.shalaga44.algorithms.graphtheory.Utils.DataTypes.Edge

open class UnweightedEdge(
    override val start: Int,
    override val end: Int
) : Edge() {
    override fun toString(): String {
        return "$start -> $end"
    }
}