package com.wiscosoft.ridefree.feature.payments

import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.view.RxView.clicks
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
    clicks(binding.paymentSubmit).bindToLifecycle(provider)
      .doOnNext { binding.cardForm.validate() }
      .filter { binding.cardForm.isValid }
      .map { vm.addPayment(binding.cardForm) }
      .subscribe({ goBack() }, this::showError)
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