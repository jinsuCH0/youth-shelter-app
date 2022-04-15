package inu.jinsol.hug.ui.sheltersearch

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import inu.jinsol.hug.databinding.FragmentSheltersearchBinding

class ShelterSearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentSheltersearchBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val sheltersearchViewModel = ViewModelProvider(this)[ShelterSearchViewModel::class.java]
        _binding = FragmentSheltersearchBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textSheltersearch
//        sheltersearchViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        var searchView = binding.searchView


        Log.d(TAG, "너비: ${searchView.width}")
        Log.d(TAG, "높이: ${searchView.height}")

        searchView.setOnQueryTextListener(this)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        Log.d(TAG, "SearchView - onClick!: ")
        Toast.makeText(context, "검색버튼 클릭!", Toast.LENGTH_SHORT).show()
        var searchView = binding.searchView
        Log.d(TAG, "너비: ${searchView.width}")
        Log.d(TAG, "높이: ${searchView.height}")
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        Log.d(TAG, "SearchView - onTyping- : ")
        return false
    }

}