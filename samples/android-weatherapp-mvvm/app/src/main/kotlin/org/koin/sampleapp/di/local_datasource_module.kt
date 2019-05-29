package org.koin.sampleapp.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.koin.sampleapp.repository.WeatherDatasource
import org.koin.sampleapp.repository.local.AndroidJsonReader
import org.koin.sampleapp.repository.local.JsonReader
import org.koin.sampleapp.repository.local.LocalDataSource

val localAndroidDatasourceModule = module {
    single { AndroidJsonReader(androidApplication()) as JsonReader }
    single { LocalDataSource(get()) as WeatherDatasource }
}