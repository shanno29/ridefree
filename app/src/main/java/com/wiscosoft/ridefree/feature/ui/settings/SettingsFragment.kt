package com.wiscosoft.ridefree.feature.ui.settings

import android.view.Gravity.CENTER
import android.view.Gravity.START
import com.wiscosoft.ridefree.R.layout.fragment_list
import com.wiscosoft.ridefree.core.app.randomId
import com.wiscosoft.ridefree.core.app.showError
import com.wiscosoft.ridefree.core.app.withModels
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.titleTextCard
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.core.base.Title
import javax.inject.Inject

@Title("Settings")
@Layout(fragment_list)
class SettingsFragment : BaseFragment<FragmentListBinding>() {

  @Inject lateinit var vm: SettingsVM

  override fun onReady() {
    super.onReady()
    showInfo()
  }

  private fun showInfo() {
    sub.add(vm.userUpdates.subscribe(this::contentState, this::showError))
  }

  private fun contentState(user: User) {
    binding.recyclerView.withModels {
      titleTextCard {
        id(randomId())
        gravity(START)
        title("Username")
        text(user.userName)
      }
      titleTextCard {
        id(randomId())
        gravity(START)
        title("Password")
        text(user.passWord)
      }

      titleTextCard {
        id(randomId())
        gravity(CENTER)
        text("Start")
        click { _ -> /* TODO start service */ }
      }
      titleTextCard {
        id(randomId())
        gravity(CENTER)
        text("Stop")
        click { _ ->  /* TODO stop service */ }
      }
    }
  }

}
