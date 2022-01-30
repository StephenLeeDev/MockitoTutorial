package com.stephen.mokitotutorial.viewmodel.todo

import com.stephen.mokitotutorial.data.model.ToDoModel
import com.stephen.mokitotutorial.domain.todo.GetToDoItemUseCase
import com.stephen.mokitotutorial.domain.todo.InsertToDoListUseCase
import com.stephen.mokitotutorial.presenter.list.ListViewModel
import com.stephen.mokitotutorial.presenter.list.ToDoListState
import com.stephen.mokitotutorial.viewmodel.TestViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject

/**
 * Written by StephenLeeDev on 2022/01/30.
 */

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
internal class TestListViewModel: TestViewModel() {

    private val viewModel: ListViewModel by inject()

    private val insertToDoListUseCase: InsertToDoListUseCase by inject()
    private val getToDoItemUseCase: GetToDoItemUseCase by inject()

    private val list = (0 until 10).map {
        ToDoModel(
            id = it.toLong(),
            title = "title $it",
            description = "description $it",
            hasCompleted = false
        )
    }

    @Before
    fun init() {
        initData()
    }

    private fun initData() = runBlockingTest {
        insertToDoListUseCase(list)
    }

    @Test
    fun `test viewModel fetch`(): Unit = runBlockingTest {
        val testObservable = viewModel.toDoListLiveData.test()

        viewModel.fetchData()

        testObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(list)
            )
        )

    }

    @Test
    fun `test Item update`(): Unit = runBlockingTest {
        val todo = ToDoModel(
            id = 1,
            title = "title 1",
            description = "description 1",
            hasCompleted = true
        )
        viewModel.updateEntity(todo)
        assert(getToDoItemUseCase(1)?.hasCompleted ?: false == todo.hasCompleted)
    }

    @Test
    fun `test Item Delete All`(): Unit = runBlockingTest {
        val testObservable = viewModel.toDoListLiveData.test()
        viewModel.deleteAll()
        testObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(listOf())
            )
        )
    }
}