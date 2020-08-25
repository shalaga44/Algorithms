package com.shalaga44.algorithms.graphtheory.Utils.DataTypes.Edges

import com.shalaga44.algorithms.graphtheory.Utils.DataTypes.Edge


class WeightedEdge(
    override val start: Int,
    override val end: Int,
    val cost: Int
) : Edge() {
    override fun toString(): String {
        return "$start >-$cost-> $end"
    }
}