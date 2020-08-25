package com.shalaga44.algorithms.graphtheory

import com.shalaga44.algorithms.graphtheory.Utils.DataTypes.UnweightedDirectedAdjacencyListGraph
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test



class BridgesAdjacencyListIterativeTests {

    @Test
    fun `simple test findBridges`() {
        val graph = UnweightedDirectedAdjacencyListGraph()
        graph.addUndirectedEdge(0, 1)
        graph.addUndirectedEdge(0, 2)
        graph.addUndirectedEdge(1, 2)
        graph.addUndirectedEdge(2, 3)
        graph.addUndirectedEdge(3, 4)
        graph.addUndirectedEdge(2, 5)
        graph.addUndirectedEdge(5, 6)
        graph.addUndirectedEdge(6, 7)
        graph.addUndirectedEdge(7, 8)
        graph.addUndirectedEdge(8, 5)
        val testActualBridges = listOf(4 to 3, 2 to 3, 2 to 5)
        val solver = BridgesAdjacencyListIterative(graph.asAdjacencyList(), graph.N)
        val bridges = solver.findBridges()
        testActualBridges.forEach { actualBridge ->
            assertTrue(
                actualBridge.reversed in bridges ||
                        actualBridge in bridges

            )

        }

    }
    private val <A, B> Pair<A, B>.reversed get() = this.second to this.first

}