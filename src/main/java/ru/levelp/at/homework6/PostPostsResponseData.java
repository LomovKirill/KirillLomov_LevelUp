package ru.levelp.at.homework6;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.levelp.at.homework6.GetPostsResponseData.DataData;
import ru.levelp.at.homework6.GetPostsResponseData.MetaData;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class PostPostsResponseData {

    private MetaData meta;
    private DataData data;
}
