package com.github.il4enkodev.househeating.presentation.ui.events

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T: Event<*>>(
        private val listener: ((active: Boolean) -> Unit)? = null
): MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner) {
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        }
    }

    override fun onActive() {
        listener?.invoke(true)
    }

    override fun onInactive() {
        listener?.invoke(false)
    }

    override fun setValue(event: T) {
        if (hasActiveObservers() || event.sticky) {
            pending.set(true)
            super.setValue(event)
        }
    }

    operator fun plusAssign(event: T) {
        setValue(event)
    }
}