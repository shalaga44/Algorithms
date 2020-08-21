package com.shalaga44.algorithms.graphtheory

import com.shalaga44.algorithms.graphtheory.Utils.DataTypes.WeightedEdge

class ConnectedComponentsDfsSolverAdjacencyList(
    private val graph: Map<Int, List<WeightedEdge>>,
    private val totalNodes: Int
) {
    fun countComponents(): Int {
        return 1
    }

    fun getComponents(): IntArray {

        return IntArray(totalNodes) { 1 }
    }

}
