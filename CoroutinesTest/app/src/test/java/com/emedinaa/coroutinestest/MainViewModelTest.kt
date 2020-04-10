package com.emedinaa.coroutinestest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get: Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher  = TestCoroutineDispatcher()

    @Test
    fun test(){
        Dispatchers.setMain(testDispatcher)

        val observer = mock<Observer<Boolean>>()

        val vm = MainViewModel()
        vm.loginResult.observeForever(observer)

        vm.onSubmitClicked("user","pass")

        verify(observer).onChanged(true)

        testDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    /**
    Exception in thread "main" java.lang.IllegalStateException: Module with the Main dispatcher had failed to initialize. For tests Dispatchers.setMain from kotlinx-coroutines-test module can be used
    at kotlinx.coroutines.internal.MissingMainCoroutineDispatcher.missing(MainDispatchers.kt:113)
    at kotlinx.coroutines.internal.MissingMainCoroutineDispatcher.isDispatchNeeded(MainDispatchers.kt:91)
    at kotlinx.coroutines.DispatchedContinuationKt.resumeCancellableWith(DispatchedContinuation.kt:285)
    at kotlinx.coroutines.intrinsics.CancellableKt.startCoroutineCancellable(Cancellable.kt:26)
    at kotlinx.coroutines.CoroutineStart.invoke(CoroutineStart.kt:109)
    at kotlinx.coroutines.AbstractCoroutine.start(AbstractCoroutine.kt:158)
    at kotlinx.coroutines.BuildersKt__Builders_commonKt.launch(Builders.common.kt:54)
    at kotlinx.coroutines.BuildersKt.launch(Unknown Source)
    at kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(Builders.common.kt:47)
    at kotlinx.coroutines.BuildersKt.launch$default(Unknown Source)
    at com.emedinaa.coroutinestest.MainViewModel.onSubmitClicked(MainViewModel.kt:17)
    at com.emedinaa.coroutinestest.MainViewModelTest.test(MainViewModelTest.kt:23)
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke(Method.java:498)
    at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
    at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
    at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
    at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
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
    Caused by: java.lang.RuntimeException: Method getMainLooper in android.os.Looper not mocked. See http://g.co/androidstudio/not-mocked for details.
    at android.os.Looper.getMainLooper(Looper.java)
    at kotlinx.coroutines.android.AndroidDispatcherFactory.createDispatcher(HandlerDispatcher.kt:55)
    at kotlinx.coroutines.android.AndroidDispatcherFactory.createDispatcher(HandlerDispatcher.kt:52)
     */

    /*

Wanted but not invoked:
observer.onChanged(true);
-> at com.emedinaa.coroutinestest.MainViewModelTest.test(MainViewModelTest.kt:34)
Actually, there were zero interactions with this mock.

Wanted but not invoked:
observer.onChanged(true);
-> at com.emedinaa.coroutinestest.MainViewModelTest.test(MainViewModelTest.kt:34)
Actually, there were zero interactions with this mock.

	at com.emedinaa.coroutinestest.MainViewModelTest.test(MainViewModelTest.kt:34)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
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