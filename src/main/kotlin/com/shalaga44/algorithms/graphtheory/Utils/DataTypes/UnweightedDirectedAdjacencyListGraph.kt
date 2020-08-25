package com.shalaga44.algorithms.graphtheory.Utils.DataTypes

import com.shalaga44.algorithms.graphtheory.Utils.DataTypes.Edges.UnweightedEdge
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.set

class UnweightedDirectedAdjacencyListGraph : Map<Int, MutableList<UnweightedEdge>> {

    private val graph = HashMap<Int, MutableList<UnweightedEdge>>()
    val N: Int
        get() = allNodes.size
    var E = 0
    private val allNodes = mutableSetOf<Int>()


    fun addUndirectedEdge(from: Int, to: Int) {
        E++
        checkIfNewNodes(to)
        checkIfNewNodes(from)
        var list = graph[from]
        if (list == null) {
            list = ArrayList()
            graph[from] = list
        }
        list.add(UnweightedEdge(from, to))
        list.add(UnweightedEdge(to, from))

    }

    private fun checkIfNewNodes(node: Int) {
        allNodes.add(node)
    }

    override val entries: Set<Map.Entry<Int, MutableList<UnweightedEdge>>>
        get() = graph.entries
    override val keys: Set<Int>
        get() = graph.keys
    override val size: Int
        get() = graph.size
    override val values: Collection<MutableList<UnweightedEdge>>
        get() = graph.values

    override fun containsKey(key: Int): Boolean {
        return graph.containsKey(key)
    }

    override fun containsValue(value: MutableList<UnweightedEdge>): Boolean {
        return graph.containsValue(value)
    }

    override fun get(key: Int): MutableList<UnweightedEdge>? {
        return graph[key]
    }

    override fun isEmpty(): Boolean {
        return graph.isEmpty()
    }

    fun getUndirectedGraphMap(): Map<Int, MutableList<Edge>> {
        exportToMyPythonTool()
        return graph as Map<Int, MutableList<Edge>>

    }

    fun exportToMyPythonTool() {
        println()
        allNodes.forEach { i ->
            get(i)?.forEach { j ->

                print("EdgeHolder(${j.from}, ${j.to}),")
            }

        }
        println()

    }

    fun asAdjacencyList(): Map<Int, List<Int>> {
        val adjacencyList = mutableMapOf<Int, List<Int>>()

        graph.forEach { (node, edges) ->
            adjacencyList[node] = edges.map { it.to }
        }


        return adjacencyList
    }
}