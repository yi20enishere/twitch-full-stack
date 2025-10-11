package com.laioffer.twitch.external.model;


import java.util.List;


public record ClipResponse(
        List<Clip> data
) {
}


// 用spring boot，java代码去发请求，而不是postman发请求，怎么做呢？
// 用HTTP client
