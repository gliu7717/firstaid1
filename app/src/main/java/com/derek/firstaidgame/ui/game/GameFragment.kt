package com.derek.firstaidgame.ui.game

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.derek.firstaidgame.databinding.FragmentGameBinding
import java.util.Locale

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private lateinit var mSpeech: TextToSpeech

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(GameViewModel::class.java)

        _binding = FragmentGameBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initSpeech()
        binding.ivSpeak.setOnClickListener {
            var str:String = binding.tvQuestion.text.toString()
            if(str.isNotEmpty()){
                mSpeech.speak(str, TextToSpeech.QUEUE_FLUSH,null,null)
            }
        }


        return root
    }

    private fun initSpeech() {
        mSpeech = TextToSpeech(context, TextToSpeech.OnInitListener {
            if (it == TextToSpeech.SUCCESS) {
                val i = mSpeech.setLanguage(Locale.ENGLISH)
                if (i == TextToSpeech.LANG_MISSING_DATA || i == TextToSpeech.LANG_NOT_SUPPORTED) {
                    mSpeech.setSpeechRate(1.0f)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}