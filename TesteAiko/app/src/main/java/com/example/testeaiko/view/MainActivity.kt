package com.example.testeaiko.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.testeaiko.DialogFragmentStop
import com.example.testeaiko.R
import com.example.testeaiko.databinding.ActivityMainBinding
import com.example.testeaiko.helper.BitmapHelper
import com.example.testeaiko.service.model.MarkerModel
import com.example.testeaiko.viewmodel.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var mapFragment: SupportMapFragment

    private val listMarker: MutableList<MarkerModel> = mutableListOf()

    private var isAllFabsVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla o layout usando o View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa o ViewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Autentica e lista as paradas
        viewModel.authenticate()

        // Configura os listeners
        binding.expandActivitiesButton.shrink()
        binding.expandActivitiesButton.setOnClickListener(this)
        binding.positionVehiclesButton.setOnClickListener(this)
        binding.stopButton.setOnClickListener(this)
        binding.lineButton.setOnClickListener(this)
        binding.clearButton.setOnClickListener(this)

        observe()

        // Configura o mapa e a exibição do mapa
        mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment

        mapFragment.getMapAsync { googleMap ->
            val bounds = LatLngBounds.builder()
            bounds.include(LatLng(-23.5489, -46.6388))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(11.0f))

        }
    }

    private fun setupMap(
        mapFragment: SupportMapFragment,
        listMarker: MutableList<MarkerModel>,
        isVehicle: Boolean
    ) {
        mapFragment.getMapAsync { googleMap ->
            googleMap.clear()
            addMarkersToMap(googleMap, listMarker, isVehicle)

            // Configura a exibição do mapa
            if (listMarker.isNotEmpty()) {
                val bounds = LatLngBounds.builder()
                listMarker.forEach { marker ->
                    bounds.include(marker.position)
                }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
            }
        }
    }

    private fun addMarkersToMap(
        googleMap: GoogleMap,
        listMarker: MutableList<MarkerModel>,
        isVehicle: Boolean
    ) {

        if (listMarker.isNotEmpty()) {
            // Adicione marcadores ao mapa
            listMarker.forEach {
                if (isVehicle) {
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(it.position)
                            .title(it.title)
                            .snippet(it.snippet)
                            .icon(
                                BitmapHelper.vectorToBitmap(
                                    this,
                                    R.drawable.baseline_directions_bus_24,
                                    ContextCompat.getColor(this, R.color.black)
                                )
                            )
                    )
                } else {
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(it.position)
                            .title(it.title)
                            .snippet(it.snippet)
                    )
                }
            }
        }
    }

    private fun observe() {

        viewModel.stops.observe(this) { stops ->
            listMarker.clear()

            stops.forEach { stop ->
                MarkerModel().apply {
                    this.position = LatLng(stop.latitudeStop, stop.longitudeStop)
                    this.title = stop.nameStop
                    this.snippet = stop.addressStop
                    listMarker.add(this)
                }
            }

            setupMap(mapFragment, listMarker, false)
        }

        viewModel.vehicles.observe(this) { vehicles ->
            listMarker.clear()

            vehicles.forEach { vehicle ->
                MarkerModel().apply {
                    this.position = LatLng(vehicle.latitude, vehicle.longitude)
                    this.title = vehicle.prefix.toString()
                    this.snippet = vehicle.accesibleDisable.toString()
                    listMarker.add(this)
                }
            }

            setupMap(mapFragment, listMarker, true)
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.expandActivitiesButton -> {

                isAllFabsVisible = if (!isAllFabsVisible) {
                    binding.positionVehiclesButton.show()
                    binding.stopButton.show()
                    binding.lineButton.show()
                    binding.clearButton.show()
                    binding.textPositionVehiclesButton.visibility = View.VISIBLE
                    binding.textStopButton.visibility = View.VISIBLE
                    binding.textSearchButton.visibility = View.VISIBLE
                    binding.textClearButton.visibility = View.VISIBLE

                    binding.expandActivitiesButton.extend()

                    true
                } else {
                    binding.positionVehiclesButton.hide()
                    binding.stopButton.hide()
                    binding.lineButton.hide()
                    binding.clearButton.hide()
                    binding.textPositionVehiclesButton.visibility = View.GONE
                    binding.textStopButton.visibility = View.GONE
                    binding.textSearchButton.visibility = View.GONE
                    binding.textClearButton.visibility = View.GONE

                    binding.expandActivitiesButton.shrink()

                    false
                }
            }

            binding.positionVehiclesButton -> {
                viewModel.listPositionVehicles()
                binding.expandActivitiesButton.performClick()
            }

            binding.stopButton -> {

                val dialogFragmentStop = DialogFragmentStop(onDismiss = { filter ->
                    viewModel.listStops(filter)
                })
                dialogFragmentStop.show(supportFragmentManager, "DialogFragmentStop")

                binding.expandActivitiesButton.performClick()
            }

            binding.lineButton -> {
                startActivity(Intent(this, LineActivity::class.java))
            }

            binding.clearButton -> {
                listMarker.clear()
                setupMap(mapFragment, listMarker, false)
                binding.expandActivitiesButton.performClick()
            }
        }
    }
}