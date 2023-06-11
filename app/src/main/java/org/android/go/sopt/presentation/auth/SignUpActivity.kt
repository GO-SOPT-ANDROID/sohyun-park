package org.android.go.sopt.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.presentation.auth.model.SignUpViewModel
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.toastByString


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel

        setOnClickEventCompleteButton()
        setClickEventBackground()
        setSignUpValidation()
        observe()
    }

    private fun setClickEventBackground() {
        binding.root.setOnClickListener { if (this != null && this.currentFocus != null) binding.root.hideKeyboard() }
    }

    private fun setOnClickEventCompleteButton() {
        binding.btnSignupComplete.setOnClickListener {
            if (viewModel.checkSignUpValidation()) {
                viewModel.signUp(
                    binding.etSignupId.text.toString(),
                    binding.etSignupPw.text.toString(),
                    binding.etSignupName.text.toString(),
                    binding.etSignupSpecialty.text.toString(),
                    message = { str -> toastByString(str) }
                )
            }
        }
    }

    private fun setSignUpValidation() {
        binding.btnSignupComplete.isEnabled = viewModel.checkSignUpValidation()
    }

    private fun observe() {
        viewModel.signUpResult.observe(this) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        viewModel.id.observe(this) {
            with(binding) {
                if (viewModel!!.checkIdFormation()) {
                    tvSignUpIdWaring.visibility = View.INVISIBLE
                    etSignupId.setBackgroundResource(R.drawable.rectangle_grey_500_radius_8)
                } else {
                    tvSignUpIdWaring.visibility = View.VISIBLE
                    etSignupId.setBackgroundResource(R.drawable.rectangle_red_500_radius_8)
                }
            }
        }

        viewModel.pw.observe(this) {
            with(binding) {
                if (viewModel!!.checkPwFormation()) {
                    tvSignUpPwWaring.visibility = View.INVISIBLE
                    etSignupPw.setBackgroundResource(R.drawable.rectangle_grey_500_radius_8)
                } else {
                    tvSignUpPwWaring.visibility = View.VISIBLE
                    etSignupPw.setBackgroundResource(R.drawable.rectangle_red_500_radius_8)
                }
            }
        }

        viewModel.isEnabledSignUpButton.observe(this) {
            binding.btnSignupComplete.isEnabled = it
        }
    }

}
