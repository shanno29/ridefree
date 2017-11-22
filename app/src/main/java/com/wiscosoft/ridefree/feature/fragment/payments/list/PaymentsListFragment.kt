package com.wiscosoft.ridefree.feature.fragment.payments.list

import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.github.salomonbrys.kodein.instance
import com.wiscosoft.ridefree.R.id.action_add
import com.wiscosoft.ridefree.R.layout.fragment_list
import com.wiscosoft.ridefree.R.menu.payment_menu
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.goTo
import com.wiscosoft.ridefree.core.extensions.showError
import com.wiscosoft.ridefree.core.extensions.withModels
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.domain.payment.Payment
import com.wiscosoft.ridefree.provider.router.Router
import com.wiscosoft.ridefree.titleTextCard

@Config(title = "Payments", layout = fragment_list)
class PaymentsListFragment : BaseFragment<FragmentListBinding>() {

  private val vm: PaymentsListVM by injector.instance()
  private val router: Router by injector.instance()

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(payment_menu, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when
      (action_add) { item.itemId -> { goTo(router.paymentAdd()); true }
      else -> false
    }
  }

  override fun onReady() {
    setHasOptionsMenu(true)
    showPayments()
  }

  private fun showPayments() {
    sub.add(vm.payments().subscribe(
        { if (it.isEmpty()) onEmpty() else onContent(it) },
        { showError(it) }
    ))
  }

  private fun onEmpty() {
    binding.recyclerView.withModels {
      titleTextCard {
        title("Begin by adding a payment")
        id(420)
      }
    }
  }

  private fun onContent(payments: List<Payment>) {
    binding.recyclerView.withModels {
      payments.forEach {
        titleTextCard {
          longClick { _, _, _, i -> showDialog(i, it); true }
          text(it.toString())
          title(it.ccNumber)
          id(it.id)
        }
      }
    }
  }

  private fun showDialog(i: Int, payment: Payment) {
    AlertDialog.Builder(context)
        .setPositiveButton("Yes") { _, _ -> deletePayment(i, payment) }
        .setNegativeButton("No") { dialog, _ -> dialog.cancel() }
        .setMessage("Are You Sure?")
        .setTitle("Delete Payment")
        .create()
        .show()
  }

  private fun deletePayment(index: Int, payment: Payment) {
    sub.add(vm.deletePayment(payment).map { index }.subscribe(
        { binding.recyclerView.removeViewAt(index) },
        { showError(it) }
    ))
  }

}