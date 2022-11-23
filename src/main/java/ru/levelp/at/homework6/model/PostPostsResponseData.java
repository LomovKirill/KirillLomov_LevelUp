package ru.levelp.at.homework6.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.levelp.at.homework6.model.GetPostsResponseData.MetaData;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class PostPostsResponseData {

    private MetaData meta;
    private DataDO data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    @Builder
    public static class DataDO {

        private int id;
        @JsonProperty("user_id")
        private int userId;
        private String title;
        private String body;
        private String message;
    }
}
