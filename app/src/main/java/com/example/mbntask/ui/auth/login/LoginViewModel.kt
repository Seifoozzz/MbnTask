package com.example.mbntask.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mbntask.common.utils.DataState
import com.example.mbntask.remote.ApiManager
import com.example.mbntask.remote.models.LoginModel
import com.example.mbntask.remote.response.LoginResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

   private val apiManager: ApiManager = ApiManager()

    private val _loginState: MutableLiveData<DataState<LoginResponse>> = MutableLiveData()
    val loginState: LiveData<DataState<LoginResponse>> = _loginState

    // login user
    fun userLogin(user: LoginModel) {
        _loginState.postValue(DataState.Loading)
        try {
            viewModelScope.launch(IO) {
                val response = apiManager.getApis()!!.userLogin(user)
                if (response.isSuccessful) {

                    _loginState.postValue(DataState.SuccessMessage(response.body()?.message!!))


                } else {
                    _loginState.postValue(DataState.ErrorMessage("incorrect mobile or password"))

                }
            }
        } catch (e: Exception) {
            _loginState.postValue(DataState.ErrorMessage("" + e.localizedMessage))
        }
    }

    fun ownerLogin(owner: LoginModel) {
        _loginState.postValue(DataState.Loading)
        try {
            viewModelScope.launch(IO) {
                val response = apiManager.getApis()!!.ownerLogin(owner)
                if (response.isSuccessful) {

                    _loginState.postValue(DataState.SuccessMessage(response.body()?.message!!))


                } else {
                    _loginState.postValue(DataState.ErrorMessage("incorrect mobile or password"))

                }
            }
        } catch (e: Exception) {
            _loginState.postValue(DataState.ErrorMessage("" + e.localizedMessage))
        }
    }

    fun repLogin(rep: LoginModel) {
        _loginState.postValue(DataState.Loading)
        try {
            viewModelScope.launch(IO) {
                val response = apiManager.getApis()!!.repLogin(rep)
                if (response.isSuccessful) {

                    _loginState.postValue(DataState.SuccessMessage(response.body()?.message!!))


                } else {
                    _loginState.postValue(DataState.ErrorMessage("incorrect mobile or password"))

                }
            }
        } catch (e: Exception) {
            _loginState.postValue(DataState.ErrorMessage("" + e.localizedMessage))
        }
    }


}