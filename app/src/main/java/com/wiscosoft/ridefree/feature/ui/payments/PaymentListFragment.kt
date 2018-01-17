package com.wiscosoft.ridefree.feature.ui.payments

import com.jakewharton.rxbinding2.view.RxView
import com.wiscosoft.ridefree.R.drawable.add_drawable
import com.wiscosoft.ridefree.R.layout.fragment_list
import com.wiscosoft.ridefree.core.app.randomId
import com.wiscosoft.ridefree.core.app.showError
import com.wiscosoft.ridefree.core.app.withModels
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.domain.payment.Payment
import com.wiscosoft.ridefree.titleTextCard
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.core.base.Title
import com.wiscosoft.ridefree.core.base.goTo
import javax.inject.Inject

@Title("Payments")
@Layout(fragment_list)
class PaymentListFragment : BaseFragment<FragmentListBinding>() {

  @Inject lateinit var vm: PaymentsVM

  override fun onReady() {
    super.onReady()
    showPayments()
    setupFab()
  }

  private fun showPayments() {
    sub.add(vm.getAllPayments()
      .subscribe (
        { if (it.isNotEmpty()) showContent(it) else showEmpty() },
        this::showError
      )
    )
  }

  private fun setupFab() {
    binding.fab.setImageResource(add_drawable)
    sub.add(RxView.clicks(binding.fab)
      .subscribe { goTo(PaymentAddFragment()) }
    )
  }

  private fun showContent(payments: List<Payment>) {
    binding.recyclerView.withModels { payments.forEach {
      titleTextCard {
        click { _ -> goTo(PaymentInfoFragment().apply { id = it.id }) }
        text("Click for more info")
        title(it.cCNumber)
        id(it.id)
      }
    }}
  }

  private fun showEmpty() {
    binding.recyclerView.withModels {
      titleTextCard {
        title("You Have Yet to Add a Payment")
        text("Tap the plus button to enter a payment!")
        id(randomId())
      }
    }
  }

}