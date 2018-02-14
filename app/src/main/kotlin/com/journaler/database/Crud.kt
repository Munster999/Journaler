package com.journaler.database

interface Crud<T> where T : DbModel {

    companion object {
        val BROADCAST_ACTION = "com.journaler.broadcast.crud"
        val BROADCAST_EXTRAS_KEY_CRUD_OPERATION_RESULT = "crud_result"
    }

    fun insert(what: T): Long // Returns ID of INSERTED item

    fun insert(what: Collection<T>): List<Long> // Returns list of INSERTED IDs

    fun update(what: T): Int // Returns the number of UPDATED items

    fun update(what: Collection<T>): Int // Returns list of UPDATED items

    fun delete(what: T): Int // Returns the number of DELETED items

    fun delete(what: Collection<T>): Int // Returns the number of DELETED items

    fun select(args: Pair<String, String>): List<T> // Returns the list of items

    fun select(args: Collection<Pair<String, String>>): List<T> // Returns the list of items

    fun selectAll(): List<T> // Returns the list of items

}
/*
- For executing CRUD operations there are TWO method versions.
    1 - Accepts a ' single item '
    2 - Accepts ' collections of instances '

*/
