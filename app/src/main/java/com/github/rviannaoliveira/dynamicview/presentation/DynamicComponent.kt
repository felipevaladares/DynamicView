package com.github.rviannaoliveira.dynamicview.presentation

import com.github.rviannaoliveira.dynamic.adapter.renderes.DynamicViewListener
import com.github.rviannaoliveira.dynamic.adapter.renderes.ViewRenderer
import com.github.rviannaoliveira.dynamic.domain.model.SimpleProperties
import com.github.rviannaoliveira.dynamicview.components.ButtonComponentRender
import com.github.rviannaoliveira.dynamicview.components.TextViewComponentRender


enum class DynamicComponent(val key: String) {
    TEXT_VIEW_COMPONENT("${DYNAMIC}TextView"),
    BUTTON_COMPONENT("${DYNAMIC}Button"),
}

fun List<SimpleProperties>.toRenders(listener : DynamicViewListener): List<ViewRenderer<*>> {
    return this
        .distinctBy { it.key }
        .mapNotNull { simpleProperties ->
            val key = simpleProperties.key
            when (DynamicComponent.values().find { it.key == key }) {
                DynamicComponent.TEXT_VIEW_COMPONENT -> TextViewComponentRender(listener)
                DynamicComponent.BUTTON_COMPONENT -> ButtonComponentRender(listener)
                else -> null
            }
        }
}

internal const val DYNAMIC = "Dynamic"
