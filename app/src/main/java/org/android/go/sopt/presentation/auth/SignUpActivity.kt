package org.android.go.sopt.presentation.auth

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.data.dto.RequestSignUpDto
import org.android.go.sopt.data.dto.ResponseSignUpDto
import org.android.go.sopt.data.factory.ServicePool
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.util.showToast
import retrofit2.Call
import retrofit2.Response


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val signUpService = ServicePool.signUpService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        completeSignUp()

        binding.root.setOnClickListener {
            hideKeyboard()
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

    private fun canUserSignUp(): Boolean {
        return binding.etSignupId.text.length in 6..10
                && binding.etSignupPw.text.length in 8..12
                && binding.etSignupName.text.isNotBlank()
                && binding.etSignupSpecialty.text.isNotBlank()
    }


    private fun completeSignUp() {
        binding.btnSignupComplete.setOnClickListener {
            signUpService.signUp(
                with(binding) {
                    RequestSignUpDto(
                        etSignupId.text.toString(),
                        etSignupPw.text.toString(),
                        etSignupName.text.toString(),
                        etSignupSpecialty.text.toString()
                    )
                }
            ).enqueue(object : retrofit2.Callback<ResponseSignUpDto> {
                override fun onResponse(
                    call: Call<ResponseSignUpDto>,
                    response: Response<ResponseSignUpDto>,
                ) {
                    if (response.isSuccessful && canUserSignUp()) {
                        response.body()?.message?.let { binding.root.showToast(it) }
                            ?: "회원가입에 성공했습니다."
                        if (!isFinishing) finish()
                    } else if (!canUserSignUp()) {
                        binding.root.showToast("회원가입에 실패했습니다.")
                    } else {
                        // 응답 실패!
                        response.body()?.message?.let { binding.root.showToast(it) }
                            ?: "서버통신 실패(40X)"
                    }
                }

                override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                    // 왜 안 오지?!
                    t.message?.let { binding.root.showToast(it) } ?: "서버통신 실패(응답값 X)"
                }
            })

        }


    }

}