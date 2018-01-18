package com.wiscosoft.ridefree.domain.payment

//import com.wiscosoft.ridefree.core.test.BaseTest
//import io.reactivex.Flowable
//import junit.framework.Assert.*
//import org.junit.Test
//
//class PaymentApiTest : BaseTest() {
//
//  class TestPaymentStorage: PaymentStorage {
//
//    private var items: MutableList<Payment> = mutableListOf(
//      Payment.DEFAULT.copy(id = 1),
//      Payment.DEFAULT.copy(id = 2),
//      Payment.DEFAULT.copy(id = 3)
//    )
//
//    override fun get(id: Int): Flowable<Payment> = Flowable.just(items.find { it.id == id }!!)
//
//    override fun all(): Flowable<List<Payment>> = Flowable.fromArray(items)
//
//    override fun modify(type: Payment): Int { items[items.indexOfFirst { it.id == type.id }] = type; return 1 }
//
//    override fun delete(type: Payment): Int { items.removeAt(items.indexOfFirst { it.id == type.id }); return 1 }
//
//    override fun add(type: Payment): Long { items.add(type); return 1 }
//
//  }
//
//  @Test
//  fun testGet() {
//    val paymentApiImp = PaymentApiImp(TestPaymentStorage())
//
//    assertEquals(1, paymentApiImp.get(1).blockingFirst().id)
//  }
//
//  @Test
//  fun testAll() {
//    val paymentApiImp = PaymentApiImp(TestPaymentStorage())
//
//    assertEquals(3, paymentApiImp.all().blockingFirst().count())
//  }
//
//  @Test
//  fun testAdd() {
//    val paymentApiImp = PaymentApiImp(TestPaymentStorage())
//
//    assertEquals(3, paymentApiImp.all().blockingFirst().count())
//
//    val payload = Payment.DEFAULT.copy(id = 4)
//    paymentApiImp.add(payload)
//
//    assertEquals(4, paymentApiImp.all().blockingFirst().count())
//    assertEquals(payload, paymentApiImp.get(4).blockingFirst())
//  }
//
//  @Test
//  fun testModify() {
//    val paymentApiImp = PaymentApiImp(TestPaymentStorage())
//
//    val payload = paymentApiImp.get(1).blockingFirst().copy(cCNumber = "Test")
//    paymentApiImp.modify(payload)
//
//    assertEquals("Test", paymentApiImp.get(1).blockingFirst().cCNumber)
//  }
//
//  @Test
//  fun testDelete() {
//    val paymentApiImp = PaymentApiImp(TestPaymentStorage())
//
//    assertEquals(3, paymentApiImp.all().blockingFirst().count())
//
//    paymentApiImp.delete(paymentApiImp.get(1).blockingFirst())
//
//    assertEquals(2, paymentApiImp.all().blockingFirst().count())
//  }
//
//}