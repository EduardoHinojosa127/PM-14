package com.miempresa.googlemapv4

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.miempresa.googlemapv4.databinding.ActivityMapsBinding
import com.miempresa.googlemapv4.databinding.ActivityDestinosBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_destinos.*
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    AdapterView.OnItemSelectedListener{

    private val tipos_mapas = intArrayOf(
        GoogleMap.MAP_TYPE_NONE,
        GoogleMap.MAP_TYPE_NORMAL,
        GoogleMap.MAP_TYPE_SATELLITE,
        GoogleMap.MAP_TYPE_HYBRID,
        GoogleMap.MAP_TYPE_TERRAIN
    )

    var imagen =""
    private var Plaza = LatLng(-16.3988032,-71.5390943)
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var destino = ""
    var marcadorDestino: Marker? = null
    var coordenada = LatLng(0.0, 0.0)
    var mensaje = ""

    fun moverCamara(view:View?){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Plaza, 18f))
    }

    fun agregarMarcador(view:View?){
        mMap.addMarker(
            MarkerOptions().position(
                LatLng(
                    mMap.cameraPosition.target.latitude,
                    mMap.cameraPosition.target.longitude
                )
            ).title("Mi ubicación")
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        spnTipoMapa.setOnItemSelectedListener(this)

        val recibidos = this.intent.extras
        if (recibidos != null) {
            destino = intent.extras!!.getString("destino")!!
        }

    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.getUiSettings().setAllGesturesEnabled(true)
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.getUiSettings().setCompassEnabled(true)
        mMap.setBuildingsEnabled(true)
        mMap.getUiSettings().setTiltGesturesEnabled(true);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 123);
        }



        when (destino) {
            "plaza de armas" -> {
                coordenada = LatLng(-16.3988031, -71.5374435)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
                mensaje = "La plaza Mayor o plaza de Armas de Arequipa, " +
                        "es uno de los principales espacios públicos de Arequipa " +
                        "y el lugar de fundación de la ciudad"
                imagen="https://upload.wikimedia.org/wikipedia/commons/5/5f/Catedral_Arequipa%2C_Peru.jpg"
            }
            "characato" -> {
                coordenada = LatLng(-16.4717678,-71.4903118)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
                mensaje = "El distrito de Characato es uno de los 29 distritos que conforman la " +
                        "provincia de Arequipa en el departamento de Arequipa, bajo la administración del Gobierno regional de Arequipa," +
                        "en el sur del Perú."
                imagen="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTRYZ_9UHiQuOdvSL5GQ2YeRWfVmcbWOiLGs33w3gXNyF6enRZv6Kq5ERPp9e1YB1AXeGo&usqp=CAU"
            }
            "colca" -> {
                coordenada = LatLng(-15.609390, -72.089595)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
                mensaje = "El Cañón del Colca se encuentra en el valle de un río del sur de Perú y es famoso por ser uno de los más " +
                        "profundos del mundo. Es un destino famoso para el senderismo. Es un hábitat del cóndor Andino gigante, que se " +
                        "observa desde miradores como la Cruz del Cóndor. El paisaje del cañón abarca un valle verde y aldeas remotas tradicionales " +
                        "con agricultura en terrazas que precedió a los Incas."
                imagen = "https://www.actualidadambiental.pe/wp-content/uploads/2019/02/noticia-1549459529-arequipa-unesco-geoparque-colca-valle-volcanes-4.png"
            }
            "mirador sachaca" -> {
                coordenada = LatLng(-16.426291, -71.567432)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
                mensaje = "Desde lo alto se ve la extensa campiña arequipeña, las antiguas y modernas casas de la ciudad." +
                        " Esta torre de 19 metros de altura fue construida en la cima del cerro que albergaba el antiguo cementerio de" +
                        " Sachaca, remodelado en 1996. El mirador tiene cinco pisos. Desde su terraza se puede apreciar a los volcanes " +
                        "Misti, Chachani y Pichu Pichu. En el tercer piso se encuentra la estatua de un Cristo Redentor Blanco hecho de " +
                        "mármol de 2.5 metros de altura, que fue enviada desde Francia en el año 1969."
                imagen = "https://youimg1.tripcdn.com/target/0ww07120008ytw8wi04A7.jpg?proc=source%2Ftrip"
            }




            else -> {
                Toast.makeText(this, "No se encontro destino turistico", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        // Cámara
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada, 15f))
        // Eventos
        mMap.setOnMarkerClickListener(this)
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        if (p0!!.equals(marcadorDestino)) {
            val intent = Intent(this, destinos::class.java)
            intent.putExtra("destino", destino)
            intent.putExtra("latitud", p0.getPosition().latitude.toString() + "")
            intent.putExtra("longitud", p0.getPosition().longitude.toString() + "")
            intent.putExtra("imagen", imagen)
            intent.putExtra("info", mensaje)
            startActivity(intent)
            return true
        }
        return false
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        mMap.setMapType(tipos_mapas[p2]);
    }


    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}