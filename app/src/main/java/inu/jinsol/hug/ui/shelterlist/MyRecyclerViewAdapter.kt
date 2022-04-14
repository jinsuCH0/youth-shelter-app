package inu.jinsol.hug.ui.shelterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import inu.jinsol.hug.data.Shelter
import inu.jinsol.hug.data.ShelterItem
import inu.jinsol.hug.databinding.ListItemBinding
import kotlin.toString as toString1

class MyRecyclerViewAdapter(private val dataset: Shelter) : RecyclerView.Adapter<MyRecyclerViewAdapter
.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class ViewHolder(private val binding:ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ShelterItem) {
            with (binding) {
                txtShelterName.text = data.name
                when {
                    data.sex.toString1() == "true" -> txtShelterSex.text = "남"
                    data.sex.toString1() == "false" -> txtShelterSex.text = "여"
                    else -> txtShelterSex.text = "공용"
                }
                txtShelterType.text = data.type
                txtShelterPeriod.text = data.period
                txtShelterLocationState.text = data.state
                txtShelterLocationCity.text = data.city
                txtShelterLocationStreet.text = data.street
                txtShelterPhone.text = data.phone
                }
            }
        }
    }
