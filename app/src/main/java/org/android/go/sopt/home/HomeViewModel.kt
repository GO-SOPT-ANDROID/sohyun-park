package org.android.go.sopt.home

import androidx.lifecycle.ViewModel
import org.android.go.sopt.R
import org.android.go.sopt.home.data.Repo

class HomeViewModel : ViewModel() {
    val mockRepoList = listOf<Repo>(
        Repo(
            id = 1,
            image = R.drawable.github,
            name = "CatchMe",
            author = "Sohyun-Park"
        ),
        Repo(
            id = 2,
            image = R.drawable.github,
            name = "FILL-IN",
            author = "Sohyun-Park"
        ),
        Repo(
            id = 3,
            image = R.drawable.github,
            name = "Hacker",
            author = "Sohyun-Park"
        ),
        Repo(
            id = 4,
            image = R.drawable.github,
            name = "BeMe",
            author = "Sohyun-Park"
        ),
        Repo(
            id = 5,
            image = R.drawable.github,
            name = "BeMe",
            author = "Sohyun-Park"
        ),
        Repo(
            id = 6,
            image = R.drawable.github,
            name = "BeMe",
            author = "Sohyun-Park"
        ),
        Repo(
            id = 7,
            image = R.drawable.github,
            name = "BeMe",
            author = "Sohyun-Park"
        )

    )

}