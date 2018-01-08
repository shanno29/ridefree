//package com.wiscosoft.ridefree.feature
//
//import android.support.v7.app.AlertDialog
//import com.braintreepayments.cardform.view.CardForm
//import com.github.salomonbrys.kodein.Kodein
//import com.github.salomonbrys.kodein.bind
//import com.github.salomonbrys.kodein.instance
//import com.github.salomonbrys.kodein.singleton
//import com.wiscosoft.ridefree.R.drawable.add_drawable
//import com.wiscosoft.ridefree.R.drawable.delete_drawable
//import com.wiscosoft.ridefree.R.layout.fragment_list
//import com.wiscosoft.ridefree.R.layout.fragment_payment_add
//import com.wiscosoft.ridefree.domain.payment.Payment
//import com.wiscosoft.ridefree.provider.api.entity.payment.PaymentApi
//import com.wiscosoft.ridefree.core.base.BaseFragment
//import com.wiscosoft.ridefree.provider.router.Router
//import com.wiscosoft.ridefree.core.setThreads
//import com.wiscosoft.ridefree.core.showError
//import io.reactivex.Flowable
//
//
//// PAYMENTS LIST FRAGMENT
//class PaymentListFragment : BaseFragment() {
//
//  private val router: Router by injector.instance()
//
//  override val buttonImg = add_drawable
//  override val title = "Payments"
//
//
//  override fun onReady() {
//    //sub.add(addButton)
//    showPayments()
//  }
//
//  //private val addButton = clicks(binding.fab).subscribe { goTo(router.paymentAdd()) }
//
//  private fun showPayments() {
////    sub.add(vm.getAllPayments().subscribe {
////      when (it) {
////        //is Response.Loading -> loadingUI.show()
////        //is Response.Loading.Done -> {
////          loadingUI.hide()
////          when (it) {
////            is Response.Loading.Done.Empty -> showEmpty(it.vm)
////            is Response.Loading.Done.Error -> showError(it.error)
////            is Response.Loading.Done.Ready -> showContent(it.items)
////          }
////        }
////      }
////    })
//  }
//
//  private fun showContent(payments: List<Payment>) {
////    binding.recyclerView.withModels { payments.forEach {
////      titleTextCard {
////        click { _ -> goTo(router.paymentInfo(it.id)) }
////        text("Click for more info")
////        title(it.cCNumber)
////        id(it.id)
////      }
////    }}
//  }
//
//  private fun showEmpty() {
////    binding.recyclerView.withModels {
////      titleTextCard {
////        title("You Have Yet to Add a Payment")
////        text("Tap the plus button to enter a payment!")
////        id(randomId())
////      }
////    }
//  }
//
//}
//
//
//// PAYMENTS INFO FRAGMENT
//class PaymentInfoFragment : BaseFragment() {
//
//  private val vm: PaymentsVM by injector.instance()
//  internal var id: Int = 0
//
//  override val buttonImg = delete_drawable
//  override val layout = fragment_list
//  override val title = "Info"
//
//  override fun onReady() {
//    setupDeleteButton()
//    showPayment()
//  }
//
//  private fun showPayment() {
//    sub.add(vm.getPayment(id).subscribe(this::contentState, this::showError))
//  }
//
//  private fun contentState(payment: Payment) {
////    binding.recyclerView.withModels {
////      titleTextCard {
////        title(payment.cCNumber)
////        text(payment.toString())
////        id(payment.id)
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
//    AlertDialog.Builder(ctx)
//      .setPositiveButton("Yes") { _, _ -> deletePayment() }
//      .setNegativeButton("No") { dialog, _ -> dialog.cancel() }
//      .setMessage("Are You Sure?")
//      .setTitle("Delete Payment")
//      .create()
//      .show()
//  }
//
//  private fun deletePayment() {
////    sub.add(vm.deletePayment(id)
////      .doOnNext { binding.recyclerView.clear() }
////      .subscribe({ goBack() }, this::showError)
////    )
//  }
//
//}
//
//
//// PAYMENTS ADD FRAGMENT
//class PaymentAddFragment : BaseFragment() {
//
//  private val vm: PaymentsVM by injector.instance()
//  override val title = "Add"
//
//  override fun onReady() {
//    setupAddButton()
//    setupPayment()
//  }
//
//  private fun setupAddButton() {
////    sub.add(clicks(binding.paymentSubmit)
////      .doOnEach { binding.cardForm.validate() }
////      .filter { binding.cardForm.isValid }
////      .map { vm.addPayment(binding.cardForm) }
////      .subscribe(
////        { goBack() },
////        { showError(it) }
////      )
////    )
//  }
//
//  private fun setupPayment() {
////    binding.cardForm.apply {
////      mobileNumberRequired(true)
////      expirationRequired(true)
////      cardRequired(true)
////      cvvRequired(true)
////      actionLabel("OK")
////      setup(activity)
////    }
//  }
//
//}
//
//
//// VIEW MODEL
//class PaymentsVM(private val paymentApi: PaymentApi) {
//
//  fun getPayment(id: Int): Flowable<Payment> = paymentApi.get(id).setThreads()
//
////  fun getAllPayments(): Flowable<Response> {
////    return repo.paymentApi.all()
////      .map<Response> { Response.Loading.Done.Empty(emptyVM) }
////      //.map<Response> { Ready(it) }
////      //.switchIfEmpty { emptyVM }
////      //.onErrorReturn { Error(it.localizedMessage, it) }
////      .setThreads()
////  }
//
//  fun addPayment(cardForm: CardForm): Flowable<Payment> = paymentApi.add(createPayment(cardForm)).setThreads()
//
//  fun deletePayment(id: Int): Flowable<Payment> = paymentApi.get(id).flatMap { paymentApi.delete(it) }.setThreads()
//
//
//  private fun createPayment(cardForm: CardForm): Payment {
//    return Payment().apply {
//      cCNumber = cardForm.cardNumber
//      cVV2Number = cardForm.cvv
//      cCExpiration = "${cardForm.expirationMonth}/${cardForm.expirationYear}"
//      phoneNumber = "${cardForm.countryCode}-${cardForm.mobileNumber}"
//    }
//  }
//
//}
//
//
//// MODULE
//class PaymentsModule {
//
//  val bind = Kodein.Module {
//    bind<PaymentsVM>() with singleton { PaymentsVM(instance()) }
//  }
//
//}