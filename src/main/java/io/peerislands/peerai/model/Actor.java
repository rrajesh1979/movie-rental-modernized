package io.peerislands.peerai.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
@Data
class Actor {
    @Field
    private String firstName;

    @Field
    private String lastName;

    @Field
    private String lastUpdate;
}