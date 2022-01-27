package com.example.halanchallenge

import com.example.halanchallenge.domain.entities.error.ErrorResponse
import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.entities.login.LoginResponse

object LoginData {

    object Input {

        val ValidCriteria = LoginRequest(
            username = "mohammedmorse",
            password = "123456789"
        )

        val NotNameValidCriteria = LoginRequest(
            username = "morse", // UserName Less than 6 Numbers  [ 6 , 15 ]
            password = "123456789"
        )

        val NotPasswordValidCriteria = LoginRequest(
            username = "mohammedmorse",
            password = ""
        )

        val BothUsernameAndPasswordNotValid = LoginRequest("null", "")

    }

    object Output {

        val SuccessResult = LoginResponse(
            status = "OK",
            token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Im1vaGFtbWVkbW9yc2UiLCJpbWFnZSI6Imh0dHBzOi8vaS5waWNzdW0ucGhvdG9zL2lkLzEwNjIvNTA5Mi8zMzk1LmpwZz9obWFjPW85bTdxZVU1MXVPTGZYdmVwWGNUcmsyWlBpU0JKRWtpaU9wLVF2eGphLWsiLCJuYW1lIjoi2KfZitin2K8g2YLYp9iv2LEiLCJlbWFpbCI6ImxjdG1ya2g3ZGs5Y3d5MEBnbWFpbC5jb20iLCJwaG9uZSI6IjAxOTEwMDMzOTM0IiwiaWF0IjoxNjQzMjkxODAwLCJleHAiOjE2NDMyOTI3MzB9.t30MGrKV7KrvjUJqpt7BPRqPcpZI_3OqFRkfLE_SYzU",
            profile = LoginResponse.Profile(
                email = "lctmrkh7dk9cwy0@gmail.com",
                image = "https://i.picsum.photos/id/1062/5092/3395.jpg?hmac=o9m7qeU51uOLfXvepXcTrk2ZPiSBJEkiiOp-Qvxja-k",
                name = "اياد قادر",
                phone = "01910033934",
                username = "mohammedmorse"
            )
        )

        val ErrorUserNameResult = ErrorResponse(
            status = "BAD_REQUEST",
            message = "missing username"
        )

        val ErrorPasswordResult = ErrorResponse(
            status = "BAD_REQUEST",
            message = "missing password"
        )

        val BothCredentialErrorResult = ErrorResponse(
            status = "BAD_REQUEST",
            message = "Bad username, username must be 6 : 15 char length"
        )

    }

}