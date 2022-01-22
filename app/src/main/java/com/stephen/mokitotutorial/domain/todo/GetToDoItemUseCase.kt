package com.stephen.mokitotutorial.domain.todo

import com.stephen.mokitotutorial.data.model.ToDoModel
import com.stephen.mokitotutorial.data.repository.ToDoRepository
import com.stephen.mokitotutorial.domain.UseCase

internal class GetToDoItemUseCase(
    private val toDoRepository: ToDoRepository
): UseCase {

    suspend operator fun invoke(id: Long): ToDoModel? {
        return toDoRepository.getToDoItem(id)
    }

}
