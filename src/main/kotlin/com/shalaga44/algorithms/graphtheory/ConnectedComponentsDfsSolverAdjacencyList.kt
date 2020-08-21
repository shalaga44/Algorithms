package com.shalaga44.algorithms.graphtheory

import com.shalaga44.algorithms.graphtheory.Utils.DataTypes.Edges.WeightedEdge
import java.util.*

class ConnectedComponentsDfsSolverAdjacencyList(
    private val graph: Map<Int, List<WeightedEdge>>,
    private val totalNodes: Int
) {
    // Instead of using boolean array it's super efficient to just use Integer array
    // and incrementing the token every time you want to set all it's values to false
    private val visited = BooleanArray(totalNodes) { false }


    private val components = IntArray(totalNodes)
    private val stack = Stack<Int>()
    private var count = 0

    private var isNotSolved = true


    private fun dfs(startNode: Int) {
        visit(startNode)
        while (stack.isNotEmpty()) {
            val nextNode = stack.pop()
            if (nextNode.isNotVisited())
                visit(nextNode)
        }
        isNotSolved = false
    }


    private fun visit(node: Int) {
        markAsVisited(node)
        setComponents(node)
        getEdgesOf(node).forEach { edge ->
            if (edge.to.isNotVisited())
                stack.push(edge.to)
        }

    }

    private fun setComponents(node: Int) {
        components[node] = count
    }

    private fun getEdgesOf(node: Int): List<WeightedEdge> {
        return graph[node] ?: emptyList()
    }


    private fun markAsVisited(node: Int) {
        visited[node] = true
    }


    private fun Int.isNotVisited(): Boolean =
        !visited[this]

    fun countComponents(): Int {
        if (isNotSolved)
            solve()
        return count
    }

    private fun solve() {
        (0 until totalNodes).forEach { node: Int ->
            if (node.isNotVisited())
                startNewDfsFrom(node)
        }
        isNotSolved = false

    }

    private fun startNewDfsFrom(node: Int) {
        dfs(node)
        count++
    }

    fun getComponents(): IntArray {
        if (isNotSolved)
            solve()
        return components
    }


}
