package com.example.mbntask.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.util.Log

import com.google.android.gms.location.*


class Utils {

    companion object {




       private lateinit var fusedLocationClient: FusedLocationProviderClient
        var myLocation: LocationResult? = null
        private lateinit var locationRequest: LocationRequest





        @SuppressLint("MissingPermission")
        fun getCurrentLocation(context :Context) {
            fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(context)
            getNewLocation()
        }

        @SuppressLint("MissingPermission")
        fun getNewLocation() {
            locationRequest = LocationRequest()
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.myLooper()!!
            )
        }

        private val locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                myLocation = p0


            }
        }


    }

}