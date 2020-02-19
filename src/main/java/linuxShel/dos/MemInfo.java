package linuxShel.dos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MemInfo {
    //总空间
    private float MemTotal;
    //空闲空间
    private float MemFree;
    //采集时间(时间戳)
    private Long gatherTime;

    /**
     *
     * @return 使用率(百分之)
     */
    public float getMemUsage() {
        return (1 - MemFree / MemTotal)*100;
    }
}