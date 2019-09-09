package com.solidice.springbootrsocketserver.greeting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class GreetingsRequest {

    private String name;
}
