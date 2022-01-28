package com.example.halanchallenge.app

import android.app.Application
import com.example.halanchallenge.data.remote.IRemoteGateway
import com.example.halanchallenge.data.repository.ProductRepository
import com.example.halanchallenge.data.repository.UserRepository
import com.example.halanchallenge.domain.repository.IProductRepository
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.remote.RetrofitCore
import com.example.halanchallenge.remote.RetrofitGateway
import com.example.halanchallenge.remote.RetrofitRemoteGateway
import com.example.halanchallenge.ui.auth.LoginViewModel
import com.example.halanchallenge.ui.products.list.ProductListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

object HalanDependencyInjector {

    fun inject (application :Application) = startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(application)
            modules(arrayListOf(PresentationModule , DomainAndDataModule , RemoteModule))
        }


}

private val RemoteModule = module {

    single<RetrofitGateway> { RetrofitCore.getGatewayAgent() }

    single<IRemoteGateway> { RetrofitRemoteGateway(get()) }

}

private val DomainAndDataModule = module {

    single<IUserRepository> { UserRepository(get()) }

    single<IProductRepository> { ProductRepository(get()) }

}

private val PresentationModule = module {

    viewModel { LoginViewModel(get()) }

    viewModel { ProductListViewModel(get(), get()) }

}