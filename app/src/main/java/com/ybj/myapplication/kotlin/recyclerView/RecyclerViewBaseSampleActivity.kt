package com.ybj.myapplication.kotlin.recyclerView

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ViewAnimator
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.recyclerView.commons.SampleActivityBase
import com.ybj.myapplication.kotlin.recyclerView.fragment.LogFragment
import com.ybj.myapplication.kotlin.recyclerView.fragment.RecyclerViewSampleFragment
import com.ybj.myapplication.kotlin.utils.Log
import com.ybj.myapplication.kotlin.utils.LogWrapper
import com.ybj.myapplication.kotlin.utils.MessageOnlyLogFilter

class RecyclerViewBaseSampleActivity : SampleActivityBase() {

    private var logShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_base_sample)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .run {
                    replace(R.id.sample_recyclerview_content_fragment,RecyclerViewSampleFragment())
                    commit()
                }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.recycler_sample,menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.findItem(R.id.menu_toggle_log).run {
            isVisible = findViewById<ViewAnimator>(R.id.sample_recyclerview_output) is ViewAnimator
            setTitle(if (logShown) R.string.action_sign_in else R.string.action_sign_in_short)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId){
        R.id.menu_toggle_log -> {
            logShown = !logShown
            val output = findViewById<ViewAnimator>(R.id.sample_recyclerview_output) as ViewAnimator
            output.displayedChild = if(logShown) 1 else 0
            invalidateOptionsMenu()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun initializeLogging() {
        val logWrapper = LogWrapper()

        Log.logNode = logWrapper

        val msgFilter = MessageOnlyLogFilter()
        logWrapper.next = msgFilter

        val logFragment = supportFragmentManager.findFragmentById(R.id.log_fragment) as LogFragment
        msgFilter.next = logFragment.logView

        Log.i(TAG, "Ready")
    }

    companion object {
        const val TAG = "RecyclerViewBaseSampleActivity"
    }

}
