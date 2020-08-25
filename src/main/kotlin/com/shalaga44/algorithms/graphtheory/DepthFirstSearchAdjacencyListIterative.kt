package com.shalaga44.algorithms.graphtheory

import com.shalaga44.algorithms.graphtheory.Utils.DataTypes.Edge
import java.util.*


class DepthFirstSearchAdjacencyListIterative(
    private val graph: Map<Int, List<Edge>>, totalNodes: Int
) {


    private val visited = BooleanArray(totalNodes) { false }
    private val stack = Stack<Int>()

    private var isNotSolved = true
    private var count = 0

    fun getNodesCountStartingFrom(node: Int): Int {
        if (isNotSolved)
            dfs(node)
        return count
    }

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
        count++
        markAsVisited(node)
        getEdgesOf(node).forEach { edge ->
            if (edge.end.isNotVisited())
                stack.push(edge.end)
        }

    }

    private fun getEdgesOf(node: Int): List<Edge> {
        return graph[node] ?: emptyList()
    }


    private fun markAsVisited(node: Int) {
        visited[node] = true
    }


    private fun Int.isNotVisited(): Boolean =
        !visited[this]
}
