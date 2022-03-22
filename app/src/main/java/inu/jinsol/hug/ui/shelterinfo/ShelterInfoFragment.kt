package inu.jinsol.hug.ui.shelterinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import inu.jinsol.hug.databinding.FragmentShelterinfoBinding

class ShelterInfoFragment : Fragment() {

    private var _binding: FragmentShelterinfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val shelterinfoViewModel = ViewModelProvider(this).get(ShelterInfoViewModel::class.java)

        _binding = FragmentShelterinfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textShelterinfo
        shelterinfoViewModel.text.observe(viewLifecycleOwner) { textView.text = it }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}