package org.koin.sampleapp.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import junit.framework.Assert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.sampleapp.di.testApp
import org.koin.sampleapp.model.DailyForecastModel
import org.koin.sampleapp.repository.WeatherRepository
import org.koin.sampleapp.view.detail.DetailViewModel
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailViewModelTest : KoinTest {

    val viewModel: DetailViewModel by inject { parametersOf("ID") }
    val repository: WeatherRepository by inject()

    @Mock
    lateinit var uiData: Observer<DailyForecastModel>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin { testApp }
    }

    @After
    fun after() {
    }

    @Test
    fun testGotDetail() {
        // Setup data
        repository.searchWeather("Toulouse").blockingGet()
        val list = repository.getWeather().blockingGet()

        // Observe
        viewModel.uiData.observeForever(uiData)

        // Select data to notify
        val weather = list.first()
        viewModel.getDetail(weather.id)

        // Has received data
        Assert.assertNotNull(viewModel.uiData.value)

        // Has been notified
        Mockito.verify(uiData).onChanged(weather)
    }
}