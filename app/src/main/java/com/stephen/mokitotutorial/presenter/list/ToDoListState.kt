package com.stephen.mokitotutorial.presenter.list

import com.stephen.mokitotutorial.data.model.ToDoModel

/**
 * Written by StephenLeeDev on 2022/01/24.
 */

sealed class ToDoListState {

    object UnInitialized : ToDoListState()

    object Loading : ToDoListState()

    data class Success(
        val toDoList: List<ToDoModel>
    ) : ToDoListState()

    object Error : ToDoListState()

}
