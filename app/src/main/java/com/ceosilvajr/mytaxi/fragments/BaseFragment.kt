package com.ceosilvajr.mytaxi.fragments

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject

/**
 * @author ceosilvajr@gmail.com
 */
open class BaseFragment : Fragment() {

    val compositeDisposable: CompositeDisposable  by inject()

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
}