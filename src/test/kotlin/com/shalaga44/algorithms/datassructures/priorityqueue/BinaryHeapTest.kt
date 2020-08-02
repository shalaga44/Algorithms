package com.shalaga44.algorithms.datassructures.priorityqueue

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.properties.Delegates

class BinaryHeapTest {
    private lateinit var pQueue: BinaryHeap<Int>
    private var intItem by Delegates.notNull<Int>()
    private lateinit var sortedIntegerItems: List<Int>
    private lateinit var randomIntegerList: List<Int>

    @BeforeEach
    fun `Create Empty Priority Queue`() {
        pQueue = BinaryHeap()
        intItem = (0..100).random()
        sortedIntegerItems = (0..5).toList()
        randomIntegerList = (0..20).shuffled()

    }

    @Test
    fun `isEmpty should return true for empty heap`() {
        assertTrue(pQueue.isEmpty())
    }

    @Test
    fun `Adding null item should throw the IllegalArgumentException`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            pQueue.add(null)
        }
        assertEquals("Required value was null.", exception.localizedMessage)
    }

    @Test
    fun `Removing item when heap isEmpty should return false`() {
        val result = pQueue.remove(0)
        assertFalse(result)
    }

    @Test
    fun `Poll item when heap isEmpty should return null`() {
        val item = pQueue.poll()
        assertNull(item)
    }

    @Test
    fun `Add one item should peek it twice `() {
        pQueue.add(intItem)
        assertEquals(intItem, pQueue.peek())
        assertEquals(intItem, pQueue.peek())
    }

    @Test
    fun `Add one item and poll it should peek null`() {
        pQueue.add(intItem)
        assertEquals(intItem, pQueue.poll())
        assertNull(pQueue.peek())
    }

    @Test
    fun `isEmpty should return true after one add end one poll`() {
        pQueue.add(intItem)
        assertEquals(intItem, pQueue.poll())
        assertTrue(pQueue.isEmpty())
    }

    @Test
    fun `Add sorted items should in order poll them`() {
        sortedIntegerItems.forEach { item ->
            pQueue.add(item)
        }

        sortedIntegerItems.forEach { item ->
            assertEquals(item, pQueue.poll())
        }
    }

    @Test
    fun `Add shuffled items should first poll the smallest`() {
        randomIntegerList.forEach { item ->
            pQueue.add(item)
        }
        assertEquals(randomIntegerList.min(), pQueue.poll())
    }

    @Test
    fun `Add shuffled items should poll the smallest every time`() {
        randomIntegerList.forEach { item ->
            pQueue.add(item)
        }
        randomIntegerList.sorted().forEach { item ->
            assertEquals(item, pQueue.poll())
        }
    }

    @Test
    fun `Adding shuffled items then remove them should make heap empty`() {
        randomIntegerList.forEach { item ->
            pQueue.add(item)
        }
        randomIntegerList.forEach { item ->
            assertTrue(pQueue.remove(item))
        }
        assertTrue(pQueue.isEmpty())
    }

    @Test
    fun `Heap size should be incremented when adding `() {
        randomIntegerList.forEachIndexed { index, item ->
            pQueue.add(item)
            assertEquals(index + 1, pQueue.size())

        }
    }

    @Test
    fun `Heap size should be decremented when poll `() {
        randomIntegerList.forEach { item -> pQueue.add(item) }
        randomIntegerList.forEachIndexed { index, _ ->
            pQueue.poll()
            assertEquals(randomIntegerList.lastIndex - index, pQueue.size())

        }
    }

    @Test
    fun `Heap size should be decremented when removing `() {
        randomIntegerList.forEach { item -> pQueue.add(item) }
        randomIntegerList.forEachIndexed { index, item ->
            pQueue.remove(item)
            assertEquals(randomIntegerList.lastIndex - index, pQueue.size())

        }
    }

    @Test
    fun `After clearing the heap poll returns null`() {
        randomIntegerList.forEach { item -> pQueue.add(item) }
        pQueue.clear()
        assertNull(pQueue.poll())
    }
}