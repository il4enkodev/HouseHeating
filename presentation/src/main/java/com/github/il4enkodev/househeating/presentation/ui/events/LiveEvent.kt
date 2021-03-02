package com.github.il4enkodev.househeating.presentation.ui.events

import androidx.lifecycle.*
import androidx.lifecycle.Observer
import java.util.*

class LiveEvent<E : Event<*>>(
        private val strategy: NotificationStrategy,
        private val onActiveListener: ((active: Boolean) -> Unit)? = null
) : LiveData<E>() {

    enum class NotificationStrategy {
        NOTIFY_LAST, NOTIFY_FIRST, NOTIFY_ALL;
    }

    private var pendings: Int = 0
    private val observers: Deque<ObserverWrapper> = ArrayDeque()

    fun push(event: E) {
        if (hasActiveObservers() || event.sticky) {
            pendings = when (strategy) {
                NotificationStrategy.NOTIFY_FIRST,
                NotificationStrategy.NOTIFY_LAST -> 1
                NotificationStrategy.NOTIFY_ALL -> observers.size
            }
            value = event
        }
    }

    private fun wrap(owner: LifecycleOwner, observer: Observer<in E>)
    : LiveEvent<E>.ObserverWrapper = when (strategy) {
        NotificationStrategy.NOTIFY_LAST -> NotifyLastObserver(owner, observer)
        NotificationStrategy.NOTIFY_FIRST -> NotifyFirstObserver(owner, observer)
        NotificationStrategy.NOTIFY_ALL -> NotifyAllObservers(owner, observer)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in E>) {
        if (owner.lifecycle.currentState != Lifecycle.State.DESTROYED) {
            val wrapper = wrap(owner, observer)
            observers.push(wrapper)
            owner.lifecycle.addObserver(wrapper)
            super.observe(owner, wrapper)
        }
    }

    override fun onActive() {
        onActiveListener?.invoke(true)
    }

    override fun onInactive() {
        onActiveListener?.invoke(false)
    }

    private inner class NotifyLastObserver(owner: LifecycleOwner,
                                           observer: Observer<in E>
    ) : ObserverWrapper(owner, observer) {

        override fun processEvent(event: E): Boolean {
            return if (isLast()){
                observer.onChanged(event)
                true
            } else false
        }
    }

    private inner class NotifyFirstObserver(owner: LifecycleOwner,
                                            observer: Observer<in E>
    ) : ObserverWrapper(owner, observer) {
        override fun processEvent(event: E): Boolean {
            return if (isFirst()) {
                observer.onChanged(event)
                true
            } else false
        }
    }

    private inner class NotifyAllObservers(owner: LifecycleOwner,
                                           observer: Observer<in E>
    ) : ObserverWrapper(owner, observer) {
        override fun processEvent(event: E): Boolean {
            observer.onChanged(event)
            return true
        }
    }

    private abstract inner class ObserverWrapper(val owner: LifecycleOwner,
                                                 val observer: Observer<in E>
    ) : Observer<E>, LifecycleEventObserver {

        abstract fun processEvent(event: E): Boolean

        override fun onChanged(event: E) {
            if (!isDestroyed() && pendings > 0 && processEvent(event))
                --pendings
        }

        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (isDestroyed()) {
                observers.remove(this)
                owner.lifecycle.removeObserver(this)
            }
        }

        fun isDestroyed(): Boolean {
            return owner.lifecycle.currentState == Lifecycle.State.DESTROYED
        }

        fun isFirst(): Boolean {
            return observers.peekFirst() == this
        }

        fun isLast(): Boolean {
            return observers.peekLast() == this
        }

        fun index(): Int {
            return observers.indexOf(this);
        }
    }
}

