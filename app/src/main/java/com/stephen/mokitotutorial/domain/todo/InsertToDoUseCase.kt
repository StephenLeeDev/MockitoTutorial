package com.stephen.mokitotutorial.domain.todo

import com.stephen.mokitotutorial.data.model.ToDoModel
import com.stephen.mokitotutorial.data.repository.ToDoRepository
import com.stephen.mokitotutorial.domain.UseCase

internal class InsertToDoUseCase(
    private val toDoRepository: ToDoRepository
): UseCase {

    suspend operator fun invoke(toDoEntity: ToDoModel): Long {
        return toDoRepository.insertToDoItem(toDoEntity)
    }

}
