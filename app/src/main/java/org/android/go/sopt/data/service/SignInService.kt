package org.android.go.sopt.data.service

import org.android.go.sopt.data.dto.RequestSignInDto
import org.android.go.sopt.data.dto.ResponseSignInDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("sign-in")
    fun signIn(
        @Body request: RequestSignInDto,
    ): Call<ResponseSignInDto>
}