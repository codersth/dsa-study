package com.example.myapplication.dsa

import org.junit.Test
import java.io.*
import java.lang.IllegalArgumentException
import java.lang.StringBuilder
import java.nio.charset.StandardCharsets

/**
 * Copy content from file to another who references it with keyword "include".
 * @author zhanglei1
 * @date 2021/3/19-14:37
 * @since V1.0.0
 */
class IncludeTest {

    companion object {
        private const val KEYWORD_INCLUDE = "#include"
        private const val KEYWORD_REGEX = "^[\\s]*$KEYWORD_INCLUDE[\\s]+[\\w]+$"

        /**
         * Invalid filenames.
         */
        private val INVALID_FILES = arrayOf("file1", "file2", "file3", "file4")

        private val FILE_DIRECTORY = "${System.getProperty("user.dir")}\\src\\test\\java\\com\\example\\myapplication\\dsa\\res"
    }

    @Test
    fun testRegex() {
//        print("#include aaAaa".matches(KEYWORD.toRegex()))
//        this.javaClass.classLoader?.getResourceAsStream("res/file1")
        print(processFile("file1"))
    }

    /**
     * Process file's include recursively.
     * @return file content with included file's content.
     */
    private fun processFile(filename: String): String {
        // If file is invalid, that is not in valid files.
        if(filename !in INVALID_FILES) {
            throw IllegalArgumentException("file not valid with name $filename")
        }
        // If file not exists, print error message and throw an exception.
        val file = File(FILE_DIRECTORY, filename)
        if(!file.exists()) {
            throw FileNotFoundException("file not foud with path $file")
        }
        // Read file content and process referenced file if needed.
        val fileReader = BufferedReader(InputStreamReader(file.inputStream(), StandardCharsets.UTF_8))
        val fileContent = StringBuilder()
        while (true) {
            val line = fileReader.readLine() ?: break
            // Hit include keyword!!!
            if(line.matches(KEYWORD_REGEX.toRegex())) {
                // Get filename after the include.
                val includeFileName = getFileName(line)
                // File is not self-referential.
                if(includeFileName.isNotEmpty() && includeFileName != filename) {
                    fileContent.append(processFile(includeFileName)).append("\n")
                }
            } else {
                fileContent.append(line).append("\n")
            }
        }
        // Simply close
        fileReader.close()
        return fileContent.toString()
    }

    private fun getFileName(includeLine: String): String {
        return includeLine.replace(KEYWORD_INCLUDE, "").trim()
    }
}