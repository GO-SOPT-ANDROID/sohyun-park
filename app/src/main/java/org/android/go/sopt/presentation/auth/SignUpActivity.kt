package org.android.go.sopt.presentation.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.presentation.auth.model.SignUpViewModel
import org.android.go.sopt.util.toast


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel by viewModels<SignUpViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickEventCompleteButton()
        setClickEventBackground()

    }

    private fun setClickEventBackground() {
        binding.root.setOnClickListener { hideKeyboard() }
    }

    private fun setOnClickEventCompleteButton() {
        binding.btnSignupComplete.setOnClickListener {
            if (canUserSignUp()) {
                viewModel.signUp(
                    binding.etSignupId.text.toString(),
                    binding.etSignupPw.text.toString(),
                    binding.etSignupName.text.toString(),
                    binding.etSignupSpecialty.text.toString(),
                    message = { str -> toast(str) }
                )
            }
        }

        viewModel.signUpResult.observe(this) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }

    private fun hideKeyboard() {
        if (this != null && this.currentFocus != null) {
            val inputManager: InputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                this.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    private fun canUserSignUp(): Boolean {
        return binding.etSignupId.text.length in 6..10
                && binding.etSignupPw.text.length in 8..12
                && binding.etSignupName.text.isNotBlank()
                && binding.etSignupSpecialty.text.isNotBlank()
    }
}
