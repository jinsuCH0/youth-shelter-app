package inu.jinsol.hug.ui.home

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import inu.jinsol.hug.databinding.FragmentHomeBinding
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class HomeFragment : Fragment(), MapView.CurrentLocationEventListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val PERMISSIONS_REQUEST_CODE = 100  // 퍼미션 요청 코드
    // 요청할 권한 목록
    var REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION)

    private lateinit var thisContext: Context
    private lateinit var locationActivityResultLauncher: ActivityResultLauncher<Intent> // 위치 서비스 요청 시 필요한 런처


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java) // <- 필요한지 모르겠음

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // fragment에 지도 부착
        val mapView = MapView(activity)
        val mapViewContainer = binding.mapView
        mapViewContainer.addView(mapView)

        /*
       // fab 클릭 리스너
       binding.appBarMain.searchFilter.setOnClickListener { view ->
           Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               .setAction("Action", null).show()*/


        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true);
        mapView.setCurrentLocationEventListener(this) // <- 매개변수 오류


        checkAllPermissions() // 권한 확인

        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading)
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        thisContext = context
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 위치 권한 확인 시퀀스 메소드
    private fun checkAllPermissions() {
        // 1. GPS 켜져있는지 확인
        if (!isLocationServicesAvailable()) {
            showDialogForLocationServiceSetting();
        } else {    // 런타임 앱 권한이 모두 허용되어 있는지 확인
            isRunTimePermissionsGranted();
        }
    }
    // 위치 서비스 활성화 확인
    private fun isLocationServicesAvailable(): Boolean {
        val locationManager = thisContext.getSystemService(LOCATION_SERVICE) as LocationManager // <- getSystemService(LOCATION_SERVICE) 오류
        return (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
    }
    // 런타임 권한 체크
    private fun isRunTimePermissionsGranted() {
        val context = context ?: return

        // 위치 퍼미션을 가지고 있는지 체크
        val hasFineLocationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) // <- context 오류
        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) // <- context 오류

        if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED || hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE) // <- context 오류
        }   // 권한 없으면 퍼미션 요청
    }
    // 런타임 권한 요청
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSIONS_REQUEST_CODE && grantResults.size == REQUIRED_PERMISSIONS.size) {    // 요청 코드가 PERMISSIONS_REQUEST_CODE이고,
            var checkResult = true                                                                          // 요청한 퍼미션 갯수만큼 수신되었다면

            for (result in grantResults) {  // 모든 퍼미션 허용했는지 체크
                if (result != PackageManager.PERMISSION_GRANTED) {
                    checkResult = false
                    break
                }
            }
            if (checkResult) {
                // 위치값을 가져올 수 있음(?)
            } else {    // 퍼미션 거부되었을 경우 앱 종료
                Toast.makeText(activity, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show()
                //finish()  <- 액티비티 종료 메소드인데 프래그먼트 종료로 바꿔야 함
            }
        }
    }
    // 위치 서비스 활성화 요청
    private fun showDialogForLocationServiceSetting() {
        // ActivityResultLauncher 설정 -> 런처가 있어야 결과값을 반환해야 하는 인텐트를 실행할 수 있음.
        locationActivityResultLauncher  = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == Activity.RESULT_OK) {  // 결과값을 받았을 때 돌아가는 로직
                if (isLocationServicesAvailable()) {    // 사용자가 GPS를 활성화시켰는지 확인
                    isRunTimePermissionsGranted()   // 런타임 권한 확인
                } else {    // Toast 띄우고 위치 서비스가 허용되지 않았다면 앱 종료
                    Toast.makeText(activity, "위치 서비스를 사용할 수 없습니다.", Toast.LENGTH_LONG).show()
                    //finish() <- 액티비티(프레그먼트) 종료 메소드 구현 필요?
                }
            }
        }

        // 사용자에게 의사를 물어보는 AlertDialog 생성
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setTitle("위치 서비스 비활성화") // AlertDialog 제목
        builder.setMessage("위치 서비스가 꺼져 있습니다. 설정해야 앱을 사용할 수 있습니다.")  // AlertDialog 내용
        builder.setCancelable(true) // Dialog 창 바깥 터치 시 창 닫힘

        builder.setPositiveButton("설정",
            DialogInterface.OnClickListener { dialog, id ->
                val callGPSSettingIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                locationActivityResultLauncher .launch(callGPSSettingIntent)
            })  // 확인 버튼

        builder.setNegativeButton("취소",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel()
                Toast.makeText(activity, "기기에서 위치서비스(GPS) 설정 후 사용해주세요.", Toast.LENGTH_SHORT).show()
                //finish()
            })  // 취소 버튼
        builder.create().show()
    }


    // MapView.CurrentLocationEventListener 오버라이드
    override fun onCurrentLocationUpdate(p0: MapView?, p1: MapPoint?, p2: Float) {}
    override fun onCurrentLocationDeviceHeadingUpdate(p0: MapView?, p1: Float) {}
    override fun onCurrentLocationUpdateCancelled(p0: MapView?) {}
    override fun onCurrentLocationUpdateFailed(p0: MapView?) {}

}
