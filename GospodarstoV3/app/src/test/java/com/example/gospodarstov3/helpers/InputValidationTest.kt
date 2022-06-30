package com.example.gospodarstov3.helpers

import junit.framework.TestCase

class InputValidationTest : TestCase() {
    val g="cos"
    val e = "m@wp.pl"
    fun testIsInputEditTextFilled() {
        val value =g
        value.isEmpty()

         }



    fun testIsInputEditTextMatches() {
        val value1 = g
        val value2 = e
        value1.contentEquals(value2)
    }
}