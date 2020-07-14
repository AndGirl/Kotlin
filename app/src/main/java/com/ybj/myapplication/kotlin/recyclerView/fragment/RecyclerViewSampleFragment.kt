package com.ybj.myapplication.kotlin.recyclerView.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.recyclerView.adapter.CustomAdapter

/**
 * Created by 杨阳洋 on 2020/7/11.
 */
class RecyclerViewSampleFragment:Fragment(){

    private lateinit var currentLayoutManagerType: LayoutManagerType
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var dataset: Array<String>

    enum class LayoutManagerType { GRID_LAYOUT_MANAGER, LINEAR_LAYOUT_MANAGER }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataset()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(
            R.layout.recycler_view_frag,
            container, false).apply { tag = TAG}

        recyclerView = rootView.findViewById(R.id.recyclerView)

        layoutManager = LinearLayoutManager(activity)

        currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER

        recyclerView.addItemDecoration(DividerItemDecoration(activity,DividerItemDecoration.VERTICAL))

        if (savedInstanceState != null) {
            currentLayoutManagerType = savedInstanceState
                .getSerializable(KEY_LAYOUT_MANAGER) as LayoutManagerType
        }
        setRecyclerViewLayoutManager(currentLayoutManagerType)

        recyclerView.adapter = CustomAdapter(dataset)

        rootView.findViewById<RadioButton>(R.id.linear_layout_rb).setOnClickListener{
            setRecyclerViewLayoutManager(LayoutManagerType.LINEAR_LAYOUT_MANAGER)
        }

        rootView.findViewById<RadioButton>(R.id.grid_layout_rb).setOnClickListener{
            setRecyclerViewLayoutManager(LayoutManagerType.GRID_LAYOUT_MANAGER)
        }

        return rootView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(KEY_LAYOUT_MANAGER,currentLayoutManagerType)
        super.onSaveInstanceState(outState)
    }

    private fun initDataset() {
        dataset = Array(DATASET_COUNT) { i -> "元素# $i"}
    }

    private fun setRecyclerViewLayoutManager(layoutManagerType: LayoutManagerType) {
        var scrollPosition = 0

        if (recyclerView.layoutManager != null) {
            scrollPosition = (recyclerView.layoutManager as LinearLayoutManager)
                .findFirstCompletelyVisibleItemPosition()
        }

        when (layoutManagerType) {
            RecyclerViewSampleFragment.LayoutManagerType.GRID_LAYOUT_MANAGER -> {
                layoutManager = GridLayoutManager(activity, SPAN_COUNT)
                currentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER
            }
            RecyclerViewSampleFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER -> {
                layoutManager = LinearLayoutManager(activity)
                currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER
            }
        }

        with(recyclerView) {
            layoutManager = this@RecyclerViewSampleFragment.layoutManager
            scrollToPosition(scrollPosition)
        }

    }

    companion object {
        private val TAG = "RecyclerViewSampleFragment"
        private val KEY_LAYOUT_MANAGER = "layoutManager"
        private val SPAN_COUNT = 2
        private val DATASET_COUNT = 60
    }

}