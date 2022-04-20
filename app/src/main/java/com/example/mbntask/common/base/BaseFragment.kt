package com.example.mbntask.common.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mbntask.ViewsManager

abstract class BaseFragment : Fragment(){

    protected fun showProgressView() {
        (requireActivity() as ViewsManager).showProgressBar()
    }

    protected fun hideProgressView() {
        (requireActivity() as ViewsManager).hideProgressBar()
    }

    protected fun makeToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }
}