package com.kdnt.notecute.di

import com.kdnt.notecute.ui.home.HomeViewModel
import com.kdnt.notecute.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { SplashViewModel() }
    viewModel { HomeViewModel() }

}
