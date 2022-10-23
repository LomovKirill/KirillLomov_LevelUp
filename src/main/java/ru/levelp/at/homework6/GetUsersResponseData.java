package ru.levelp.at.homework6;

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
public class GetUsersResponseData {

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
        private String name;
        private String email;
        private String gender;
        private String status;
        private String field;
        private String message;
    }
}
