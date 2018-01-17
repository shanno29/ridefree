package com.wiscosoft.ridefree.feature.ui.map

import android.annotation.SuppressLint
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MapStyleOptions
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.R.layout.fragment_map
import com.wiscosoft.ridefree.core.app.showError
import com.wiscosoft.ridefree.databinding.FragmentMapBinding
import com.wiscosoft.ridefree.provider.gps.Loc
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.core.base.Title
import javax.inject.Inject

@Title("Home")
@Layout(fragment_map)
class MapFragment : BaseFragment<FragmentMapBinding>() {

  private lateinit var map: GoogleMap
  @Inject lateinit var vm: MapVM

  override fun onReady() {
    super.onReady()
    checkGps()
  }

  private fun checkGps() {
    sub.add(vm.awaitGps.subscribe(this::startMap, this::showError))
  }

//  private fun userLocUpdate() {
//    sub.add(vm.locUpdates.subscribe(this::updateUserPin, this::showError))
//  }

  private fun startMap(style: MapStyleOptions) {
    binding.mapView.onCreate(null)
    binding.mapView.onResume()
    binding.mapView.getMapAsync {
      map = it
      map.isBuildingsEnabled = false
      map.isTrafficEnabled = false
      map.isIndoorEnabled = false
      map.setMapStyle(style)
      toggleMapState(true)

      //startService<NotificationService>()
      //userLocUpdate()
    }
  }

//  private fun onPlaceSelected(loc: Loc) {
//    userPin = loc
//
//    map.addMarker(
//      MarkerOptions()
//        .position(userPin.toLatLng)
//        .title("Marker in Home")
//    )
//
//    updateCamera(CameraUpdateFactory.)
//
//    updateCamera(CameraUpdateFactory
//      .newCameraPosition(
//        CameraPosition
//          .Builder()
//          .target(map.cameraPosition.target)
//          .bearing(30f)
//          .tilt(45f)
//          .zoom(17f)
//          .build()
//      )
//    )
//  }
//
//  private fun update
//
  private fun updateCamera(loc: Loc) {
    map.moveCamera(CameraUpdateFactory.newLatLng(loc.toLatLng))
  }

  @SuppressLint("MissingPermission")
  private fun toggleMapState(flag: Boolean) {
    map.isMyLocationEnabled = flag
    map.uiSettings.isCompassEnabled = flag
    map.uiSettings.isZoomControlsEnabled = flag
    map.uiSettings.isMyLocationButtonEnabled = flag
  }

  override fun onDestroy() {
    toggleMapState(false)
    super.onDestroy()
  }
}

