package org.android.go.sopt

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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

                MySharedPreferences.setUserId(this, binding.etSigninId.text.toString())
                MySharedPreferences.setUserPw(this, binding.etSigninPw.text.toString())
                MySharedPreferences.setUserName(this, name)
                MySharedPreferences.setUserSpeciality(this, specialty)

                val intent = Intent(this, MainActivity::class.java)

                Snackbar.make(binding.root, "로그인에 성공했습니다.", Snackbar.LENGTH_SHORT).show()

                startActivity(intent)
            } else {
                Snackbar.make(binding.root, "로그인에 실패했습니다.", Snackbar.LENGTH_SHORT).show()
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
        if (MySharedPreferences.getUserId(this).isNullOrBlank()
            || MySharedPreferences.getUserPw(this).isNullOrBlank()
        ) {
            signIn()
        } else {
            val intent = Intent(this, MainActivity::class.java)

            Snackbar.make(binding.root, "자동 로그인되었습니다.", Snackbar.LENGTH_SHORT).show()
            Toast.makeText(this.applicationContext,"자동 로그인되었습니다.",Toast.LENGTH_SHORT).show()

            startActivity(intent)
            finish()
        }
    }

}

object MySharedPreferences {
    private const val myAccount: String = "account"
    fun setUserId(context: Context, input: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_ID", input)
        editor.commit()
    }

    fun getUserId(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
        return prefs.getString("MY_ID", "").toString()
    }

    fun setUserPw(context: Context, input: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_PW", input)
        editor.commit()
    }

    fun getUserPw(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
        return prefs.getString("MY_PW", "").toString()
    }

    fun setUserName(context: Context, input: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_NAME", input)
        editor.commit()
    }

    fun getUserName(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
        return prefs.getString("MY_NAME", "").toString()
    }

    fun setUserSpeciality(context: Context, input: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_SPECIALITY", input)
        editor.commit()
    }

    fun getUserSpeciality(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
        return prefs.getString("MY_SPECIALITY", "").toString()
    }

    fun delete(context: Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(myAccount, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.clear()
        editor.commit()
    }


}