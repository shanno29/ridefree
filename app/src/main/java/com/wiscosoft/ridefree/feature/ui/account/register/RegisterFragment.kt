package com.wiscosoft.ridefree.feature.ui.account.register

import br.com.ilhasoft.support.validation.Validator
import com.jakewharton.rxbinding2.view.RxView.clicks
import com.wiscosoft.ridefree.R.layout.fragment_register
import com.wiscosoft.ridefree.core.app.hideKeyboard
import com.wiscosoft.ridefree.core.app.showMessage
import com.wiscosoft.ridefree.databinding.FragmentRegisterBinding
import com.wiscosoft.ridefree.feature.ui.account.login.LoginFragment
import com.wiscosoft.ridefree.core.base.BaseFragment
import com.wiscosoft.ridefree.core.base.Layout
import com.wiscosoft.ridefree.core.base.Title
import com.wiscosoft.ridefree.core.base.goTo
import javax.inject.Inject

@Title("Register")
@Layout(fragment_register)
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

  private val validator: Validator by lazy { Validator(binding) }
  @Inject lateinit var vm: RegisterVM

  override fun onReady() {
    super.onReady()
    validator.enableFormValidationMode()
    setupSubmitButton()
    setupLoginButton()
  }

  fun setupSubmitButton() {
    sub.add(clicks(binding.btSubmit)
      .doOnNext { hideKeyboard() }
      .filter { validator.validate() }
      .subscribe { attempt() }
    )
  }

  fun setupLoginButton() {
    sub.add(clicks(binding.btLogin)
      .subscribe { goTo(LoginFragment()) }
    )
  }

  fun attempt() {
    val email = binding.etEmail.text.toString()
    val username = binding.etUsername.text.toString()
    val password = binding.etPassword.text.toString()

    sub.add(vm.register(username, email, password)
      .doOnSubscribe { loadingUI.show() }
      .doOnEach { loadingUI.dismiss() }
      .subscribe(
        { goTo(LoginFragment()) },
        { showMessage(vm.reason(it)) }
      )
    )
  }

}



//interface LoginView<Data> {
//  fun showProgress(show: Boolean)
//  fun showItems(items: List<Data>)
//  fun showError(error: Throwable)
//  fun showEmpty()
//}

//override fun render(state: FeedsViewState) {
//  adapter.setData(state.feeds.orEmpty())
//}

//private fun reduce(newState: FeedsViewState) =
//newState.copy(feeds= newState.feeds ?: oldState.feeds)

//class RxPersonsViewModel {
//  private PublishSubject<Boolean> loading;
//  private PublishSubject<List<Person> persons;
//  private PublishSubject loadPersonsCommand;
//
//  public RxPersonsViewModel(){
//    loadPersonsCommand.flatMap(ignored -> backend.loadPersons())
//    .doOnSubscribe(ignored -> loading.onNext(true))
//    .doOnTerminate(ignored -> loading.onNext(false))
//    .subscribe(persons)
//    // Could also be implemented entirely different
//  }
//
//  // Subscribed to in View (i.e. Activity / Fragment)
//  public Observable<Boolean> loading(){
//    return loading;
//  }
//
//  // Subscribed to in View (i.e. Activity / Fragment)
//  public Observable<List<Person>> persons(){
//    return persons;
//  }
//
//  // Whenever this action is triggered (calling onNext() ) we load persons
//  public PublishSubject loadPersonsCommand(){
//    return loadPersonsCommand;
//  }
//}