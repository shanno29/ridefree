package com.wiscosoft.ridefree.core.base

import android.databinding.DataBindingUtil.setContentView
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.AppCompatActivityInjector
import io.reactivex.Flowable.fromIterable
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<Binding : ViewDataBinding> : AppCompatActivity(), AppCompatActivityInjector {

  private val config = javaClass.getAnnotation(Config::class.java)
  override val injector = KodeinInjector()
  internal val sub = CompositeDisposable()
  lateinit var binding: Binding
  abstract fun onReady()

  override fun onCreate(bundle: Bundle?) {
    super.onCreate(bundle)
    binding = setContentView(this, config.layout)!!
    initializeInjector()
    toolbarUpdates()
  }

  private fun toolbarUpdates() {
    supportFragmentManager.addOnBackStackChangedListener {
      sub.add(fromIterable(supportFragmentManager.fragments)
        .lastElement()
        .cast(BaseFragment::class.java)
        .map { it.config.title }
        .subscribe(this::setTitle)
      )
    }
  }

  override fun onStart() {
    super.onStart()
    onReady()
  }

  override fun onDestroy() {
    sub.clear()
    destroyInjector()
    super.onDestroy()
  }

//  fun changeFragment(f: Fragment, cleanStack: Boolean = false) {
//    val ft = supportFragmentManager.beginTransaction()
//    cleanStack.let { clearBackStack() }
//    if (cleanStack) { clearBackStack() }
//    ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
//    ft.replace(R.id.activity_base_content, f)
//    ft.addToBackStack(null)
//    ft.commit()
//  }
//
//  fun clearBackStack() {
//    val manager = supportFragmentManager
//    if (manager.backStackEntryCount > 0) {
//      val first = manager.getBackStackEntryAt(0)
//      manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//    }
//  }

  override fun onBackPressed() {
    val fragmentManager = supportFragmentManager
    if (fragmentManager.backStackEntryCount > 1)
      fragmentManager.popBackStack()
    else
      finish()
  }


}


