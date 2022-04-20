package com.example.mbntask.remote

import com.example.mbntask.remote.models.LoginModel
import com.example.mbntask.remote.response.LoginResponse
import com.example.mbntask.remote.models.SignUpModel
import com.example.mbntask.remote.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WebService {


    @POST("user/register")
    suspend fun userRegister(
        @Body user: SignUpModel
    ): Response<SignUpResponse>

    @POST("user/login")
    suspend fun userLogin(
        @Body user: LoginModel
    ): Response<LoginResponse>


    @POST("owner/register")
    suspend fun ownerRegister(
        @Body owner: SignUpModel
    ): Response<SignUpResponse>


    @POST("owner/login")
    suspend fun ownerLogin(
        @Body owner: LoginModel
    ): Response<LoginResponse>


    @POST("rep/register")
    suspend fun repRegister(
        @Body user: SignUpModel
    ): Response<SignUpResponse>


    @POST("rep/login")
    suspend fun repLogin(
        @Body user: LoginModel
    ): Response<LoginResponse>


}