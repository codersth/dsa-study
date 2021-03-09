package com.example.myapplication.dsa

import org.junit.Test
import java.lang.StringBuilder
import kotlin.math.max

/**
 * Find out all word from 2d array made up of several single letters.
 * @author zhanglei1
 * @date 2021/3/8-17:51
 * @since V1.0.0
 */
class WordRiddleTest {

    companion object {
        /**
         * List as English word dictionary.
         */
        private val DICTIONARY = arrayOf("this", "two", "fat", "that")

        /**
         * 2d array named puzzle board.
         */
        private val PUZZLE_BOARD = arrayOf(
            arrayOf("t", "h", "i", "s"),
            arrayOf("w", "a", "t", "s"),
            arrayOf("o", "a", "h", "g"),
            arrayOf("f", "g", "d", "t")
        )
    }

    /**
     * Method1: For every direction of every letter inside of [PUZZLE_BOARD], detect whether the directed line contains
     * invalid word in [DICTIONARY].If word recognized, print its location with rows, cols, direction and letter count.
     */
    @Test
    fun detectWords() {
        // Loop puzzle board by rows.
        PUZZLE_BOARD.forEachIndexed { rows, rowsItems ->
            // Loop the puzzle board by cols.
            rowsItems.forEachIndexed { cols, _ ->
                // Loop by directions.
                Direction.values().forEachIndexed { _, direction ->
                    // Retrieve all string by rows, cols, and directions.
                    val suspiciousWords = getSuspiciousWords(PUZZLE_BOARD, rows, cols, direction)
                    // Loop the retrieved list
                    suspiciousWords?.forEachIndexed { _, s ->
                        // If the string is a valid word, print word and its positions.
                        if(isValidWord(s)) {
                            print(s, rows, cols, direction)
                        }
                    }
                }
            }
        }
    }

    /**
     *  For every direction of every letter inside of [PUZZLE_BOARD], retrieve all string on the the directed line with
     *  start point fixed.
     *  @param rows is rows of start point.
     *  @param cols is cols of start point.
     *  @param direction is the loop direction to find out words.
     *  @return list contains suspicious words, null or empty list if no string found out.
     */
    private fun getSuspiciousWords(array: Array<Array<String>>, rows: Int, cols: Int, direction: Direction): List<String>? {
        // return null if rows and cols exceed the range of array.
        if(array.isEmpty()) {
            return null
        }
        val arrayRows = array.size
        val arrayCols = array[0].size
        if(rows < 0 || rows >= arrayRows || cols < 0 || cols >= arrayCols) {
            return null
        }
        val resultList = ArrayList<String>()
        // To the assigned direction, add the positions string to the result list.
        var currentRows = rows
        var currentCols = cols
        // Add the first letter as well as the start point.
        val sb = StringBuilder(array[currentRows][currentCols])
        resultList.add(sb.toString())
        while (true) {
            currentRows += direction.yStep
            currentCols += direction.xStep
            // Check range!
            if(currentRows < 0 || currentRows >= arrayRows || currentCols < 0 || currentCols >= arrayCols) {
                break
            } else {
                resultList.add(sb.append(array[currentRows][currentCols]).toString())
            }
        }
        return resultList
    }

    /**
     * Detect whether a string in a valid word.
     */
    private fun isValidWord(string: String): Boolean {
        return DICTIONARY.contains(string)
    }

    /**
     * Define output styles for the assigned positions.
     */
    private fun print(word: String, rows: Int, cols: Int, direction: Direction) {
        var endRows = rows
        var endCols = cols
        for(i in word.indices - 1) {
            endRows += direction.yStep
            endCols += direction.xStep
        }
        println("$word from ($rows, $cols) to ($endRows, $endCols)")
    }

    /**
     * All directions for finding out.
     * @param xStep is the offset when moving on x-axis.
     * @param yStep is the offset when moving on y-axis.
     */
    enum class Direction(val xStep: Int, val yStep: Int) {
        Left(-1, 0),
        Top(0, -1),
        Right(1, 0),
        Bottom(0, 1),
        TopLeft(-1, -1),
        TopRight(-1, 1),
        BottomLeft(1, -1),
        BottomRight(1, 1)
    }
}