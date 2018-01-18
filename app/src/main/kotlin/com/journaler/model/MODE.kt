package com.journaler.model


enum class MODE(val mode: Int) {
    CREATE(0),
    EDIT(1),
    VIEW(2);

    companion object {
        val EXTRAS_KEY = "MODE" // defined extra keys definition

        fun getByValue(value: Int): MODE { // method created to give us enum based on its value
            values().forEach {
                item ->
                if (item.mode == value) {
                    return item
                }
            }
            return VIEW
        }
    }

}

/*
================================== COMMENTS =============================================

- Start by defining an 'enum class' that represents an operation that we will perform in an opened activity.
- When we open it we can view, create, or update Note or Todo



*/