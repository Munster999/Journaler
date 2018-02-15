package com.journaler.database

import kotlin.reflect.KClass


interface Crud<T> {

    companion object {
        val BROADCAST_ACTION = "com.journaler.broadcast.crud"
        val BROADCAST_EXTRAS_KEY_CRUD_OPERATION_RESULT = "crud_result"
    }

    fun insert(what: T): Boolean

    fun insert(what: Collection<T>): Boolean

    fun update(what: T): Boolean

    fun update(what: Collection<T>): Boolean

    fun delete(what: T) : Boolean

    fun delete(what: Collection<T>) : Boolean

    fun select(args: Pair<String, String>, clazz: KClass<DbModel>): List<T>

    fun select(args: Collection<Pair<String, String>>, clazz: KClass<DbModel>): List<T>

}
/*
- For executing CRUD operations there are TWO method versions.
    1 - Accepts a ' single item '
    2 - Accepts ' collections of instances '

*/
