package org.android.go.sopt.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.R
import org.android.go.sopt.data.dto.ResponseSignInDto
import org.android.go.sopt.databinding.ActivitySignInBinding
import org.android.go.sopt.presentation.auth.model.SignInViewModel
import org.android.go.sopt.presentation.main.MainActivity
import org.android.go.sopt.util.SharedPreferences
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.toastByInt
import org.android.go.sopt.util.toastByString

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var savedId: String
    private lateinit var savedPw: String
    private lateinit var savedName: String
    private lateinit var savedSpeciality: String
    private val viewModel by viewModels<SignInViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickEventOnSignUpButton()
        setClickEventBackground()
        autoSignIn()
    }

    private fun setClickEventBackground() {
        binding.root.setOnClickListener { if (this != null && this.currentFocus != null) binding.root.hideKeyboard() }
    }

    private fun setClickEventOnSignUpButton() {
        binding.btnSigninSignup.setOnClickListener {
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent)
            finish()
        }
    }

    private fun setClickEventOnSignInButton() {
        binding.btnSigninLogin.setOnClickListener {
            viewModel.signIn(
                binding.etSigninId.text.toString(),
                binding.etSigninPw.text.toString(),
                message = { str -> toastByString(str) }
            )
        }

        viewModel.signInResult.observe(this) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            saveUserInfo(it.data)
        }
    }

    private fun autoSignIn() {
        if (SharedPreferences.MySharedPreferences.getUserId(this)
                .isNullOrBlank() || SharedPreferences.MySharedPreferences.getUserPw(this)
                .isNullOrBlank()
        ) {
            setClickEventOnSignInButton()
        } else {
            toastByInt(R.string.auto_sign_in_success)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun saveSignInData(id: String, pw: String, name: String, speciality: String) {
        SharedPreferences.MySharedPreferences.setUserId(this, id)
        SharedPreferences.MySharedPreferences.setUserPw(this, pw)
        SharedPreferences.MySharedPreferences.setUserName(this, name)
        SharedPreferences.MySharedPreferences.setUserSpeciality(this, speciality)
    }

    private fun saveUserInfo(response: ResponseSignInDto.SignInData) {
        response.id?.let { savedId = it }
        response.skill?.let { savedPw = it }
        response.name?.let { savedName = it }
        response.skill?.let { savedSpeciality = it }
        saveSignInData(savedId, savedPw, savedName, savedSpeciality)
    }

}


