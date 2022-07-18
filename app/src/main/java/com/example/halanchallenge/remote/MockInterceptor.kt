package com.example.halanchallenge.remote

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri().toString()
        val responseString = when {
            uri.startsWith("https://assessment-sn12.halan.io/auth") -> LOGIN_RESPONSE
            uri.startsWith("https://assessment-sn12.halan.io/products") -> PRODUCTS_RESPONSE
            else -> DETAILS_RESPONSE
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(responseString.toByteArray()
                .toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json")
            .build()
    }

    val LOGIN_RESPONSE = "{\n" +
            "    \"status\": \"OK\",\n" +
            "    \"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Im1vaGFtbWVkbW9yc2UiLCJpbWFnZSI6Imh0dHBzOi8vaS5waWNzdW0ucGhvdG9zL2lkLzEwNjIvNTA5Mi8zMzk1LmpwZz9obWFjPW85bTdxZVU1MXVPTGZYdmVwWGNUcmsyWlBpU0JKRWtpaU9wLVF2eGphLWsiLCJuYW1lIjoi2LPZhdmK2LEg2LnZhNin2KEiLCJlbWFpbCI6Iml6bjM4eGZ2M2Jvc2Y2N0BnbWFpbC5jb20iLCJwaG9uZSI6IjAxOTEwMjA0NTkyIiwiaWF0IjoxNjU2NTM5Mzg4LCJleHAiOjE2NTY1NDAzMTh9.kHOySpR0GDSCNkKraBFM4tT_RenBctSgpalsgaTfoxw\",\n" +
            "    \"profile\": {\n" +
            "        \"username\": \"mohammedmorse\",\n" +
            "        \"image\": \"https://i.picsum.photos/id/1062/5092/3395.jpg?hmac=o9m7qeU51uOLfXvepXcTrk2ZPiSBJEkiiOp-Qvxja-k\",\n" +
            "        \"name\": \"سمير علاء\",\n" +
            "        \"email\": \"izn38xfv3bosf67@gmail.com\",\n" +
            "        \"phone\": \"01910204592\"\n" +
            "    }\n" +
            "}"
    val PRODUCTS_RESPONSE = "{\n" +
            "    \"status\": \"OK\",\n" +
            "    \"products\": [\n" +
            "        {\n" +
            "            \"id\": 116,\n" +
            "            \"name_ar\": \"ثلاجة كريازي سوليتير تربو نو فروست 14 قدم\",\n" +
            "            \"deal_description\": \"رقم الموديل: KH 335 NV/2 \\nالسعة اللترية: 335 لتر\\nعدد الأبواب: 2 باب\\nلون الثلاجة: سيلفر\\nعرض: 66 سم\\nعمق: 65.5 سم\\nارتفاع 168.4 سم\\nسعة الفريزر: 83 لتر\\nجسم الثلاجة: صاج ضد الصدأ\\nالأرفف: مصنعة من مادة الكريستال - مضاد للكسر - ارتفاعات قابلة للضبط\",\n" +
            "            \"brand\": \"Kiriazi\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/item_XXL_10807347_7c4bd56c4c1e8.jpg\",\n" +
            "            \"name_en\": \"Kiriazi Refrigerator Solitaire Turbo No Frost- 14 Feet KH 335 NV/2\",\n" +
            "            \"price\": 341,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02171111.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Z8lwPGHYmPDcLmx.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/O2VK7ikOeqVH7qC.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/tzVso7fRi6e879K.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/UHD43UT7000_1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02_112957.png\"\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}"
    val DETAILS_RESPONSE =""

}