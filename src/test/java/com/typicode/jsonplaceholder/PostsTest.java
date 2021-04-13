package com.typicode.jsonplaceholder;

import com.typicode.jsonplaceholder.models.Post;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static com.typicode.jsonplaceholder.PostsEndPoint.getPostById;
import static com.typicode.jsonplaceholder.PostsEndPoint.createPost;
import static com.typicode.jsonplaceholder.PostsEndPoint.modifyPost;
import static com.typicode.jsonplaceholder.PostsEndPoint.deletePost;
import static org.hamcrest.Matchers.equalTo;

public class PostsTest {

    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";
    private static final String ID = "id";

    private static final int POST_ID = 1;
    private static final int NEXT_ID = 101;
    private static final int USER_ID = 1;
    private static final String MY_TITLE = "My title";
    private static final String MY_BODY = "My body";
    private static final Post EXISTING_POST = new Post(USER_ID, POST_ID, MY_TITLE, MY_BODY);
    private static final Post NEW_POST = new Post(USER_ID, MY_TITLE, MY_BODY);

    private static final int STATUS_CODE_OK = 200;
    private static final int STATUS_CODE_CREATED = 201;
    private static final int STATUS_NOT_FOUND = 404;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    public void shouldReturnOkStatusAndPostWithSameId() {
        getPostById(POST_ID)
                .then()
                .body(ID, equalTo(POST_ID))
                .and()
                .statusCode(STATUS_CODE_OK);
    }

    @Test
    public void shouldReturnNotFoundStatusWhenEnterNonExistingId() {
        getPostById(NEXT_ID)
                .then()
                .statusCode(STATUS_NOT_FOUND);
    }

    @Test
    public void shouldReturnCreatedStatusAndCreateNewPostWithSameId() {
        createPost(NEW_POST)
                .then()
                .body(ID, equalTo(NEXT_ID))
                .and()
                .statusCode(STATUS_CODE_CREATED);
    }

    @Test
    public void shouldReturnOkStatusWhenModifyingExistingProduct() {
        modifyPost(EXISTING_POST)
                .then()
                .body(ID, equalTo(POST_ID))
                .and()
                .statusCode(STATUS_CODE_OK);
    }

    @Test
    public void shouldReturnOkStatusAndDeleteExistingPost() {
        deletePost(POST_ID)
                .then()
                .statusCode(STATUS_CODE_OK);
    }
}
