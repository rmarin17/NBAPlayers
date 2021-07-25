package com.rmarin17.nbaplayers.common.ext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.distinctUntilChanged
import androidx.viewbinding.ViewBinding

/**
 * File that contains all the extensions related to view.
 */

/**
 * Extension function to get the ViewModel in fragment without ViewModelFactory
 */
inline fun <reified T : ViewModel> Fragment.getViewModel(): T {
    return ViewModelProvider(this).get(T::class.java)
}

/**
 * Extension function to get the ViewModel in fragment with ViewModelFactory
 */
inline fun <reified T : ViewModel> Fragment.getViewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit = {}): T {
    val vm = ViewModelProvider(this, factory).get(T::class.java)
    vm.body()
    return vm
}

/**
 * Extension function to get the ViewModel in FragmentActivity with ViewModelFactory
 */
inline fun <reified T : ViewModel> FragmentActivity.getViewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit = {}): T {
    val vm = ViewModelProvider(this, factory).get(T::class.java)
    vm.body()
    return vm
}

/**
 * Extension function to get the ViewModel in AppCompatActivity with ViewModelFactory
 */
inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit = {}): T {
    val vm = ViewModelProvider(this, factory).get(T::class.java)
    vm.body()
    return vm
}

/**
 * Extension function for observe a livedata.
 */
fun <T> Fragment.observe(liveData: LiveData<T>?, observer: Observer<in T>) {
    liveData?.distinctUntilChanged()?.observe(viewLifecycleOwner, observer)
}

/**
 * Extension function to set up visibility of the view.
 */
fun View.visible(visible: Boolean = true) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

/**
 * Extension function to set up visibility of the ViewBinding.
 */
fun ViewBinding.visible(visible: Boolean = true) {
    root.visibility = if (visible) View.VISIBLE else View.GONE
}

/**
 * Extension function to inflate view.
 */
fun ViewGroup.inflate(layout: Int): View = LayoutInflater.from(context).inflate(layout, this, false)
