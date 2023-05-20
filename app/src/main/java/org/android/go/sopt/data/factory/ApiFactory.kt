package org.android.go.sopt.data.factory

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.android.go.sopt.BuildConfig
import org.android.go.sopt.data.service.ListUsersService
import org.android.go.sopt.data.service.SignInService
import org.android.go.sopt.data.service.SignUpService
import retrofit2.Retrofit

object ApiFactory {

    private const val AUTH_BASE_URL = BuildConfig.AUTH_BASE_URL
    private const val REQRES_BASE_URL = BuildConfig.REQRES_BASE_URL

    val authRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AUTH_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val reqresRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(REQRES_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> authCreate(): T = authRetrofit.create<T>(T::class.java)

    inline fun <reified T> reqresCreate(): T = reqresRetrofit.create<T>(T::class.java)
}

object ServicePool {
    val signUpService = ApiFactory.authCreate<SignUpService>()
    val signInService = ApiFactory.authCreate<SignInService>()
    val listUsersService = ApiFactory.reqresCreate<ListUsersService>()

}