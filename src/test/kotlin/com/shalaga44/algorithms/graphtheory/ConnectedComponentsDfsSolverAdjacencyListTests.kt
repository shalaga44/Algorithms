package com.shalaga44.algorithms.graphtheory

import com.shalaga44.algorithms.graphtheory.Utils.DataTypes.WeightedDirectedAdjacencyListGraph
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConnectedComponentsDfsSolverAdjacencyListTests {
    private lateinit var graph: WeightedDirectedAdjacencyListGraph


    @Test
    fun `countComponents() in fully Connected graph must return 1`() {
        graph = createFullyConnectedGraph()
        val solver = ConnectedComponentsDfsSolverAdjacencyList(graph, graph.N)
        val count = solver.countComponents()
        assertEquals(1, count)

    }

    @Test
    fun `getComponents() in fully Connected graph must integer array full of ones`() {
        graph = createFullyConnectedGraph()
        val solver = ConnectedComponentsDfsSolverAdjacencyList(graph, graph.N)
        val components = solver.getComponents()
        (0 until graph.N).forEach {
            assertEquals(1, components[it])
        }
    }


    @Test
    fun `Connected Components Test`() {
        (0..10).forEach { testConnectedComponents ->
            graph = createcomponentsGraph(testConnectedComponents)
            val solver = ConnectedComponentsDfsSolverAdjacencyList(graph, graph.N)
            val components = solver.getComponents()
            (0 until graph.N).forEach {
                assertEquals(testConnectedComponents , components[it])
            }
        }
    }

}