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
    fun `success if user and pass are not empty`() =testDispatcher.runBlockingTest {
        val observer = mock<Observer<Boolean>>()
        vm.loginResult.observeForever(observer)

        vm.onSubmitClicked("user","pass")

        verify(observer).onChanged(true)
    }

    @Test
    fun `error if user is  empty`()= testDispatcher.runBlockingTest {
        val observer = mock<Observer<Boolean>>()
        vm.loginResult.observeForever(observer)

        vm.onSubmitClicked("","pass")

        verify(observer).onChanged(false)
    }

    /**
    Argument(s) are different! Wanted:
    observer.onChanged(true);
    -> at com.emedinaa.coroutinestest.MainViewModelTest$error if user is  empty$1.invokeSuspend(MainViewModelTest.kt:56)
    Actual invocations have different arguments:
    observer.onChanged(false);
    -> at androidx.lifecycle.LiveData.considerNotify(LiveData.java:113)

    Comparison Failure:
    <Click to see difference>

    Argument(s) are different! Wanted:
    observer.onChanged(true);
    -> at com.emedinaa.coroutinestest.MainViewModelTest$error if user is  empty$1.invokeSuspend(MainViewModelTest.kt:56)
    Actual invocations have different arguments:
    observer.onChanged(false);
    -> at androidx.lifecycle.LiveData.considerNotify(LiveData.java:113)

    at com.emedinaa.coroutinestest.MainViewModelTest$error if user is  empty$1.invokeSuspend(MainViewModelTest.kt:56)
    at com.emedinaa.coroutinestest.MainViewModelTest$error if user is  empty$1.invoke(MainViewModelTest.kt)
    at kotlinx.coroutines.test.TestBuildersKt$runBlockingTest$deferred$1.invokeSuspend(TestBuilders.kt:50)
    at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
    at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:56)
    at kotlinx.coroutines.test.TestCoroutineDispatcher.dispatch(TestCoroutineDispatcher.kt:50)
    at kotlinx.coroutines.DispatchedContinuationKt.resumeCancellableWith(DispatchedContinuation.kt:288)
    at kotlinx.coroutines.intrinsics.CancellableKt.startCoroutineCancellable(Cancellable.kt:26)
    at kotlinx.coroutines.CoroutineStart.invoke(CoroutineStart.kt:109)
    at kotlinx.coroutines.AbstractCoroutine.start(AbstractCoroutine.kt:158)
    at kotlinx.coroutines.BuildersKt__Builders_commonKt.async(Builders.common.kt:89)
    at kotlinx.coroutines.BuildersKt.async(Unknown Source)
    at kotlinx.coroutines.BuildersKt__Builders_commonKt.async$default(Builders.common.kt:82)
    at kotlinx.coroutines.BuildersKt.async$default(Unknown Source)
    at kotlinx.coroutines.test.TestBuildersKt.runBlockingTest(TestBuilders.kt:49)
    at kotlinx.coroutines.test.TestBuildersKt.runBlockingTest(TestBuilders.kt:78)
    at com.emedinaa.coroutinestest.MainViewModelTest.error if user is  empty(MainViewModelTest.kt:50)
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke(Method.java:498)
    at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
    at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
    at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
    at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
    at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)
    at org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:27)
    at org.junit.rules.TestWatcher$1.evaluate(TestWatcher.java:55)
    at org.junit.rules.RunRules.evaluate(RunRules.java:20)
    at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
    at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
    at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
    at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
    at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
    at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
    at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
    at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
    at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
    at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
    at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
    at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
    at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
    at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)

     */
}