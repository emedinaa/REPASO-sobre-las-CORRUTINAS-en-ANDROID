package com.emedinaa.coroutinestest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(),CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job:Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = SupervisorJob()

        submit.setOnClickListener {
            launch{
                val success =  withContext(Dispatchers.IO){
                    validateLogin(username.text.toString(),password.text.toString())
                }
                toast(if (success) "Success" else "Failure")
            }
        }
    }

    private fun validateLogin(user:String,pass:String):Boolean{
        Thread.sleep(2000)
        return user.isNotEmpty() && pass.isNotEmpty()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}

private fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}
