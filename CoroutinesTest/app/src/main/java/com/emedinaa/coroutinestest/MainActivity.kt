package com.emedinaa.coroutinestest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submit.setOnClickListener {
            lifecycleScope.launch{
                val success1 =  withContext(Dispatchers.IO){
                    validateLogin1(username.text.toString(),password.text.toString())
                }

                val success2 =  withContext(Dispatchers.IO){
                    validateLogin2(username.text.toString(),password.text.toString())
                }
                toast(if (success1 && success2) "Success" else "Failure")
            }
        }
    }

    private fun validateLogin1(user:String,pass:String):Boolean{
        Thread.sleep(2000)
        return user.isNotEmpty() && pass.isNotEmpty()
    }

    private fun validateLogin2(user:String,pass:String):Boolean{
        Thread.sleep(2000)
        return user.isNotEmpty() && pass.isNotEmpty()
    }

}

private fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}
