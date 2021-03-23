package com.example.myapplication.dsa.chapter01

import org.junit.Test

/**
 * @author zhanglei1
 * @date 2021/3/19-16:33
 * @since V1.0.0
 */
class NumberOfBinaryTest {

    @Test
    fun testGetOneCount() {
        print(getOneCount(17))
//        print(8 shr 4)
    }
    /**
     * Output the count of "1" from N's binary.
     * @param value is the number to be calculated.
     * @param sum of "1" performed by bit.
     */
    private fun getOneCount(value: Int): Int {
        if(value != 0) {
            return (value and 1) + getOneCount(value shr 1)
        } else {
            return 0
        }
    }
}