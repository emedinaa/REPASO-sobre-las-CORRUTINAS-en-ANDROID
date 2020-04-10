package com.emedinaa.coroutinestest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @get: Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var vm:MainViewModel

    @Before
    fun setUp(){

        vm = MainViewModel(coroutinesTestRule.testDispatcher)
    }

    @Test
    fun `success if user and pass are not empty`() =coroutinesTestRule.testDispatcher.runBlockingTest {
        val observer = mock<Observer<Boolean>>()
        vm.loginResult.observeForever(observer)

        vm.onSubmitClicked("user","pass")

        verify(observer).onChanged(true)
    }

    @Test
    fun `error if user is  empty`()= coroutinesTestRule.testDispatcher.runBlockingTest {
        val observer = mock<Observer<Boolean>>()
        vm.loginResult.observeForever(observer)

        vm.onSubmitClicked("","pass")

        verify(observer).onChanged(false)
    }

    /**
     * Test passed : 2
     */
}