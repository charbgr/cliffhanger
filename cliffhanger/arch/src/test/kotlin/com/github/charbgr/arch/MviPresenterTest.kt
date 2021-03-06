package com.github.charbgr.arch

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import kotlin.test.assertTrue

class MviPresenterTest {

  @Test
  fun test_creating_null_intent() {
    val presenter = TestPresenter()
    presenter.init(TestView)

    assertTrue(presenter.addIntent(null) == Observable.empty<Any>())
  }

  @Test
  fun test_creating_intent() {
    val presenter = TestPresenter()
    presenter.init(TestView)

    val observable = PublishSubject.create<Unit>().hide()
    assertTrue(presenter.addIntent(observable) == observable)
  }

  private object TestView : View
  private class TestPresenter : MviPresenter<TestView, Unit>() {
    fun addIntent(observable: Observable<*>?): Observable<*> = intent(observable)
    override fun bindIntents() {}
    override fun renders(): Observable<Unit> = Observable.empty()
  }
}
