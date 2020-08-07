package com.shalaga44.algorithms.graphtheory.Utils.DataTypes

class UnweightedEdge(
    val from: Int,
    val to: Int
) {
    override fun toString(): String {
        return "$from -> $to"
    }
}