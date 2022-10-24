package ru.levelp.at.homework6.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class PostAndPutCommentsRequestData {

    @JsonProperty("post_id")
    private int postId;
    private String name;
    private String email;
    private String body;
}
