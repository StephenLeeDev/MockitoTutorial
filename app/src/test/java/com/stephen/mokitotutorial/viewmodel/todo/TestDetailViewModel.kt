package com.stephen.mokitotutorial.viewmodel.todo

import com.stephen.mokitotutorial.data.model.ToDoModel
import com.stephen.mokitotutorial.domain.todo.InsertToDoUseCase
import com.stephen.mokitotutorial.presenter.detail.DetailMode
import com.stephen.mokitotutorial.presenter.detail.DetailViewModel
import com.stephen.mokitotutorial.presenter.detail.ToDoDetailState
import com.stephen.mokitotutorial.presenter.list.ListViewModel
import com.stephen.mokitotutorial.presenter.list.ToDoListState
import com.stephen.mokitotutorial.viewmodel.TestViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

/**
 * Written by StephenLeeDev on 2022/01/29.
 */

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
internal class TestDetailViewModel : TestViewModel() {

    private val detailViewModel: DetailViewModel by inject { parametersOf(DetailMode.DETAIL, id) }
    private val listViewModel: ListViewModel by inject()
    private val insertToDoUseCase: InsertToDoUseCase by inject()

    private val id = 1L

    private val todo = ToDoModel(
        id,
        title = "title 1",
        description = "description 1",
        hasCompleted = false
    )

    @Before
    fun init() {
        initData()
    }

    private fun initData() = runBlockingTest {
        insertToDoUseCase(todo)
    }

    @Test
    fun `test viewModel fetch`() = runBlockingTest {
        val testObservable = detailViewModel.toDoDetailLiveData.test()

        detailViewModel.fetchData()

        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(todo)
            )
        )
    }

    @Test
    fun `test delete todo`() = runBlockingTest {
        val detailTestObservable = detailViewModel.toDoDetailLiveData.test()

        detailViewModel.deleteToDo()

        detailTestObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Delete
            )
        )

        val listTestObservable = listViewModel.toDoListLiveData.test()

        listViewModel.fetchData()

        listTestObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(listOf())
            )
        )
    }

    @Test
    fun `test update todo`() = runBlockingTest {
        val detailTestObservable = detailViewModel.toDoDetailLiveData.test()

        val updateTitle = "title 1 update"
        val updateDescription = "description 1 update"

        val updateToDo = todo.copy(
            title = updateTitle,
            description = updateDescription
        )

        detailViewModel.writeToDo(
            title = updateTitle,
            description = updateDescription
        )

        detailTestObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(updateToDo)
            )
        )
    }
}
