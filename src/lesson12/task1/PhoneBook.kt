@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

import java.lang.IllegalArgumentException
import java.lang.NullPointerException


class PhoneBook {

    private var book = mutableMapOf<String, MutableSet<String>>()


    fun addHuman(name: String): Boolean = if (name !in book) {
        book[name] = mutableSetOf()
        true
    } else false


    fun removeHuman(name: String): Boolean = if (name in book) {
        book.remove(name)
        true
    } else false

    fun containsPhone(phone: String): Pair<Boolean, String> {
        if (bookSearch(phone)) {
            for ((name, numbers) in book) {
                if (phone in numbers) return true to name
            }
        }
        return false to ""
    }

    fun containsMan(name: String): Boolean = name in book.keys

    fun addPhone(name: String, phone: String): Boolean {
        if (bookSearch(phone)) return false
        return if (name in book) {
            val s = book[name]
            s!!.add(phone)
            true
        } else false

    }

    private fun bookSearch(a: String): Boolean = a in book.values.flatten()

    fun removePhone(name: String, phone: String): Boolean {
        if (!bookSearch(phone)) return false
        return if (phone in book[name]!!) {
            val s = book[name]
            s!!.remove(phone)
            book[name] = s
            true
        } else false

    }


    fun phones(name: String): Set<String> = if (name in book) book[name]!!.toSet() else emptySet()

    fun humanByPhone(phone: String): String? {
        for ((a, b) in book) {
            if (phone in b) return a
        }
        return null
    }

    override fun equals(other: Any?): Boolean = other is PhoneBook && this.book == other.book
    override fun hashCode(): Int = book.hashCode()
}