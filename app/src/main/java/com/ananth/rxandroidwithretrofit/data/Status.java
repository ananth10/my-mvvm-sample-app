package com.ananth.rxandroidwithretrofit.data;

/**
 * Created by Babu on 2/13/2018.
 */

/**
 * Status of resource that is provided to UI
 *
 * <p>
 *
 *     These are usually created by Repository classes where they return
 *
 *     {@code LiveData<Resource<T>>} to pass back latest data to the UI with its fetch status
 *
 * </p>
 * */
public enum Status {
    SUCCESS,
    ERROR,
    LOADING
}
