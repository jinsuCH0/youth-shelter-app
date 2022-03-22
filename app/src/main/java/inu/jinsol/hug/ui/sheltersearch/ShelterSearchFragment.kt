package inu.jinsol.hug.ui.sheltersearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import inu.jinsol.hug.databinding.FragmentSheltersearchBinding

class ShelterSearchFragment : Fragment() {

    private var _binding: FragmentSheltersearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val sheltersearchViewModel = ViewModelProvider(this).get(ShelterSearchViewModel::class.java)

        _binding = FragmentSheltersearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSheltersearch
        sheltersearchViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}