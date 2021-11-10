package com.capiter.android.all_posts_list.ui.model

import com.capiter.android.core.mappers.Mapper
import com.capiter.android.core.network.responses.BaseResponse
import com.capiter.android.core.network.responses.DataResponse

class PostItemMapper:Mapper< DataResponse,List<PostItem>,> {
    override suspend fun map(from: DataResponse): List<PostItem> =
       from.data.posts.map {
          PostItem(
              title = it.title,
              isVideo = it.is_video,
              thumbnailUrl = it.thumbnail,
              id = it.id

          )
       }


}