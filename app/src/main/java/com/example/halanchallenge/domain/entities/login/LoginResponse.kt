package com.example.halanchallenge.domain.entities.login

import androidx.annotation.Keep

@Keep
data class LoginResponse(
    val profile: Profile?,
    val status: String?, // OK
    val token: String? // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Im1vaGFtbWVkbW9yc2UiLCJpbWFnZSI6Imh0dHBzOi8vaS5waWNzdW0ucGhvdG9zL2lkLzEwNjIvNTA5Mi8zMzk1LmpwZz9obWFjPW85bTdxZVU1MXVPTGZYdmVwWGNUcmsyWlBpU0JKRWtpaU9wLVF2eGphLWsiLCJuYW1lIjoi2YLYr9mK2YUg2YXYrdio2YjYqCIsImVtYWlsIjoibXFqeHN1MnJ3aHU1eDRrQGdtYWlsLmNvbSIsInBob25lIjoiMDE5MTAyODI3MTMiLCJpYXQiOjE2NDMyODc2MjMsImV4cCI6MTY0MzI4ODU1M30.U__WOb0Y7y8QEBDdS3JrblSclCKRyuBp6xsb_LDhFP0
) {
    @Keep
    data class Profile(
        val email: String?, // mqjxsu2rwhu5x4k@gmail.com
        val image: String?, // https://i.picsum.photos/id/1062/5092/3395.jpg?hmac=o9m7qeU51uOLfXvepXcTrk2ZPiSBJEkiiOp-Qvxja-k
        val name: String?, // قديم محبوب
        val phone: String?, // 01910282713
        val username: String? // mohammedmorse
    )


}