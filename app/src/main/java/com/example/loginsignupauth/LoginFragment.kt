package com.example.loginsignupauth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.loginsignupauth.databinding.FragmentLoginBinding
import com.example.loginsignupauth.utils.*


class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)
        emailTextChangeListener()
        passwordTextChangeListener()

        with(binding){
            etEmail.addTextChangedListener(validWatcher)
            etPassword.addTextChangedListener(validWatcher)
            etPassword.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
                override fun beforeTextChanged(s: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
                override fun afterTextChanged(arg0: Editable) {
                    if (etPassword.text.toString().contains(" ")) {
                        etPassword.setText(etPassword.text.toString().replace(" ", ""))
                        etPassword.text?.let { etPassword.setSelection(it.length) }
                    }
                }
            })
        }


    }

    private fun emailTextChangeListener(){
        with(binding){
            etEmail.doOnTextChanged { _, _, _, _ ->
                when{
                    etEmail.text.toString().isValidEmail() -> {
                        tilEmail.helperText = ""
                    }
                    etEmail.text.toString().isEmpty() -> {
                        tilEmail.helperText = "Required"
                    }
                    else -> {
                        tilEmail.helperText = "Invalid email address"
                    }
                }
            }
        }
    }

    private fun passwordTextChangeListener(){
        with(binding){
            etPassword.doOnTextChanged { _, _, _, _ ->
                when{
                    !etPassword.text.toString().atLeastOneLowerCase() -> {
                        tilPassword.helperText = "must contain at least one lowercase"
                    }
                    !etPassword.text.toString().atLeastOneUpperCase() -> {
                        tilPassword.helperText = "must contain at least one uppercase"
                    }
                    !etPassword.text.toString().atLeastOneDigit() -> {
                        tilPassword.helperText = "must contain at least one digit"
                    }
                    !etPassword.text.toString().atLeastOneSpecialCharacter() -> {
                        tilPassword.helperText = "must contain at least one special character"
                    }
                    !etPassword.text.toString().atLeastEightCharacters() -> {
                        tilPassword.helperText = "must be at least 8 characters long"
                    }
                    else -> {
                        tilPassword.helperText = ""
                    }
                }
            }
        }
    }

    private val validWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val email = binding.etEmail.text.toString().isValidEmail()
            val password = when{
                binding.etPassword.text.toString().atLeastOneLowerCase() &&
                binding.etPassword.text.toString().atLeastOneUpperCase() &&
                binding.etPassword.text.toString().atLeastOneDigit() &&
                binding.etPassword.text.toString().atLeastOneSpecialCharacter() &&
                binding.etPassword.text.toString().atLeastEightCharacters() -> {
                    true
                }
                else -> {
                    false
                }
            }
            binding.loginBtn.isEnabled = email == true && password == true
        }
        override fun afterTextChanged(s: Editable) {}
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}