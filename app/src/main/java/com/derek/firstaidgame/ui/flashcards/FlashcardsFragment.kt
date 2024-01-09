package com.derek.firstaidgame.ui.flashcards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.derek.firstaidgame.R
import com.derek.firstaidgame.databinding.FragmentFlashcardsBinding

class FlashcardsFragment : Fragment() {

    private var _binding: FragmentFlashcardsBinding? = null
    lateinit var  flashcardsViewModel:FlashcardsViewModel
    // lateinit var slideAdapter: SlideAdapter
    private val slideAdapter = SlideAdapter(
        listOf(
            Slide(
                "First Aid Question 1",
                "Question Description",
                R.drawable.logo1
            ),
            Slide(
                "First Aid Question 2",
                "Question Description",
                R.drawable.logo2
            ),
            Slide(
                "First Aid Question 3",
                "Question Description",
                R.drawable.first_aid_logo
            )
        )
    )

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        flashcardsViewModel =
            ViewModelProvider(this).get(FlashcardsViewModel::class.java)
        _binding = FragmentFlashcardsBinding.inflate(inflater, container, false)

        val root: View = binding.root
        binding.viewPager.adapter = slideAdapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}