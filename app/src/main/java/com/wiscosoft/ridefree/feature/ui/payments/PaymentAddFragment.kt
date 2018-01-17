package com.wiscosoft.ridefree.feature.ui.payments

import com.jakewharton.rxbinding2.view.RxView.*
import com.wiscosoft.ridefree.R.layout.fragment_payment_add
import com.wiscosoft.ridefree.core.app.showError
import com.wiscosoft.ridefree.databinding.FragmentPaymentAddBinding
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.core.base.Title
import com.wiscosoft.ridefree.core.base.goBack
import javax.inject.Inject

@Title("Add")
@Layout(fragment_payment_add)
class PaymentAddFragment : BaseFragment<FragmentPaymentAddBinding>() {

  @Inject lateinit var vm: PaymentsVM

  override fun onReady() {
    super.onReady()
    setupPayment()
    addButton()
  }

  fun addButton() {
    sub.add(clicks(binding.paymentSubmit)
      .doOnEach { binding.cardForm.validate() }
      .filter { binding.cardForm.isValid }
      .map { vm.addPayment(binding.cardForm) }
      .ignoreElements()
      .subscribe(this::goBack, this::showError)
    )
  }

  fun setupPayment() {
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