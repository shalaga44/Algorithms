package com.shalaga44.algorithms.graphtheory.Utils.DataTypes.Edges

import com.shalaga44.algorithms.graphtheory.Utils.DataTypes.Edge

open class UnweightedEdge(
    override val from: Int,
    override val to: Int
) : Edge() {
    override fun toString(): String {
        return "$from -> $to"
    }
}