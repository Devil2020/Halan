object TestInstrumentedRunner {
    const val Runner = "androidx.test.runner.AndroidJUnitRunner"
}

object CustomPlugin {

    private const val gradleVersion = "4.2.1"
    private const val ktlintVersion = "10.0.0"
    private const val KotlinVersion = "1.6.10"

    const val GradleVersion = "com.android.tools.build:gradle:${gradleVersion}"
    const val GradleKTlint = "org.jlleitschuh.gradle:ktlint-gradle:${ktlintVersion}"
    const val KotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${KotlinVersion}"

}

object NetworkLibraries {

    val Retrofit = arrayListOf(
        "com.squareup.retrofit2:retrofit:${Versions.NetworkLibraries.RetrofitVersion}",
        "com.squareup.retrofit2:converter-gson:${Versions.NetworkLibraries.GsonConverter}",
        "com.google.code.gson:gson:${Versions.NetworkLibraries.Gson}",
        "com.squareup.okhttp3:okhttp:${Versions.NetworkLibraries.OkHttpVersion}",
        "com.squareup.okhttp3:logging-interceptor:${Versions.NetworkLibraries.OkHttpVersion}",
    )

}

object Coroutine {

    val Libraries = arrayListOf(
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.CoroutineVersion}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.CoroutineVersion}",
    )

}

object MaterialDesignLibraries {

    val Libraries = arrayListOf(
        "androidx.appcompat:appcompat:${Versions.MaterialDesign.AppCompatVersion}",
        "androidx.constraintlayout:constraintlayout:${Versions.MaterialDesign.ConstrainLayoutVersion}",
        "androidx.recyclerview:recyclerview:${Versions.MaterialDesign.RecyclerViewVersion}",
        "androidx.recyclerview:recyclerview-selection:${Versions.MaterialDesign.RecyclerViewSelectionVersion}",
        "androidx.cardview:cardview:${Versions.MaterialDesign.CardViewVersion}",
        "com.google.android.material:material:${Versions.MaterialDesign.MaterialDesignVersion}"
    )

    val CustomViews = arrayListOf(
        "de.hdodenhof:circleimageview:${Versions.MaterialDesign.CirculeImageView}",
        "com.github.martinstamenkovski:ARIndicatorView:${Versions.MaterialDesign.DotIndicator}"
    )

}

object TestingLibraries {
    const val Junit = "junit:junit:${Versions.Testing.JunitVersion}"

    const val JunitExtensions =
        "androidx.test.ext:junit:${Versions.Testing.JunitExtensionVersion}"

    const val Core = "androidx.test:core:${Versions.Testing.TestCoreVersion}"

    const val Falcoon = "com.jraska:falcon:${Versions.Testing.FalcoonVersion}"

    const val ArcCore = "androidx.arch.core:core-testing:${Versions.Testing.TestArcCoreVersion}"

    val Mockito = arrayListOf(
        // why use it , mockito need to generate classes at runtime , so it done in android using dex bytecode
        "com.linkedin.dexmaker:dexmaker-mockito:${Versions.Testing.MockitoDexMarkerVersion}",
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.Testing.NhaarmanMockitoVersion}",
        "org.mockito:mockito-core:${Versions.Testing.MockitoCoreVersion}",
        "org.mockito:mockito-inline:${Versions.Testing.MockitoCoreVersion}",
    )

    const val CoroutineTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Testing.KotlinCoroutineTestVersion}"

    const val Robolectric = "org.robolectric:robolectric:${Versions.Testing.RobolectricVersion}"

    const val Runner = "androidx.test:runner:${Versions.Testing.RunnersVersion}"

    const val Turbine = "app.cash.turbine:turbine:${Versions.Testing.TurbineVersion}"

    const val Hamcrest = "org.hamcrest:hamcrest-all:${Versions.Testing.HamcrestVersion}"

    const val Truth = "com.google.truth:truth:1.1.3"
}

object UITestingLibraries {
    val Espresso = arrayListOf(
        "androidx.test.espresso:espresso-core:${Versions.UITesting.EspressoVersion}"
    )
    const val FragmentTest =
        "androidx.fragment:fragment-testing:${Versions.UITesting.FragmentVersion}"

    const val AndroidXRuleTest = "androidx.test:rules:${Versions.UITesting.AndroidXRuleVersion}"
}

object AndroidLibrary {
    const val Core = "androidx.core:core-ktx:${Versions.Kotlin.CoreKtxVersion}"
}

object KotlinLibrary {
    const val Library = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin.KotlinVersion}"
}

object Jetpack {


    val LifeCycle = arrayListOf(
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Jetpack.LifeCycleVersion}",
        "androidx.lifecycle:lifecycle-extensions:${Versions.Jetpack.LifeCycleExtensionsVersion}",
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.Jetpack.LifeCycleVersion}",
        //for viewModelScope
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Jetpack.LifeCycleVersion}",
        //for lifecycleScope
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Jetpack.LifeCycleVersion}",
        "androidx.fragment:fragment:${Versions.Jetpack.FragmentKtxVersion}",
        "androidx.fragment:fragment-ktx:${Versions.Jetpack.FragmentKtxVersion}",
        "androidx.activity:activity-ktx:${Versions.Jetpack.ActivityKtxVersion}",
    )

}

object DI {

    val Koin = arrayListOf(
        "io.insert-koin:koin-core:${Versions.DI.KoinVersion}",
        "io.insert-koin:koin-android:${Versions.DI.KoinVersion}"
    )

}