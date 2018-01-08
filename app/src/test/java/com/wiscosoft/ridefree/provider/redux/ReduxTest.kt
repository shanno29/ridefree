package com.wiscosoft.ridefree.provider.redux

import com.wiscosoft.ridefree.domain.user.User
import com.wiscosoft.ridefree.provider.gps.Pos
import org.junit.Assert.*
import org.junit.Test
import redux.api.Store
import redux.createStore

class ReduxTest {

  @Test
  fun testReducer() {
    val state = State.DEFAULT
    val reducer = Reducers.root
    val newState = reducer.reduce(state, null)

    assertEquals(state, newState)
  }

  @Test
  fun testStore() {
    val state = State.DEFAULT
    val reducer = Reducers.root
    val store: Store<State> = createStore(reducer, state)

    assertEquals(state, store.state)
  }

  @Test
  fun testAuthAction() {
    val state = State.DEFAULT
    val reducer = Reducers.root
    val store: Store<State> = createStore(reducer, state)

    assertFalse(store.state.auth.flag)
    val action = Action.AuthUpdate(true)
    assertNotNull(action.toString())
    assertNotNull(action.hashCode())
    assertEquals(action, action)
    store.dispatch(action)

    assertTrue(store.state.auth.flag)
  }

  @Test
  fun testUserAction() {
    val state = State.DEFAULT
    val reducer = Reducers.root
    val store: Store<State> = createStore(reducer, state)

    assertEquals(User.DEFAULT, store.state.user)
    val action = Action.UserUpdate.Register("email", "userName", "passWord")
    assertNotNull(action.toString())
    assertNotNull(action.hashCode())
    assertEquals(action, action)
    store.dispatch(action)

    assertEquals("email", store.state.user.email)
    assertEquals("userName", store.state.user.userName)
    assertEquals("passWord", store.state.user.passWord)
  }

  @Test
  fun testPosAction() {
    val state = State.DEFAULT
    val reducer = Reducers.root
    val store: Store<State> = createStore(reducer, state)

    assertEquals(Pos.DEFAULT, store.state.pos)
    val action = Action.LocUpdate(1.0, 2.0)
    assertNotNull(action.toString())
    assertNotNull(action.hashCode())
    assertEquals(action, action)
    store.dispatch(action)

    assertEquals(1.0, store.state.pos.lat, 0.0)
    assertEquals(2.0, store.state.pos.lon, 0.0)
  }

}
