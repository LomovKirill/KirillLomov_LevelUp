package ru.levelp.at.homework6;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.levelp.at.homework6.GetUsersResponseData.MetaData;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class PostUsersResponseData {

    private MetaData meta;
    private DataData data;

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
    }
}
