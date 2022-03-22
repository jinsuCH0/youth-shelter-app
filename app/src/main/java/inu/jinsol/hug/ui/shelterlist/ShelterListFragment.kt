package inu.jinsol.hug.ui.shelterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import inu.jinsol.hug.databinding.FragmentShelterlistBinding

class ShelterListFragment : Fragment() {

    private var _binding: FragmentShelterlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val shelterlistViewModel = ViewModelProvider(this).get(ShelterListViewModel::class.java)

        _binding = FragmentShelterlistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textShelterlist
        shelterlistViewModel.text.observe(viewLifecycleOwner) { textView.text = it }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}