package org.android.go.sopt

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.android.go.sopt.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var name: String
    private lateinit var specialty: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUp()
        autoSignIn()

        binding.root.setOnClickListener {
            hideKeyboard()
        }

    }


    private fun signUp() {

        val resultLauncher: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    id = result.data?.getStringExtra("id") ?: ""
                    pw = result.data?.getStringExtra("pw") ?: ""
                    name = result.data?.getStringExtra("name") ?: ""
                    specialty = result.data?.getStringExtra("specialty") ?: ""
                }
            }

        binding.btnSigninSignup.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)

        }
    }

    private fun signIn() {
        binding.btnSigninLogin.setOnClickListener {

            if (binding.etSigninId.text.toString() == id && binding.etSigninPw.text.toString() == pw) {

                SharedPreferences.MySharedPreferences.setUserId(
                    this,
                    binding.etSigninId.text.toString()
                )
                SharedPreferences.MySharedPreferences.setUserPw(
                    this,
                    binding.etSigninPw.text.toString()
                )
                SharedPreferences.MySharedPreferences.setUserName(this, name)
                SharedPreferences.MySharedPreferences.setUserSpeciality(this, specialty)

                val intent = Intent(this, MainActivity::class.java)

                binding.root.showSnackbar("로그인에 성공했습니다.")

                startActivity(intent)
            } else {
                binding.root.showSnackbar("로그인에 실패했습니다.")
            }

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
            signIn()
        } else {
            val intent = Intent(this, MainActivity::class.java)

            binding.root.showToast("자동 로그인 되었습니다.")

            startActivity(intent)
            finish()
        }
    }

}

