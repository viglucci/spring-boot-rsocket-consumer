package com.solidice.springbootrsocketserver.user;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private RSocketRequester requester;

    @GetMapping("/users")
    public Publisher<UsersListResponse> getUsers() {
        UsersListRequest request = new UsersListRequest();
        return requester
            .route("getUsers")
            .data(request)
            .retrieveMono(UsersListResponse.class);
    }

    @GetMapping("/users/{id}")
    public Publisher<User> getUserById(@PathVariable Integer id) {
        UserRequest request = new UserRequest(id);
        return requester
            .route("getUserById")
            .data(request)
            .retrieveMono(User.class);
    }

    @GetMapping(value = "/users-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<User> usersStream() {
        UserStreamRequest request = new UserStreamRequest();
        return requester
            .route("streamRandomUser")
            .data(request)
            .retrieveFlux(User.class);
    }
}
