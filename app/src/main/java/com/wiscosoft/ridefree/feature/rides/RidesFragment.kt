//package com.wiscosoft.ridefree.feature
//
//import android.app.AlertDialog
//import com.github.salomonbrys.kodein.Kodein
//import com.github.salomonbrys.kodein.bind
//import com.github.salomonbrys.kodein.instance
//import com.github.salomonbrys.kodein.singleton
//import com.wiscosoft.ridefree.R.drawable.add_drawable
//import com.wiscosoft.ridefree.R.drawable.delete_drawable
//import com.wiscosoft.ridefree.R.layout.fragment_list
//import com.wiscosoft.ridefree.R.layout.fragment_ride_add
//import com.wiscosoft.ridefree.domain.payment.Payment
//import com.wiscosoft.ridefree.domain.ride.Ride
//import com.wiscosoft.ridefree.provider.api.entity.ride.RideApi
//import com.wiscosoft.ridefree.core.base.BaseFragment
//import com.wiscosoft.ridefree.provider.router.Router
//import com.wiscosoft.ridefree.core.setThreads
//import com.wiscosoft.ridefree.core.showError
//import com.wiscosoft.ridefree.core.showMessage
//import io.reactivex.Flowable
//
//// RIDES LIST FRAGMENT
//class RidesListFragment : BaseFragment() {
//
//  override val buttonImg = add_drawable
//  override val layout = fragment_list
//  override val title = "Rides"
//
//  private val router: Router by injector.instance()
//  private val vm: RidesVM by injector.instance()
//
//  override fun onReady() {
//    setupAddButton()
//    showRides()
//  }
//
//  private fun setupAddButton() {
////    sub.add(clicks(binding.fab)
////      .map { router.rideAdd() }
////      .subscribe(this::goTo)
////    )
//  }
//
//  private fun showRides() {
//    sub.add(vm.getAllRides(10, 0)
//      .subscribe({ if (it.isEmpty()) emptyState() else contentState(it) }, this::showError)
//    )
//  }
//
////  titleTextCard {
////    id(randomId())
////    gravity(Gravity.START)
////    title("Password")
////    text(user.passWord)
////  }
//
//  private fun emptyState() {
////    binding.recyclerView.withModels {
////      titleTextCard {
////        title("We Have No Rides on Record For You")
////        text("Tap the plus button to add a ride!")
////        id(randomId())
////      }
////    }
//  }
//
//  private fun contentState(rides: List<Ride>) {
////    binding.recyclerView.setModels(rides.map {
////      TitleTextCardBindingModel_().apply {
////        click { _ -> goTo(router.rideInfo(it.id)) }
////        text("Click for more info")
////        title(it.restaurantId)
////        id(it.id)
////      }
////    })
//  }
//
//}
//
//
//// RIDES INFO FRAGMENT
//class RideInfoFragment : BaseFragment() {
//
//  override val buttonImg = delete_drawable
//  override val layout = fragment_list
//  override val title = "Info"
//
//  private val vm: RidesVM by injector.instance()
//  internal var id: Int = 0
//
//  override fun onReady() {
//    setupDeleteButton()
//    showRide()
//  }
//
//  private fun showRide() {
//    sub.add(vm.getRide(id)
//      .subscribe(this::contentState, this::showError)
//    )
//  }
//
//  private fun contentState(ride: Ride) {
////    binding.recyclerView.withModels {
////      titleTextCard {
////        title(ride.restaurantId)
////        text(ride.toString())
////        id(ride.id)
////      }
////    }
//  }
//
//  private fun setupDeleteButton() {
////    sub.add(clicks(binding.fab)
////      .subscribe { dialogState() }
////    )
//  }
//
//  private fun dialogState() {
//    AlertDialog.Builder(context)
//      .setPositiveButton("Yes") { _, _ -> deleteRide() }
//      .setNegativeButton("No") { dialog, _ -> dialog.cancel() }
//      .setMessage("Are You Sure?")
//      .setTitle("Delete Ride")
//      .create()
//      .show()
//  }
//
//  private fun deleteRide() {
////    sub.add(vm.deleteRide(id)
////      .doOnNext { binding.recyclerView.removeViewAt(0) }
////      .subscribe(
////        { goBack() },
////        { showError(it) }
////      )
////    )
//  }
//
//}
//
//
//// RIDES ADD FRAGMENT
//class RideAddFragment : BaseFragment() {
//
//  private val vm: RidesVM by injector.instance()
//  private val ride: Ride by injector.instance()
//
//  override val layout = fragment_ride_add
//  override val title = "Add"
//
//  override fun onReady() {
//    setupAddButton()
//    //binding.ride = ride
//  }
//
//  private fun setupAddButton() {
////    sub.add(clicks(binding.rideSubmit)
////      .toFlowable(BUFFER)
////      .flatMap { vm.addRide(ride, Payment()) }
////      .retryWhen(RetryRideAdd())
////      .subscribe(this::showSuccess, this::showError)
////    )
//  }
//
//  private fun showSuccess(ride: Ride) {
//    showMessage("Ride $ride.id Submitted!")
//  }
//
//}
//
//
//// VIEW MODEL
//class RidesVM(private val rideApi: RideApi) {
//
//  fun deleteRide(id: Int): Flowable<Ride> = getRide(id).flatMap { rideApi.delete(it) }.setThreads()
//
//  fun getRide(id: Int): Flowable<Ride> = rideApi.get(id).setThreads()
//
//  fun getAllRides(max: Int, offset: Int): Flowable<List<Ride>> {
//    return rideApi
//      .all(mapOf(
//          "maxResults" to max,
//          "offsetResults" to offset
//      ))
//      .setThreads()
//  }
//
//  fun addRide(ride: Ride, payment: Payment): Flowable<Ride> {
//    return rideApi
//      .add(mapOf(
//          "pickUp" to ride.pickup,
//          "restaurantId" to ride.restaurantId,
//          "cCNumber" to payment.cCNumber,
//          "cVV2Number" to payment.cVV2Number,
//          "cCExpiration" to payment.cCExpiration
//      ))
//      .setThreads()
//  }
//
//}
//
//
//// MODULE
//class RidesModule {
//  val bind = Kodein.Module {
//    bind<RidesVM>() with singleton { RidesVM(instance()) }
//  }
//
//}
