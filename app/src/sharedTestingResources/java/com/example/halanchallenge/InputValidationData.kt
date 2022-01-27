package com.example.halanchallenge

object InputValidationData {

   object Password {

       val ValidPassword = "12345678"

       val NotValidPassword = ""

   }

    object UserName {

        val ValidUserName ="mohammedmorse"

        val ValidUserNameWithNumbers ="mohammedmorse123"

        val NotValidUserNameExceedLength = "mohammedmorsemorseelsayedmorsemabrok"

        val NotValidUserNameLowestLength = "morse"

        val NotValidUserNameWithSpecialCharacters = "mohammed morse @123"

    }

}