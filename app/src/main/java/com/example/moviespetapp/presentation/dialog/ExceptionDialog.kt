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
import com.example.moviespetapp.databinding.DialogExceptionV2Binding
import com.example.moviespetapp.presentation.contract.navigator


class ExceptionDialog : DialogFragment() {

    private var _binding: DialogExceptionV2Binding? = null
    private val binding get() = _binding ?: throw RuntimeException("DialogExceptionBinding is null")

    private lateinit var message: String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext()).apply {
            isCancelable = false
            setContentView(R.layout.dialog_exception_v2)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DialogExceptionV2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvErrorMessage.text = "Error: $message"
        binding.btnCloseApp.setOnClickListener { navigator().finish() }
        binding.btnSendReport.setOnClickListener { navigator().toast("Место для обращения в поддержку") }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(KEY_MESSAGE)) throw RuntimeException("Param MESSAGE is absent")
        args.getString(KEY_MESSAGE)?.let { message = it }
    }

    companion object {
        val TAG = ExceptionDialog::class.simpleName.toString()
        private const val KEY_MESSAGE = "message"

        fun newInstance(message: String) = ExceptionDialog().apply {
            arguments = Bundle().apply {
                putString(KEY_MESSAGE, message)
            }
        }
    }

}