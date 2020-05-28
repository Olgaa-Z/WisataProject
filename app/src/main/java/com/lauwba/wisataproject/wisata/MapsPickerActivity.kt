package com.lauwba.wisataproject.wisata

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.lauwba.wisataproject.R

class MapsPickerActivity : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var mapFragment : SupportMapFragment
    //fused location (lokasi sekarang)
    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var mLocationRequest: LocationRequest
    private lateinit var mLocationCallback: LocationCallback
    private  var isGpsEnabled : Boolean = false

    private var  latitude : Double? = null
    private var longitude : Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps_picker)

        mapFragment= supportFragmentManager.findFragmentById(R.id.mapfragment) as SupportMapFragment

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        checkGpsPermission()
    }



    private fun getLocation(){
        mLocationRequest = LocationRequest.create()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 10 * 1000
        mLocationRequest.fastestInterval = 5 * 1000

        GpsUtils(this).turnGPSOn {
            isGpsEnabled = it
        }

        mLocationCallback = object : LocationCallback(){
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                if(p0 == null)
                    return

                for(location in p0.locations) {
                    if (location != null) {
                        latitude = location.latitude
                        longitude = location.longitude
                    }

                    Toast.makeText(this@MapsPickerActivity, "$latitude, $longitude", Toast.LENGTH_SHORT).show()

                    if (mFusedLocationProviderClient != null) {
                        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback)
                    }
                }

                setLocation()
            }
        }

        mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null)
    }

    private fun setLocation(){
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {
        val myLocation = LatLng(latitude!!, longitude!!)
        p0?.addMarker(MarkerOptions().position(myLocation).title("Lokasi Saya"))
        p0?.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 14F))
    }

    private fun checkGpsPermission(){
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            getLocation()
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), 200)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 200){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLocation()
            }
        }
    }




}
