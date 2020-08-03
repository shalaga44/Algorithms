package com.shalaga44.algorithms.datassructures.priorityqueue

import org.jetbrains.annotations.TestOnly
import kotlin.math.max
import kotlin.math.min

open class MinIndexedDHeap<T : Comparable<T>>(degree: Int, maxSize: Int) {
    private var currentSize = 0

    private val maximumSize: Int

    private val nodesDegree: Int

    // Lookup arrays to track the child/parent indexes of each node.
    private val child: IntArray
    private val parent: IntArray

    // The Position Map (positionMap) maps Key Indexes (ki) to where the position of that
    // key is represented in the priority queue in the domain [0, currentSize).
    private val positionMap: IntArray

    // The Inverse Map (im) stores the indexes of the keys in the range
    // [0, currentSize) which make up the priority queue. It should be noted that
    // 'im' and 'positionMap' are inverses of each other, so: positionMap[im[i]] = im[positionMap[i]] = i
    private val inverseMap: IntArray

    // The values associated with the keys. It is very important  to note
    // that this array is indexed by the key indexes (aka 'ki').
    private val values: ArrayList<T?>
    fun size(): Int {
        return currentSize
    }

    fun isEmpty(): Boolean {
        return currentSize == 0

    }

    operator fun contains(ki: Int): Boolean {
        keyInBoundsOrThrow(ki)
        return positionMap[ki] != -1
    }

    fun peekMinKeyIndex(): Int {
        isNotEmptyOrThrow()
        return inverseMap[0]
    }

    fun pollMinKeyIndex(): Int {
        val minKi = peekMinKeyIndex()
        delete(minKi)
        return minKi
    }


    fun peekMinValue(): T {
        isNotEmptyOrThrow()
        return values[inverseMap[0]]!!
    }

    fun pollMinValue(): T {
        val minValue = peekMinValue()
        delete(peekMinKeyIndex())
        return minValue
    }

    fun insert(ki: Int, value: T) {
        require(!contains(ki)) { "index already exists; received: $ki" }
        positionMap[ki] = currentSize
        inverseMap[currentSize] = ki
        values.add(value)
        swim(currentSize++)
    }


    fun valueOf(ki: Int): T? {
        keyExistsOrThrow(ki)
        return values[ki]
    }


    fun delete(ki: Int): T {
        keyExistsOrThrow(ki)
        val i = positionMap[ki]
        swap(i, --currentSize)
        sink(i)
        swim(i)
        val deletedValue = values[ki]
        values[ki] = null
        positionMap[ki] = -1
        inverseMap[currentSize] = -1
        return deletedValue!!
    }

    fun update(ki: Int, value: T): T {
        keyExistsOrThrow(ki)
        val i = positionMap[ki]
        val oldValue = values[ki]
        values[ki] = value
        sink(i)
        swim(i)
        return oldValue!!
    }

    fun decreaseKey(ki: Int, value: T) {
        keyExistsOrThrow(ki)
        if (less(value, values[ki])) {
            values[ki] = value
            swim(positionMap[ki])
        }
    }

    fun increaseKey(ki: Int, value: T) {
        keyExistsOrThrow(ki)
        if (less(values[ki], value)) {
            values[ki] = value
            sink(positionMap[ki])
        }
    }

    private fun sink(i: Int) {
        var node = i
        var j = minChild(node)
        while (j != -1) {
            swap(node, j)
            node = j
            j = minChild(j)
        }
    }

    private fun swim(i: Int) {
        var node = i
        while (less(node, parent[node])) {
            swap(node, parent[node])
            node = parent[node]
        }
    }

    private fun minChild(i: Int): Int {
        var node = i
        var index = -1
        val from = child[node]
        val to = min(currentSize, from + nodesDegree)
        for (j in from until to) if (less(j, node)) {
            node = j
            index = node
        }
        return index
    }

    private fun swap(i: Int, j: Int) {
        positionMap[inverseMap[j]] = i
        positionMap[inverseMap[i]] = j
        val tmp = inverseMap[i]
        inverseMap[i] = inverseMap[j]
        inverseMap[j] = tmp
    }

    private fun less(i: Int, j: Int): Boolean {
        return values[inverseMap[i]]!! < (values[inverseMap[j]])!!
    }


    private fun less(obj1: T?, obj2: T?): Boolean {
        return obj1!! < obj2!!
    }

    override fun toString(): String {
        val lst: MutableList<Int> = ArrayList(currentSize)
        for (i in 0 until currentSize) lst.add(inverseMap[i])
        return lst.toString()
    }

    private fun isNotEmptyOrThrow() {
        if (isEmpty()) throw NoSuchElementException("Priority queue underflow")
    }


    private fun keyExistsOrThrow(ki: Int) {
        if (!contains(ki)) throw java.util.NoSuchElementException("Index does not exist; received: $ki")
    }


    private fun keyInBoundsOrThrow(ki: Int) {
        require(!(ki < 0 || ki >= maximumSize)) { "Key index out of bounds; received: $ki" }
    }

    @TestOnly
    fun isMinHeap(i: Int = 0): Boolean {
        val from = child[i]
        val to = min(currentSize, from + nodesDegree)
        for (j in from until to) {
            if (!less(i, j)) return false
            if (!isMinHeap(j)) return false
        }
        return true
    }

    init {
        require(maxSize > 0) { "maxSize <= 0" }
        nodesDegree = max(2, degree)
        maximumSize = max((nodesDegree + 1), maxSize)
        inverseMap = IntArray(maximumSize) { -1 }
        positionMap = IntArray(maximumSize) { -1 }
        child = IntArray(maximumSize) { it * nodesDegree + 1 }
        parent = IntArray(maximumSize) { (it - 1) / nodesDegree }
        values = ArrayList(maximumSize)
    }
}



