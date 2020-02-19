package linuxShel.dos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CpuInfo {
    //空闲时间
    private Long idleCpuTime;
    //总时间
    private Long totalCpuTime;
    //采集时间(时间戳)
    private Long gatherTime;
}
