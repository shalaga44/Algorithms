package com.shalaga44.algorithms.datassructures.priorityqueue


class BinaryHeap<T : Comparable<T>> {
    private var heapSize = 0

    private var heapCapacity = 0

    private var heap: MutableList<T> = mutableListOf()


    fun isEmpty(): Boolean {
        return heapSize == 0
    }

    fun size(): Int {
        return heapSize
    }

    fun clear() {
        heap.clear()
        heapSize = 0
    }

    fun peek(): T? {
        return when {
            isEmpty() -> null
            else -> heap[0]
        }
    }

    fun poll(): T? {
        return removeAt(0)
    }

    operator fun contains(elem: T): Boolean {
        for (i in 0 until heapSize)
            if (heap[i] == elem)
                return true
        return false
    }

    fun add(elem: T?) {
        requireNotNull(elem)
        if (heapSize < heapCapacity) {
            heap[heapSize] = elem
        } else {
            heap.add(elem)
            heapCapacity++
        }
        swim(heapSize)
        heapSize++
    }

    private fun less(i: Int, j: Int): Boolean {
        val node1 = heap[i]
        val node2 = heap[j]
        return node1 <= node2
    }


    private fun swim(node: Int) {

        var k = node
        var parent = (k - 1) / 2

        while (k > 0 && less(k, parent)) {
            swap(parent, k)
            k = parent

            parent = (k - 1) / 2
        }
    }

    private fun sink(node: Int) {
        var k = node
        while (true) {
            val left = 2 * k + 1 // Left  node
            val right = 2 * k + 2 // Right node
            var smallest = left // Assume left is the smallest node of the two children

            // Find which is smaller left or right
            // If right is smaller set smallest to be right
            if (right < heapSize && less(right, left)) smallest = right

            // Stop if we're outside the bounds of the tree
            // or stop early if we cannot sink k anymore
            if (left >= heapSize || less(k, smallest)) break

            // Move down the tree following the smallest node
            swap(smallest, k)
            k = smallest
        }
    }

    private fun swap(i: Int, j: Int) {
        val elemI = heap[i]
        val elemJ = heap[j]
        heap[i] = elemJ
        heap[j] = elemI
    }

    fun remove(element: T?): Boolean {
        if (element == null) return false
        for (i in 0 until heapSize) {
            if (element == heap[i]) {
                removeAt(i)
                return true
            }
        }
        return false
    }

    private fun removeAt(i: Int): T? {
        if (isEmpty()) return null
        heapSize--
        val removedData = heap[i]
        swap(i, heapSize)

        heap.removeAt(heapSize)

        if (i == heapSize) return removedData
        val elem = heap[i]

        sink(i)

        if (heap[i] == elem) swim(i)
        return removedData
    }

    override fun toString(): String {
        return heap.toString()
    }
}
