package com.wiscosoft.ridefree.feature.settings

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.setThreads
import com.wiscosoft.ridefree.core.showError
import com.wiscosoft.ridefree.databinding.FragmentListBinding
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.api.entity.user.UserApi
import com.wiscosoft.ridefree.provider.prefs.Prefs
import com.wiscosoft.ridefree.provider.redux.State
import io.reactivex.Flowable
import redux.api.Store

@Config("Settings", R.layout.fragment_list)
class SettingsFragment : BaseFragment<FragmentListBinding>() {

  private val vm: SettingsVM by injector.instance()

  override fun onReady() {
    showInfo()
  }

  private fun showInfo() {
    sub.add(vm.user.subscribe(this::contentState, this::showError))
  }

  private fun contentState(user: User) {
//    binding.recyclerView.withModels {
//      titleTextCard {
//        id(randomId())
//        gravity(START)
//        title("Username")
//        text(user.userName)
//      }
//      titleTextCard {
//        id(randomId())
//        gravity(START)
//        title("Password")
//        text(user.passWord)
//      }
//
//      vm.networkInfo().forEach {
//        titleTextCard {
//          id(randomId())
//          gravity(START)
//          title("Cookies")
//          text(it)
//        }
//      }
//
//      titleTextCard {
//        id(randomId())
//        gravity(CENTER)
//        text("Start")
//        click { _ -> startService<NotificationService>() }
//      }
//      titleTextCard {
//        id(randomId())
//        gravity(CENTER)
//        text("Stop")
//        click { _ -> stopService<NotificationService>() }
//      }
//    }
  }
}

class SettingsVM(private val userApi: UserApi, store: Store<State>, prefs: Prefs) {

  val user: Flowable<User> =
    userApi.get(store.state.user.id).setThreads()

  val networkInfo: Set<String> =
    prefs.cookies.get()

  fun setUser(user: User): Flowable<User> =
    userApi.modify(user).setThreads()
}

val settingsModule = Kodein.Module {

  bind<SettingsVM>() with singleton {
    val userApi: UserApi = instance()
    val store: Store<State> = instance()
    val prefs: Prefs = instance()
    SettingsVM(userApi, store, prefs)
  }

}