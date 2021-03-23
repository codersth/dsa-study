package com.example.myapplication.dsa.chapter01

import org.junit.Test

/**
 *
 * @author zhanglei1
 * @date 2021/3/23-14:56
 * @since V1.0.0
 */
class FindRectangleTest {

    @Test
    fun main() {
        val array = arrayOf(
            Rectangle(3, 5),
            Rectangle(6, 2),
            Rectangle(9, 1)
        )
        println("max area:" + findMax(array, AreaComparator()))
        println("max circumstance:" + findMax(array, CircumstanceComparator()))
    }

    /**
     * Find the max from array with rule in comparator.
     */
    fun findMax(array: Array<Rectangle>, cmp: Comparator<Rectangle>): Rectangle? {
        if(array.isEmpty()) {
            return null
        }
        var maxIndex = 0
        for(i in array.indices) {
            if(cmp.compare(array[i], array[maxIndex]) > 0) {
                maxIndex = i
            }
        }
        return array[maxIndex]
    }
}

/**
 * Rectangle with only length and width.
 */
data class Rectangle(private val length: Int, private val width: Int) {

    fun getLength(): Int = length

    fun getWidth(): Int = width
}

/**
 * Compare two rectangles with area.
 */
class AreaComparator: Comparator<Rectangle> {
    override fun compare(lhs: Rectangle, rhs: Rectangle): Int {
        return lhs.getLength() * lhs.getWidth() - rhs.getLength() * rhs.getWidth()
    }
}

/**
 * Compare two rectangles with circumstance.
 */
class CircumstanceComparator: Comparator<Rectangle> {
    override fun compare(lhs: Rectangle, rhs: Rectangle): Int {
        return (lhs.getLength() + lhs.getWidth()) - (rhs.getLength() + rhs.getWidth())
    }
}

/**
 * An interface for comparing two values.
 */
interface Comparator<T> {
    fun compare(lhs: T, rhs: T): Int
}