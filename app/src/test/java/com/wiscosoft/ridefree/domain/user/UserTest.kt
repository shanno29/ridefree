package com.wiscosoft.ridefree.domain.user

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.gps.Position
import org.junit.Assert
import org.junit.Test
import redux.api.Reducer
import redux.api.Store
import redux.createStore

class UserTest {

  private val graph = Kodein { import(reduxModule) }

  @Test
  fun testState()  {
    // given

    // when
    val state = State.default

    // then

    Assert.assertEquals(false, state.auth)
    Assert.assertEquals(Position.default, state.position)
    Assert.assertEquals(User.default, state.user)
  }

  @Test
  fun testReducer() {
    // given
    val state: State = graph.instance()

    // when
    val reducer = Reducers.root
    val res = reducer.reduce(state, null)

    // then
    Assert.assertEquals(state, res)
  }

  @Test
  fun testStore() {
    // given
    val state: State = graph.instance()
    val reducer: Reducer<State> = graph.instance()

    // when
    val store: Store<State> = createStore(reducer, state)

    // then
    Assert.assertEquals(state, store.state)
  }

  @Test
  fun testAuthAction() {
    // given
    val store: Store<State> = graph.instance()

    // when
    Assert.assertEquals(false, store.state.auth)
    val req = Action.AuthUpdate(true)

    // then
    store.dispatch(req)
    Assert.assertEquals(true, store.state.auth)
  }

}
