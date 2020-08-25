package com.shalaga44.algorithms.graphtheory.Utils.DataTypes

import com.shalaga44.algorithms.graphtheory.Utils.DataTypes.Edges.WeightedEdge
import java.util.*
import kotlin.collections.set

class WeightedDirectedAdjacencyListGraph : Map<Int, MutableList<WeightedEdge>> {

    val graphMap = mutableMapOf<Int, MutableList<WeightedEdge>>()
    val N: Int
        get() = allNodes.size
    var E = 0
    private val allNodes = mutableSetOf<Int>()


    fun addDirectedEdge(from: Int, to: Int, cost: Int) {
        E++
        checkIfNewNodes(to)
        checkIfNewNodes(from)
        var list = graphMap[from]
        if (list == null) {
            list = ArrayList()
            graphMap[from] = list
        }
        list.add(WeightedEdge(from, to, cost))

    }

    private fun checkIfNewNodes(node: Int) {
        allNodes.add(node)
    }

    override val entries: Set<Map.Entry<Int, MutableList<WeightedEdge>>>
        get() = graphMap.entries
    override val keys: Set<Int>
        get() = graphMap.keys
    override val size: Int
        get() = graphMap.size
    override val values: Collection<MutableList<WeightedEdge>>
        get() = graphMap.values

    override fun containsKey(key: Int): Boolean {
        return graphMap.containsKey(key)
    }

    override fun containsValue(value: MutableList<WeightedEdge>): Boolean {
        return graphMap.containsValue(value)
    }

    override fun get(key: Int): MutableList<WeightedEdge>? {
        return graphMap[key]
    }

    override fun isEmpty(): Boolean {
        return graphMap.isEmpty()
    }

    fun asAdjacencyList(): Map<Int, List<Int>> {
        val adjacencyList = mutableMapOf<Int, List<Int>>()

        graphMap.forEach { (node, edges) ->
            adjacencyList[node] = edges.map { it.end }
        }


        return adjacencyList
    }

}