package com.example.myapplication.dsa

import org.junit.Test

/**
 *
 * Combine with Fibonacci to understand 4 designing principles about recursion.
 * @author zhanglei1
 * @date 2021/3/11-17:07
 * @since V1.0.0
 */
class Recursion4Principles {

    @Test
    fun testFibonacci() {
        print("${fibonacciPlus(10)}")
    }

    /**
     * Retrieve Fibonacci result by recursion.
     * F(0)=0，F(1)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 2，n ∈ N*）
     */
    private fun fibonacci(n: Int): Int {
        // Ignore parameter checking to ensure n >= 2.
        // Decide the basic point.
        return when(n) {
            0 -> 0
            1 -> 1
            else -> fibonacci(n - 1) + fibonacci(n - 2)
        }
    }

    /**
     * Retrieve Fibonacci result by eliminating repeated works in recursion.
     * F(0)=0，F(1)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 2，n ∈ N*）
     */
    private fun fibonacciPlus(n: Int): Int {
        // Ignore parameter checking to ensure n >= 2.
        // Declare an array to store Fibonacci numbers.
        val storeArray = Array<Int>(n + 2) { it }
        storeArray[0] = 0
        storeArray[1] = 1
        for(i in 2 .. n) {
            //Add the previous 2 numbers in the series and store it.
            storeArray[i] = storeArray[i - 1] + storeArray[i - 2]
        }
        return storeArray[n]
    }
}