package com.example.halanchallenge.utils

import com.example.halanchallenge.utils.validator.InputValidator
import org.junit.Assert
import org.junit.Test

class InputValidatorTest {

    @Test
    fun `1 - Check If Input Validator Work as we planned or Not , Give Valid Password Then Return True`() {

        val password = "12345678"

        val result = InputValidator.isPasswordValid(password)

        Assert.assertTrue(result == true)
    }

    @Test
    fun `2 - Check If Input Validator Work as we planned or Not , Give Not Valid Password Then Return True`() {

        val password = ""

        val result = InputValidator.isPasswordValid(password)

        Assert.assertTrue(result == false)
    }

    @Test
    fun `3 - Check If Input Validator Work as we planned or Not , Give Valid UserName Then Return True`() {

        val userName = "mohammedmorse"

        val result = InputValidator.isUsernameValid(userName)

        Assert.assertTrue(result == true)
    }

    @Test
    fun `4 - Check If Input Validator Work as we planned or Not , Give Not Valid UserName With Numbers Inside Then Return False`() {

        val userName = "mohammedmorse123"

        val result = InputValidator.isUsernameValid(userName)

        Assert.assertTrue(result == false)
    }

    @Test
    fun `5 - Check If Input Validator Work as we planned or Not , Give Not Valid UserName With Spaces Inside Then Return False`() {

        val userName = "mohammed morse"

        val result = InputValidator.isUsernameValid(userName)

        Assert.assertTrue(result == false)
    }

    @Test
    fun `6 - Check If Input Validator Work as we planned or Not , Give Not Valid UserName With Length Bigger Than Expected Then Return False`() {

        val userName = "mohammedmorsemorseelsayedmorsemabrok"

        val result = InputValidator.isUsernameValid(userName)

        Assert.assertTrue(result == false)
    }


    @Test
    fun `7 - Check If Input Validator Work as we planned or Not , Give Not Valid UserName With Length Lower Than Expected Then Return False`() {

        val userName = "morse"

        val result = InputValidator.isUsernameValid(userName)

        Assert.assertTrue(result == false)
    }

}