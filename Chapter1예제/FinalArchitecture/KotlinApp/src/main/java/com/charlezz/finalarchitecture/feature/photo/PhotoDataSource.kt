package com.charlezz.finalarchitecture.feature.photo

import androidx.paging.PositionalDataSource
import android.database.Cursor
import android.provider.MediaStore

class PhotoDataSource(private val cursor: Cursor) : PositionalDataSource<Photo>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Photo>) {
        val totalCount = cursor.count
        val photoList = getPhotos(0, params.requestedLoadSize)

        callback.onResult(photoList, 0,totalCount)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Photo>) {
        val photoList = getPhotos(params.startPosition, params.startPosition+params.loadSize)
        callback.onResult(photoList)
    }

    private fun getPhotos(start:Int, end:Int):ArrayList<Photo>{
        val photoList = ArrayList<Photo>()

        for (index in start until end) {
            if (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME))
                val path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA))
                photoList.add(Photo(name, path))
            }else{
                break
            }
        }
        return photoList
    }
}