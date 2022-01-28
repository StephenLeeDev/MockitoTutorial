package com.stephen.mokitotutorial.di

import com.stephen.mokitotutorial.data.repository.TestToDoRepository
import com.stephen.mokitotutorial.data.repository.ToDoRepository
import com.stephen.mokitotutorial.domain.todo.*
import com.stephen.mokitotutorial.presenter.detail.DetailMode
import com.stephen.mokitotutorial.presenter.detail.DetailViewModel
import com.stephen.mokitotutorial.presenter.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Written by StephenLeeDev on 2022/01/18.
 */

internal val appTestModule = module {

    factory { GetToDoListUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { InsertToDoUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { UpdateToDoUseCase(get()) }

    single<ToDoRepository> { TestToDoRepository() }

    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { (detailMode: DetailMode, id: Long) -> DetailViewModel(detailMode, id, get(), get(), get(), get()) }

}