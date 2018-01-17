package com.wiscosoft.ridefree.provider.redux

import com.wiscosoft.ridefree.core.test.BaseTest
import com.wiscosoft.ridefree.provider.gps.Loc
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import redux.api.Reducer
import redux.api.Store
import redux.createStore

class ReduxTest : BaseTest() {

  @Test
  fun testReducer() {
    val state: State = State.DEFAULT

    val reducer = Reducers.root
    val newState = reducer.reduce(state, null)

    assertEquals(state, newState)
  }

  @Test
  fun testStore() {
    val state: State = State.DEFAULT
    val reducer: Reducer<State> = Reducers.root

    val store: Store<State> = createStore(reducer, state)

    assertEquals(state, store.state)
  }

  @Test
  fun testAuthAction() {
    val state: State = State.DEFAULT
    val reducer: Reducer<State> = Reducers.root
    val store: Store<State> = createStore(reducer, state)

    val action = Action.AuthUpdate(true)
    assertTrue(objectHelper(action))

    assertFalse(store.state.auth.flag)
    store.dispatch(action)

    assertTrue(store.state.auth.flag)
  }

  @Test
  fun testPosAction() {
    val state: State = State.DEFAULT
    val reducer: Reducer<State> = Reducers.root
    val store: Store<State> = createStore(reducer, state)

    val action = Action.LocUpdate(1.0, 2.0)
    assertTrue(objectHelper(action))

    assertEquals(Loc.DEFAULT, store.state.loc)
    store.dispatch(action)

    assertEquals(1.0, store.state.loc.lat, 0.0)
    assertEquals(2.0, store.state.loc.lon, 0.0)
  }

}
