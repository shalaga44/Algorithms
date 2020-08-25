package com.shalaga44.algorithms.graphtheory


import java.util.*


class BridgesAdjacencyListIterative(private val graph: Map<Int, List<Int>>, private val n: Int) {
    private var id = 0
    private val callBackToken = -1
    private var isNotSolved = true
    private val stack = Stack<Int>()
    private lateinit var nodesIds: IntArray
    private lateinit var visited: BooleanArray
    private lateinit var lowLinkValues: IntArray
    private val bridges = mutableListOf<Pair<Int, Int>>()
    private val previousNodesMap = mutableMapOf<Int, Int>()


    fun findBridges(): List<Pair<Int, Int>> {
        if (isNotSolved)
            solve()
        return bridges
    }

    private fun solve() {
        initVars()
        allGraphNodes().forEach { node ->
            if (node.isNotVisited())
                startNewDFS(node)
        }
        isNotSolved = false
    }

    private fun startNewDFS(startingNode: Int) {
        stack.push(startingNode)
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            if (node.isCallBackStatus())
                updatePreviousNodeLowLinkOnCallBack()
            else if (node != node.previousNode)
                visit(node)
        }
    }

    private fun visit(node: Int) {
        markAsVisited(node)
        initNodeId(node)
        initLowLinkValue(node)
        getNodesFrom(node).forEach { nextNode ->
            if (nextNode.isNotVisited())
                addToCallBackStack(nextNode, node)
            else
                updatePreviousNodeLowLinkFromVisited(nextNode, node)
        }
    }

    private fun updatePreviousNodeLowLinkOnCallBack() {
        val currentNode = stack.pop()
        val prevNode = currentNode.previousNode
        prevNode.lowLink = minOf(prevNode.lowLink, currentNode.lowLink)
        if (prevNode.id < currentNode.lowLink)
            bridges.add(prevNode to currentNode)

    }

    private fun addToCallBackStack(newNode: Int, previousNode: Int) {
        stack.push(newNode)
        stack.push(callBackToken)
        newNode.setPreviousNode(previousNode)

        stack.push(newNode)
    }


    private fun updatePreviousNodeLowLinkFromVisited(visitedNode: Int, previousNode: Int) {
        previousNode.lowLink = minOf(previousNode.lowLink, visitedNode.id)
    }

    private fun initVars() {
        id = 0
        lowLinkValues = IntArray(n)
        nodesIds = IntArray(n)
        visited = BooleanArray(n)
    }

    private fun allGraphNodes(): Set<Int> {
        return graph.keys
    }

    private fun getNodesFrom(at: Int): List<Int> {
        return graph[at] ?: emptyList()
    }

    private fun initLowLinkValue(at: Int) {
        at.lowLink = at.id
    }

    private fun initNodeId(at: Int) {
        nodesIds[at] = ++id
    }

    private fun markAsVisited(at: Int) {
        visited[at] = true
    }

    private fun Int.isCallBackStatus(): Boolean {
        return this == callBackToken
    }

    private fun Int.isNotVisited(): Boolean {
        return !visited[this]
    }

    private fun Int.setPreviousNode(previousNode: Int) {
        previousNodesMap[this] = previousNode
    }

    private val Int.previousNode: Int
        get() = previousNodesMap[this] ?: -1

    private val Int.id: Int
        get() = nodesIds[this]

    private var Int.lowLink: Int
        get() = lowLinkValues[this]
        set(value) { lowLinkValues[this] = value }

}






