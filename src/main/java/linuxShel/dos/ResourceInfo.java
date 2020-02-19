package linuxShel.dos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResourceInfo {
    //io占用
    private float ResourceUsage;

    //采集时间(时间戳)
    private Long gatherTime;

}