package com.example.platzi.view.ui.fragments.locationFragment

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.platzi.R
import com.example.platzi.models.Location
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class LocationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =childFragmentManager.findFragmentById(R.id.map_fragment_location) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        /**
         * LIGA PARA PERZONALIZAR EL MAPA DE GOOGLE MAPS
         * https://mapstyle.withgoogle.com
         * */

        val location = Location()//traemos el Objeto con el contenido del mapa.
        val zoomLocation = 16f//ponemos el zoom con el que se iniciara el mapa.
        val centerMap = LatLng(location.latitude, location.longitude) //centramos el mapa mediante la funcion LatLng y lo almacenamos en una variable
        val markerOptions = MarkerOptions()
        val bitmapDraw = context?.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.img_logo_platzi)  } as BitmapDrawable
        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw.bitmap,150,150,false)

        markerOptions.position(centerMap)//centramos el mapa al marcador
        markerOptions.title("Platzi Conf 2020")//colocamos un titulo
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))//agregamos un marcador

        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoomLocation))// mediante la propiedad animateCamara centramos la ubicacion y colocamos el zoom.
        googleMap?.addMarker(markerOptions) //Añadimos las opciones del marcador
        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.custom_map))//obtenemos el json de personalizacion de el mapa

        googleMap?.setOnMarkerClickListener(this)
    }

    override fun onMarkerClick(markerClick: Marker?): Boolean {
        findNavController().navigate(R.id.location_detail_fragment_dialog)
        return true
    }
}