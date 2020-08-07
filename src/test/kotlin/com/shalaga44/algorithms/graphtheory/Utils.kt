package com.shalaga44.algorithms.graphtheory

import com.shalaga44.algorithms.graphtheory.Utils.DataTypes.WeightedDirectedAdjacencyListGraph

     fun createFullyConnectedGraph(): WeightedDirectedAdjacencyListGraph {
         val graph = WeightedDirectedAdjacencyListGraph()

        graph.addDirectedEdge(0, 1, 4)
        graph.addDirectedEdge(0, 2, 5)
        graph.addDirectedEdge(1, 2, -2)
        graph.addDirectedEdge(1, 3, 6)
        graph.addDirectedEdge(2, 3, 1)
        graph.addDirectedEdge(2, 2, 10) // Self loop


        return graph
    }


