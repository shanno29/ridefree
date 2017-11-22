package com.wiscosoft.ridefree.feature.fragment.payments.add

import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.view.RxView.clicks
import com.wiscosoft.ridefree.R.layout.fragment_payments_add
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.goBack
import com.wiscosoft.ridefree.databinding.FragmentPaymentsAddBinding

@Config(title = "Add", layout = fragment_payments_add)
class PaymentAddFragment : BaseFragment<FragmentPaymentsAddBinding>() {

  private val vm: PaymentsAddVM by injector.instance()

  override fun onReady() {
    setupCardFormView()
    setupSubmitButton()
  }

  private fun setupCardFormView() {
    binding.cardForm.apply {
      cardRequired(true)
      mobileNumberRequired(true)
      expirationRequired(true)
      cvvRequired(true)
      actionLabel("OK")
      setup(activity)
    }
  }

  private fun setupSubmitButton() {
    sub.add(clicks(binding.submit)
        .doOnNext { binding.cardForm.validate() }
        .filter { binding.cardForm.isValid }
        .map { vm.save(binding.cardForm) }
        .subscribe { goBack() }
    )
  }

}