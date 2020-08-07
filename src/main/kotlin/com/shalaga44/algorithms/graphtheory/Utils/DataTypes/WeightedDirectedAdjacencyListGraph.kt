package com.shalaga44.algorithms.graphtheory.Utils.DataTypes

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.set

class WeightedDirectedAdjacencyListGraph : Map<Int, MutableList<WeightedEdge>> {

    private val graph = HashMap<Int, MutableList<WeightedEdge>>()
    val N: Int
        get() = allNodes.size
    var E = 0
    private val allNodes = mutableSetOf<Int>()


    fun addDirectedEdge(from: Int, to: Int, cost: Int) {
        E++
        checkIfNewNodes(to)
        checkIfNewNodes(from)
        var list = graph[from]
        if (list == null) {
            list = ArrayList()
            graph[from] = list
        }
        list.add(WeightedEdge(from, to, cost))

    }

    private fun checkIfNewNodes(node: Int) {
        allNodes.add(node)
    }

    override val entries: Set<Map.Entry<Int, MutableList<WeightedEdge>>>
        get() = graph.entries
    override val keys: Set<Int>
        get() = graph.keys
    override val size: Int
        get() = graph.size
    override val values: Collection<MutableList<WeightedEdge>>
        get() = graph.values

    override fun containsKey(key: Int): Boolean {
        return graph.containsKey(key)
    }

    override fun containsValue(value: MutableList<WeightedEdge>): Boolean {
        return graph.containsValue(value)
    }

    override fun get(key: Int): MutableList<WeightedEdge>? {
        return graph[key]
    }

    override fun isEmpty(): Boolean {
        return graph.isEmpty()
    }

}