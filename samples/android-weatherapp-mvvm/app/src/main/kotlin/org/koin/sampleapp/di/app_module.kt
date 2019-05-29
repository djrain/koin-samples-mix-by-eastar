package org.koin.sampleapp.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.sampleapp.repository.WeatherRepository
import org.koin.sampleapp.repository.WeatherRepositoryImpl
import org.koin.sampleapp.util.rx.ApplicationSchedulerProvider
import org.koin.sampleapp.util.rx.SchedulerProvider
import org.koin.sampleapp.view.detail.DetailViewModel
import org.koin.sampleapp.view.result.ResultViewModel
import org.koin.sampleapp.view.search.SearchViewModel

val weatherModule = module {

    // ViewModel for Search View
    viewModel {
        SearchViewModel(get(), get())
    }

    // ViewModel for Result View
    viewModel {
        ResultViewModel(get(), get())
    }

    // ViewModel for Detail View
    viewModel { params ->
        DetailViewModel(params[0], get(), get())
    }

    // Weather Data Repository
    single { WeatherRepositoryImpl(get()) as WeatherRepository }
}

val rxModule = module {
    // provided components
    single { ApplicationSchedulerProvider() as SchedulerProvider }
}

// Gather all app modules
val weatherApp = listOf(weatherModule, rxModule)
