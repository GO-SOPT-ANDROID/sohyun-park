package org.android.go.sopt

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
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

        signIn()
        signUp()

    }


    private fun signIn() {

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

    private fun signUp() {
        binding.btnSigninLogin.setOnClickListener {

            if (binding.etSigninId.text.toString() == id && binding.etSigninPw.text.toString() == pw) {

                val intent = Intent(this, MypageActivity::class.java)

                Snackbar.make(binding.root, "로그인에 성공했습니다.", Snackbar.LENGTH_SHORT).show()
                intent.putExtra("name", name)
                intent.putExtra("specialty", specialty)
                startActivity(intent)
            } else {
                Snackbar.make(binding.root, "로그인에 실패했습니다.", Snackbar.LENGTH_SHORT).show()
            }

        }

    }

}