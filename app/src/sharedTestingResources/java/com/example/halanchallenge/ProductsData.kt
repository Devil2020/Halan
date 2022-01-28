package com.example.halanchallenge

import com.example.halanchallenge.domain.entities.error.ErrorResponse
import com.example.halanchallenge.domain.entities.product.ProductResponse

object ProductsData {

    object Input {

        val ExpiredToken ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Im1vaGFtbWVkbW9yc2UiLCJpbWFnZSI6Imh0dHBzOi8vaS5waWNzdW0ucGhvdG9zL2lkLzEwNjIvNTA5Mi8zMzk1LmpwZz9obWFjPW85bTdxZVU1MXVPTGZYdmVwWGNUcmsyWlBpU0JKRWtpaU9wLVF2eGphLWsiLCJuYW1lIjoiICDYr9in2YbZitin2YQgICDYrNmG2YrYryIsImVtYWlsIjoiaG96cDRoanR0dXd1d3Z3QGdtYWlsLmNvbSIsInBob25lIjoiMDE5MTA4Nzk0MTciLCJpYXQiOjE2NDMyOTAxMDcsImV4cCI6MTY0MzI5MTAzN30.5079Effn70eLPdxrjS1DxkSvP6DkfyjgAKeVpexkGD8"

        val EmptyToken = ""

        val ValidToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Im1vaGFtZWRtb18xMjMiLCJpbWFnZSI6Imh0dHBzOi8vaS5waWNzdW0ucGhvdG9zL2lkLzEwNjIvNTA5Mi8zMzk1LmpwZz9obWFjPW85bTdxZVU1MXVPTGZYdmVwWGNUcmsyWlBpU0JKRWtpaU9wLVF2eGphLWsiLCJuYW1lIjoiICDYudio2YrYryDYqNiv2LEiLCJlbWFpbCI6InRrN25yMWc0MHc3NGRkcUBnbWFpbC5jb20iLCJwaG9uZSI6IjAxOTEwMjE0OTM5IiwiaWF0IjoxNjQzMzYxMjA1LCJleHAiOjE2NDMzNjIxMzV9.I7faRBrPqlNhc18QJdGHRDFlJAPnUHRfc3jQ7CxC7eg"

    }

    object Output {

        val ExpiredAuthorizedUser = ErrorResponse(
            status = "UNAUTHORIZED",
            message = "Token expired"
        )

        val MissingAuthorization = ErrorResponse(
            status = "UNAUTHORIZED",
            message = "missing Authorization"
        )

        val SuccessProducts = ProductResponse(
            status = "OK",
            products = arrayListOf(
                ProductResponse.Product(
                    brand = "Rocker",
                    consumerFinancePrice = 965,
                    dealDescription = "الأبعاد: 110 * 95 * 80 سم\nاللون: بيج غامق\nمناسب للاستخدام بالداخل.\nالطراز: عصري / مودرن\nيناسب غرفة المعيشة",
                    id = 147,
                    image = "https://cdn.halan.io/images/E-Commerce/Products/19_1.jpg",
                    images = arrayListOf(
                        "https://cdn.halan.io/images/E-Commerce/Products/sharp_2.25_AC.png",
                        "https://cdn.halan.io/images/E-Commerce/Products/lSFewR5Wxt17zA9.jpg",
                        "https://cdn.halan.io/images/E-Commerce/Products/4I41TqdLGgyEidm.jpeg",
                        "https://cdn.halan.io/images/E-Commerce/Products/LG_55UN7095_1.png",
                        "https://cdn.halan.io/images/E-Commerce/Products/infinix-smart5-1.jpg",
                        "https://cdn.halan.io/images/E-Commerce/Products/beYNFoXhsTtqimY.png"
                    ),
                    nameAr = "كرسي هزاز روكر ريكلاينر (بيج غامق)",
                    nameEn = "Rocker Recliner (Dark Beige)",
                    price = 965
                )
            )
        )

    }

}