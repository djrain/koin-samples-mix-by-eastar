package org.koin.sampleapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.sampleapp.di.remoteDatasourceModule
import org.koin.sampleapp.di.testApp
import org.koin.sampleapp.di.weatherApp
import org.koin.test.KoinTest

class DryRunTest : KoinTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    val defaultParameters = mapOf("id" to "ID")

    @After
    fun after() {
    }

    @Test
    fun testRemoteConfiguration() {
        // Use remote web service with SERVER_URL property from koin.properties file
        startKoin { weatherApp + remoteDatasourceModule }
//        dryRun  { defaultParameters }
    }

    @Test
    fun testLocalConfiguration() {
        startKoin { testApp }
//        dryRun { defaultParameters }
    }
}