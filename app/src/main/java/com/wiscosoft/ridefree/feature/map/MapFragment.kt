package com.wiscosoft.ridefree.feature.map

import android.annotation.SuppressLint
import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.flowable
import com.wiscosoft.ridefree.core.setThreads
import com.wiscosoft.ridefree.core.showError
import com.wiscosoft.ridefree.databinding.FragmentMapBinding
import com.wiscosoft.ridefree.provider.gps.Gps
import com.wiscosoft.ridefree.provider.gps.Position
import com.wiscosoft.ridefree.provider.redux.State
import io.reactivex.Flowable
import redux.api.Store
import redux.asObservable

@Config("Home", R.layout.fragment_map)
class MapFragment : BaseFragment<FragmentMapBinding>() {

  private val mapStyle: MapStyleOptions by injector.instance()
  private val vm: MapVM by injector.instance()
  private lateinit var map: GoogleMap

  override fun onReady() {
    checkGps()
  }

  private fun checkGps() {
    sub.add(
      vm.checkGps           // check gps status
        .filter { it }        // on enabled
        .subscribe(
          { startMap() },     // on success start map
          { showError(it) }   // on error show error
        )
    )
  }

  private fun userLocUpdate() {
    sub.add(
      vm.locUpdates             // listen for location updates
        .subscribe(
          { updateCamera(it) },   // on success update camera
          { showError(it) }       // on error show error
        )
    )
  }

  private fun startMap() {

//    binding.mapView.onCreate(null)
//    binding.mapView.onResume()
//    binding.mapView.getMapAsync {
//      map = it
//      map.isBuildingsEnabled = false
//      map.isTrafficEnabled = false
//      map.isIndoorEnabled = false
//      map.setMapStyle(mapStyle)
//      toggleMapState(true)
//
//      startService<NotificationService>()
//      userLocUpdate()
//    }
  }

//  private fun onPlaceSelected(position: Position) {
//    userPin = position.toLatLn()
//
//    map.addMarker(
//      MarkerOptions()
//        .position(userPin)
//        .title("Marker in Home")
//    )
//
//    updateCamera(CameraUpdateFactory.newLatLng(userPin))
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

  private fun updateCamera(position: Position) {
    val latLng = LatLng(position.lat, position.lon)
    map.moveCamera(CameraUpdateFactory.newLatLng(latLng))
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

class MapVM(gps: Gps, store: Store<State>) {

  val checkGps: Flowable<Boolean> =
    gps.settings.setThreads()

  val locUpdates: Flowable<Position> =
    store.asObservable()
      .map(State::position)
      .flowable
      .setThreads()

}

val mapModule = Kodein.Module {

  bind<MapStyleOptions>() with singleton {
    val context: Context = instance()
    MapStyleOptions.loadRawResourceStyle(context, R.raw.map_full)
  }

  bind<MapVM>() with singleton {
    val gps: Gps = instance()
    val store: Store<State> = instance()
    MapVM(gps, store)
  }

}
