package com.emedinaa.coroutinestest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get: Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher  = TestCoroutineDispatcher()
    private lateinit var vm:MainViewModel

    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        vm = MainViewModel(testDispatcher)
    }

    @After
    fun tearDown(){
        testDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun test(){

        val observer = mock<Observer<Boolean>>()

        //runBlocking {
        testDispatcher.runBlockingTest {

            vm.loginResult.observeForever(observer)

            vm.onSubmitClicked("user","pass")

            verify(observer).onChanged(true)
        }
    }

    /**
     * Tests passed 1
     */
}