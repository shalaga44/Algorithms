@file:Suppress("NonAsciiCharacters")

package com.shalaga44.algorithms.graphtheory

import com.shalaga44.algorithms.graphtheory.Utils.DataTypes.WeightedDirectedAdjacencyListGraph
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DepthFirstSearchAdjacencyListTest {
    private lateinit var graph: WeightedDirectedAdjacencyListGraph

    @BeforeEach
    fun `create graph`() {
        graph = createFullyConnectedGraph()

    }

    @Test
    fun `Just a random test for now, I'm going to create something to virtualize it`() {

        val solver = DepthFirstSearchAdjacencyListIterative(graph, graph.N)
        val nodeCount = solver.getNodesCountStartingFrom(node = 0)
        assertEquals(graph.N, nodeCount) { "DFS node count starting from 0 must be ${graph.N}" }


    }

    @Test
    fun `Anther random one 😹😹😹😹😹😹`() {
        graph.addDirectedEdge(5, 6, 99)
        graph.addDirectedEdge(7, 6, 99)
        graph.addDirectedEdge(7, 6, 99)
        val solver = DepthFirstSearchAdjacencyListIterative(graph, graph.N)
        val nodeCount = solver.getNodesCountStartingFrom(node = 5)
        assertEquals(2, nodeCount)
    }

}
