package inu.jinsol.hug.activity

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import inu.jinsol.hug.databinding.ActivityFilteringBinding

class FilteringActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityFilteringBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilteringBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "FilteringActivity - onCreate() called")

        binding.btnFiltering.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        var privacy = binding.chkPrivacyPolicy.isChecked

        if (!privacy) {
            Toast.makeText(this, "개인정보 수집에 동의하셔야 해당 기능을 이용할 수 있습니다.", Toast.LENGTH_LONG).show()
        }
        else {
            Log.d(TAG, "FilteringActivity - do filtering")
        }
    }
}