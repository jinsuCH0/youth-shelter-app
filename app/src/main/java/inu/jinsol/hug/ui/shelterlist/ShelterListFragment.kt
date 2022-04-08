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
        Log.d(TAG, "setExpandableListView: ")

        cityList= listOf("서울시", "인천시", "경기도")
        shelterList = HashMap()

        val seoulList = arrayListOf(
            Shelter(111, "서울특별시립금천청소년쉼터", "여자", "단기", "3-9개월", /*"9-24세",*/ "서울시", "강남구", "봉은사로 114길 43, 서울의료원 강남분원 신관 1층", "02-3281-8200"),
            Shelter(222, "서울시립강북청소년드림센터", "남자", "고정", "7일", /*"9-24세",*/ "서울시", "강북구", "한천로140길 5-26(수유3동)", "02-6435-7979"),
            Shelter(333, "어울림청소년쉼터", "여자", "중장기", "2-3년", /*"14-19세",*/ "서울시", "강서구", "공항대로 48길 76, 303호(예루뜨빌라)", "02-302-9006"),
        )
        val incheonList = arrayListOf(
            Shelter(444, "강서청소년쉼터", "남자", "단기", "3-6개월", /*"13-19세",*/ "서울시", "강서구", "초록마을로10길 5, 201호", "02-2697-7377"),
            Shelter(555, "서울시립신림중장기청소년쉼터", "남자", "중장기", "최대 4년", /*"17-19세",*/ "서울시", "관악구", "난곡로24가길 54 301호", "02-3281-7942"),
            Shelter(666, "서울시립신림청소년쉼터", "남자", "단기", "3-9개월", /*"14-21세",*/ "서울시", "관악구", "신림로 376 대경빌딩 3층", "02-876-7942"),
        )
        val gyunggiList = arrayListOf(
            Shelter(777, "서울시립금천여자중장기청소년쉼터", "여자", "중장기", "최대 3년", /*"14-20세",*/ "서울시", "금천구", "독산로73길 10-16", "02-6959-1011"),
            Shelter(888, "서울시립용산청소년일시쉼터", "공용", "고정", "7일", /*"9-24세",*/ "서울시", "용산구", "만리재로 156-1", "02-718-1318"),
            Shelter(999, "은평여자일시청소년쉼터", "여자", "고정", "7일", /*"9-24세",*/ "서울시", "은평구", "통일로 89길 6-20", "02-382-1388"),
            Shelter(2323, "서울시립은평여자중장기청소년쉼터", "여자", "중장기", "6개월", /*"9-24세",*/ "서울시", "은평구", "통일로 92길 37-6 동산홈타운", "02-6959-2401")
        )

        shelterList[cityList[0]] = seoulList
        shelterList[cityList[1]] = incheonList
        shelterList[cityList[2]] = gyunggiList

        adapter = ExpandableListAdapter(requireContext(), cityList, shelterList)
        expandableListView?.setAdapter(adapter)


    }









}


