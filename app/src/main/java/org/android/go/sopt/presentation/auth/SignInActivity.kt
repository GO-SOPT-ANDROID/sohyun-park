package org.android.go.sopt.presentation.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.R
import org.android.go.sopt.data.dto.ResponseSignInDto
import org.android.go.sopt.databinding.ActivitySignInBinding
import org.android.go.sopt.presentation.auth.model.SignInViewModel
import org.android.go.sopt.presentation.main.MainActivity
import org.android.go.sopt.util.SharedPreferences
import org.android.go.sopt.util.toast

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
        binding.root.setOnClickListener { hideKeyboard() }
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
                message = { str -> toast(str) }
            )
        }

        viewModel.signInResult.observe(this) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            saveUserInfo(it.data)
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

    private fun autoSignIn() {
        if (SharedPreferences.MySharedPreferences.getUserId(this)
                .isNullOrBlank() || SharedPreferences.MySharedPreferences.getUserPw(this)
                .isNullOrBlank()
        ) {
            setClickEventOnSignInButton()
        } else {
            toast(R.string.auto_sign_in_success.toString())
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


