package inu.jinsol.hug.ui.home

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context.LOCATION_SERVICE
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import inu.jinsol.hug.databinding.FragmentHomeBinding
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class HomeFragment : Fragment(), MapView.CurrentLocationEventListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val PERMISSIONS_REQUEST_CODE = 100
    var REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    lateinit var locationActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val mapView = MapView(activity)
        val mapViewContainer = binding.mapView
        mapViewContainer.addView(mapView)

        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true);

        mapView.setCurrentLocationEventListener(mapView);
        checkAllPermissions()

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkAllPermissions() {
        if (!isLocationServicesAvailable()) {
            showDialogForLocationServiceSetting();
        } else {
            isRunTimePermissionsGranted();
        }
    }

    fun isLocationServicesAvailable(): Boolean {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        return (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
    }

    fun isRunTimePermissionsGranted() {
        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            getActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            getActivity(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED ||
            hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                getActivity(),
                REQUIRED_PERMISSIONS,
                PERMISSIONS_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE && grantResults.size ==
            REQUIRED_PERMISSIONS.size
        ) {
            var checkResult = true

            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    checkResult = false
                    break
                }
            }
            if (checkResult) {

            } else {
                Toast.makeText(getActivity(), "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show()
                //finish()  <- 액티비티 종료 메소드인데 프래그먼트 종료로 바꿔야 함
            }
        }
    }

    private fun showDialogForLocationServiceSetting() {
        locationActivityResultLauncher  = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (isLocationServicesAvailable()) {
                    isRunTimePermissionsGranted()
                } else {
                    Toast.makeText(getActivity(), "위치 서비스를 사용할 수 없습니다.", Toast.LENGTH_LONG).show()
                    //finish()
                }
            }
        }

        val builder: AlertDialog.Builder = AlertDialog.Builder(
            getActivity()
        )
        builder.setTitle("위치 서비스 비활성화")
        builder.setMessage(
            "위치 서비스가 꺼져 있습니다. 설정해야 앱을 사용할 수 있습니다."
        )

        builder.setCancelable(true)
        builder.setPositiveButton("설정",
            DialogInterface.OnClickListener { dialog, id ->
                val callGPSSettingIntent = Intent(
                    Settings.ACTION_LOCATION_SOURCE_SETTINGS
                )
                locationActivityResultLauncher .launch(callGPSSettingIntent)
            })
        builder.setNegativeButton("취소",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel()
                Toast.makeText(getActivity(), "기기에서 위치서비스(GPS) 설정 후 사용해주세요.", Toast.LENGTH_SHORT).show()
                //finish()
            })
        builder.create().show()
    }

    override fun onCurrentLocationUpdate(p0: MapView?, p1: MapPoint?, p2: Float) {}
    override fun onCurrentLocationDeviceHeadingUpdate(p0: MapView?, p1: Float) {}
    override fun onCurrentLocationUpdateCancelled(p0: MapView?) {}
    override fun onCurrentLocationUpdateFailed(p0: MapView?) {}
}
