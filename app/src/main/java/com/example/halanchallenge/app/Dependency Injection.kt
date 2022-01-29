package com.example.halanchallenge.app

import com.example.halanchallenge.data.remote.IRemoteGateway
import com.example.halanchallenge.data.repository.ProductRepository
import com.example.halanchallenge.data.repository.UserRepository
import com.example.halanchallenge.domain.repository.IProductRepository
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.remote.RetrofitCore
import com.example.halanchallenge.remote.RetrofitRemoteGateway
import com.example.halanchallenge.ui.auth.LoginViewModel
import com.example.halanchallenge.ui.products.list.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

object HalanDependencyInjector {

    fun inject () = startKoin {
            modules(arrayListOf(PresentationModule , DomainAndDataModule , RemoteModule))
        }


}

private val RemoteModule = module {

    single { RetrofitCore.getGatewayAgent() }

    single<IRemoteGateway> { RetrofitRemoteGateway(get()) }

}

private val DomainAndDataModule = module {

    factory<IUserRepository> { UserRepository(get()) }

    factory<IProductRepository> { ProductRepository(get()) }

}

private val PresentationModule = module {

    viewModel { LoginViewModel(get()) }

    viewModel { ProductListViewModel(get(), get()) }

}