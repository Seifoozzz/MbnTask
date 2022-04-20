package com.example.mbntask.ui.signUp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mbntask.R
import com.example.mbntask.common.Constant
import com.example.mbntask.common.base.BaseFragment
import com.example.mbntask.common.utils.DataState
import com.example.mbntask.common.utils.Utils
import com.example.mbntask.databinding.SignUpFragmentBinding
import com.example.mbntask.remote.models.SignUpModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class SignUpFragment : BaseFragment() {


    private lateinit var binding: SignUpFragmentBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val vm: SignUpViewModel by viewModels()
    private var mobile: String? = null
    private var password: String? = null
    private var name: String? = null
    private var address: String? = null
    private var email: String? = null
    private var user: SignUpModel? = null
    private var cityId: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignUpFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        fetchLocation()
        setBtnListener()
        subscribeData()

    }

    private fun fetchLocation() {

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )

        } else
            Utils.getCurrentLocation(requireContext())

    }

    override fun onResume() {
        super.onResume()
        Utils.getCurrentLocation(requireContext())
    }

    private fun setBtnListener() {
        binding.btnSignup.setOnClickListener {
            if (validateForm()) {
                mobile = binding.edtPhone.text.toString()
                name = binding.edtName.text.toString()
                email = binding.edtEmail.text.toString()
                address = binding.edtAddress.text.toString()
                password = binding.edtPassword.text.toString()
                cityId = binding.edtCityID.text.toString()
                user = SignUpModel(
                    name,
                    email,
                    mobile,
                    address,
                    password,
                    Constant.defaultCustomerImage,
                    cityId,
                    Utils.myLocation!!.lastLocation.longitude.toString(),
                    Utils.myLocation!!.lastLocation.latitude.toString()
                )

                when (binding.roleGroup.checkedRadioButtonId) {
                    binding.btnUser.id -> {
                        vm.userRegister(user!!)
                    }
                    binding.btnRep.id -> {
                        vm.repRegister(user!!)
                    }
                    binding.btnOwner.id -> {
                        vm.ownerRegister(user!!)
                    }
                    else -> makeToast(getString(R.string.please_choose_your_role))
                }
            }
        }
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

    }

    private fun subscribeData() {
        vm.signUpState.observe(viewLifecycleOwner) {

            when (it) {
                is DataState.SuccessMessage -> {
                    makeToast(it.message)
                    hideProgressView()
                }
                is DataState.Loading -> showProgressView()
                is DataState.Finished -> hideProgressView()

                is DataState.ErrorMessage -> {
                    hideProgressView()
                    makeToast(it.error)

                }

            }
        }
    }

    private fun validateForm(): Boolean {
        mobile = binding.edtPhone.text.trim().toString()
        password = binding.edtPassword.text.trim().toString()
        name = binding.edtName.text.trim().toString()
        email = binding.edtEmail.text.trim().toString()
        address = binding.edtAddress.text.trim().toString()
        cityId = binding.edtCityID.text.trim().toString()
        if (mobile?.isEmpty() == true) {
            binding.edtPhone.requestFocus()
            binding.edtPhone.error = getString(R.string.required)
            return false
        }
        if (name?.isEmpty() == true) {
            binding.edtName.requestFocus()
            binding.edtName.error = getString(R.string.required)
            return false
        }
        if (email?.isEmpty() == true) {
            binding.edtEmail.requestFocus()
            binding.edtEmail.error = getString(R.string.required)
            return false
        }
        if (address?.isEmpty() == true) {
            binding.edtAddress.requestFocus()
            binding.edtAddress.error = getString(R.string.required)
            return false
        }
        if (password?.isEmpty() == true) {
            binding.edtPassword.requestFocus()
            binding.edtPassword.error = getString(R.string.required)
            return false
        }
        if (cityId?.isEmpty() == true) {
            binding.edtCityID.requestFocus()
            binding.edtCityID.error = getString(R.string.required)
            return false
        }
        return true

    }


}




