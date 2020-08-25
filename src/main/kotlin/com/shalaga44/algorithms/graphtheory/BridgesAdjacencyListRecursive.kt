package com.shalaga44.algorithms.graphtheory


class BridgesAdjacencyListRecursive(private val graph: Map<Int, List<Int>>, private val n: Int) {
    private var id = 0
    private var isNotSolved = true
    private lateinit var nodesIds: IntArray
    private lateinit var visited: BooleanArray
    private lateinit var lowLinkValues: IntArray
    private val bridges = mutableListOf<Pair<Int, Int>>()


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
        dfs(startingNode, -1)
    }

    private fun dfs(currentNode: Int, previousNode: Int) {
        markAsVisited(currentNode)
        initNodeId(currentNode)
        initLowLinkValue(currentNode)
        getNodesFrom(currentNode).filter { it != previousNode }.forEach { nextNode ->
            if (nextNode.isNotVisited()) {
                dfs(nextNode, currentNode)
                updatePreviousNodeLowLinkOnCallBack(nextNode, currentNode)
            } else
                updatePreviousNodeLowLinkFromVisited(nextNode, currentNode)
        }
    }


    private fun updatePreviousNodeLowLinkOnCallBack(currentNode: Int, previousNode: Int) {
        previousNode.lowLink = minOf(previousNode.lowLink, currentNode.lowLink)
        if (previousNode.id < currentNode.lowLink)
            bridges.add(previousNode to currentNode)

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


    private fun Int.isNotVisited(): Boolean {
        return !visited[this]
    }

    private val Int.id: Int
        get() = nodesIds[this]

    private var Int.lowLink: Int
        get() = lowLinkValues[this]
        set(value) {
            lowLinkValues[this] = value
        }

}
