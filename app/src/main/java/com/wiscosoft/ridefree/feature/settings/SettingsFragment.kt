package com.wiscosoft.ridefree.feature.settings

import android.view.Gravity.START
import com.github.salomonbrys.kodein.instance
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.wiscosoft.ridefree.R.layout.fragment_list
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.extensions.randomId
import com.wiscosoft.ridefree.core.extensions.showError
import com.wiscosoft.ridefree.core.extensions.withModels
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.titleTextCard

@Config(title = "Settings", layout = fragment_list)
class SettingsFragment : BaseFragment<FragmentListBinding>() {

  private val vm: SettingsVM by injector.instance()

  override fun onReady() {
    showInfo()
  }

  private fun showInfo() {
    vm.user().bindToLifecycle(provider)
      .subscribe(this::onContent, this::showError)
  }

  private fun onContent(user: User) {
    binding.recyclerView.withModels {
      titleTextCard {
        text(user.userName)
        title("Username")
        gravity(START)
        id(randomId())
      }
      titleTextCard {
        text(user.passWord)
        title("Password")
        gravity(START)
        id(randomId())
      }
      vm.networkInfo().forEach {
        titleTextCard {
          title("Cookies")
          id(randomId())
          gravity(START)
          text(it)
        }
      }
    }
  }

}
