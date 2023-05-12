package org.android.go.sopt


import retrofit2.Call
import retrofit2.http.GET

interface ListUsersService {
    @GET("/api/users?pager=2")
    fun listUsers(): Call<ResponseSignUpDto>
}