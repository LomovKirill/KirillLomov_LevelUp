package ru.levelp.at.homework6.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.levelp.at.homework6.model.GetUsersResponseData.MetaData;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class PostUsersResponseData {

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
        private String name;
        private String email;
        private String gender;
        private String status;
        private String message;
    }
}
