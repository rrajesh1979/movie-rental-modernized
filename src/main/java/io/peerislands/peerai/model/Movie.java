package io.peerislands.peerai.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
@Data
@Document(collection = "movies")
public class Movie {
    @Id
    private String id;

    @Field
    private String title;

    @Field
    private String description;

    @Field
    private int releaseYear;

    @Field
    private String rentalRate;

    @Field
    private String replacementCost;

    @Field
    private String rating;

    @Field
    private String specialFeatures;

    @Field
    private String language;

    @Field
    private List<Category> categories;

    @Field
    private List<Actor> actors;
}
