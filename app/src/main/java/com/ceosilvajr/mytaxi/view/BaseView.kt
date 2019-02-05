package com.ceosilvajr.mytaxi.view

/**
 * @author ceosilvajr@gmail.com
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun alertMessage(message: String)
}