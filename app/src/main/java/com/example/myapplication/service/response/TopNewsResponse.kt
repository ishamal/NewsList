package com.example.myapplication.service.response

class TopNewsResponse {
    var status : String? = null
    var totalResults : Int = 0
    var articles : List<Article>? = null
}

data class Article(
    var author : String?,
    var title : String?,
    var description : String?,
    var url : String?,
    var urlToImage : String?,
    var publishedAt : String?,
    var content : String?,
    var source : Source?
)

data class Source(
    var id : String?,
    var name : String?
)