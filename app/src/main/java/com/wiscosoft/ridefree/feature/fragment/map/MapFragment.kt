package com.wiscosoft.ridefree.feature.fragment.map

import android.annotation.SuppressLint
import com.github.salomonbrys.kodein.instance
import com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.wiscosoft.ridefree.R.layout.fragment_map
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.showError
import com.wiscosoft.ridefree.databinding.FragmentMapBinding
import com.wiscosoft.ridefree.provider.gps.Loc


@Config(title = "RideFree", layout = fragment_map)
class MapFragment : BaseFragment<FragmentMapBinding>() {

  private val mapStyle: MapStyleOptions by injector.instance()
  private val vm: MapVM by injector.instance()
  private var map: GoogleMap? = null

  override fun onReady() {
    checkGps()
  }

  private fun checkGps() {
    sub.add(vm.gps.checkGps().filter { it }.subscribe(
        { startMap() },
        { showError(it) }
    ))
  }

  private fun startMap() {
    binding.mapView.getMapAsync {
      map = it
      toggleMapState(true, mapStyle)
      getLocation()
    }
  }

  private fun getLocation() {
    sub.add(vm.gps.getLocation().subscribe(
        { onPlaceSelected(it) },
        { showError(it) }
    ))
  }

  private fun onPlaceSelected(loc: Loc) {
    map?.addMarker(MarkerOptions().position(loc.toLatLn()))
    map?.moveCamera(newLatLngZoom(loc.toLatLn(), 14f))
  }

  override fun onDestroyView() {
    toggleMapState(false, null)
    map?.clear()
    binding.mapView.getMapAsync(null)
    //binding.mapView.onDestroy()
    super.onDestroyView()
  }

  @SuppressLint("MissingPermission")
  private fun toggleMapState(flag: Boolean, mapStyle: MapStyleOptions?) {
    map?.setMapStyle(mapStyle)
    map?.isMyLocationEnabled = flag
    map?.uiSettings?.isCompassEnabled = flag
    map?.uiSettings?.isMyLocationButtonEnabled = flag
  }

}
