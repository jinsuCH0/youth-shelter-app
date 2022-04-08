package inu.jinsol.hug.ui.shelterlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.ExpandableListView.OnGroupClickListener
import android.widget.TextView
import android.widget.Toast
import inu.jinsol.hug.R
import inu.jinsol.hug.data.Shelter

/*  2022/03/31
*   created by JinSu CHO
*/

class ExpandableListAdapter (
    private val context: Context,
    private val cityList: List<String>,
    private val shelterList: HashMap<String, ArrayList<Shelter>>
) : BaseExpandableListAdapter(), OnGroupClickListener, OnChildClickListener {
    // 상위 리스트 레이아웃
    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, _convertView: View?, parent: ViewGroup?): View {
        //  상위 리스트 부착
        var convertView = _convertView
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.parent_listview, null)
        }

        // 상위 리스트 타이틀 텍스트뷰 설정
        val listTitle = getGroup(groupPosition) as String
        val expandableTitleTextView = convertView!!.findViewById<TextView>(R.id.expand_title)
        expandableTitleTextView.text = listTitle


        return convertView
    }



    // 하위 리스트 레이아웃
    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, _convertView: View?, parent: ViewGroup?): View {
        var convertView = _convertView
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.parent_listview, null)
        }

        val childTitle = getGroup(groupPosition) as String
        var nameTextView = convertView!!.findViewById<TextView>(R.id.txt_shelter_name)
        var sexTextView = convertView!!.findViewById<TextView>(R.id.txt_shelter_sex)
        var typeTextView = convertView!!.findViewById<TextView>(R.id.txt_shelter_type)
        var periodTextView = convertView!!.findViewById<TextView>(R.id.txt_shelter_period)
        var stateTextView = convertView!!.findViewById<TextView>(R.id.txt_shelter_location_state)
        var cityTextView = convertView!!.findViewById<TextView>(R.id.txt_shelter_location_city)
        var streetTextView = convertView!!.findViewById<TextView>(R.id.txt_shelter_location_street)
        var phoneTextView = convertView!!.findViewById<TextView>(R.id.txt_shelter_phone)
        nameTextView.text = childTitle
        sexTextView.text = childTitle
        typeTextView.text = childTitle
        periodTextView.text = childTitle
        stateTextView.text = childTitle
        cityTextView.text = childTitle
        streetTextView.text = childTitle
        phoneTextView.text = childTitle

        return convertView
    }

    override fun getGroupCount() = cityList.size                            // 그룹 사이즈 반환
    override fun getGroup(groupPosition: Int) = cityList[groupPosition]     // 그룹 포지션 반환
    override fun getGroupId(groupPosition: Int) = groupPosition.toLong()    // 그룹 ID 반환
    override fun getChildrenCount(groupPosition: Int) = shelterList[cityList[groupPosition]]?.size as Int                                   // 하위 리스트뷰 사이즈 반환
    override fun getChild(groupPosition: Int, childPosition: Int): Any = shelterList[cityList[groupPosition]]?.get(childPosition) as Any    // 주어진 그룹 위치와 차일드 위치에 해당하는 차일드 아이템을 반환
    override fun getChildId(groupPosition: Int, childPosition: Int) = childPosition.toLong()                                                // 하위 리스트뷰 ID 반환

    override fun hasStableIds() = true  // ExpandableListView 재사용 여부, 메소드는 그룹과 차일드가 반환하는 아이디가 항상 일관된 아이디라면 true를, 아니라면 false를 반환
    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = false  // 차일드 아이템을 선택할 수 있다면 true를 반환하고, 아니라면 false를 반환

    override fun onGroupClick(p0: ExpandableListView?, p1: View?, p2: Int, p3: Long): Boolean {
        Toast.makeText(context, "Group : ${cityList[p2]}", Toast.LENGTH_SHORT).show()
        return false
    }   // 상위 리스트 클릭 시 Toast 메세지 띄움
    override fun onChildClick(parent: ExpandableListView?, view: View?, groupPosition: Int, childPosition: Int, id: Long): Boolean {
        Toast.makeText(context, "Child : ${shelterList[cityList[groupPosition]]}", Toast.LENGTH_SHORT).show()
        return false
    }   // 하위 리스트 클릭 시 Toast 메세지 띄움
}