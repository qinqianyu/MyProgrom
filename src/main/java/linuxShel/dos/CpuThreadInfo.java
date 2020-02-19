package linuxShel.dos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CpuThreadInfo {
    //空闲时间
    private Long idleCpuTime;
    //总时间
    private Long totalCpuTime;
}
