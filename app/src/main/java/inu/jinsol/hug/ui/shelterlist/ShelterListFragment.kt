package inu.jinsol.hug.ui.shelterlist

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import inu.jinsol.hug.data.Shelter
import inu.jinsol.hug.databinding.FragmentShelterlistBinding

/*  2022/03/31
*   created by JinSu CHO
*/

class ShelterListFragment : Fragment() {
    private var _binding: FragmentShelterlistBinding? = null
    private val binding get() = _binding!!

    private var expandableListView: ExpandableListView? = null // https://github.com/pam412/SpotAssets/blob/1a611e5ce1b3e3d3a40e7547dd494f1a92a60c91/app/src/main/java/com/pam/spotassets/view/DetailsFragment.kt
    private var adapter:ExpandableListAdapter? = null

    private lateinit var cityList:List<String>
    private lateinit var shelterList:HashMap<String, ArrayList<Shelter>>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d(TAG, "onCreateView: ")

        _binding = FragmentShelterlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        //  View 의 초기값을 설정해주거나 LiveData 옵저빙, RecyclerView 또는 ViewPager2 에 사용될 Adapter 세팅 등은 onViewCreated() 에서 해주는 것이 적절하겠습니다.


        setExpandableListView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d(TAG, "onDestroy: ")
    }

    private fun setExpandableListView() {
        // TODO: 쉼터 리스트 구현 필요
        Log.d(TAG, "setExpandableListView: ")

        cityList= listOf("서울시", "인천시", "경기도")
        shelterList = HashMap()

        adapter = ExpandableListAdapter(requireContext(), cityList, shelterList)
        expandableListView?.setAdapter(adapter)
    }









}


