package com.mcgrady.xskeleton.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by mcgrady on 2019-08-10.
 */
public interface IView {

    <T> LifecycleTransformer<T> bindToLifecycle();

    default void showProgress() {
    }

    default void hideProgress() {
    }

    default void finish() {

    }
}
