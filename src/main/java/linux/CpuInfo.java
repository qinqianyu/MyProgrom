package linux;

import lombok.Data;

@Data
public class CpuInfo {
    private Long idleCpuTime;
    private Long totalCpuTime;

    public CpuInfo(Long idleCpuTime, Long totalCpuTime) {
        this.idleCpuTime = idleCpuTime;
        this.totalCpuTime = totalCpuTime;
    }
}
