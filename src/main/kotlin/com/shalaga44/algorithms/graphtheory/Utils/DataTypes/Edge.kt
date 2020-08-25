package com.shalaga44.algorithms.graphtheory.Utils.DataTypes

abstract class Edge {
    abstract val start: Int
    abstract val end: Int
    abstract override fun toString(): String
}