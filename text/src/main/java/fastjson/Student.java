package fastjson;

import lombok.Builder;
import lombok.Data;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-23 15:56
 **/
@Data
@Builder
public class Student {
    String name;
    Integer id;
}
