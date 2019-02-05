package com.ceosilvajr.mytaxi.features

/**
 * @author ceosilvajr@gmail.com
 */
interface BaseView {

    fun showLoading()

    fun hideLoading()

    fun alertMessage(message: String)
}