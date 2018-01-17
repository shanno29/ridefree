package com.wiscosoft.ridefree.feature.ui.payments

import android.support.v7.app.AlertDialog
import com.jakewharton.rxbinding2.view.RxView.clicks
import com.wiscosoft.ridefree.R.drawable.delete_drawable
import com.wiscosoft.ridefree.R.layout.fragment_list
import com.wiscosoft.ridefree.core.app.showError
import com.wiscosoft.ridefree.core.app.withModels
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.domain.payment.Payment
import com.wiscosoft.ridefree.titleTextCard
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.core.base.Title
import com.wiscosoft.ridefree.core.base.goBack
import javax.inject.Inject

@Title("Info")
@Layout(fragment_list)
class PaymentInfoFragment : BaseFragment<FragmentListBinding>() {

  @Inject lateinit var vm: PaymentsVM
  internal var id: Int = 0

  override fun onReady() {
    super.onReady()
    deleteButton()
    showPayment()
  }

  private fun showPayment() {
    sub.add(vm.getPayment(id)
      .subscribe(this::contentState, this::showError))
  }

  private fun contentState(payment: Payment) {
    binding.recyclerView.withModels {
      titleTextCard {
        title(payment.cCNumber)
        text(payment.toString())
        id(payment.id)
      }
    }
  }

  private fun deleteButton() {
    binding.fab.setImageResource(delete_drawable)
    sub.add(clicks(binding.fab)
      .subscribe { dialogState() }
    )
  }

  private fun dialogState() {
    AlertDialog.Builder(context)
      .setPositiveButton("Yes") { _, _ -> deletePayment() }
      .setNegativeButton("No") { dialog, _ -> dialog.cancel() }
      .setMessage("Are You Sure?")
      .setTitle("Delete Payment")
      .create()
      .show()
  }

  private fun deletePayment() {
    sub.add(vm.deletePayment(id)
      .doOnNext { binding.recyclerView.clear() }
      .ignoreElements()
      .subscribe(this::goBack, this::showError)
    )
  }

}


