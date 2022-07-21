package com.example.halanchallenge.app

import android.content.Context
import com.example.halanchallenge.data.local.ILocalGateway
import com.example.halanchallenge.data.remote.IRemoteGateway
import com.example.halanchallenge.data.repository.ProductRepository
import com.example.halanchallenge.data.repository.UserRepository
import com.example.halanchallenge.domain.repository.IProductRepository
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.domain.usecase.IProductsGateway
import com.example.halanchallenge.domain.usecase.IUserGateway
import com.example.halanchallenge.domain.usecase.ProductsGateway
import com.example.halanchallenge.domain.usecase.UserGateway
import com.example.halanchallenge.local.SharedPreferenceLocalGateway
import com.example.halanchallenge.local.SharedPreferencesManager
import com.example.halanchallenge.remote.RetrofitCore
import com.example.halanchallenge.remote.RetrofitRemoteGateway
import com.example.halanchallenge.utils.base.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

object HalanDependencyInjector {
    fun inject(applicationContext: Context) = startKoin {
        androidContext(applicationContext)
        modules(arrayListOf(PresentationModule, DomainAndDataModule, LocaleModule, RemoteModule))
    }
}

private val RemoteModule = module {
    single { RetrofitCore.getGatewayAgent() }
    single<IRemoteGateway> { RetrofitRemoteGateway(get()) }
}

private val LocaleModule = module {
    single {
        androidContext().getSharedPreferences(
            Constants.SHARED_PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
    }
    single { SharedPreferencesManager(get()) }
    single<ILocalGateway> { SharedPreferenceLocalGateway(get()) }
}

private val DomainAndDataModule = module {
    factory<IUserRepository> { UserRepository(get(), get()) }
    factory<IProductRepository> { ProductRepository(get()) }
    factory<IUserGateway> { UserGateway(get()) }
    factory<IProductsGateway> { ProductsGateway(get()) }
}

private val PresentationModule = module {

}