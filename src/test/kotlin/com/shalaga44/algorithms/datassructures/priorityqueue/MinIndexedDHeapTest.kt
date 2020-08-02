package com.shalaga44.algorithms.datassructures.priorityqueue


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

data class Patient(var disease: String, var urgency: Int) : Comparable<Patient> {

    override operator fun compareTo(other: Patient) = this.urgency - other.urgency


}


class MinIndexedDHeapTest {

    private val ipqIsEmptyMessage = "Priority queue underflow"

    private lateinit var iPQueue: MinIndexedBinaryHeap<Patient>
    private val shalaga = Patient("can not talk", 100)
    private val firstIndex = 0
    private var sortedPatientsList = generatePatients(10).toList()
    private var randomPatientsList = generatePatients(10).shuffled()


    @BeforeEach
    fun `Clear Empty Indexed Priority Queue`() {
        iPQueue = MinIndexedBinaryHeap(50)
    }

    private fun generatePatients(total: Int): List<Patient> {
        return (1..total).map { Patient("$it", it * 2) }
    }

    @Test
    fun `isEmpty should return true for empty heap`() {
        assertTrue(iPQueue.isEmpty)
    }

    @Test
    fun `Inserting null item should throw the IllegalArgumentException`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            iPQueue.insert(firstIndex, null)
        }
        assertEquals("value cannot be null", exception.localizedMessage)
    }

    @Test
    fun `Deleting item when heap isEmpty should throw NoSuchElementException`() {
        val exception = assertThrows(NoSuchElementException::class.java) {
            iPQueue.delete(firstIndex)
        }
        assertEquals("Index does not exist; received: $firstIndex", exception.localizedMessage)
    }

    @Test
    fun `PollMinValue item when heap isEmpty should throw NoSuchElementException`() {
        val exception = assertThrows(NoSuchElementException::class.java) {
            iPQueue.pollMinValue()
        }
        assertEquals(ipqIsEmptyMessage, exception.localizedMessage)

    }

    @Test
    fun `pollMinKeyIndex item when heap isEmpty should throw NoSuchElementException`() {
        val exception = assertThrows(NoSuchElementException::class.java) {
            iPQueue.pollMinKeyIndex()
        }
        assertEquals(ipqIsEmptyMessage, exception.localizedMessage)

    }

    @Test
    fun `PeekMinValue item when heap isEmpty should throw NoSuchElementException`() {
        val exception = assertThrows(NoSuchElementException::class.java) {
            iPQueue.peekMinValue()
        }
        assertEquals(ipqIsEmptyMessage, exception.localizedMessage)

    }

    @Test
    fun `PeekMinKeyIndex item when heap isEmpty should throw NoSuchElementException`() {
        val exception = assertThrows(NoSuchElementException::class.java) {
            iPQueue.peekMinKeyIndex()
        }
        assertEquals(ipqIsEmptyMessage, exception.localizedMessage)

    }

    @Test
    fun `Patient compareTo test`() {
        val shalaga = Patient("can not talk", 0)
        val mo = Patient("poison", 10)
        assertFalse(shalaga > mo)
    }

    @Test
    fun `Insert one item should peekMinValue it twice `() {
        iPQueue.insert(firstIndex, shalaga)
        assertEquals(shalaga, iPQueue.peekMinValue())
        assertEquals(shalaga, iPQueue.peekMinValue())
    }

    @Test
    fun `Insert one item should peekMinKeyIndex it twice `() {
        iPQueue.insert(firstIndex, shalaga)
        assertEquals(firstIndex, iPQueue.peekMinKeyIndex())
        assertEquals(firstIndex, iPQueue.peekMinKeyIndex())
    }

    @Test
    fun `insert one item and poll it should throw NoSuchElementException`() {
        iPQueue.insert(firstIndex, shalaga)
        assertEquals(shalaga, iPQueue.pollMinValue())
        assertThrows(NoSuchElementException::class.java) {
            iPQueue.pollMinValue()
        }
    }

    @Test
    fun `isEmpty should return true after one insert & one poll`() {
        iPQueue.insert(firstIndex, shalaga)
        assertEquals(shalaga, iPQueue.pollMinValue())
        assertTrue(iPQueue.isEmpty)
    }

    @Test
    fun `isEmpty should return false after one insert`() {
        iPQueue.insert(firstIndex, shalaga)
        assertFalse(iPQueue.isEmpty)
    }

    @Test
    fun `Insert sorted items should in order pollMinValue them`() {
        sortedPatientsList.forEachIndexed { index, item ->
            iPQueue.insert(index, item)
        }

        sortedPatientsList.forEach { item ->
            assertEquals(item, iPQueue.pollMinValue())
        }
    }

    @Test
    fun `Insert sorted items should in order pollMinKeyIndex them`() {
        sortedPatientsList.forEachIndexed { index, item ->
            iPQueue.insert(index, item)
        }

        sortedPatientsList.indices.forEach { index ->
            assertEquals(index, iPQueue.pollMinKeyIndex())
        }
    }

    @Test
    fun `Insert shuffled items should first pollMinValue the smallest`() {
        randomPatientsList.forEachIndexed { index, item ->
            iPQueue.insert(index, item)
        }
        assertEquals(randomPatientsList.min(), iPQueue.pollMinValue())
    }

    @Test
    fun `Insert shuffled items should first pollMinKeyIndex the smallest`() {
        randomPatientsList.forEachIndexed { index, item ->
            iPQueue.insert(index, item)
        }
        assertEquals(
            randomPatientsList.indexOf(randomPatientsList.min()),
            iPQueue.pollMinKeyIndex()
        )
    }

    @Test
    fun `insert shuffled items should pollMinValue the smallest every time`() {
        randomPatientsList.forEachIndexed { index, item ->
            iPQueue.insert(index, item)
        }
        randomPatientsList.sorted().forEach { item ->
            assertEquals(item, iPQueue.pollMinValue())
        }
    }

    @Test
    fun `insert shuffled items should pollMinKeyIndex the smallest every time`() {
        randomPatientsList.forEachIndexed { index, item ->
            iPQueue.insert(index, item)
        }
        randomPatientsList.sorted().forEach { item ->
            assertEquals(
                randomPatientsList.indexOf(item),
                iPQueue.pollMinKeyIndex()
            )
        }
    }

    @Test
    fun `inserting shuffled items then delete them should make heap empty`() {
        randomPatientsList.forEachIndexed { index, item ->
            iPQueue.insert(index, item)
        }
        randomPatientsList.indices.forEach { index ->
            iPQueue.delete(index)
        }
        assertTrue(iPQueue.isEmpty)
    }

    @Test
    fun `Heap size should be incremented when inserting `() {
        randomPatientsList.forEachIndexed { index, item ->
            iPQueue.insert(index, item)
            assertEquals(index + 1, iPQueue.size())

        }
    }

    @Test
    fun `Heap size should be decremented when pollMinValue `() {
        randomPatientsList.forEachIndexed { index, item -> iPQueue.insert(index, item) }
        randomPatientsList.forEachIndexed { index, _ ->
            iPQueue.pollMinValue()
            assertEquals(randomPatientsList.lastIndex - index, iPQueue.size())

        }
    }

    @Test
    fun `Heap size should be decremented when pollMinKeyIndex `() {
        randomPatientsList.forEachIndexed { index, item -> iPQueue.insert(index, item) }
        randomPatientsList.forEachIndexed { index, _ ->
            iPQueue.pollMinKeyIndex()
            assertEquals(randomPatientsList.lastIndex - index, iPQueue.size())

        }
    }

    @Test
    fun `Heap size should be decremented when delete `() {
        randomPatientsList.forEachIndexed { index, item -> iPQueue.insert(index, item) }
        randomPatientsList.indices.forEach { index ->
            iPQueue.delete(index)
            assertEquals(randomPatientsList.lastIndex - index, iPQueue.size())

        }
    }

    @Suppress("NonAsciiCharacters")
    @Test
    fun `Update item to smallest should peek it`() {
        val شلاقه = Patient("ما بيقدر يكلم", -5)
        val shalagaKey = 0
        val محي_الدين = Patient("زهج بس", -1)
        val mohiaoKey = 1
        val شيخو = Patient("عدم موضوع", 0)
        val moKey = 2
        val ود_سيف = Patient("شامي الوضع", -3)
        val saifKey = 3
        iPQueue.insert(shalagaKey, شلاقه)
        iPQueue.insert(mohiaoKey, محي_الدين)
        iPQueue.insert(moKey, شيخو)
        iPQueue.insert(saifKey, ود_سيف)

        assertEquals(شلاقه, iPQueue.peekMinValue())
        محي_الدين.disease = "منفسن"
        محي_الدين.urgency = -10
        iPQueue.update(mohiaoKey, محي_الدين)

        assertEquals(محي_الدين, iPQueue.peekMinValue())

    }


}
