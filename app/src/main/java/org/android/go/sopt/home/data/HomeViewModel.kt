package org.android.go.sopt.home.data

import androidx.lifecycle.ViewModel
import org.android.go.sopt.R
import org.android.go.sopt.home.data.Pop

class HomeViewModel : ViewModel() {
    val mockPopLists = listOf<Pop>(
        Pop(
            id = 1,
            image = R.drawable.ic_lost_stars,
            title = "Lost Stars",
            singer = "Adam-Levine"
        ),
        Pop(
            id = 2,
            image = R.drawable.ic_2002,
            title = "2002",
            singer = "Anne-Marie"
        ),
        Pop(
            id = 3,
            image = R.drawable.ic_7years,
            title = "7 Years",
            singer = "Lukas-Graham"
        ),
        Pop(
            id = 4,
            image = R.drawable.ic_attention,
            title = "Attention",
            singer = "Charlie-Puth"
        ),
        Pop(
            id = 5,
            image = R.drawable.ic_numb_little_bug,
            title = "Numb Little Bug",
            singer = "Em-Beihold"
        ),
        Pop(
            id = 6,
            image = R.drawable.ic_pumping_up_clouds,
            title = "Pumping Up Clouds",
            singer = "Urban-Cone"
        ),
        Pop(
            id = 7,
            image = R.drawable.ic_snowman,
            title = "Snowman",
            singer = "Sia"
        ),
        Pop(
            id = 8,
            image = R.drawable.ic_we_dont_care,
            title = "We Don't Care",
            singer = "Sigala, The Vamps"
        ),
        Pop(
            id = 9,
            image = R.drawable.ic_when_we_were_young,
            title = "When We Were Young",
            singer = "Adele"
        ),


        )

}