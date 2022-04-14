package inu.jinsol.hug.ui.shelterlist

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import inu.jinsol.hug.data.Shelter
import inu.jinsol.hug.databinding.FragmentShelterlistBinding
import java.io.IOException

class ShelterListFragment : Fragment() {
    private var _binding: FragmentShelterlistBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d(TAG, "onCreateView: ")
        _binding = FragmentShelterlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //  View 의 초기값을 설정해주거나 LiveData 옵저빙, RecyclerView 또는 ViewPager2 에 사용될 Adapter 세팅 등은 onViewCreated() 에서 해주는 것이 적절하겠습니다.
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")

        val shelterdata = getJsonData("DatabaseShelter.json")

        binding.recyclerview.apply {
            adapter = MyRecyclerViewAdapter(shelterdata!!)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d(TAG, "onDestroy: ")
    }

    private fun getJsonData(filename: String): Shelter? {
        val assetManger = resources.assets
        var result: Shelter? = null
        try {
            val inputStream = assetManger.open(filename)
            val reader = inputStream.bufferedReader()
            val gson = Gson()
            result = gson.fromJson(reader, Shelter::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }
}


