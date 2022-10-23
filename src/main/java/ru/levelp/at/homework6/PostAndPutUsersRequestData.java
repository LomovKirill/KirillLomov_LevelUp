package ru.levelp.at.homework6;

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
public class PostAndPutUsersRequestData {

    private String name;
    private String email;
    private String gender;
    private String status;
}
