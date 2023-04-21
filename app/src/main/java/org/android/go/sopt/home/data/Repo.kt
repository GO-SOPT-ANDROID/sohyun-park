package org.android.go.sopt.home.data

import androidx.annotation.DrawableRes

data class Repo(
    val id:Int,
    @DrawableRes val image: Int,
    val name: String,
    val author: String
)
