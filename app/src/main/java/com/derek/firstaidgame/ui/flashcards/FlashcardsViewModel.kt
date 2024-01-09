package com.derek.firstaidgame.ui.flashcards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.derek.firstaidgame.R
import com.derek.firstaidgame.ui.learn.Dictionary
import java.util.Collections

class FlashcardsViewModel : ViewModel() {
    var listSlides = mutableListOf<Slide>()


    private val cardImageResourceIds = arrayOf(
        R.drawable.cherries_no_bg,
        R.drawable.clementine_no_bg,
        R.drawable.melon_no_bg,
        R.drawable.pear_no_bg,
        R.drawable.pineapple_no_bg,
        R.drawable.banana_no_bg,
        R.drawable.watermelon_no_bg,
        R.drawable.tomato_no_bg)

    fun initSlides(count:Int)
    {
        var sampleCount = Math.min(count, Dictionary.dict.size)
        var list: List<String> = ArrayList<String>(Dictionary.dict.keys)
        Collections.shuffle(list)
        for( i in 0 until sampleCount){
            val j = (0..7).random()
            var slide = Slide(list[i], Dictionary.dict.get(list[i]).toString(),cardImageResourceIds[j] )
            listSlides.add(slide)
        }
    }
}