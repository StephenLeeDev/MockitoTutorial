package com.stephen.mokitotutorial.presenter.detail

import com.stephen.mokitotutorial.data.model.ToDoModel

/**
 * Written by StephenLeeDev on 2022/01/24.
 */

sealed class ToDoDetailState {

    object UnInitialized : ToDoDetailState()

    object Loading : ToDoDetailState()

    data class Success(
        val toDoItem: ToDoModel
    ) : ToDoDetailState()

    object Delete : ToDoDetailState()

    object Modify : ToDoDetailState()

    object Error : ToDoDetailState()

    object Write : ToDoDetailState()

}