package org.android.go.sopt

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("sign-in")
    fun signIn(
        @Body request: RequestSignInDto,
    ): Call<ResponseSignInDto>
}