package com.wiscosoft.ridefree.feature.fragment.payments

import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.view.RxView.clicks
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.wiscosoft.ridefree.R.drawable.add_24dp
import com.wiscosoft.ridefree.R.layout.fragment_list
import com.wiscosoft.ridefree.TitleTextCardBindingModel_
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.goTo
import com.wiscosoft.ridefree.core.extensions.showError
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.domain.payment.Payment
import com.wiscosoft.ridefree.provider.router.Router

@Config(title = "Payments", layout = fragment_list)
class PaymentListFragment : BaseFragment<FragmentListBinding>() {

  private val router: Router by injector.instance()
  private val vm: PaymentsVM by injector.instance()
  private val img: Int = add_24dp

  override fun onReady() {
    binding.fab.setImageResource(img)
    setupAddButton()
    showPayments()
  }

  private fun setupAddButton() {
    clicks(binding.fab)
      .bindToLifecycle(provider)
      .map { router.paymentAdd() }
      .subscribe(this::goTo)
  }

  private fun showPayments() {
    vm.getAllPayments()
      .bindToLifecycle(provider)
      .map { if (it.isEmpty()) emptyView() else contentView(it) }
      .subscribe(binding.recyclerView::setModels, this::showError)
  }

  private fun emptyView() = listOf(
    TitleTextCardBindingModel_().apply {
      title(vm.empty.title)
      text(vm.empty.text)
      id(vm.empty.id)
    }
  )

  private fun contentView(payments: List<Payment>) = payments.map {
    TitleTextCardBindingModel_().apply {
      click { _ -> goTo(router.paymentInfo(it.id)) }
      text("Click for more info")
      title(it.cCNumber)
      id(it.id)
    }
  }

}
