package com.capiter.android.posts_list.ui.model

import com.capiter.android.core.mappers.Mapper
import com.capiter.android.core.network.responses.DataResponse

class PostItemMapper:Mapper< DataResponse,List<PostItem>,> {
    override suspend fun map(from: DataResponse): List<PostItem> =
       from.data.posts.map {
          PostItem(
              title = it.subPost.title,
              isVideo = it.subPost.is_video,
              imageUrl = it.subPost.imageUrl,
              id = it.subPost.id,
              gilded = it.subPost.gilded

          )
       }


}