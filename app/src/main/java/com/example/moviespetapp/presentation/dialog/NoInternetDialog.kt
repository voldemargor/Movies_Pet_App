package com.example.moviespetapp.presentation.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.moviespetapp.R
import com.example.moviespetapp.databinding.DialogNoInternetBinding
import com.example.moviespetapp.presentation.contract.navigator


class NoInternetDialog : DialogFragment() {

    private var _binding: DialogNoInternetBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("DialogExceptionBinding is null")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext()).apply {
            isCancelable = false
            setContentView(R.layout.dialog_no_internet)
            setCanceledOnTouchOutside(false)
            window?.setGravity(Gravity.BOTTOM)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.attributes = window?.attributes?.apply {
                width = ViewGroup.LayoutParams.MATCH_PARENT
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                windowAnimations = R.style.DialogAnimation
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DialogNoInternetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTryAgain.setOnClickListener {
            navigator().tryReconnect()
            dismiss()
        }
        binding.btnSendReport.setOnClickListener { navigator().toast("Место для обращения в поддержку") }
    }

    companion object {
        val TAG = NoInternetDialog::class.simpleName.toString()

        fun newInstance() = NoInternetDialog()
    }

}