package com.wiscosoft.ridefree.feature.logout

import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.wiscosoft.ridefree.R
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Config
import com.wiscosoft.ridefree.core.goTo
import com.wiscosoft.ridefree.core.setThreads
import com.wiscosoft.ridefree.core.showMessage
import com.wiscosoft.ridefree.databinding.FragmentLogoutBinding
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.domain.user.api.UserApi
import com.wiscosoft.ridefree.provider.redux.State
import com.wiscosoft.ridefree.provider.router.Router
import io.reactivex.Flowable
import redux.api.Store
import retrofit2.HttpException

@Config("Logout", R.layout.fragment_logout)
class LogoutFragment : BaseFragment<FragmentLogoutBinding>() {

  private val router: Router by injector.instance()
  private val vm: LogoutVM by injector.instance()

  override fun onReady() {
    logoutUser()
  }

  private fun logoutUser() {
    sub.add(vm.logout()
      .subscribe(
        { goTo(router.splash) },
        { showMessage(vm.getReason(it)) }
      )
    )
  }
}

class LogoutVM(private val userApi: UserApi, private val store: Store<State>) {

  fun logout(): Flowable<User> {
    return userApi.get(store.state.user.id)
      .flatMap { userApi.logoff(it) }
      .setThreads()
  }

  fun getReason(error: Throwable): String {
    if (error !is HttpException) return error.localizedMessage
    return when (error.code()) {
      503 -> "Server Error Try Again Later"
      else -> "Unidentified Error: "
    }
  }
}

val logoutModule = Module {

  bind<LogoutVM>() with singleton {
    val userApi: UserApi = instance()
    val store: Store<State> = instance()
    LogoutVM(userApi, store)
  }

}
