package com.derek.firstaidgame.ui.learn

import android.widget.TextView

data class LearnItem(
    var tvItem: TextView,
    var tvItemNum: TextView,
    var selected: Boolean,
    var backGroundResourceId: Int
)
