package com.example.a6

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : FragmentActivity(),OnMapReadyCallback {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var currentLocation: Location
    private lateinit var btnLocate: FloatingActionButton
    private lateinit var currentMarker:Marker
    private lateinit var mMap:GoogleMap
    private lateinit var btnCamera:FloatingActionButton
    val db = MyDB(this)
    private val albumrequest = 1002
    private val cameraRequest = 1000
    private lateinit var imgCapture:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fetchlocation()
        btnLocate = findViewById(R.id.float1)
        btnCamera = findViewById(R.id.float2)
        imgCapture = findViewById(R.id.myImg)
        btnLocate.setOnClickListener(){
            currentMarker.remove()
            fetchlocation()
            val markerPos = LatLng(currentLocation.latitude, currentLocation.longitude)
            currentMarker = mMap.addMarker(
                MarkerOptions()
                    .position(markerPos).title("swinburne").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )!!
        }
        btnCamera.setOnClickListener(){
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, cameraRequest)
        }
    }

    private fun PrintImage(imageName:String) {
        val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val image = File(imagesDir, imageName)
        imgCapture.setImageURI(image.toUri())
    }

    fun printDetails(ttt:String){
        val ddd:MyData = db.getData2(ttt)
        val geo:Geocoder = Geocoder(this, Locale.getDefault())
        val xx = geo.getFromLocation(ddd.latitude,ddd.longitude,1)
        val txt:TextView = findViewById(R.id.txt)
        txt.setText("Address : " + xx.get(0).getAddressLine(0).toString())
        PrintImage(ddd.imageName)
    }

    // this method saves the image to gallery
    private fun saveMediaToStorage(bitmap: Bitmap) {
        // Generating a file name
        val filename = "${System.currentTimeMillis()}.jpg"

        // Output stream
        var fos: OutputStream? = null

        // For devices running android >= Q
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // getting the contentResolver
            this.contentResolver?.also { resolver ->

                // Content resolver will process the contentvalues
                val contentValues = ContentValues().apply {

                    // putting file information in content values
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                // Inserting the contentValues to
                // contentResolver and getting the Uri
                val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                // Opening an outputstream with the Uri that we got
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            // These for devices running on android < Q
            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            Toast.makeText(applicationContext,image.toURI().toString(),Toast.LENGTH_LONG).show()
            fos = FileOutputStream(image)
        }

        fos?.use {
            // Finally writing the bitmap to the output stream that we opened
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            //Toast.makeText(this , "Captured View and saved to Gallery" , Toast.LENGTH_SHORT).show()
            db.addData( "tt" + db.getData().size.toString(),filename,currentLocation.longitude,currentLocation.latitude)
            //filename == imageName, title = tt+db.getData().size() , longitude = currentLocation.longitude , latitude = currentLocation.latitude , address
            fetchlocation()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequest) {
            val photo: Bitmap = data?.extras?.get("data") as Bitmap
            saveMediaToStorage(photo)
        }
        if(requestCode == albumrequest){
            Toast.makeText(applicationContext,"Not u",Toast.LENGTH_LONG).show()
            imgCapture.setImageURI(data?.data)
        }
    }

    private fun fetchlocation(){
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
                currentLocation = location
                //Toast.makeText(applicationContext,currentLocation.latitude.toString() + "||" + currentLocation.longitude.toString(),Toast.LENGTH_LONG).show()
                val supportMapFragment = (supportFragmentManager.findFragmentById(R.id.myMap) as
                        SupportMapFragment?)!!

                //Toast.makeText(applicationContext,"My Address: " + xx.get(0).getAddressLine(0).toString() , Toast.LENGTH_LONG ).show()
                supportMapFragment.getMapAsync(this@MainActivity)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        googleMap.isMyLocationEnabled = true
        googleMap.uiSettings.isMapToolbarEnabled = false
        val markerPos = LatLng(currentLocation.latitude, currentLocation.longitude)

        currentMarker = mMap.addMarker(
            MarkerOptions()
                .position(markerPos).title("swinburne").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        )!!

        val tt:ArrayList<MyData> = db.getData()
        for(i in 0..tt.size-1){
            val markerPos1 = LatLng(tt[i].latitude,  tt[i].longitude)
            googleMap.addMarker(
                MarkerOptions()
                    .position(markerPos1).title(tt[i].title)
            )!!
        }

        googleMap.setOnMarkerClickListener { marker->
            //Toast.makeText(applicationContext,marker.title.toString(),Toast.LENGTH_LONG).show()
            if(marker.title != "swinburne"){
                printDetails(marker.title.toString())
            }
            true
        }

        val cameraPosition = CameraPosition.Builder()
            .target(markerPos)
            .zoom(16f).build()

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

}
