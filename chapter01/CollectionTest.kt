package com.example.myapplication.dsa.chapter01

import org.junit.Test

/**
 * @author zhanglei1
 * @date 2021/3/23-13:08
 * @since V1.0.0
 */
class CollectionTest {

    @Test
    fun testMyCollections() {
        val coll = MyCollection<Int?>()
        coll.insert(3)
        coll.insert(null)
        coll.insert(7)
        println(coll)
        println(coll.isEmpty())
        println(coll.isPresent(3))
        println(coll.isPresent(null))
        coll.remove(7)
        println(coll)
        coll.makeEmpty()
        println(coll)
    }
}

/**
 * Simulate ADT of collection.
 */
class MyCollection<T> {

    companion object {
        /**
         * Default size of initial array.
         */
        private const val INIT_SIZE = 10

        /**
         * Space for array increment.
         */
        private const val INCREMENT_SPACE = 5
    }
    /**
     * Array contains data with type Any.
     */
    private var elements: Array<Any?> = arrayOfNulls<Any>(INIT_SIZE)

    /**
     * Actual number of elements in collection.
     */
    private var size = 0

    /**
     * Check if collection is empty or not.
     */
    fun isEmpty(): Boolean{
        return size == 0
    }

    /**
     * Clear all elements from collection.
     */
    fun makeEmpty() {
        for(i in 0 until size) {
            elements[i] = null
        }
        size = 0
    }

    /**
     * Insert element to collection.Note that expand array if its space not enough.
     */
    fun insert(element: T?){
        if(size >= elements.size) {
            elements = elements.copyOf(size + INCREMENT_SPACE)
        }
        elements[size] = element
        size ++
    }

    /**
     * Remove element from collection even having multiple same values.
     */
    fun remove(element: T?) {
        if(isEmpty()) {
            return
        }
        // Create a new array to receive the values not equals the removing element.
        val newArray = arrayOfNulls<Any?>(elements.size)
        var pointer = 0
        elements.forEach {
            if(it != element) {
                newArray[pointer ++ ] = it
            }
        }
        // Clear unnecessary references.
        makeEmpty()
        elements = newArray
        size = pointer + 1
    }

    /**
     * Detect whether collection contains the assigned element.
     * @param element to be checked.
     * @return true if collection contains, otherwise not.
     */
    fun isPresent(element: T?): Boolean {
        elements.forEach {
            // Different from java or other language.
            if(element == it) {
                return true
            }
        }
        return false
    }

    override fun toString(): String {
        return "MyCollection(elements=${elements.contentToString()})"
    }


}