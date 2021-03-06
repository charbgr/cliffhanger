package com.github.charbgr.arch

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RxPresenterTest {

  @Test
  fun test_init_view() {
    val presenter = TestPresenter()
    presenter.init(TestView)

    assertTrue(presenter.hasDisposableZeroSize())
  }

  @Test
  fun test_destroy_view() {
    val presenter = TestPresenter()
    presenter.init(TestView)
    presenter.addDummyUseCase()

    assertFalse(presenter.hasDisposableZeroSize())
    presenter.destroy()

    assertTrue(presenter.hasDisposableZeroSize())
  }

  private object TestView : View
  private class TestPresenter : RxPresenter<TestView>() {
    fun addDummyUseCase() {
      val useCase = object : UseCase<Unit, Unit>() {
        override fun build(params: Unit) {
        }
      }

      register(useCase)
    }

    fun hasDisposableZeroSize() = disposable.size() == 0
  }
}
