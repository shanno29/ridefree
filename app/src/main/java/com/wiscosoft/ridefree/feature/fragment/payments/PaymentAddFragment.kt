package com.wiscosoft.ridefree.feature.fragment.payments

import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.view.RxView
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.wiscosoft.ridefree.R.layout.fragment_payment_add
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.goBack
import com.wiscosoft.ridefree.core.extensions.showError
import com.wiscosoft.ridefree.databinding.FragmentPaymentAddBinding

@Config(title = "Add", layout = fragment_payment_add)
class PaymentAddFragment : BaseFragment<FragmentPaymentAddBinding>() {

  private val vm: PaymentsVM by injector.instance()

  override fun onReady() {
    setupAddButton()
    setupPayment()
  }

  private fun setupAddButton() {
    RxView.clicks(binding.paymentSubmit)
      .bindToLifecycle(provider)
      .doOnNext { binding.cardForm.validate() }
      .filter { binding.cardForm.isValid }
      .map { vm.createPayment(binding.cardForm) }
      .map(vm::savePayment)
      .subscribe({ this.goBack() }, this::showError)
  }

  private fun setupPayment() {
    binding.cardForm.apply {
      mobileNumberRequired(true)
      expirationRequired(true)
      cardRequired(true)
      cvvRequired(true)
      actionLabel("OK")
      setup(activity)
    }
  }

}