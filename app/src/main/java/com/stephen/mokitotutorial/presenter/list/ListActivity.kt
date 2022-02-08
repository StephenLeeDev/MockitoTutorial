package com.stephen.mokitotutorial.presenter.list

import android.os.Bundle
import com.stephen.mokitotutorial.R
import com.stephen.mokitotutorial.databinding.ActivityListBinding
import com.stephen.mokitotutorial.presenter.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

internal class ListActivity : BaseActivity<ListViewModel>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    private val binding: ActivityListBinding by lazy {
        ActivityListBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: ToDoListAdapter

    override val viewModel: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }

    override fun observeData() {

    }
}