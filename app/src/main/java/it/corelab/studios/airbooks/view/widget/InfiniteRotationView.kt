package it.corelab.studios.airbooks.view.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import butterknife.ButterKnife
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.adapters.home.InfiniteRotationAdapter
import java.util.concurrent.TimeUnit

/**
 * Created by Roberto_Vecchio on 16/02/18.
 */
class InfiniteRotationView(context: Context, attributeSet: AttributeSet?)
    : RelativeLayout(context, attributeSet) {

    private val layoutManager: LinearLayoutManager
    private lateinit var onScrollListener: OnScrollListener

    private var dispose: Disposable? = null

    init {
        View.inflate(context, R.layout.view_infinite_rotation, this)
        ButterKnife.bind(this)
        layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    fun setAdapter(adapter: InfiniteRotationAdapter) {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView_horizontalList)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        adapter.itemCount
                .takeIf { it > 1 }
                ?.apply {
                    onScrollListener = OnScrollListener(
                            adapter.itemCount,
                            layoutManager
                    ) {
                        // When dragging, we assume user swiped. So we will stop auto rotation
                        if (it == RecyclerView.SCROLL_STATE_DRAGGING) {
                            dispose?.dispose()
                        } else {
                            autoScroll(adapter.itemCount, 4000)
                        }
                    }
                    recyclerView.addOnScrollListener(onScrollListener)
                    recyclerView.scrollToPosition(1)
                }
    }

    fun autoScroll(listSize: Int, intervalInMillis: Long) {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView_horizontalList)
        dispose?.let {
            if(!it.isDisposed) return
        }
        dispose = Flowable.interval(intervalInMillis, TimeUnit.MILLISECONDS)
                .map { it % listSize + 1 }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    recyclerView.smoothScrollToPosition(it.toInt() + 1)
                }
    }

    fun stopAutoScroll() {
        dispose?.let(Disposable::dispose)
    }

    class OnScrollListener(
            private val itemCount: Int,
            val layoutManager: LinearLayoutManager,

            private val stateChanged: (Int) -> Unit) : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            super.onScrolled(recyclerView, dx, dy)

            val firstItemVisible = layoutManager.findFirstVisibleItemPosition()

            Log.i("RCOUNTER", "$itemCount")


            if (firstItemVisible > 0 && firstItemVisible % (itemCount - 1) == 0) {
                // When position reaches end of the list, it should go back to the beginning
                recyclerView?.scrollToPosition(1)
            } else if (firstItemVisible == 0) {
                // When position reaches beginning of the list, it should go back to the end
                recyclerView?.scrollToPosition(itemCount - 1)
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            stateChanged(newState)
        }
    }
}