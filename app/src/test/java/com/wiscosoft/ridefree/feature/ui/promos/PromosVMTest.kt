package com.wiscosoft.ridefree.feature.ui.promos

import com.wiscosoft.ridefree.core.test.BaseTest
import com.wiscosoft.ridefree.feature.view.EmptyVM
import org.junit.Assert
import org.junit.Test

class PromosVMTest : BaseTest() {

  @Test
  fun testEmptyVM() {
    val vm = PromosVM()

    Assert.assertNotEquals(EmptyVM.DEFAULT, vm.empty)
  }

}

