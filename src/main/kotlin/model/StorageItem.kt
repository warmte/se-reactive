package model

import org.bson.Document

interface StorageItem {
    fun toDocument(): Document
    fun toHTML(): String
}
