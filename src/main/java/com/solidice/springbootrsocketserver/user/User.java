package com.solidice.springbootrsocketserver.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String email;
    private String username;
    private Integer id;
}
