package ru.levelp.at.homework6;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.levelp.at.homework6.GetUsersResponseData.DataData;
import ru.levelp.at.homework6.GetUsersResponseData.LinksData;
import ru.levelp.at.homework6.GetUsersResponseData.MetaData;
import ru.levelp.at.homework6.GetUsersResponseData.PaginationData;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class GetPostsResponseData {

    private MetaData meta;
    private List<DataData> data;

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
    public static class PaginationData {

        private int total;
        private int pages;
        private int page;
        private int limit;
        private LinksData links;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    @Builder
    public static class LinksData {

        private String previous;
        private String current;
        private String next;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    @Builder
    public static class DataData {

        private int id;
        @JsonProperty("user_id")
        private int userId;
        private String title;
        private String body;
    }
}
