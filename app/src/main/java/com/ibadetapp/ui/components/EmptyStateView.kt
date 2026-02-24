package com.ibadetapp.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import com.ibadetapp.databinding.ViewEmptyStateBinding

/**
 * Reusable empty state view for displaying when no content is available
 * Shows icon, title, description, and optional action button
 */
class EmptyStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: ViewEmptyStateBinding

    init {
        binding = ViewEmptyStateBinding.inflate(LayoutInflater.from(context), this, true)
    }

    /**
     * Set the empty state with all parameters
     * @param iconResId Drawable resource ID for the icon
     * @param title Title text
     * @param description Description text
     * @param actionButtonText Text for action button (null to hide button)
     * @param onActionClick Callback when action button is clicked
     */
    fun setEmptyState(
        @DrawableRes iconResId: Int,
        title: String,
        description: String,
        actionButtonText: String? = null,
        onActionClick: (() -> Unit)? = null
    ) {
        binding.apply {
            ivEmptyIcon.setImageResource(iconResId)
            tvEmptyTitle.text = title
            tvEmptyDescription.text = description

            if (actionButtonText != null) {
                btnEmptyAction.text = actionButtonText
                btnEmptyAction.visibility = VISIBLE
                btnEmptyAction.setOnClickListener { onActionClick?.invoke() }
            } else {
                btnEmptyAction.visibility = GONE
            }
        }
    }

    /**
     * Set only the icon
     */
    fun setIcon(@DrawableRes iconResId: Int) {
        binding.ivEmptyIcon.setImageResource(iconResId)
    }

    /**
     * Set only the title
     */
    fun setTitle(title: String) {
        binding.tvEmptyTitle.text = title
    }

    /**
     * Set only the description
     */
    fun setDescription(description: String) {
        binding.tvEmptyDescription.text = description
    }

    /**
     * Set action button with callback
     */
    fun setActionButton(
        text: String,
        onClick: () -> Unit
    ) {
        binding.apply {
            btnEmptyAction.text = text
            btnEmptyAction.visibility = VISIBLE
            btnEmptyAction.setOnClickListener { onClick() }
        }
    }

    /**
     * Hide action button
     */
    fun hideActionButton() {
        binding.btnEmptyAction.visibility = GONE
    }
}
