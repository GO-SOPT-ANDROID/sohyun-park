package org.android.go.sopt.presentation.auth.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.dto.RequestSignUpDto
import org.android.go.sopt.data.dto.ResponseSignUpDto
import org.android.go.sopt.data.factory.ServicePool
import org.android.go.sopt.util.addSourceList
import org.android.go.sopt.util.enqueueUtil

class SignUpViewModel : ViewModel() {

    private val _signUpResult: MutableLiveData<ResponseSignUpDto> = MutableLiveData()
    val signUpResult: LiveData<ResponseSignUpDto> = _signUpResult

    val id: MutableLiveData<String> = MutableLiveData()
    val pw: MutableLiveData<String> = MutableLiveData()
    val name: MutableLiveData<String> = MutableLiveData()
    val specialty: MutableLiveData<String> = MutableLiveData()

    val isEnabledSignUpButton = MediatorLiveData<Boolean>().apply {
        addSourceList(id, pw, name, specialty) {
            checkSignUpValidation()
        }
    }

    fun checkIdFormation(): Boolean {
        return id.value?.matches(ID_RULE) ?: false && !id.value.isNullOrBlank()
    }

    fun checkPwFormation(): Boolean {
        return pw.value?.matches(PW_RULE) ?: false && !pw.value.isNullOrBlank()
    }


    private val signUpService = ServicePool.signUpService

    fun signUp(
        id: String,
        password: String,
        name: String,
        speciality: String,
        message: (String) -> Unit
    ) {
        signUpService.signUp(
            RequestSignUpDto(
                id,
                password,
                name,
                speciality
            )
        ).enqueueUtil(
            onSuccess = {
                _signUpResult.value = it
                message.invoke(it.message)
            },
            onError = {
                message.invoke("error:${it}")
            }
        )

    }


    fun checkSignUpValidation(): Boolean {
        return checkIdFormation() && checkPwFormation() && !name.value.isNullOrBlank() && !specialty.value.isNullOrBlank()
    }

    companion object {
        val ID_RULE = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}\$".toRegex()
        val PW_RULE =
            "^(?=.*[a-zA-Z])(?=.*[!@#\$%^&*()])(?=.*\\d)[a-zA-Z!@#\$%^&*()\\d]{6,12}\$".toRegex()
    }
}