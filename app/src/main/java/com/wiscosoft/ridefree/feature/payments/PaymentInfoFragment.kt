package com.wiscosoft.ridefree.feature.payments

import android.support.v7.app.AlertDialog
import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding2.view.RxView.clicks
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.wiscosoft.ridefree.R.drawable.delete_24dp
import com.wiscosoft.ridefree.R.layout.fragment_list
import com.wiscosoft.ridefree.TitleTextCardBindingModel_
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.goBack
import com.wiscosoft.ridefree.core.extensions.showError
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.domain.payment.Payment

@Config(title = "Info", layout = fragment_list)
class PaymentInfoFragment : BaseFragment<FragmentListBinding>() {

  private val vm: PaymentsVM by injector.instance()
  private val img: Int = delete_24dp
  internal var id: Int = 0

  override fun onReady() {
    binding.fab.setImageResource(img)
    setupDeleteButton()
    showPayment()
  }

  private fun setupDeleteButton() {
    clicks(binding.fab).bindToLifecycle(provider)
      .subscribe { dialogState() }
  }

  private fun showPayment() {
    vm.getPayment(id).bindToLifecycle(provider)
      .map(this::contentState)
      .subscribe(binding.recyclerView::setModels, this::showError)
  }

  private fun deletePayment() {
    vm.deletePayment(id).bindToLifecycle(provider)
      .doOnNext { binding.recyclerView.removeViewAt(0) }
      .subscribe({ goBack() }, this::showError)
  }

  private fun contentState(payment: Payment) = listOf(
    TitleTextCardBindingModel_().apply {
      title(payment.cCNumber)
      text(payment.toString())
      id(payment.id)
    }
  )

  private fun dialogState() {
    AlertDialog.Builder(context)
      .setPositiveButton("Yes") { _, _ -> deletePayment() }
      .setNegativeButton("No") { dialog, _ -> dialog.cancel() }
      .setMessage("Are You Sure?")
      .setTitle("Delete Payment")
      .create()
      .show()
  }

}
