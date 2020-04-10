package com.emedinaa.coroutinestest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel:ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult:LiveData<Boolean> get() = _loginResult

    fun onSubmitClicked(user:String,pass:String){
        viewModelScope.launch {
            //val result = withContext(Dispatchers.IO){ validateLogin(user,pass) }
            //_loginResult.value =result

            _loginResult.value= withContext(Dispatchers.IO){ validateLogin(user,pass) }
        }
    }

    private fun validateLogin(user:String,pass:String):Boolean{
        Thread.sleep(2000)
        return user.isNotEmpty() && pass.isNotEmpty()
    }

    /*override fun onCleared() {
        super.onCleared()
    }*/
}