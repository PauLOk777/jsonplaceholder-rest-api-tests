package com.typicode.jsonplaceholder;

import com.typicode.jsonplaceholder.models.Post;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostsEndPoint {

    private static final String ID = "id";
    private static final String POSTS = "/posts";
    private static final String POST_BY_ID = POSTS + "/{" + ID + "}";

    public static Response getPostById(long id) {
        return given()
                .pathParam(ID, id)
                .when()
                .get(POST_BY_ID)
                .then()
                .extract()
                .response();
    }

    public static Response createPost(Post post) {
        return given()
                .body(post)
                .when()
                .post(POSTS)
                .then()
                .extract()
                .response();
    }

    public static Response modifyPost(Post post) {
        return given()
                .pathParam(ID, post.getId())
                .body(post)
                .when()
                .put(POST_BY_ID)
                .then()
                .extract()
                .response();
    }

    public static Response deletePost(long id) {
        return given()
                .pathParam(ID, id)
                .when()
                .delete(POST_BY_ID)
                .then()
                .extract()
                .response();
    }
}
