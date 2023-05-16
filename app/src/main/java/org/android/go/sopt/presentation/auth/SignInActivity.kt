package org.android.go.sopt.presentation.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import org.android.go.sopt.*
import org.android.go.sopt.data.dto.RequestSignInDto
import org.android.go.sopt.data.dto.ResponseSignInDto
import org.android.go.sopt.data.factory.ServicePool
import org.android.go.sopt.databinding.ActivitySignInBinding
import org.android.go.sopt.presentation.main.MainActivity
import org.android.go.sopt.util.SharedPreferences
import retrofit2.Call
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val signInService = ServicePool.signInService
    private lateinit var savedName: String
    private lateinit var savedSpeciality: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickSignUp()
        autoSignIn()
        completeSignIn()

        binding.root.setOnClickListener {
            hideKeyboard()
        }

    }


    private fun clickSignUp() {

        binding.btnSigninSignup.setOnClickListener {

            val signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }
    }

    private fun completeSignIn() {

        val mainIntent = Intent(this, MainActivity::class.java)

        binding.btnSigninLogin.setOnClickListener {
            signInService.signIn(
                with(binding) {
                    RequestSignInDto(
                        etSigninId.text.toString(),
                        etSigninPw.text.toString(),
                    )
                }
            ).enqueue(object : retrofit2.Callback<ResponseSignInDto> {
                override fun onResponse(
                    call: Call<ResponseSignInDto>,
                    response: Response<ResponseSignInDto>,
                ) {
                    if (response.isSuccessful) {
                        response.body()?.message?.let { binding.root.showToast(it) }
                            ?: "로그인에 성공했습니다."

                        response.body()?.data?.name?.let { savedName = it } ?: "정보 오류"
                        response.body()?.data?.skill?.let { savedSpeciality = it } ?: "정보 오류"
                        saveSignInData(savedName, savedSpeciality)

                        startActivity(mainIntent)


                    } else {
                        // 응답 실패!
                        binding.root.showToast("로그인에 실패했습니다.")
                        response.body()?.message?.let { binding.root.showToast(it) }
                            ?: "서버통신 실패(40X)"
                    }
                }

                override fun onFailure(call: Call<ResponseSignInDto>, t: Throwable) {
                    // 왜 안 오지?!
                    binding.root.showToast("로그인에 실패했습니다.")
                    t.message?.let { binding.root.showToast(it) } ?: "서버통신 실패(응답값 X)"
                }
            })
        }

    }

    private fun hideKeyboard() {
        if (this != null && this.currentFocus != null) {

            val inputManager: InputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                this.currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }


    private fun autoSignIn() {
        if (SharedPreferences.MySharedPreferences.getUserId(this).isNullOrBlank()
            || SharedPreferences.MySharedPreferences.getUserPw(this).isNullOrBlank()
        ) {
            completeSignIn()
        } else {
            val intent = Intent(this, MainActivity::class.java)

            binding.root.showToast("자동 로그인 되었습니다.")

            startActivity(intent)
            finish()
        }
    }

    private fun saveSignInData(name: String, speciality: String) {

        SharedPreferences.MySharedPreferences.setUserId(
            this,
            binding.etSigninId.text.toString()
        )
        SharedPreferences.MySharedPreferences.setUserPw(
            this,
            binding.etSigninPw.text.toString()
        )
        SharedPreferences.MySharedPreferences.setUserName(this, name)
        SharedPreferences.MySharedPreferences.setUserSpeciality(this, speciality)

    }

}

