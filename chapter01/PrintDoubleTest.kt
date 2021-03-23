package com.example.myapplication.dsa.chapter01

import org.junit.Test
import java.math.BigDecimal

/**
 *
 * @author zhanglei1
 * @date 2021/3/19-11:12
 * @since V1.0.0
 */
class PrintDoubleTest {

    @Test
    fun testPrintDouble() {
        printDouble(-0.10839)
    }

    /**
     * Print every digit of a double value by method [printDigit].
     */
    private fun printDouble(value: Double) {
        var positiveValue = value
        // Print and change sign if needed.
        if(value < 0){
            print("-")
            positiveValue = -value
        }
        // Print int part recursively.
        val intValue = value.toInt()
        printInt(intValue)
        print(".")
        // Print double part recursively.
        printPositiveDouble(intValue, positiveValue)
    }

    private fun printInt(value: Int) {
        if(value >= 10) {
            printInt(value / 10)
        }
        printDigit(value % 10)
    }

    private fun printPositiveDouble(intValue: Int, value: Double) {
       if(intValue - value != 0.0) {
           val value10Times = value * 10
           print((value10Times - intValue * 10).toInt())
           printPositiveDouble(value10Times.toInt(), value10Times)
       }
    }

    private fun printDigit(number: Int) {
        print(number)
    }
}