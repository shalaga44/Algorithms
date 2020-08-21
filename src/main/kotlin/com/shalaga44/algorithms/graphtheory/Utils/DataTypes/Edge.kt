package com.shalaga44.algorithms.graphtheory.Utils.DataTypes

abstract class Edge {
    abstract val from: Int
    abstract val to: Int
    abstract override fun toString(): String
}