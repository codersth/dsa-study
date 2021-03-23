import org.junit.Test

import java.util.*

/**
 * Compare two methods for K-largest problem.
 */
class KLargestTest {
    companion object {
        private const val LEN = 100000
        private const val K = 8
        private val random = Random()
        private val ARR = Array<Int>(LEN) {position ->
            random.nextInt(LEN)
        }
    }
    /**
     * Get the k largest item with bubble sort, that is, bubble sort for the array first
     * and then get the item at position k-1.
     */
    @Test
    fun kLargestWithSort() {
        val startTime = System.currentTimeMillis()
        bubbleSort(ARR, 0, LEN - 1)
        println("${ARR[K - 1]} with time ${System.currentTimeMillis() - startTime}")
    }

    /**
     * Get the k largest item with bubble sort only for the previous k items inside of array, then make the remain
     * items in its suitable position in the sorted sub array.
     */
    @Test
    fun kLargestWithKSort() {
        val startTime = System.currentTimeMillis()
        // Bubble sort from 0 to k - 1.
        bubbleSort(ARR, 0, K - 1)
        // compare the remain items from k to the final with item at k-1.
        for(i in K until LEN) {
            // Stop when the current is lower than the k-item(position is k-1).
            if(ARR[i] < ARR[K - 1]) {
                continue
            }
            // Insert with its correct order.
            insert(ARR, ARR[i])
        }
        println("${ARR[K - 1]} with time ${System.currentTimeMillis() - startTime}")
    }

    /**
     * Insert the assigned value to descended array.
     * @param array is the array to be inserted into.
     * @param insertValue is the value to be inserted.
     */
    private fun insert(array: Array<Int>, insertValue: Int) {
        for(i in K - 1 downTo 0) {
            if(insertValue > array[i]) {
                array[i + 1] = array[i]
            } else {
                array[i + 1] = insertValue
                // No need for the next loop due to the remain items are already larger than the insert value.
                break
            }
        }
    }

    /**
     * Bubble sort algorithm to sort array with assigned range.
     * @param array is the the collection to be sorted.
     * @param start is the start position of sort range.
     * @param end is th end position of sort range.
     */
    private fun bubbleSort(array: Array<Int>, start: Int, end: Int) {
        for(i in 0 until end - start) {
            for(j in 0 until end - start - i) {
                if(array[start + j] < array[start + j + 1]) {
                    val temp = array[start + j + 1]
                    array[start + j + 1] = array[start + j]
                    array[start + j] = temp
                }
            }
        }
    }
}
