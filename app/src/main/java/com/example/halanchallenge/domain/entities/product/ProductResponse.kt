package com.example.halanchallenge.domain.entities.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ProductResponse(
    val products: List<Product>?,
    val status: String? // OK
) {


    @Parcelize
    data class Product(
        val brand: String?, // SYM
        @SerializedName("consumer_finance_price")
        val consumerFinancePrice: Int?, // 600
        @SerializedName("deal_description")
        val dealDescription: String?, // الأبعاد:الطول الكلي: 2000 مليمترالارتفاع الكلي: 1290 مليمترالعرض الكلي: 690 مليمترالوزن الكلي: 120 كيلوجرامالفرامل الأمامية: شوكة تلسكوبيةالفرامل الخلفية: ,وحدة ذراع التأرجحخامة الحواف الأمامية والخلفية: ألومنيوم الإطار الأمامي: 110 / 70-16الإطار الخلفي: 110 / 70-16قطر الفرامل الأمامية: 260 مليمترنوع الفرامل الأمامية: ديسك + نظام مشترك(ABS)قطر الفرامل الخلفية: 240 مليمترنوع الفرامل الخلفية: ديسك + نظام مشترك(ABS)الجنوط (قاعدة العجل): 1330 مليمترالمحرك والوقود:معيار العوادم: E4نوع المحرك: رباعي الأشواطنوع السلندر: أحادينظام التبريد: هوائيمعدل الإزاحة: 124.6 سي سينوع الوقود: بنزين (خالي من الرصاص)ناقل الحركة: C.V.T (انتقال متغير باستمرار)أعلى قوة للمحرك: 8500 لفة في الدقيقةأعلى عزم للدوران: 9.2 نيوتن/7000 لفة في الدقيقةالسرعة القصوى: 95 كيلو في الساعةسعة الوقود: 5.4 لترإشارة بدء المحرك: كهربائيةمميزات أخرى:الفانوس الأمامي: 12 فولت/35 وات *1الفانوس الخلفي: 12 فولت/21 وات *1الفانوس الثانوي الأمامي: 12 فولت/5 وات*1فوانيس الإشارة (2 جانبي+2 أمامي): 12 فولت/10 وات *4
        val id: Int?, // 76
        val image: String?, // https://cdn.halan.io/images/E-Commerce/Products/Symphony-SR-1.PNG
        val images: List<String>,
        @SerializedName("name_ar")
        val nameAr: String?, // سكوتر SYM Symphony SR سيمفوني ۱٥۰ سي سي
        @SerializedName("name_en")
        val nameEn: String?, // SYM Symphony SR Scooter 150cc 
        val price: Int? // 837
    ) : Parcelable


}