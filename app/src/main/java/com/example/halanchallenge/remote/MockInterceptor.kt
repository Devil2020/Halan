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
            "        },\n" +
            "        {\n" +
            "            \"id\": 36,\n" +
            "            \"name_ar\": \"موتوسيكل بوكسر 150 BM\",\n" +
            "            \"deal_description\": \"مميزات أساسية:\\n150 سي سي بقوة 12 حصان\\nالسرعة القصوى: 94 كيلومتر/ساعة\\n5 غيارات للسرعة\\nمؤشر غيارات\\nكرسي واسع\\nإضاءة ثابتة (35 فولت)\\nشاحن USB للموبايل\\nالموتور: \\nقوة الموتور: 8.83 كيلو واط بمعدل 7500 دورة في الدقيقة\\nالعزم: 12.26 نيوتن متر بمعدل  4500 دورة في الدقيقة\\nحماية الموتور: كروم على شنابر البستم\\nخاصية رفع عزم الموتور Exhaus TEC\\nتبريد الموتور: هوائي\\nالإشعال: شمعي\\nسعة التانك: 11 لتر\\nعوامل الأمان:\\nخاصية نظام التعليق: SNS (الأول من نوعه على مستوى العالم).\\nقاعدة عجلات بطول 1285 مم\\nفرامل سفلية ميكانيكية\\nخواص أخرى:\\nمؤشر للوقود\\nمؤشر للسرعة\\nجنوط ألومينيوم\\n\",\n" +
            "            \"brand\": \"Bajaj\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/boxer-150bm-01.png\",\n" +
            "            \"name_en\": \"Motorcycle Boxer 150 BM\",\n" +
            "            \"price\": 558,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/aKXYt6JZTQZZaIO.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/reBlmVA3aWx5Jea.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Tricycle200-king-02.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/RKb72buIALduFJb.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/cooker_Jumbo_fresh.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/E230N5-3-Silver-Close.jpg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 349,\n" +
            "            \"name_ar\": \"Durable Linen Bottle\",\n" +
            "            \"deal_description\": \"Script\",\n" +
            "            \"brand\": \"Script Brand\",\n" +
            "            \"image\": \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "            \"name_en\": \"Durable Linen Bottle\",\n" +
            "            \"price\": 970,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/jqUcWRtXIv4yx6Q.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02 112023.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/6IHHSLnoLMHTHzg.png\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/VWcGAJDH7nx3pef.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/C5.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 149,\n" +
            "            \"name_ar\": \"كنبة 3 مقاعد ركنة شيزلونج على اليمين , و2 مقاعد على الشمال\",\n" +
            "            \"deal_description\": \"الأبعاد: 275 سم + 200 سم \\nاللون: رمادي\\nمناسب للاستخدام بالداخل.\\nالطراز: عصري / مودرن\\nيناسب غرفة المعيشة\",\n" +
            "            \"brand\": \"Furniture\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/img_9880.jpg\",\n" +
            "            \"name_en\": \"L-shape Sofa with 3 seats, Lounge (right) + Armchair (left)\",\n" +
            "            \"consumer_finance_price\": 600,\n" +
            "            \"price\": 692,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/TCL-55-1.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/tcl-10-plus-01.png\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/6LZGVd8cafJ8xjM.png\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/INFINIX-HOT9-01.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 219,\n" +
            "            \"name_ar\": \"موبايل شاومي 9T سمارت، بشريحتين، 4 جيجا رامات، 128 جيجا، كاميرا رباعية + سيلفي\",\n" +
            "            \"deal_description\": \"الشاشة:\\nحجم الشاشة: 6.53 بوصة Dot Drop\\nدرجة الوضوح: 2340x1080 أعلي من كامل الوضوح FullHD+\\nالتباين: 1500:1\\nالكاميرا الخلفية (الرئيسية): رباعية\\nالعدسة الأولى: 48 ميجا بيكسل\\nالعدسة الثانية: 8 ميجا بيكسل\\nالعدسة الثالثة: 2 ميجا بيكسل\\nالعدسة الرابعة: 2 ميجا بيكسل\\nفلاش: متوفر LED\\nفيديو: 1080 بيكسل (30 فريم في الثانية)\\nفلاتر للصورة وتسريع للفيديو\\nالكاميرا الأمامية (السيلفي):\\nالدقة: 8 ميجا بيكسل\\nفلاش: متوفر من خلال الشاشة.\\nالمعالج والذاكرة: \\nQualcomm Snapdragon 662\\nوحدة معالجة الرسوميات Adreno 610\\nمحرك Qualcomm بالذكاء الاصطناعي من الجيل الثالث\\nذاكرة النظام: 4 جيجا رام\\nذاكرة التخزين: 128 جيجا\\nمساحة تخزين إضافية تصل إلى: 512 جيجا\\nنظام التشغيل: أندرويد\\nالصوت: \\nمكبر صوت: استريو مزدوج \\nمدخل سماعات: 3.5 مم\\nدعم الصوت عالي الدقة\\nقنوات الاتصال: \\nفتحة SIM مختلطة nanoSIM+ MicroSD\\nواي فاي WiFi\\nبلوتوث Bluetooth 5.0\\nراديو FM\\nيو إس بي microUSB 2.0 \\nأخرى: \\nمستشعر بصمة على الجانب\\nمستشعر الوجه بالذكاء الاصطناعي\\nمستشعر الضوء المحيط\\nالبوصلة الإلكترونية\\nمحرك الاهتزاز\\nثنائي شريحة الاتصال SIM card\\nمستشعرات أخرى: السرعة، الموقع، وغيرها.\\nمحدد الموقع GPS: متوفر\\nالبطارية: \\nسعة البطارية: 6000 ميللي أمبير\\nتدعم الشحن السريع بقدرة 18 واط\\nيأتي مع شاحن ( الشحن السريع )\\nجسم الجهاز:\\nالارتفاع: 162.3 ملم\\nالعرض: 77.3 ملم\\nالسمك: 9.6 ملم\\nالوزن: 198 جرام\",\n" +
            "            \"brand\": \"Xiaomi\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/XiaomiRedmi9T4GB -128GB-1.jpg\",\n" +
            "            \"name_en\": \"Xiaomi 9T Smartphone, Dual SIM, 4G RAM, 128 GB, Quadcam + Selfie\",\n" +
            "            \"price\": 589,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/jqUcWRtXIv4yx6Q.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/BThY0GEMjkJ33dt.png\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/ywuHw54ICpUcqaf.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Cooker_Fresh1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/landing_TCL_front.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 359,\n" +
            "            \"name_ar\": \"hh\",\n" +
            "            \"deal_description\": \"hh\",\n" +
            "            \"brand\": \"hh\",\n" +
            "            \"image\": \"hh\",\n" +
            "            \"name_en\": \"hh\",\n" +
            "            \"price\": 276,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/SYM-Orbit-02.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/5DMTzktaB1pLdlH.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/toshiba-smart40-01.jpg\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 5,\n" +
            "            \"name_ar\": \"موبايل OPPO رينو 4 - 128جيجا - 4+1 كاميرات\",\n" +
            "            \"deal_description\": \"الشاشة:\\nحجم الشاشة: 6.4 بوصة\\nدرجة الوضوح: 1080*2400\\nشهادة نتفليكس لجودة العرض فائق الوضوح والدقة\\nالكاميرات:\\nالكاميرا الخلفية (الرئيسية): 4 عدسات \\nالعدسة الأولى: 48 ميجا بيكسل\\nالعدسة الثانية: 8 ميجا بيكسل\\nالعدسة الثالثة: 2 ميجا بيكسل\\nالعدسة الرابعة: 2 ميجا بيكسل\\nفلاش: متوفر\\nفيديو: 1080 بيكسل (30-60 لقطة في الثانية)\\nفتحات العدسة (متعددة): F2.4 , F2.2 , F2.4 , F1.7\\nالكاميرا الأمامية (السيلفي):\\nالدقة: 32 ميجا بيكسل\\nفلاش: غير متوفر\\nفيديو 1080 بيكسل (30 فريم في الثانية)\\nخواص تصوير مبتكرة: \\nبورتريه ملون على خلفية أبيض واسود\\nفيديو أحادي اللون\\nالتصوير البطيء (960 لقطة في الثانية)\\nتصوير ثابت (منع الاهتزاز في تصوير الفيديو)\\nمحرر الفيديو So Loop\\nخاصية التصوير الليلي فائق الوضوح\\nمستشعرات ذكية:\\nمنع التلصص\\nالتصفح عن بعد AIR Control\\nإلتفاف الشاشة الذكي  Smart Rotation\\nمستشعر بصمة: متوفر\\nخاصية التعرف على الوجه: متوفر\\nثنائي شريحة الاتصال SIM card\\nمستشعرات أخرى: السرعة، الموقع، وغيرها.\\nمحدد الموقع GPS: متوفر\\n \\nالمعالج والذاكرة: \\nأوكتاكور Qualcomm SM7123 Snapdragon 720G (8nm)\\nذاكرة النظام: 8 جيجا رام\\nذاكرة التخزين: 128 جيجا\\nمدخل كارت الداتا: microSD حتى 512 جيجا بايت\\nنظام التشغيل: أندرويد 10 (ColorOS 7.2)\\nالصوت: \\nمكبر صوت: متوفر\\nمدخل سماعات: 3.5 مم\\nقنوات الاتصال: \\nواي فاي WiFi\\nبلوتوث Bluetooth\\nيو إس بي microUSB 2.0 \\nراديو FM\\nالبطارية: \\nسعة البطارية: 4015 ميللي أمبير\\nخاصية الشحن السريع (VOOC flash charge 4.0)\\nمدة الشحن حتى 50% سعة للبطارية: أقل من 30 دقيقة\\nخاصية التوفير الفائق للطاقة: تتيح استخدام واتساب لمدة 1.5 ساعة مع بطارية 5% فقط.\\nجسم الجهاز: \\nالأبعاد: 160.3*73.9*7.7 مم\\nالوزن: 165 جرام\",\n" +
            "            \"brand\": \"Oppo\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/oppo-reno4-11.jpg\",\n" +
            "            \"name_en\": \"Oppo Reno4 - 128 GB - 4+1 Cameras\",\n" +
            "            \"price\": 565,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/phiX8unFZomayPd.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Kymco_Agility150.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/item_XL_37223772_145133940.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/infinix-note7lite-1.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/samsung-a71-02.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/191112030641elsafeerproduct.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 138,\n" +
            "            \"name_ar\": \"كيمكو أجيليتي سكوتر ١٥٠ سي سي\",\n" +
            "            \"deal_description\": \"الأبعاد:\\nالطول الكلي: 2050 مم\\nالارتفاع الكلي: 1210 مم\\nالعرض الكلي: 735 مم\\nالوزن الكلي: 109 كيلوجرام\\nالإطار الأمامي: 100 / 80-16\\nالإطار الخلفي: 120 / 80-16\\nالفرامل الأمامية: 260 مم\\nقطر الفرامل الخلفية: 240 مم\\nالجنوط (قاعدة العجل): 1340 مم\\nارتفاع المقعد: 815 مم\\nوزن المركبة الصافي: 126 كيلوجرام\\nالمحرك والوقود:\\nنوع المحرك: رباعي الأشواط\\nنظام التبريد: هوائي\\nقوة المحرك: 10.62 حصان\\nمعدل الإزاحة: 149.5 سي سي\\nنظام الوقود: كاربيراتير\\nالسلندر: سلندر واحد\\nناقل الحركة: إنتقال متغير باستمرار CVT\\nسعة الوقود: 7 لتر\\nمميزات أخرى:\\nسعة التخزين: تكفي لخوذة وجه كاملة، باب أمامي للتخزين.\\nالشاسيه: صلب\\nالفرامل الأمامية: شوكة تلسكوبية\\nالفرامل الخلفية: وحدة ذراع الترجح Unit Swing Arm\\nلوحة تحكم: كمبيوتر\\nصندوق علوي\\nتعليق خلفي مزدوج\",\n" +
            "            \"brand\": \"Kymco\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/Kymco_Agility150.jpg\",\n" +
            "            \"name_en\": \"Kymco Agility City Scooter 150 cc\",\n" +
            "            \"price\": 978,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/3y2eYaYvjySAH31.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-02-21164129.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/uKDW9yr1k31jSNg.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/oppo-reno4-11.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/1tUQJmI35dV5isS.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/infinix-Hot9-01.jpg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 321,\n" +
            "            \"name_ar\": \"ثلاجة فريش 397 لتر نوفروست ديجيتال- 2 باب + مبرد مياه خارجي، أسود  FNT-D470 YB\",\n" +
            "            \"deal_description\": \"رقم الموديل: FNT-D470 YB\\nالسعة اللترية: 397 لتر\\nعدد الأبواب: 2 باب\\nلون الثلاجة: أسود\\nنوفروست\\nفلتر بلازما الأيوني\\nثلاجة بحنفية\\nخاصية التبريد والتجميد السريع\\nأرفف مصنوعة من الزجاج المقاوم للكسر\\nمنطقة حفظ الطعام طازج\\nصانع الثلج منفصل\\nالإضاءة الداخلية: LED\\nدرج خضروات موازن للرطوبة سهل التقسيم\\nالارتفاع: 1755 مم\\nالعرض: 650 مم\\nالعمق: 635 مم\\nضمان لمدة 10 سنوات\\n\",\n" +
            "            \"brand\": \"Fresh\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/289aLba1NTlyTfI.png\",\n" +
            "            \"name_en\": \"Fresh Refrigerator, 397 Litres Nofrost Digital, 2 Door, Black  FNT-D470 YB\",\n" +
            "            \"price\": 734,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/t5HNNXiYhgXOTgM.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/TCL-32-1.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/refrigerator-toshiba-359L-02.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/4I41TqdLGgyEidm.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Tornado-water-heater-01-1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/E230N5-3-Silver-Close.jpg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 174,\n" +
            "            \"name_ar\": \"ثلاجة الكتروستار ماجيستا 338 لتر - 2 باب، فضي\",\n" +
            "            \"deal_description\": \"رقم الموديل: Magesta\\nالسعة اللترية: 338 لتر\\nعدد الأبواب: 2 باب\\nلون الثلاجة: سيلفر\\nعزل متعدد الطبقات\\nصديقة للبيئة\\nاستهلاك أقل للكهرباء\\nتكنولوجيا نوفروست مصممة لمنع تكوين الثلج بالثلاجة\\nتحكم تام في درجة التبريد تصل للصفر في الفريزر\\nيحتفظ الطعام بجودته حتى عند انقطاع الكهرباء حتى 6 ساعات\\nموتور ذو جودة عالية\\nعزل بطبقتين إلكتروستاتيك لمقاومة الصدأ\\nأرفف بايركس لضمان النظافة والشكل الرائع وطول العمر\\nالطول: 176 سم\\nالعرض: 72.5 سم\\nالعمق: 60 سم\",\n" +
            "            \"brand\": \"Electrostar\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02_112957.png\",\n" +
            "            \"name_en\": \"Electrostar Refrigerator Magesta, 338 Litres, 2 Door, Silver\",\n" +
            "            \"price\": 132,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02170938.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02115045.png\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/C5.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02114216.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/VWcGAJDH7nx3pef.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 208,\n" +
            "            \"name_ar\": \"موبايل أوبو A15S ذاكرة 64 جيجا، 4 جيجا رام، ٣ كاميرات خلفية + سيلفي\",\n" +
            "            \"deal_description\": \"الشاشة:\\nحجم الشاشة: 6.52 بوصة\\nدرجة الوضوح: 1600*720 عالي الوضوح\\nالكاميرا الخلفية (الرئيسية):  ثلاثية\\nالعدسة الأولى: 13 ميجا بيكسل\\nالعدسة الثانية: 2 ميجا بيكسل\\nالعدسة الثالثة: 2 ميجا بيكسل\\nفلاش: متوفر\\nأوضاع التصوير: صور، فيديو، الوضع الاحترافي، الوضع الليلي، بانوراما، صورة شخصية، معزولة، التصوير بفاصل زمني، الحركة البطيئة.\\nالكاميرا الأمامية (السيلفي):\\nالدقة: 5 ميجا بيكسل\\nالمعالج والذاكرة: \\nأوكتاكور ميدياتك هيليو 2.3GHz\\nذاكرة النظام: 4 جيجا رام\\nذاكرة التخزين: 64 جيجا\\nمدخل كارت الداتا: microSD\\nنظام التشغيل: أندرويد\\nالصوت: \\nمدخل سماعات: 3.5 مم\\nقنوات الاتصال: \\nواي فاي WiFi\\nبلوتوث Bluetooth\\nيو إس بي microUSB 2.0 \\nراديو FM\\nأخرى: \\nمستشعر بصمة: متوفر\\nخاصية التعرف على الوجه: متوفر\\nثنائي شريحة الاتصال Nano SIM card \\nمستشعرات أخرى: السرعة، الموقع، وغيرها.\\nمحدد الموقع GPS: متوفر\\nيدعم Gear\\nالبطارية: \\nسعة البطارية: 4230 ميللي أمبير\\nجسم الجهاز: \\nالأبعاد: 164*75.4*7.9 مم\\nالوزن: 177 جرام\",\n" +
            "            \"brand\": \"Oppo\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/OPPOA15S_01.png\",\n" +
            "            \"name_en\": \"OPPO A15S, 64 GB, 4G Ram, 3 Rear Cameras + Selfie\",\n" +
            "            \"price\": 624,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/LHtn7xdBtMPyETW.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/nMvLPB8gUqECaDh.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-03154001_1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/527PaToOSBTDgHH.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Ecobike-02.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 351,\n" +
            "            \"name_ar\": \"Gorgeous Iron Coat\",\n" +
            "            \"deal_description\": \"Script\",\n" +
            "            \"brand\": \"Script Brand\",\n" +
            "            \"image\": \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "            \"name_en\": \"Gorgeous Iron Coat\",\n" +
            "            \"price\": 614,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/lg-smart55-01.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/samsung-smart55-02.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/MD88lTuE0mlzFIl.jpeg\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Freezer_Toshiba-1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Samsung-Galaxy-A12_1.jpg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 271,\n" +
            "            \"name_ar\": \" غسالة ملابس تورنيدو فوق أوتوماتيك 12 كيلو تحميل علوي + طلمبة\",\n" +
            "            \"deal_description\": \"رقم الموديل: TWT-TLN12LDS\\nتفاصيل خواص الغسالة:\\nنوع الغسالة: فوق أوتوماتيك\\nنوع التحميل: تحميل علوي\\nسعة الغسالة: 12 كجم\\nمعدل الدوران: 860 لفة في الدقيقة\\nلون الغسالة: سيلفر\\nلون الجزء العلوي من الغسالة: أسود\\nمزودة بطلمبة مياه للصرف\\nمزودة بمدخلين للمياه (بارد + ساخن)\\nمزودة بفلتر\\nتحكم كامل لوظائف التشغيل بواسطة كمبيوتر ذكي\\nتحديد مستوى المياه بالغسالة أوتوماتيكيًا ليتناسب مع كمية الغسيل\\nتصميم مطور للمروحة لمنع تشابك الغسيل\\nيمكن تنظيف حلة الغسيل مع كل استخدام بالضغط على Tub Cleaning \\nوضع حماية الأطفال\\nخاصية تأجيل الغسيل\\nتجفيف أغلى عن طريق مدخل هواء ثلاثي أثناء دورة العصر\\nخاصية الغسيل المكثف التمهيدي\\nخاصية Hydrotwin Power\\nمصنعة من مواد عالية الجودة ومقاومة للصدأ.\\nبرامج الغسيل:\\nدورة عادية\\nدورة نقع حتى 60 دقيقة\\nغسيل ثقيل\\nدورة غسيل البطاطين\\nدورة لطيفة للملابس الرقيقة والحراير\\nالبرنامج السريع\\nتفصل الغسالة أوتوماتيكيًا بعد انتهاء دورة الغسيل لتوفير استهلاك الكهرباء.(بعد 15 ثانية)\\nأبعاد ووزن الغسالة:\\nالوزن الصافي: 42 كيلو\\nالوزن الإجمالي 48 كيلو\\nالعرض: 607 مم\\nالارتفاع: 1047 مم\\nالعمق: 683 مم\\nتفاصيل أخرى: \\nبلد المنشأ: مصر\\n\",\n" +
            "            \"brand\": \"Tornado\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/DPHX6zbyyfjRYno.png\",\n" +
            "            \"name_en\": \" Tornado Washing Machine Top Automatic 12 Kg with pump\",\n" +
            "            \"price\": 397,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/SJ-48CST1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-18101348_10.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Honor9X_1.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/HPlaptop15dA-3002_corei3_1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/heater-02.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Gas-Cooker-8604-5-Burner-80-CM-Close.jpg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 40,\n" +
            "            \"name_ar\": \"سكوتر SYM Fiddle 2 فيدل ١٥٠ سي سي\",\n" +
            "            \"deal_description\": \"الأبعاد:\\nالطول الكلي: 1870 مليمتر\\nالارتفاع الكلي: 1150 مليمتر\\nالعرض الكلي: 695 مليميتر\\nالوزن الكلي: 107 كيلوجرام\\nالإطار الأمامي: 110 / 70-12\\nالإطار الخلفي: 120 / 70-12\\nقطر الفرامل الأمامية: 190 مليمتر\\nقطر الفرامل الخلفية: 130 مليمتر\\nالجنوط (قاعدة العجل): 1320 مليمتر\\nالمحرك والوقود:\\nنوع المحرك: رباعي الأشواط\\nنظام التبريد: هوائي\\nمعدل الإزاحة: 149.5 سي سي\\nنظام الوقود: كاربيراتير\\nالسلندر: سلندر واحد\\nنوع الوقود: بنزين (خالي من الرصاص)\\nناقل الحركة: C.V.T (انتقال متغير باستمرار)\\nسعة الوقود: 5.2 لتر\\nمميزات أخرى:\\nالشاسيه: صلب\\nنوع الشاسيه: أنابيب سفلية Pipe underbone\\nنوع الإطارات: تيوبلس Tubeless\\nنظام التعليق الأمامي: شوكة تليسكوبية\\nنظام التعليق الخلفي: وحدة ذراع الترجح Unit Swing Arm\\nالفرامل الأمامية: ديسك\\nالفرامل الخلفية: اسطوانة\",\n" +
            "            \"brand\": \"SYM\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/uKDW9yr1k31jSNg.png\",\n" +
            "            \"name_en\": \"SYM Fiddle 2 Scooter 150 CC\",\n" +
            "            \"price\": 887,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/samsung-a10s-02.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/cBrzU0lCLJCpSlA.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/5DMTzktaB1pLdlH.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/infinix-Hot9-01.jpg\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/tornado-43-1.jpg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 373,\n" +
            "            \"name_ar\": \"try\",\n" +
            "            \"deal_description\": \"r\",\n" +
            "            \"brand\": \"55\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/FW8pGSiu3ysdQeJ.png\",\n" +
            "            \"name_en\": \"try\",\n" +
            "            \"price\": 845,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/4I41TqdLGgyEidm.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/jqUcWRtXIv4yx6Q.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/reBlmVA3aWx5Jea.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/toshiba-smart32-01.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/oppo-a53-01.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/49UN7340_1.jpg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 75,\n" +
            "            \"name_ar\": \"ثلاجة شارب نوفروست 385 لتر، 2 باب\",\n" +
            "            \"deal_description\": \"رقم الموديل:SJ-48C(SL)\\nالسعة اللترية: 385 لتر\\nعدد الأبواب: 2 باب\\nلون الثلاجة: سيلفر\\nفلتر إليكتروني للروائح Nano Deodorizer + ِAG\\nتكنولوجيا التبريد السريع Hybrid Cooling System\\nنظام فصل أوتوماتيكي\\nإضاءة داخلية: LED\\nأرفف الثلاجة: زجاجية شديدة التحمل\\nمزودة بمقبض اسطواني استانليس مضاد للصدأ\\nتصميم مانع للضوضاء\\nمزودة بعجلات خلفية ومقابض علوية\\nمزودة بقوالب لمكعبات الثلج\\nتعمل في المناطق الحارة \\nمستوى كفاءة الطاقة: A (أغلى كفاءة وأقل استهلاك للكهرباء)\\nأبعاد الثلاجة: 645 × 680 × 1670 مليمتر\\nوزن الثلاجة: 66 صافي، 73 إجمالي\\nبلد المنشأ: مصر\",\n" +
            "            \"brand\": \"Sharp\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/Sharp_fridge.png\",\n" +
            "            \"name_en\": \"Sharp Refrigerator, No Frost, 385 Litres, 2 Doors\",\n" +
            "            \"price\": 565,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/191112030641elsafeerproduct.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/samsung-smart55-02.jpg\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/aC5UfJnFLYVvtd5.png\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 250,\n" +
            "            \"name_ar\": \"آيفون 12 برو ماكس ، 256 جيجا، كاميرا ثلاثية + سيلفي + فيس تايم\",\n" +
            "            \"deal_description\": \"الشاشة:\\nحجم الشاشة: 6.7 بوصة Super Retina XDR\\nدرجة الوضوح: 2278× 1284 بكسل HDR عالية الوضوح\\nالتباين: 2,000,000:1\\nشاشة True Tone\\nطلاء مقاوم للزيوت وبصمات الأصابع\\nدعم عرض اللغات وأنظمة كتابة متعددة في آن واحد.\\nمقاوم للطرطشة والغبار والماء: حتى 6 متر عمق حتى 30 دقيقة.\\nالكاميرا الخلفية (الرئيسية): ثلاثية\\nالعدسة الأولى: 12 ميجا بيكسل\\nالعدسة الثانية: 12 ميجا بيكسل\\nالعدسة الثالثة: 12 ميجا بيكسل\\nنطاق التصغير والتكبير بصري  5 مرات\\nنطاق التكبير والتصغير الرقمي حتى 12 مرة.\\nأنماط التصوير: بورتريه، بوكيه، بوكيه، استوديو، محيطي، مسرح، مسرح أحادي، ضوء عالي.\\nتثبيت بصري للصور\\nFocus Pixels بنسبة 100% (الكاميرا الواسعة)\\nعدسة خماسية العناصر، وعدسة سداسية العناصر\\nغطاء العدسة من الكريستال الياقوتي\\nنمط الليل، Deep Fusion، وHDR 3 الذكية للتعرف على المشاهد\\nالتقاط مجموعة ألوان كبيرة للصور وLive Photos\\nالجيل الجديد من ميزة الصور عالية الوضوح\\nتصحيح العين الحمراء\\nتثبيت تلقائي للصور\\nنمط تصوير متتابع\\nتحديد الموقع الجغرافي للصور\\nفلاش: متوفر LED\\nفيديو: 1080 بيكسل (30 فريم في الثانية)\\nفلاتر للصورة وتسريع للفيديو والفيديو البطيء\\nالكاميرا الأمامية (السيلفي):\\nالدقة: 12 ميجا بيكسل\\nفلاش: متوفر من خلال الشاشة.\\nنمط بورتريه مع تأثير بوكيه المطور وميزة ضبط العمق\\nإضاءة بورتريه مع ستة تأثيرات (طبيعي، استوديو، محيطي، مسرح، مسرح أحادي، ضوء عالي أحادي)\\nتسجيل فيديو HDR مع Dolby Vision لغاية 30 إطاراً في الثانية\\nتسجيل فيديو 4K\u200F بمعدل 24 إطاراً في الثانية أو 25 إطاراً في الثانية أو 30 إطاراً في الثانية أو 60 إطاراً في الثانية\\nتسجيل فيديو 1080p HD بمعدل 25 إطاراً في الثانية أو 30 إطاراً في الثانية أو 60 إطاراً في الثانية\\nدعم التصوير بالحركة البطيئة\\nالجيل الجديد من ميزة HDR الذكية للصور\\nنطاق ديناميكي أوسع للفيديو بمعدل 30 إطاراً في الثانية\\nتثبيت سينمائي للفيديو (4K و1080p و720p)\\nفيديو QuickTake\\nالمعالج والذاكرة: \\nشريحة A14 Bionic\u200F\\nالجيل الثالث من المحرك العصبي Neural Engine\\nذاكرة التخزين: 256 جيجا\\nنظام التشغيل: iOS 14\\nالصوت: \\nتشغيل الصوت المكاني الغامر\\nإمكانية ضبط الحد الأقصى للصوت في الإعدادات\\nقنوات الاتصال: \\nشريحة: nano-SIM \\nواي فاي WiFi\\nبلوتوث Bluetooth 5.0\\nيو إس بي microUSB 2.0 \\nفيس تايم FaceTime\\nمستشعرات \\nمستشعر التعرف على الوجه\\nمقياس الضغط الجوي\\nجيروسكوب ثلاثي المحاور\\nمقياس تسارع\\nمستشعر تقارب\\nمستشعر الإضاءة\\nسعة البطارية: \\nتشغيل فيديو: حتى 20 ساعة\\nفيديو أونلاين: حتى 12 ساعات\\nصوت وموسيقى: حتى 80 ساعة\\nتدعم الشحن السريع (50% شحن في 30 دقيقة بشاحن بقوة 20 وات أو أعلى، يباع منفردًا)\\nيدعم الشحن اللاسلكي\\nجسم الجهاز:\\nالطول: 160.8 ملم\\nالعرض: 78.1 ملم\\nالسمك: 7.4 ملم\\nالوزن: 226 جرام\\n\",\n" +
            "            \"brand\": \"Apple\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/Q5Mj7EFU4zUzL06.jpg\",\n" +
            "            \"name_en\": \" iPhone 12 Pro Max, 256GB, Triple Camera + Selfie + Facetime\",\n" +
            "            \"price\": 210,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/kH0keGwpkdaJ4FX.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/toshiba-smart32-01.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/1tUQJmI35dV5isS.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/bedroom-cover.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/INFINIX_HOT_10_1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Bundle-03-11.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 328,\n" +
            "            \"name_ar\": \"nada\",\n" +
            "            \"deal_description\": \"44\",\n" +
            "            \"brand\": \"44\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/WamfsygF0rBkcIt.png\",\n" +
            "            \"name_en\": \"nada\",\n" +
            "            \"price\": 396,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/FW8pGSiu3ysdQeJ.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-18101210.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02115045.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/tornado-43-01.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/U6KLY8T4D4kyrAs.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/LG_55UN7095_1.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 72,\n" +
            "            \"name_ar\": \"ديب فريزر توشيبا، نوفروست 233 لتر، 5 درج بخاصية التجميد السريع\",\n" +
            "            \"deal_description\": \"رقم الموديل: GF-22H-S\\nالسعة اللترية: 233 لتر\\nعدد الأدراج: 5 درج + 1 رف\\nلون الثلاجة: سيلفر\\nمزود بخاصية التجميد السريع\\nتوزيع منتظم للهواء البارد\\nتصميم مانع للضوضاء\\nمستوى كفاءة الطاقة: C \\nأبعاد الديب فريزر: 604 × 650 × 1367 مليمتر\\nوزن الديب فريزر: 53 كجم صافي، 58 كجم إجمالي\\nبلد المنشأ: مصر\",\n" +
            "            \"brand\": \"Toshiba\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/Freezer_Toshiba-1.png\",\n" +
            "            \"name_en\": \"Toshiba Deep Freezer, No Frost 233 Litres, 5 Drawers + Quick Freezing \",\n" +
            "            \"price\": 979,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/item_XXL_10807347_7c4bd56c4c1e8.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/WuBDmz0qkDHc0CY.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/foxLH5GSu138uG9.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/kiHmXY1y92maZg6.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Kymco_Agility150.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/samsung-smart55-02.jpg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 241,\n" +
            "            \"name_ar\": \"آيفون 12 ، 256 جيجا، كاميرا مزدوجة + سيلفي + فيس تايم\",\n" +
            "            \"deal_description\": \"الشاشة:\\nحجم الشاشة: 6.1 بوصة Super Retina XDR\\nدرجة الوضوح: 2532 × 1170 بكسل\\nالتباين: 2,000,000:1\\nشاشة True Tone\\nطلاء مقاوم للزيوت وبصمات الأصابع\\nدعم عرض اللغات وأنظمة كتابة متعددة في آن واحد.\\nمقاوم للطرطشة والغبار والماء: حتى 6 متر عمق حتى 30 دقيقة.\\nالكاميرا الخلفية (الرئيسية): ثنائية\\nالعدسة الأولى: 12 ميجا بيكسل\\nالعدسة الثانية: 12 ميجا بيكسل\\nتصغير بصري حتى مرتين\\nتكبير وتصغير رقمي حتى خمس مرات.\\nأنماط التصوير: بورتريه، بوكيه، بوكيه، استوديو، محيطي، مسرح، مسرح أحادي، ضوء عالي.\\nتثبيت بصري للصور\\nFocus Pixels بنسبة 100% (الكاميرا الواسعة)\\nعدسة خماسية العناصر، وعدسة سداسية العناصر\\nغطاء العدسة من الكريستال الياقوتي\\nنمط الليل، Deep Fusion، وHDR 3 الذكية للتعرف على المشاهد\\nالتقاط مجموعة ألوان كبيرة للصور وLive Photos\\nالجيل الجديد من ميزة الصور عالية الوضوح\\nتصحيح العين الحمراء\\nتثبيت تلقائي للصور\\nنمط تصوير متتابع\\nتحديد الموقع الجغرافي للصور\\nفلاش: متوفر LED\\nفيديو: 1080 بيكسل (30 فريم في الثانية)\\nفلاتر للصورة وتسريع للفيديو والفيديو البطيء\\nالكاميرا الأمامية (السيلفي):\\nالدقة: 12 ميجا بيكسل\\nفلاش: متوفر من خلال الشاشة.\\nنمط بورتريه مع تأثير بوكيه المطور وميزة ضبط العمق\\nإضاءة بورتريه مع ستة تأثيرات (طبيعي، استوديو، محيطي، مسرح، مسرح أحادي، ضوء عالي أحادي)\\nتسجيل فيديو HDR مع Dolby Vision لغاية 30 إطاراً في الثانية\\nتسجيل فيديو 4K\u200F بمعدل 24 إطاراً في الثانية أو 25 إطاراً في الثانية أو 30 إطاراً في الثانية أو 60 إطاراً في الثانية\\nتسجيل فيديو 1080p HD بمعدل 25 إطاراً في الثانية أو 30 إطاراً في الثانية أو 60 إطاراً في الثانية\\nدعم التصوير بالحركة البطيئة\\nالجيل الجديد من ميزة HDR الذكية للصور\\nنطاق ديناميكي أوسع للفيديو بمعدل 30 إطاراً في الثانية\\nتثبيت سينمائي للفيديو (4K و1080p و720p)\\nفيديو QuickTake\\nالمعالج والذاكرة: \\nشريحة A14 Bionic\u200F\\nالجيل الثالث من المحرك العصبي Neural Engine\\nذاكرة التخزين: 256 جيجا\\nنظام التشغيل: iOS 14\\nالصوت: \\nتشغيل الصوت المكاني الغامر\\nإمكانية ضبط الحد الأقصى للصوت في الإعدادات\\nقنوات الاتصال: \\nشريحة: nano-SIM \\nواي فاي WiFi\\nبلوتوث Bluetooth 5.0\\nيو إس بي microUSB 2.0 \\nمستشعرات \\nمستشعر التعرف على الوجه\\nمقياس الضغط الجوي\\nجيروسكوب ثلاثي المحاور\\nمقياس تسارع\\nمستشعر تقارب\\nمستشعر الإضاءة\\nسعة البطارية: \\nتشغيل فيديو: حتى 17 ساعة\\nفيديو أونلاين: حتى 11 ساعات\\nصوت وموسيقى: حتى 65 ساعة\\nتدعم الشحن السريع (50% شحن في 30 دقيقة بشاحن بقوة 20 وات أو أعلى، يباع منفردًا)\\nيدعم الشحن اللاسلكي\\nجسم الجهاز:\\nالارتفاع: 146.7 ملم\\nالعرض: 71.5 ملم\\nالسمك: 7.4 ملم\\nالوزن: 162 جرام\\n\",\n" +
            "            \"brand\": \"Apple\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/BawdMFCAoNuakce.png\",\n" +
            "            \"name_en\": \" iPhone 12, 256GB, Dual Camera + Selfie + Facetime\",\n" +
            "            \"price\": 423,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/aC5UfJnFLYVvtd5.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/191112030641elsafeerproduct.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/liVJpcUhKBecyAP.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Toshiba-washing-machine-8k-01.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Samsung_Galaxy_A20s–1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/1hHOeMBCHErwMnv.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 132,\n" +
            "            \"name_ar\": \"سخان غاز اوليمبك اليكتريك 6 لتر مدخنة - ابيض\",\n" +
            "            \"deal_description\": \"رقم الموديل: OEGWDG06FLWH\\nالسعة اللترية: 6 لتر\\nلون السخان: أبيض\\nمزود بشاشة ديجيتال\\nيعمل مع أقل ضغط مياه\\nمفتاحين للتحكم في المياه ودرجة الحرارة\\nمصدر الاشتعال : بطارية جافة \\\"5 عناصر امان\\\"\",\n" +
            "            \"brand\": \"Olympic Electric\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/Olympic Electric_3.png\",\n" +
            "            \"name_en\": \"Olympic Electric 6 Liters White Gas Water Heater - OEGWDG06FLWH\",\n" +
            "            \"price\": 678,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/cBrzU0lCLJCpSlA.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/6LZGVd8cafJ8xjM.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/1_g2.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/bedroom-cover.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/BTULHvI8UIQK7NY.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-02-21164129_12.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 297,\n" +
            "            \"name_ar\": \" غسالة ملابس تورنيدو فوق أوتوماتيك 10 كيلو تحميل علوي + طلمبة - أبيض\",\n" +
            "            \"deal_description\": \"رقم الموديل: TWT-TLN10LWT\\nتفاصيل خواص الغسالة:\\nنوع الغسالة: فوق أوتوماتيك\\nنوع التحميل: تحميل علوي\\nسعة الغسالة: 10 كجم\\nمعدل الدوران: 860 لفة في الدقيقة\\nلون الغسالة: أبيض\\nلون الجزء العلوي من الغسالة: أسود\\nمزودة بطلمبة مياه للصرف\\nمزودة بمدخلين للمياه (بارد + ساخن)\\nمزودة بفلتر\\nتحكم كامل لوظائف التشغيل بواسطة كمبيوتر ذكي\\nتحديد مستوى المياه بالغسالة أوتوماتيكيًا ليتناسب مع كمية الغسيل\\nيمكن تنظيف حلة الغسيل مع كل استخدام بالضغط على Tub Cleaning \\nوضع حماية الأطفال\\nخاصية تأجيل الغسيل\\nتجفيف أغلى عن طريق مدخل هواء ثلاثي أثناء دورة العصر\\nخاصية الغسيل المكثف التمهيدي\\nخاصية الغسيل بالرغوة المكثفة\\nخاصية الغسيل بشلالات المياه لإذابة تامة للمسحوق.\\nخاصية مضاد التشابك لمنع تشابك الغسيل لضمان غسيل داخل الملابس بفضل قوة دوران المياه داخل الحلة\\nخاصية تأجيل بدء التشغيل\\nمصنعة من مواد عالية الجودة ومقاومة للصدأ.\\nبرامج الغسيل:\\nدورة عادية\\nدورة نقع حتى 60 دقيقة\\nغسيل ثقيل\\nدورة غسيل البطاطين\\nدورة لطيفة للملابس الرقيقة والحراير\\nالبرنامج السريع\\nتفصل الغسالة أوتوماتيكيًا بعد انتهاء دورة الغسيل لتوفير استهلاك الكهرباء.(بعد 15 ثانية)\\nتستكمل الغسالة دورة الغسيل تلقائيًا في حالة انقطاع التيار الكهربائي.\\nأبعاد ووزن الغسالة:\\nالوزن الصافي: 41 كيلو\\nالوزن الإجمالي 46 كيلو\\nالعرض: 607 مم\\nالارتفاع: 1007 مم\\nالعمق: 683 مم\\nتفاصيل أخرى: \\nبلد المنشأ: مصر\\n5 سنوات ضمان مجاني شامل\\n\",\n" +
            "            \"brand\": \"Tornado\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/cH4wGQrRxwU2MnW.png\",\n" +
            "            \"name_en\": \" Tornado Washing Machine Top Automatic 10Kg with pump - White\",\n" +
            "            \"price\": 388,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/TCL-55-1.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02164343_10.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/toshiba-smart49-01.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/fkpoXSUOFCSGK8B.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/samsung-smart49-01.jpg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 288,\n" +
            "            \"name_ar\": \" عرض العربي 2 (ثلاجة شارب 385 لتر + غسالة شارب 7 كيلو+ تلفزيون توشيبا 55\\\" + ميكرويف شارب 20 لتر)\\\"\",\n" +
            "            \"deal_description\": \"ثلاجة شارب 385 لتر، نوفروست، 2 باب، أسود.\\nغسالة شارب فول أوتوماتيك، 7 كيلو، فضي.\\nتلفزيون توشيبا، سمارت 55 بوصة 4K، بدون إطار، رسيفر داخلي، 3 مداخل HDMI+ مدخلين USB\\nميكرويف شارب سولو 20 لتر، 800 وات، سلفر.\\n\",\n" +
            "            \"brand\": \"ElAraby\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/BKP3mjVDFCgdxox.png\",\n" +
            "            \"name_en\": \" El Araby Offer 2 (Sharp Refrigerator 385 Litres+ Sharp Washer 7Kg + Toshiba TV 55” + Sharp Microwave 20 Litres)\",\n" +
            "            \"price\": 361,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/OPPO_Reno5_1.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/diningroom-cover.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-03152209_1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/lg-smart55-01.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Refrigerator-nofrost-385L-01.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02113537.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 256,\n" +
            "            \"name_ar\": \"آيفون XR, ٦٤ جيجا، كاميرا نقية+ سيلفي + فيس تايم\",\n" +
            "            \"deal_description\": \"الشاشة:\\nحجم الشاشة: 6.1 بوصة Liquid Retina HD \\nدرجة الوضوح: 1792×828 بكسل \\nالتباين: 1400:1\\nشاشة True Tone\\nطلاء مقاوم للزيوت وبصمات الأصابع\\nدعم عرض اللغات وأنظمة كتابة متعددة في آن واحد.\\nمقاوم للطرطشة والغبار والماء: حتى 1  متر عمق حتى 30 دقيقة.\\nالكاميرا الخلفية (الرئيسية): \\nالدقة: 12 ميجا بيكسل\\nنطاق التكبير والتصغير الرقمي حتى 5 مرات.\\nأنماط التصوير: طبيعي، ستوديو، محيطي\\nتثبيت بصري للصور\\nFocus Pixels بنسبة 100% (الكاميرا الواسعة)\\nعدسة خماسية العناصر، وعدسة سداسية العناصر\\nالتقاط مجموعة ألوان كبيرة للصور وLive Photos\\nالجيل الجديد من ميزة الصور عالية الوضوح\\nتصحيح العين الحمراء\\nتثبيت تلقائي للصور\\nنمط تصوير متتابع\\nتحديد الموقع الجغرافي للصور\\nفلاش: متوفر LED\\nفيديو: 4K بيكسل (24، 25، 30  فريم في الثانية)\\nفلاتر للصورة وتسريع للفيديو والفيديو البطيء\\nالكاميرا الأمامية (السيلفي):\\nالدقة: 7 ميجا بيكسل\\nفلاش: متوفر من خلال الشاشة.\\nنمط بورتريه مع تأثير بوكيه المطور وميزة ضبط العمق\\nإضاءة بورتريه مع ستة تأثيرات (طبيعي، استوديو، محيطي، مسرح، مسرح أحادي، ضوء عالي أحادي)\\nتسجيل فيديو HDR مع Dolby Vision لغاية 30 إطاراً في الثانية\\nتسجيل فيديو 4K\u200F بمعدل 24 إطاراً في الثانية أو 25 إطاراً في الثانية أو 30 إطاراً في الثانية أو 60 إطاراً في الثانية\\nتسجيل فيديو 1080p HD بمعدل 25 إطاراً في الثانية أو 30 إطاراً في الثانية أو 60 إطاراً في الثانية\\nدعم التصوير بالحركة البطيئة\\nالجيل الجديد من ميزة HDR الذكية للصور\\nنطاق ديناميكي أوسع للفيديو بمعدل 30 إطاراً في الثانية\\nتثبيت سينمائي للفيديو (4K و1080p و720p)\\nفيديو QuickTake\\nالمعالج والذاكرة: \\nشريحة A12 Bionic\u200F\\nالجيل الثاني من المحرك العصبي Neural Engine\\nذاكرة التخزين: 128 جيجا\\nنظام التشغيل: iOS 14\\nالصوت: \\nتشغيل الصوت المكاني الغامر\\nإمكانية ضبط الحد الأقصى للصوت في الإعدادات\\nقنوات الاتصال: \\nشريحة: nano-SIM \\nواي فاي WiFi\\nبلوتوث Bluetooth 5.0\\nيو إس بي microUSB 2.0 \\nفيس تايم FaceTime\\nمستشعرات \\nمستشعر التعرف على الوجه\\nمقياس الضغط الجوي\\nجيروسكوب ثلاثي المحاور\\nمقياس تسارع\\nمستشعر تقارب\\nمستشعر الإضاءة\\nسعة البطارية: \\nتحدث حتى 25 ساعة\\nانترنت حتى 15 ساعة\\nتشغيل فيديو: حتى 16 ساعة\\nصوت وموسيقى: حتى 65 ساعة\\nتدعم الشحن السريع (50% شحن في 30 دقيقة بشاحن بقوة 20 وات أو أعلى، يباع منفردًا)\\nيدعم الشحن اللاسلكي\\nجسم الجهاز:\\nالطول: 150.9 ملم\\nالعرض: 75.5 ملم\\nالسمك: 8.3 ملم\\nالوزن: 194 جرام\\n\",\n" +
            "            \"brand\": \"Apple\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/RJF2HrgDHj56JlF.jpg\",\n" +
            "            \"name_en\": \" iPhone XR, 64GB, Dual Camera + Selfie + Facetime\",\n" +
            "            \"price\": 882,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Pulsar-01.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/191112030641elsafeerproduct.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/IwMti1gQALHYQlM.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/DPHX6zbyyfjRYno.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/samsung-50-01.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/1_g2.jpg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 151,\n" +
            "            \"name_ar\": \"طقم كنبة 3 أشخاص + كنبة شخصين + كرسي (رمادي) Ae-8070\",\n" +
            "            \"deal_description\": \"أبعاد الكنبة: 246 * 106 * 80 سم\\nأبعاد كنبة شخصين: 183 * 106 * 80 سم\\nأبعاد الكرسي: 141 * 106 * 80 سم\\nاللون: رمادي\\nمناسب للاستخدام بالداخل.\\nالطراز: عصري / مودرن\\nيناسب غرفة المعيشة\",\n" +
            "            \"brand\": \"Furniture\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/ae-8070-1.jpg\",\n" +
            "            \"name_en\": \"Sofa Set 3-2-1 (Grey) Ae-8070\",\n" +
            "            \"price\": 481,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/44zalNW6jiNnIg8.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/phiX8unFZomayPd.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot 2021-03-03 133741_24.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Samsung-Galaxy-A12_1.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/OPPOA15S_01.png\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 109,\n" +
            "            \"name_ar\": \"غسالة ملابس زانوسي 8 كيلو, أوتوماتيك تحميل أماميZWF8240SXV\",\n" +
            "            \"deal_description\": \"رقم الموديل: ZWF8240SXV\\nتفاصيل خواص الغسالة:\\nنوع الغسالة: أوتوماتيك\\nاللون: سيلفر\\nنوع التحميل : تحميل أمامي\\nسعة الغسالة: 8 كجم\\nمعدل الدوران: 1200 لفة في الدقيقة\\nباب الغسالة: كروم\\nفئة الطاقة: A\\nمستوى الضوضاء 52 ديسيبل\\nخواص أخرى: \\nخاصية البخار تعمل كمضاد للحساسية\\nقفل الطفل\\nبرنامج الغسيل اللطيف للملابس الرقيقة\\nبرنامج مكثف ساعة واحدة\\nبرنامج صحة الطفل\\nبرنامج الملابس الداكنة\\nبرنامج الملابس الرياضية\\nبرنامج الحيوانات الأليفة للحماية من الحساسية\\nجميع الإعدادات معروضة على الشاشة\",\n" +
            "            \"brand\": \"Zanussi\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/ZWF8240SXV_1.png\",\n" +
            "            \"name_en\": \"Zanussi Washing Machine 8 Kg, Front loading, Automatic ZWF8240SXV\",\n" +
            "            \"price\": 344,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/FW-16_BRS.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/44zalNW6jiNnIg8.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/C5.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-02-21164129_12.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-03154001_1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot%202021-03-02%20113414.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 282,\n" +
            "            \"name_ar\": \" تلفزيون إل جي 43 بوصة سمارت 4K UHD فائق الوضوح + واي فاي + رسيفر داخلي + 2HDMI + 1USB\",\n" +
            "            \"deal_description\": \"رقم الموديل:  43UP7550PVG\\nنوع الشاشة: Ultra HD فائقة الوضوح\\nمقاس الشاشة: 43 بوصة\\nواي فاي\\nالنوع: تليفزيون سمارت\\nنظام التشغيل: webOS +AI ThinQ\\nالصورة:\\nدرجة الوضوح: 3840x2160 بيكسل\\nزاوية مشاهدة واسعة\\nدقة عالية وفائقة\\nدقة الألوان الحقيقية\\nدقة HDR: 4K\\nتعيين درجة اللون الديناميكية بالنطاق الديناميكي العالي (HDR)\\n 7 أوضاع الصورة: مستخدم، قياسي، حيوي، رياضة، أفلام، ألعاب، توفير الطاقة.\\nمعالج: \\nمعالج رباعي النواه\\nمحسن الصورة\\nمحسن الصوت \\nضبط الصوت بتقنية الذكاء الاصطناعي\\nالذكاء الاصطناعي:\\nأوامر صوتية بالعربية \\nتحكم سهل للإنترنت المنزلي للأشياء\\nتعديل ذكي\\nلوحة معلومات في الصفحة الرئيسية\\nدعم الذكاء الاصطناعي المنزلي\\nالريموت السحري Magic Remote\\nخاصية الوصول السريع \\nتوصيل الموبايل\\nتشغيل الواقع الافتراضي\\nإير بلاي Air Play 2 \\nالصوت: \\n2 قناة * 20 واط\\nتوليف بلمسة واحدة\\nالتحكم التكيفي في الصوت \\nصوت واضح \\nمزامنة الصوت\\nتشغيل الصوت بلوتوث \\nتقنية الصوت من دولبي Dolby Audio\\nمميزات اخرى:\\nالتنبيهات الرياضية \\nأوضاع اللعب المخصصة\\nدعم الصوت المحيطي\\nدعم وضع السينما صوت وصورة\\nيمكنك مشاهدة نتفليكس \\nواي فاي\\nبلوتوث\\nريسيفر داخلي\\nيدعم صيغ الفيديو المختلفة\\nمؤقت إغلاق للجهاز\\nإمكانية التسجيل من الإرسال الرقمي الأرضي\\nمداخل ومخارج:\\nمدخل فيديو\\n2 مدخل HDMI\\nمدخل فلاشة\\nمدخل كابل إنترنت\\n\",\n" +
            "            \"brand\": \"LG\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/U6KLY8T4D4kyrAs.jpg\",\n" +
            "            \"name_en\": \" LG TV 43 ”Smart 4K UHD + WiFi + receiver + 2 HDMI + 1 USB\",\n" +
            "            \"price\": 359,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/image-1_table.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Ecobike-02.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/oppo-a53-01.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/UHD43UT7000_1.png\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Tcl-tab-8-01.jpg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 252,\n" +
            "            \"name_ar\": \"آيفون 7 بلس ، 32جيجا، كاميرا مزدوجة + سيلفي + فيس تايم\",\n" +
            "            \"deal_description\": \"الشاشة:\\nحجم الشاشة: 5.5 بوصة Retina HD\\nدرجة الوضوح:  1080 بكسل Full HD كاملة الوضوح\\nذاكرة التخزين: 32 جيجا\\nذاكرة عشوائية: 3 جيجا\\nمقاوم للماء والأتربة\\nبطارية: 2000-3000 مللي أمبير\\nشريحة واحدة\\nالكاميرا الأمامية: 7 ميجا بكسل\\nالكاميرا الخلفية: 12 ميجا بكسل\\nبلوتوث\\nنظام التشغيل: iOS 10\\nنوع المعالج: A10\\nفيس تايم\\nمستشعرات متعددة\\nتكبير وتصغير بصري حتى مرتين\\nتكبير وتصغير بصري حتى 10 مرات \\nفتح الجهاز ببصمة الأصبع من خلال الشاشة الرئيسية\\nالطول: 158.2 مم\\nالعرض: 77.9 مم\\nالعمق: 7.3 مم\",\n" +
            "            \"brand\": \"Apple\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/QFUtZp6e28ccl44.png\",\n" +
            "            \"name_en\": \" iPhone 7 Plus,32GB, Dual Camera + Selfie + Facetime\",\n" +
            "            \"price\": 823,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/FW-16_BRS.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-02-23132118_2.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Refrigerator-nofrost-385L-01.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/image-1_table.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/vQjn1w4UZHyW5Ux.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/fe3zfaHRKoasvw5.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 202,\n" +
            "            \"name_ar\": \"غسالة ملابس زانوسي 10 كيلو, أوتوماتيك تحميل علوي ZWT10710S \",\n" +
            "            \"deal_description\": \"رقم الموديل: ZWT10710S \\nتفاصيل خواص الغسالة:\\nنوع الغسالة: أوتوماتيك\\nاللون: فضي\\nنوع التحميل : تحميل علوي\\nسعة الغسالة: 10 كجم\\nمعدل الدوران: 800 لفة في الدقيقة\\nخواص أخرى: \\nمدخلين للمياه الساخنة والباردة\\nدورة غسيل قصيرة (Time Manager)\\nبرنامج الغسيل السريع\\nتقنية العناية بالحركة الإعصارية\\nشاشة ديجيتال\\nخيار تأخير بدء التشغيل\\nخاصية إيقاف التشغيل التلقائي\\nقفل حماية ضد عبث الأطفال\\nأبعاد الغسالة:\\nالارتفاع: 55 سم\\nالعرض: 56.5 سم\\nالعمق: 96.5 سم\",\n" +
            "            \"brand\": \"Kiriazi\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02171111.png\",\n" +
            "            \"name_en\": \"Zanussi Washing Machine 10 Kg, Top Load, Automatic ZWT10710S\",\n" +
            "            \"price\": 635,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/DPHX6zbyyfjRYno.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/infinix-Hot9-01.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/LG_55UN7095_1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/FP7331BM.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/1tUQJmI35dV5isS.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/191112030641elsafeerproduct.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 258,\n" +
            "            \"name_ar\": \" بوتجاز يونيفرسال غاز ستانلس أيرون كوك 5 شعلة مع مروحة، فضي\",\n" +
            "            \"deal_description\": \"رقم الموديل: \\nتفاصيل خواص البوتجاز:\\nنوع: غاز\\n5 شعلات\\nإضاءة داخلية\\nالإشعال: ذاتي\\nشواية متوفرة بموتور\\nالشعلة الكبرى ذات إشعال ثلاثي\\nصمام أمان\\nغطاء زجاجي كريستال\\nتايمر متوفر\\nمروحة للفرن\\nزجاج حراري مزدوج\\nترموستات لتنظيم الحرار\\nحوامل\\nأرجل قابلة للضبط\\nالارتفاع: 90 سم\\nالعرض: 90 سم\\nالعمق: 60 سم\",\n" +
            "            \"brand\": \"Universal\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/fVya8LgAECOKYT1.png\",\n" +
            "            \"name_en\": \" Universal Cooker - Iron Cook with a fan, 5 Burners, Silver -  GR\",\n" +
            "            \"price\": 688,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02164343.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02171330.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/O2VK7ikOeqVH7qC.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02171111.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/blroom21.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/img_9880.jpg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 47,\n" +
            "            \"name_ar\": \"توكتوك بجاج الهندي 200 سي سي (شامل الترخيص)\",\n" +
            "            \"deal_description\": \"مميزات العرض: \\nتوكتوك بجاج الهندي\\nالسعر شامل الترخيص\\nالضمان 6 شهور أو 2000 كيلومتر أيهما أقرب\\nمميزات أساسية:\\nتشغيل كهربي يعمل بثلاثة أنواع للوقود: غاز مسال، بنزين، أو غاز طبيعي.\\nيتحمل 3 أشخاص وأحمال إضافية دون التأثير على الفرامل أو على سرعة الموتور.\\nالمحرك: \\n200 سي سي\\n8.1 كيلو واط بمعدل 5000 دورة في الدقيقة\\nغاز طبيعي: 7.25 كيلو واط بمعدل 5500 دورة في الدقيقة\\nغاز مسال: 8.5 كيلو واط بمعدل 6000 دورة في الدقيقة.\\nإشعال مزدوج الشمعات.\\nإشعال رقمي بخاصية مستشعر TPS وغرفة رفع العزم.\\nالتصميم والسلامة:\\nممتص خلفي للصدمات\\nمصابيح أمامية مزدوجة\\nزجاج أمامي مستقيم\\nمصابيح خلفية مصممة للأمان\\nمؤشرات دوران ذكية\\nمقاعد كبيرة\\nطول جانبي كبير\\nكابينة قيادة مصممة لراحة السائق\\nكابينة ركوب واسعة\\nأرضية واسعة للكابينة\\n\",\n" +
            "            \"brand\": \"Bajaj\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/Bajaj-TukTuk-02.png\",\n" +
            "            \"name_en\": \"Tuktuk Bajaj RE 4S - 200 CC (Includinglicense)\",\n" +
            "            \"price\": 248,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Olympic Electric_4.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/CpvU3TxFMRJVybb.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/img_0414-1.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-02-21164129_12.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/43UN7340_1.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/cH4wGQrRxwU2MnW.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 7,\n" +
            "            \"name_ar\": \"تلفزيون سامسونج 40 بوصة سمارت LED فائق الوضوح Full HD + ريسيفر داخلي + واي فاي + ٢ HDMI + ١ USB\",\n" +
            "            \"deal_description\": \"رقم الموديل: T5300\\nنوع الشاشة: Full HD كاملة الوضوح\\nمقاس الشاشة: 40 بوصة\\nواي فاي\\nالنوع: تليفزيون سمارت\\nنظام التشغيل: Taizen\\nالصورة:\\nدرجة الوضوح: 1920*1080\\nمحرك صور الفيديو: HyperReal\\nمعدل الحركة: 50\\nمؤشر جودة الصورة: 1000\\nالمدى الديناميكي العالي: HDR\\nالتباين: ميجا\\nزاوية مشاهدة واسعة\\nتقنية الإعتام الدقيق\\nمعزز التباين\\nالأوضاع المختلفة للصورة: وضع الفيلم + الوضع الطبيعي\\nالصوت:\\nتقنية دولبي ديجيتال Dolby Digital\\nمكبرات الصوت: 2 قناة\\nرابط Multiroom\\nمميزات اخرى:\\nواي فاي\\nريسيفر داخلي\\nمتصفح انترنت\\nدعم تطبيق SmartThings من سامسونج\\nستوديو الصور\\nدعم الاتصال المباشر: واي فاي\\nإمكانية الدخول على نتفليكس\\nعرض ملفات الترجمة مع الفيديو\\nمداخل ومخارج:\\nإيريال ديجيتال\\nريسيفر داخلي + البحث التلقائي عن القنوات\\nمدخل فيديو\\n2 مدخل HDMI\\n1  مدخل فلاشة USB\\nمدخل كابل إنترنت\\nالطاقة: \\nالحد الأقصى لاستهلاك الطاقة: 100 وات\\nمصدر الطاقة: 240 فولت 50/60 هرتز\\nالملحقات:\\nريموت\\nحامل الشاشة\\nدليل المستخدم\\nالدليل الإلكتروني\\nكابل الطاقة\",\n" +
            "            \"brand\": \"Samsung\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/samsung-smart40-02.png\",\n" +
            "            \"name_en\": \"Samsung Smart 40\\\" LED FULL HD + WiFi + Internal Receiver + 2 HDMI + 1 USB\\\"\",\n" +
            "            \"price\": 176,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/ae-4525-1.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02163448_1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/ZRT45200SA.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/nV5xcJ4hIwP0P2p.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Q5Mj7EFU4zUzL06.jpg\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 141,\n" +
            "            \"name_ar\": \"ثلاجة شارب ديجيتال نوفروست سعة 450 لتر ، 2 باب لون فضى مزودة بتكنولوجيا البلازما كلاستر\",\n" +
            "            \"deal_description\": \"رقم الموديل: SJ-PC58A(SL\\nالسعة اللترية: 450 لتر\\nعدد الأبواب: 2 باب\\nلون الثلاجة: فضى\\nشاشة تحكم ديجيتال للثلاجة والفريزر\\nمزودة بتكنولوجيا البلازما كلاستر للحفاظ على الطعام مدة أطول\\nتكنولوجيا التبريد السريع Hybrid Cooling System\\nنظام فصل أوتوماتيكي\\nإنذار وتنبيه فتح الباب\\nإضاءة داخلية: LED\\nأرفف الثلاجة: زجاجية شديدة التحمل\\nمزودة بمقبض اسطواني استانليس مضاد للصدأ\\nتصميم مانع للضوضاء\\nمزودة بعجلات خلفية ومقابض علوية\\nمزودة بقوالب لمكعبات الثلج\\nتعمل في المناطق الحارة \\nمستوى كفاءة الطاقة: A (أغلى كفاءة وأقل استهلاك للكهرباء)\\nأبعاد الثلاجة: 700 × 720 × 1670 مليمتر\\nوزن الثلاجة: 74 صافي، 83 إجمالي\\nبلد المنشأ: مصر\",\n" +
            "            \"brand\": \"Sharp\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/sharp-refrigerator-450-litre-digital-2-door-silver.jpg\",\n" +
            "            \"name_en\": \"Sharp Refrigerator Digital No Frost 450 Litres, 2 Doors + Plasmacluster\",\n" +
            "            \"price\": 778,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/3IokA9mtvWk9rSO.png\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Samsung-Note-10-Lite_1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-03152209_1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Toshiba-washing-machine-VH1000s-10k-01.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/D1XiK4arKnC8a4F.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 333,\n" +
            "            \"name_ar\": \"Mediocre Wooden Wallet\",\n" +
            "            \"deal_description\": \"Script\",\n" +
            "            \"brand\": \"Script Brand\",\n" +
            "            \"image\": \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "            \"name_en\": \"Mediocre Wooden Wallet\",\n" +
            "            \"price\": 916,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/4I41TqdLGgyEidm.jpeg\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/cooker_Jumbo_fresh.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Toshiba_Washer.png\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Xg1H3K3Pshpo0mP.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 157,\n" +
            "            \"name_ar\": \"غرفة سفرة كاملة + نيش وبوفيه (ابيض) AE-D545\",\n" +
            "            \"deal_description\": \"اللون: ابيض\\nمكونة من: 8 كراسي + بوفيه + طاولة طعام + نيش\\nأبعاد الطاولة: 220 * 110 * 80 سم (العرض * الطول * إرتفاع)\\nأبعاد النيش: 160 * 45 * 110 سم (العرض * الطول * إرتفاع)\\nأبعاد البوفيه: 165* 45 * 90 سم (العرض * الطول * إرتفاع)\\nمناسب للاستخدام بالداخل.\\nالطراز: عصري / مودرن\\nيناسب غرفة السفرة\",\n" +
            "            \"brand\": \"Furniture\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/Set_fur1.png\",\n" +
            "            \"name_en\": \"Dining Room full set + Hutch + Buffet AE-D545 (White)\",\n" +
            "            \"price\": 968,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/OPPO-A93-01.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/samsung-smart43-02.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/OEKFOcDpi0HAHpv.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/C1Z7FMYN0MEI2DH.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/infinix-note7lite-1.jpg\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 269,\n" +
            "            \"name_ar\": \" ثلاجة كريازي 16 قدم نوفروست توين تربو\",\n" +
            "            \"deal_description\": \"رقم الموديل: E470 NV/2 \\nالسعة اللترية: 450 لتر\\nعدد الأبواب: 2 باب\\nلون الثلاجة: ذهبي\\nعرض: 73 سم\\nعمق: 69.3 سم\\nارتفاع 171.5 سم\\nكفاءة استهلاك الطاقة: B\\nسعة الفريزر: 98 لتر\\nخاصية نو فروست\\nالأرفف: مصنعة من زجاج مضاد للكسر\\nموتور يعمل في درجات حراراة قارية\\n\",\n" +
            "            \"brand\": \"Kiriazi\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/phiX8unFZomayPd.png\",\n" +
            "            \"name_en\": \" Kiriazi Refrigerator Twin Turbo No Frost 16 feet E470 NV/2 G\",\n" +
            "            \"price\": 767,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/5WwNxCbLY1iMvLY.jpg\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Xiaomi_Redmi_9Pro_1.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/infinix-note7lite-1.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-18101533_1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/OPPO-A93-01.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 129,\n" +
            "            \"name_ar\": \"سخان غاز اوليمبك اليكتريك ديجيتال 10 لتر بالمدخنة ابيض\",\n" +
            "            \"deal_description\": \"رقم الموديل: OEGWDG10FLWH  \\nالسعة اللترية: 10 لتر\\n2 طريقة للتحكم في ضغط المياه ودرجة الحرارة\\nلون السخان: أبيض\\nمزود بشاشة ديجيتال\\nصمام أمان\",\n" +
            "            \"brand\": \"Olympic Electric\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-02-09150838.png\",\n" +
            "            \"name_en\": \"Olympic Electric 10 Liters Flue Type White Gas Water Heater  - OEGWDG10FLWH\",\n" +
            "            \"price\": 154,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/OPPOA15S_01.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/UHD43UT7000_1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/SYM-Jet14-200-02.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02_112311.png\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/5WwNxCbLY1iMvLY.jpg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 240,\n" +
            "            \"name_ar\": \"آيفون 12 ، 128 جيجا، كاميرا مزدوجة + سيلفي + فيس تايم\",\n" +
            "            \"deal_description\": \"الشاشة:\\nحجم الشاشة: 6.1 بوصة Super Retina XDR\\nدرجة الوضوح: 2532 × 1170 بكسل\\nالتباين: 2,000,000:1\\nشاشة True Tone\\nطلاء مقاوم للزيوت وبصمات الأصابع\\nدعم عرض اللغات وأنظمة كتابة متعددة في آن واحد.\\nمقاوم للطرطشة والغبار والماء: حتى 6 متر عمق حتى 30 دقيقة.\\nالكاميرا الخلفية (الرئيسية): ثنائية\\nالعدسة الأولى: 12 ميجا بيكسل\\nالعدسة الثانية: 12 ميجا بيكسل\\nتصغير بصري حتى مرتين\\nتكبير وتصغير رقمي حتى خمس مرات.\\nأنماط التصوير: بورتريه، بوكيه، بوكيه، استوديو، محيطي، مسرح، مسرح أحادي، ضوء عالي.\\nتثبيت بصري للصور\\nFocus Pixels بنسبة 100% (الكاميرا الواسعة)\\nعدسة خماسية العناصر، وعدسة سداسية العناصر\\nغطاء العدسة من الكريستال الياقوتي\\nنمط الليل، Deep Fusion، وHDR 3 الذكية للتعرف على المشاهد\\nالتقاط مجموعة ألوان كبيرة للصور وLive Photos\\nالجيل الجديد من ميزة الصور عالية الوضوح\\nتصحيح العين الحمراء\\nتثبيت تلقائي للصور\\nنمط تصوير متتابع\\nتحديد الموقع الجغرافي للصور\\nفلاش: متوفر LED\\nفيديو: 1080 بيكسل (30 فريم في الثانية)\\nفلاتر للصورة وتسريع للفيديو والفيديو البطيء\\nالكاميرا الأمامية (السيلفي):\\nالدقة: 12 ميجا بيكسل\\nفلاش: متوفر من خلال الشاشة.\\nنمط بورتريه مع تأثير بوكيه المطور وميزة ضبط العمق\\nإضاءة بورتريه مع ستة تأثيرات (طبيعي، استوديو، محيطي، مسرح، مسرح أحادي، ضوء عالي أحادي)\\nتسجيل فيديو HDR مع Dolby Vision لغاية 30 إطاراً في الثانية\\nتسجيل فيديو 4K\u200F بمعدل 24 إطاراً في الثانية أو 25 إطاراً في الثانية أو 30 إطاراً في الثانية أو 60 إطاراً في الثانية\\nتسجيل فيديو 1080p HD بمعدل 25 إطاراً في الثانية أو 30 إطاراً في الثانية أو 60 إطاراً في الثانية\\nدعم التصوير بالحركة البطيئة\\nالجيل الجديد من ميزة HDR الذكية للصور\\nنطاق ديناميكي أوسع للفيديو بمعدل 30 إطاراً في الثانية\\nتثبيت سينمائي للفيديو (4K و1080p و720p)\\nفيديو QuickTake\\nالمعالج والذاكرة: \\nشريحة A14 Bionic\u200F\\nالجيل الثالث من المحرك العصبي Neural Engine\\nذاكرة التخزين: 128 جيجا\\nنظام التشغيل: iOS 14\\nالصوت: \\nتشغيل الصوت المكاني الغامر\\nإمكانية ضبط الحد الأقصى للصوت في الإعدادات\\nقنوات الاتصال: \\nشريحة: nano-SIM \\nواي فاي WiFi\\nبلوتوث Bluetooth 5.0\\nيو إس بي microUSB 2.0 \\nمستشعرات \\nمستشعر التعرف على الوجه\\nمقياس الضغط الجوي\\nجيروسكوب ثلاثي المحاور\\nمقياس تسارع\\nمستشعر تقارب\\nمستشعر الإضاءة\\nسعة البطارية: \\nتشغيل فيديو: حتى 17 ساعة\\nفيديو أونلاين: حتى 11 ساعات\\nصوت وموسيقى: حتى 65 ساعة\\nتدعم الشحن السريع (50% شحن في 30 دقيقة بشاحن بقوة 20 وات أو أعلى، يباع منفردًا)\\nيدعم الشحن اللاسلكي\\nجسم الجهاز:\\nالارتفاع: 146.7 ملم\\nالعرض: 71.5 ملم\\nالسمك: 7.4 ملم\\nالوزن: 162 جرام\\n\",\n" +
            "            \"brand\": \"Apple\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/O2VK7ikOeqVH7qC.png\",\n" +
            "            \"name_en\": \" iPhone 12, 128GB, Dual Camera + Selfie + Facetime\",\n" +
            "            \"price\": 738,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/MoKWlbvcIVcvENX.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-02170938.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/jqUcWRtXIv4yx6Q.png\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/MBundle-02-1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Honor9X-Lite_1.jpg\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 237,\n" +
            "            \"name_ar\": \"آيفون 11 ، 128 جيجا، كاميرا مزدوجة + سيلفي + فيس تايم\",\n" +
            "            \"deal_description\": \"الشاشة:\\nحجم الشاشة: 6.1 بوصة Liquid Retina HD\\nدرجة الوضوح: 1792 × 828\\nالتباين: 1400:1\\nشاشة True Tone\\nطلاء مقاوم للزيوت وبصمات الأصابع\\nدعم عرض اللغات وأنظمة كتابة متعددة في آن واحد.\\nمقاوم للطرطشة والغبار والماء: حتى 2 متر عمق حتى 30 دقيقة.\\nالكاميرا الخلفية (الرئيسية): ثنائية\\nالعدسة الأولى: 12 ميجا بيكسل\\nالعدسة الثانية: 12 ميجا بيكسل\\nتكبير وتصغير رقمي حتى خمس مرات.\\nأنماط التصوير: بورتريه، بوكيه، بوكيه، استوديو، محيطي، مسرح، مسرح أحادي، ضوء عالي.\\nتثبيت بصري للصور\\nعدسة خماسية العناصر، وعدسة سداسية العناصر\\nغطاء العدسة من الكريستال الياقوتي\\nنمط الليل\\nالجيل الجديد من ميزة الصور عالية الوضوح\\nتصحيح العين الحمراء\\nتثبيت تلقائي للصور\\nنمط تصوير متتابع\\nتحديد الموقع الجغرافي للصور\\nفلاش: متوفر LED\\nفيديو: 1080 بيكسل (30 فريم في الثانية)\\nفلاتر للصورة وتسريع للفيديو والفيديو البطيء\\nالكاميرا الأمامية (السيلفي):\\nالدقة: 12 ميجا بيكسل\\nفلاش: متوفر من خلال الشاشة.\\nنمط بورتريه مع تأثير بوكيه المطور وميزة ضبط العمق\\nإضاءة بورتريه مع ستة تأثيرات (طبيعي، استوديو، محيطي، مسرح، مسرح أحادي، ضوء عالي أحادي)\\nتسجيل فيديو 4K\u200F بمعدل 24 إطاراً في الثانية أو 25 إطاراً في الثانية أو 30 إطاراً في الثانية أو 60 إطاراً في الثانية\\nدعم التصوير بالحركة البطيئة لفيديو 1080p بمعدل 120 إطاراً في الثانية\\nالجيل الجديد من ميزة HDR الذكية للصور\\nنطاق ديناميكي أوسع للفيديو بمعدل 30 إطاراً في الثانية\\nتثبيت سينمائي للفيديو (4K و1080p و720p)\\nفيديو QuickTake\\nالمعالج والذاكرة: \\nشريحة A13 Bionic\u200F\\nالجيل الثالث من المحرك العصبي Neural Engine\\nذاكرة التخزين: 128 جيجا\\nنظام التشغيل: iOS 14\\nالصوت: \\nتشغيل الصوت المكاني الغامر\\nإمكانية ضبط الحد الأقصى للصوت في الإعدادات\\nقنوات الاتصال: \\nشريحة: nano-SIM \\nواي فاي WiFi\\nبلوتوث Bluetooth 5.0\\nيو إس بي microUSB 2.0 \\nمستشعرات \\nمستشعر التعرف على الوجه\\nمقياس الضغط الجوي\\nجيروسكوب ثلاثي المحاور\\nمقياس تسارع\\nمستشعر تقارب\\nمستشعر الإضاءة\\nسعة البطارية: \\nتشغيل فيديو: حتى 17 ساعة\\nفيديو أونلاين: حتى 10 ساعات\\nصوت وموسيقى: حتى 65 ساعة\\nتدعم الشحن السريع (50% شحن في 30 دقيقة بشاحن بقوة 20 وات أو أعلى، يباع منفردًا)\\nيدعم الشحن اللاسلكي\\nجسم الجهاز:\\nالارتفاع: 150.9 ملم\\nالعرض: 75.7 ملم\\nالسمك: 8.3 ملم\\nالوزن: 194 جرام\\n\",\n" +
            "            \"brand\": \"Apple\",\n" +
            "            \"image\": \"https://cdn.halan.io/images/E-Commerce/Products/D1XiK4arKnC8a4F.png\",\n" +
            "            \"name_en\": \" iPhone 11, 128GB, Dual Camera + Selfie + Face Time\",\n" +
            "            \"price\": 794,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/KTW1212-CS1.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-03174029.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/INFINIX-Note-8i-01.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/KH690LN_20020266.png\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/toshiba-smart43-02.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-02-09140842.png\"\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 343,\n" +
            "            \"name_ar\": \"Incredible Rubber Bottle\",\n" +
            "            \"deal_description\": \"Script\",\n" +
            "            \"brand\": \"Script Brand\",\n" +
            "            \"image\": \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "            \"name_en\": \"Incredible Rubber Bottle\",\n" +
            "            \"price\": 120,\n" +
            "            \"images\": [\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/file_45_9.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/5WwNxCbLY1iMvLY.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Screenshot2021-03-18101050_10.png\",\n" +
            "                \"https://cdn.halan.io68750de880f7d7c47961490c18c58e5a.jpeg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/oppo-reno3-11.jpg\",\n" +
            "                \"https://cdn.halan.io/images/E-Commerce/Products/Cooker_Fresh1.png\"\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}"
    val DETAILS_RESPONSE =""

}