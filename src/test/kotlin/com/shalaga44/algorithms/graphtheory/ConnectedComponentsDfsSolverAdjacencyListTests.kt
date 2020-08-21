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
    fun `getComponents() in fully Connected graph must integer array full of zeros`() {
        graph = createFullyConnectedGraph()
        val solver = ConnectedComponentsDfsSolverAdjacencyList(graph, graph.N)
        val components = solver.getComponents()
        (0 until graph.N).forEach {
            assertEquals(0, components[it])
        }
    }


    @Test
    fun `Connected Components Test`() {
        (0..10).forEach { totalConnectedComponents ->
            graph = createcomponentsGraph(totalConnectedComponents)
            val solver = ConnectedComponentsDfsSolverAdjacencyList(graph, graph.N)
            val components = solver.getComponents()
            var testComponentCounter = -1
            (0 until graph.N).forEach { node ->
                if (node % 3 == 0)
                    testComponentCounter++
                assertEquals(testComponentCounter, components[node])
            }
        }
    }

}