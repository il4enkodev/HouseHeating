package com.github.il4enkodev.househeating.presentation.ui.behavior

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.github.il4enkodev.househeating.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlin.math.abs


class NavigationSheetBehavior<V : View> : BottomSheetBehavior<V> {

    companion object {
        fun <V : View> from(view: V): NavigationSheetBehavior<V> {
            try {
                return BottomSheetBehavior.from(view) as NavigationSheetBehavior<V>
            } catch (e: ClassCastException) {
                throw IllegalArgumentException(
                        "The view is not associated with NavigationLayoutBehavior"
                )
            }
        }
    }

    var scrimColor: Int = Color.BLACK

    var scrimClickable: Boolean = true
        set(value) {
            if (field != value) {
                field = value
                if (!value) scrimGestureDetector = null
            }
        }

    var scrimEmphasis: Float = 0.6f
        set(value) {
            field = value.coerceIn(0f, 1f)
        }

    var expanded: Boolean
        get() = state == STATE_EXPANDED
        set(value) {
            state = if (value) STATE_EXPANDED else STATE_HIDDEN
        }

    private var scrimOpacity = 0f
    private var scrimGestureDetector: GestureDetector? = null

    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            onScrimClicked()
            return true
        }
    }

    private val bottomSheetCallback = object : BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            updateScrimOpacity(bottomSheet.parent as CoordinatorLayout, slideOffset)
        }
    }

    constructor() : super()

    @SuppressLint("RestrictedApi", "VisibleForTests")
    constructor(context: Context, attr: AttributeSet) : super(context, attr) {
        val typedArray = context.obtainStyledAttributes(
                attr, R.styleable.NavigationSheetBehavior)

        scrimColor = typedArray.getColor(
                R.styleable.NavigationSheetBehavior_behavior_scrim_color, Color.BLACK)
        scrimEmphasis = typedArray.getFloat(
                R.styleable.NavigationSheetBehavior_behavior_scrim_emphasis, .6f)

        scrimClickable = typedArray.getBoolean(
                R.styleable.NavigationSheetBehavior_behavior_scrim_clickable, true)

        if (typedArray.getBoolean(
                        R.styleable.NavigationSheetBehavior_behavior_shape_animation_disabled, false)) {
            disableShapeAnimations()
        }

        if (typedArray.hasValue(R.styleable.NavigationSheetBehavior_behavior_expanded)) {
            expanded = typedArray.getBoolean(
                    R.styleable.NavigationSheetBehavior_behavior_expanded, false)
        }

        typedArray.recycle()
    }

    init {
        addBottomSheetCallback(bottomSheetCallback)
    }

    override fun onLayoutChild(parent: CoordinatorLayout, child: V, layoutDirection: Int): Boolean {
        if (scrimClickable && scrimGestureDetector == null)
            scrimGestureDetector = GestureDetector(parent.context, gestureListener)
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onAttachedToLayoutParams(layoutParams: CoordinatorLayout.LayoutParams) {
        super.onAttachedToLayoutParams(layoutParams)
        scrimGestureDetector = null
    }

    override fun onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams()
        scrimGestureDetector = null
    }

    private fun onScrimClicked() {
        expanded = false
    }

    override fun getScrimColor(parent: CoordinatorLayout, child: V): Int {
        return scrimColor
    }

    override fun getScrimOpacity(parent: CoordinatorLayout, child: V): Float {
        return scrimOpacity
    }

    override fun onInterceptTouchEvent(parent: CoordinatorLayout, child: V, event: MotionEvent): Boolean {
        return detectScrimInteraction(parent, child, event) or
                super.onInterceptTouchEvent(parent, child, event)
    }

    override fun onTouchEvent(parent: CoordinatorLayout, child: V, event: MotionEvent): Boolean {
        return detectScrimInteraction(parent, child, event)
                || super.onTouchEvent(parent, child, event)
    }

    private fun updateScrimOpacity(parent: CoordinatorLayout, slideOffset: Float) {
        scrimOpacity = (1 - abs(slideOffset)).coerceIn(0f, 1f) * scrimEmphasis
        parent.invalidate()
    }

    private fun detectScrimInteraction(parent: CoordinatorLayout, child: V,
                                       event: MotionEvent): Boolean {
        if (state == STATE_EXPANDED
                && !parent.isPointInChildBounds(child, event.x.toInt(), event.y.toInt())) {
            return scrimGestureDetector?.onTouchEvent(event) ?: false
        }
        return false
    }
}