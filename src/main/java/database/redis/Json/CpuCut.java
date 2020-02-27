package database.redis.Json;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
@Data
public class CpuCut {
    String time;
    Integer value;
}
