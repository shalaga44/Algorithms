package com.shalaga44.algorithms.graphtheory.Utils.DataTypes


class WeightedEdge(
    val from: Int,
    val to: Int,
    val cost: Int
){
    override fun toString(): String {
        return "$from >-$cost-> $to"
    }
}