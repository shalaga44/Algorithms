package com.shalaga44.algorithms.graphtheory.Utils.DataTypes.Edges

import com.shalaga44.algorithms.graphtheory.Utils.DataTypes.Edge


class WeightedEdge(
    override val from: Int,
    override val to: Int,
    val cost: Int
) : Edge() {
    override fun toString(): String {
        return "$from >-$cost-> $to"
    }
}