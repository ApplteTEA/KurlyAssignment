package com.kurly.assignment.datastore

import android.content.Context
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object WishlistStorage {

    suspend fun saveWishlist(context: Context, wishlist: Set<Int>) {
        val stringSet = wishlist.map { it.toString() }.toSet()
        DataStoreManager.getInstance().put(context, Pref.WISHLIST, stringSet)
    }

    suspend fun loadWishlist(context: Context): Set<Int> {
        return DataStoreManager
            .getInstance()
            .get(context, Pref.WISHLIST, emptySet())
            .map { set -> set.mapNotNull { it.toIntOrNull() }.toSet() }
            .first()
    }

    suspend fun clearWishlist(context: Context) {
        DataStoreManager.getInstance().remove(context, Pref.WISHLIST)
    }
}
