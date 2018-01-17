package com.wiscosoft.ridefree.domain.ride

import com.wiscosoft.ridefree.core.test.BaseTest
import io.reactivex.Flowable
import io.reactivex.Observable
import junit.framework.Assert.*
import org.junit.Test

class RideApiTest : BaseTest() {

  class TestRideStorage: RideStorage {

    private var items: MutableList<Ride> = mutableListOf(
      Ride.DEFAULT.copy(id = 1),
      Ride.DEFAULT.copy(id = 2),
      Ride.DEFAULT.copy(id = 3)
    )

    override fun get(id: Int): Flowable<Ride> = Flowable.just(items.find { it.id == id }!!)

    override fun all(): Flowable<List<Ride>> = Flowable.fromArray(items)

    override fun modify(type: Ride): Int { items[items.indexOfFirst { it.id == type.id }] = type; return 1 }

    override fun delete(type: Ride): Int { items.removeAt(items.indexOfFirst { it.id == type.id }); return 1 }

    override fun add(type: Ride): Long {  return 1 }

  }

  @Test
  fun testGet() {
    val rideApiImp = RideApiImp(TestRideStorage())

    assertEquals(1, rideApiImp.get(1).blockingFirst().id)
  }

  @Test
  fun testAll() {
    val rideApiImp = RideApiImp(TestRideStorage())

    assertEquals(3, rideApiImp.all(emptyMap()).blockingFirst().count())
  }

  @Test
  fun testAdd() {
    val rideApiImp = RideApiImp(TestRideStorage())

    assertEquals(3, rideApiImp.all(emptyMap()).blockingFirst().count())

    val payload = mapOf(
      "pickup" to "testPickup",
      "restaurantId" to "testRestaurant"
    )
    rideApiImp.add(payload)

    assertEquals(4, rideApiImp.all(emptyMap()).blockingFirst().count())
    assertEquals("testPickup", rideApiImp.get(4).blockingFirst().pickup)
    assertEquals("testRestaurant", rideApiImp.get(4).blockingFirst().restaurantId)

  }

  @Test
  fun testModify() {
    val rideApiImp = RideApiImp(TestRideStorage())

    val payload = rideApiImp.get(1).blockingFirst().copy(pickup = "Test")
    rideApiImp.modify(payload)

    assertEquals("Test", rideApiImp.get(1).blockingFirst().pickup)
  }

  @Test
  fun testDelete() {
    val rideApiImp = RideApiImp(TestRideStorage())

    assertEquals(3, rideApiImp.all(emptyMap()).blockingFirst().count())

    rideApiImp.delete(rideApiImp.get(1).blockingFirst())

    assertEquals(2, rideApiImp.all(emptyMap()).blockingFirst().count())
  }

}



//  class TestRideNetwork: RideNetwork {
//
//    private var items: MutableList<Ride> = mutableListOf(
//      Ride.DEFAULT.copy(id = 1),
//      Ride.DEFAULT.copy(id = 2),
//      Ride.DEFAULT.copy(id = 3)
//    )
//
//    override fun all(queryMap: Map<String, Int>): Observable<List<Ride>> = Observable.fromArray(items)
//
//    override fun get(id: Int): Observable<Ride> = Observable.just(items.find { it.id == id }!!)
//
//    override fun add(body: Map<String, String>): Observable<Ride> {
//      val ride = Ride.DEFAULT.copy(
//        id = 1,
//        pickup = body["pickup"]!!,
//        restaurantId = body["restaurantId"]!!
//      )
//      items.add(ride)
//      return Observable.just(ride)
//    }
//
//    override fun modify(request: Ride): Observable<Ride> {
//      items[items.indexOfFirst { it.id == request.id }] = request
//      return Observable.just(request)
//    }
//
//    override fun delete(ride: Ride): Observable<Ride> {
//      items.removeAt(items.indexOfFirst { it.id == ride.id })
//      return Observable.just(ride)
//    }
//
//    override fun claim(ride: Ride): Observable<Ride> {
//      return Observable.just(items.find { it.id == ride.id }!!)
//    }
//
//  }
