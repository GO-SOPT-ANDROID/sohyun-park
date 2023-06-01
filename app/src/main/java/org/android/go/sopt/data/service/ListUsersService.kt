package org.android.go.sopt.data.service


import org.android.go.sopt.data.dto.ResponseListUsersDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ListUsersService {
    @GET("/api/users")
    fun listUsers(
        @Query("page") num: Int = 2
    ): Call<ResponseListUsersDto>
}