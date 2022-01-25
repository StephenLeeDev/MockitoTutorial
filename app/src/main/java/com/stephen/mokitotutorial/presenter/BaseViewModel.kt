package com.stephen.mokitotutorial.presenter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

/**
 * Written by StephenLeeDev on 2022/01/25.
 */

internal abstract class BaseViewModel : ViewModel() {
    abstract fun fetchData(): Job
}