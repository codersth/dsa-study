package com.example.myapplication.dsa.chapter01

import org.junit.Test

/**
 *
 * @author zhanglei1
 * @date 2021/3/22-16:50
 * @since V1.0.0
 */
class ArrangeCharsTest {

    @Test
    fun testPermute() {
        permute("abc")
    }

    private fun permute(str: String) {
        permute(str.toCharArray(), 0, str.length - 1)
    }

    /**
     * Permute array from range of [low] to [high] recursively.
     * @param array to be permuted.
     * @param low is the start of permute range.
     * @param high is the end of permute range.
     */
    private fun permute(array: CharArray, low: Int, high: Int) {
        if(low == high) {
            println(array.contentToString())
            return
        }
        for(i in low .. high) {
            swap(array, low, i)
            // Permute from the next position.
            permute(array, low + 1, high)
            // Restore array to the original against previous swap.
            swap(array, i, low)
        }
    }

    /**
     * Exchange in array between value in [pos1] and [pos2]
     */
    private fun swap(array: CharArray, pos1: Int, pos2: Int) {
        if(pos1 == pos2) {
            return
        }
        val temp = array[pos1]
        array[pos1] = array[pos2]
        array[pos2] = temp
    }
}