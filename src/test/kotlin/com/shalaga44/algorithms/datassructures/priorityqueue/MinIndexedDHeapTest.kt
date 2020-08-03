package com.shalaga44.algorithms.datassructures.priorityqueue


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

data class Patient(var disease: String, var urgency: Int) : Comparable<Patient> {

    override operator fun compareTo(other: Patient) = this.urgency - other.urgency


}


class MinIndexedDHeapTest {


    private lateinit var iPQueue: MinIndexedBinaryHeap<Patient>
    private val shalaga = Patient("can not talk", 100)
    private val firstIndex = 0
    private var sortedPatientsList = generatePatients(10).toList()
    private var randomPatientsList = generatePatients(50).shuffled()


    @BeforeEach
    fun `Clear Empty Indexed Priority Queue`() {
        iPQueue = MinIndexedBinaryHeap(50)
    }

    private fun generatePatients(total: Int): List<Patient> {
        return (1..total).map { Patient("$it", it * 2) }
    }

    @Test
    fun `isEmpty should return true for empty heap`() {
        assertTrue(iPQueue.isEmpty())
    }

//    @Test
//    fun `Inserting null item should throw the IllegalArgumentException`() {
//        assertThrows(IllegalArgumentException::class.java) {
//            iPQueue.insert(firstIndex, null)
//        }
//    }

    @Test
    fun `Deleting item when heap isEmpty should throw NoSuchElementException`() {
        assertThrows(NoSuchElementException::class.java) {
            iPQueue.delete(firstIndex)
        }
    }

    @Test
    fun `PollMinValue item when heap isEmpty should throw NoSuchElementException`() {
        assertThrows(NoSuchElementException::class.java) {
            iPQueue.pollMinValue()
        }

    }

    @Test
    fun `pollMinKeyIndex item when heap isEmpty() should throw NoSuchElementException`() {
        assertThrows(NoSuchElementException::class.java) {
            iPQueue.pollMinKeyIndex()
        }

    }

    @Test
    fun `PeekMinValue item when heap isEmpty should throw NoSuchElementException`() {
        assertThrows(NoSuchElementException::class.java) {
            iPQueue.peekMinValue()
        }

    }

    @Test
    fun `PeekMinKeyIndex item when heap isEmpty should throw NoSuchElementException`() {
        assertThrows(NoSuchElementException::class.java) {
            iPQueue.peekMinKeyIndex()
        }

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
        assertTrue(iPQueue.isEmpty())
    }

    @Test
    fun `isEmpty should return false after one insert`() {
        iPQueue.insert(firstIndex, shalaga)
        assertFalse(iPQueue.isEmpty())
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
        assertTrue(iPQueue.isEmpty())
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

    @Test
    fun `Update item to smallest should peek it`() {
        sortedPatientsList.forEachIndexed { index, item ->
            iPQueue.insert(index, item)
        }
        assertEquals(sortedPatientsList.min(), iPQueue.peekMinValue())

        val newMinPatientKey = sortedPatientsList.indexOf(sortedPatientsList.min())
        val newMinPatient = sortedPatientsList.min()!!.copy()

        newMinPatient.urgency--
        iPQueue.update(newMinPatientKey, newMinPatient)

        assertEquals(newMinPatient, iPQueue.peekMinValue())
    }

    @Test
    fun `Increase update item to smaller should not update it`() {
        sortedPatientsList.forEachIndexed { index, item ->
            iPQueue.insert(index, item)
        }
        assertEquals(sortedPatientsList.min(), iPQueue.peekMinValue())

        val newMinPatientKey = sortedPatientsList.indexOf(sortedPatientsList.min())
        val newMinPatient = sortedPatientsList.min()!!.copy()

        newMinPatient.urgency--
        iPQueue.increaseKey(newMinPatientKey, newMinPatient)

        assertNotEquals(newMinPatient, iPQueue.valueOf(newMinPatientKey))

        assertNotEquals(newMinPatient, iPQueue.peekMinValue())
    }

    @Test
    fun `Increase update item to bigger should  update it`() {
        sortedPatientsList.forEachIndexed { index, item ->
            iPQueue.insert(index, item)
        }
        assertEquals(sortedPatientsList.min(), iPQueue.peekMinValue())

        val newMinPatientKey = sortedPatientsList.indexOf(sortedPatientsList.min())
        val newMinPatient = sortedPatientsList.min()!!.copy()

        newMinPatient.urgency++
        iPQueue.increaseKey(newMinPatientKey, newMinPatient)

        assertEquals(newMinPatient, iPQueue.valueOf(newMinPatientKey))

        assertEquals(newMinPatient, iPQueue.peekMinValue())
    }


    @Test
    fun `Decrease update item to bigger should not update it`() {
        sortedPatientsList.forEachIndexed { index, item ->
            iPQueue.insert(index, item)
        }
        assertEquals(sortedPatientsList.min(), iPQueue.peekMinValue())

        val newMinPatientKey = sortedPatientsList.indexOf(sortedPatientsList.min())
        val newMinPatient = sortedPatientsList.min()!!.copy()

        newMinPatient.urgency++
        iPQueue.decreaseKey(newMinPatientKey, newMinPatient)

        assertNotEquals(newMinPatient, iPQueue.valueOf(newMinPatientKey))

        assertNotEquals(newMinPatient, iPQueue.peekMinValue())

    }

    @Test
    fun `Decrease update item to smaller should  update it`() {
        sortedPatientsList.forEachIndexed { index, item ->
            iPQueue.insert(index, item)
        }
        assertEquals(sortedPatientsList.min(), iPQueue.peekMinValue())

        val newMinPatientKey = sortedPatientsList.indexOf(sortedPatientsList.min())
        val newMinPatient = sortedPatientsList.min()!!.copy()

        newMinPatient.urgency--
        iPQueue.decreaseKey(newMinPatientKey, newMinPatient)

        assertEquals(newMinPatient, iPQueue.valueOf(newMinPatientKey))

        assertEquals(newMinPatient, iPQueue.peekMinValue())

    }

    @Test
    fun `Delete smallest item should not peek it`() {

        sortedPatientsList.forEachIndexed { index, item ->
            iPQueue.insert(index, item)
        }
        assertEquals(sortedPatientsList.min(), iPQueue.peekMinValue())

        val newMinPatientKey = sortedPatientsList.indexOf(sortedPatientsList.min())
        val newMinPatient = sortedPatientsList.min()!!.copy()

        iPQueue.delete(newMinPatientKey)

        assertNotEquals(newMinPatient, iPQueue.peekMinValue())

    }

    @Test
    fun `Recursively checks if this heap is a min heap`() {
        sortedPatientsList.forEachIndexed { index, item ->
            iPQueue.insert(index, item)
        }
        assertTrue(iPQueue.isMinHeap())

        sortedPatientsList.indices.forEach { index ->
            iPQueue.delete(index)
        }
        assertTrue(iPQueue.isMinHeap())

    }


}
