package com.shalaga44.algorithms.graphtheory

import com.shalaga44.algorithms.graphtheory.Utils.DataTypes.WeightedEdge
import java.util.*

class DepthFirstSearchAdjacencyListIterative(
    private val graph: Map<Int, MutableList<WeightedEdge>>, totalNodes: Int
) {

    // Instead of using boolean array it's super efficient to just use Integer array
    // and incrementing the token every time you want to set all it's values to false
    private var visited = IntArray(totalNodes) { 0 }
    private var visitedToken = 1

    private val stack = Stack<Int>()
    private var count = 0

    fun getNodesCountStartingFrom(node: Int): Int {
        count = 0
        visitedToken++
        dfs(node)
        return count
    }

    private fun dfs(start: Int) {
        visit(start)
        while (!stack.isEmpty()) {
            val node = stack.pop()
            if (isNotVisited(node))
                visit(node)
        }
    }


    private fun visit(node: Int) {
        count++
        markAsVisited(node)
        getEdgesOf(node).forEach { edge ->
            if (isNotVisited(edge.to))
                stack.push(edge.to)
        }

    }

    private fun getEdgesOf(node: Int): List<WeightedEdge> {
        return graph[node] ?: emptyList()
    }


    private fun markAsVisited(node: Int) {
        visited[node] = visitedToken
    }


    private fun isNotVisited(node: Int): Boolean {
        return visited[node] != visitedToken

    }


}
