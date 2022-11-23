package ru.levelp.at.homework6.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
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
public class GetAllCommentsResponseData {

    private MetaData meta;
    private List<ArrayOfData> data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    @Builder
    public static class MetaData {

        private PaginationData pagination;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    @Builder
    public static class ArrayOfData {

        private int id;
        @JsonProperty("post_id")
        private int postId;
        private String name;
        private String email;
        private String body;
        private String field;
        private String message;
    }
}
