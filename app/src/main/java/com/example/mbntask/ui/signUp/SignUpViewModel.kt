package com.example.mbntask.ui.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mbntask.common.utils.DataState
import com.example.mbntask.remote.ApiManager
import com.example.mbntask.remote.response.LoginResponse
import com.example.mbntask.remote.models.SignUpModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

   private val apiManager: ApiManager = ApiManager()

    private val _signUpState: MutableLiveData<DataState<LoginResponse>> = MutableLiveData()
    val signUpState: LiveData<DataState<LoginResponse>> = _signUpState

    fun userRegister(user: SignUpModel) {
        _signUpState.postValue(DataState.Loading)
        try {
            viewModelScope.launch(IO) {
                val response = apiManager.getApis()!!.userRegister(user)

                if (response.isSuccessful) {
                    _signUpState.postValue(DataState.SuccessMessage(response.body()?.message!!))
                } else {
                    _signUpState.postValue(DataState.ErrorMessage("please enter correct data"))
                }
            }

        } catch (e: Exception) {

            _signUpState.postValue(DataState.ErrorMessage("" + e.localizedMessage))
        }
    }

    fun ownerRegister(user: SignUpModel) {
        _signUpState.postValue(DataState.Loading)
        try {
            viewModelScope.launch(IO) {
                val response = apiManager.getApis()!!.userRegister(user)

                if (response.isSuccessful) {

                    _signUpState.postValue(DataState.SuccessMessage(response.body()?.message!!))
                } else {

                    _signUpState.postValue(DataState.ErrorMessage("please enter correct data"))
                }
            }

        } catch (e: Exception) {

            _signUpState.postValue(DataState.ErrorMessage("" + e.localizedMessage))
        }
    }

    fun repRegister(user: SignUpModel) {
        _signUpState.postValue(DataState.Loading)
        try {
            viewModelScope.launch(IO) {
                val response = apiManager.getApis()!!.userRegister(user)

                if (response.isSuccessful) {
                    _signUpState.postValue(DataState.SuccessMessage(response.body()?.message!!))
                } else {
                    _signUpState.postValue(DataState.ErrorMessage("please enter correct data"))
                }
            }

        } catch (e: Exception) {

            _signUpState.postValue(DataState.ErrorMessage("" + e.localizedMessage))
        }
    }
}