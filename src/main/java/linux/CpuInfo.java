package linux;

import lombok.Data;

@Data
public class CpuInfo {
    private Long idleCpuTime;
    private Long totalCpuTime;
    private Long gatherTime;

    /**
     *
     * @param idleCpuTime   空闲时间
     * @param totalCpuTime  总时间
     * @param gatherTime    采集时间(时间戳)
     */
    public CpuInfo(Long idleCpuTime, Long totalCpuTime, Long gatherTime) {
        this.idleCpuTime = idleCpuTime;
        this.totalCpuTime = totalCpuTime;
        this.gatherTime = gatherTime;
    }
}
