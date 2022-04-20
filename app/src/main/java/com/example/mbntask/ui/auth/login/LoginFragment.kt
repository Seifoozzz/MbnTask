package com.example.mbntask.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mbntask.R
import com.example.mbntask.common.base.BaseFragment
import com.example.mbntask.common.utils.DataState
import com.example.mbntask.databinding.LoginFragmentBinding
import com.example.mbntask.remote.models.LoginModel

class LoginFragment : BaseFragment() {

    private lateinit var binding: LoginFragmentBinding

    private val vm: LoginViewModel by viewModels()
    private var mobile: String? = null
    private var password: String? = null
    private var user: LoginModel? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBtnListener()
        subscribeData()
    }

    private fun setBtnListener() {
        binding.btnLogin.setOnClickListener {
            if (validateForm()) {

                user = LoginModel(mobile, password)


                when (binding.roleGroup.checkedRadioButtonId) {
                    binding.btnUser.id -> {
                        vm.userLogin(user!!)
                    }
                    binding.btnRep.id -> {
                        vm.repLogin(user!!)
                    }
                    binding.btnOwner.id -> {
                        vm.ownerLogin(user!!)
                    }
                    else -> makeToast(getString(R.string.please_choose_your_role))
                }
            }

        }
        binding.btnSign.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    private fun subscribeData() {
        vm.loginState.observe(viewLifecycleOwner) {

            when (it) {
                is DataState.SuccessMessage -> {
                    hideProgressView()
                    makeToast(it.message)
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
        if (mobile?.isEmpty() == true) {
            binding.edtPhone.requestFocus()
            binding.edtPhone.error = getString(R.string.required)
            return false
        }
        if (password?.isEmpty() == true) {
            binding.edtPassword.requestFocus()
            binding.edtPassword.error = getString(R.string.required)
            return false
        }
        return true

    }
}