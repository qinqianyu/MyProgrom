package qingdao.works.id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-27 11:07
 **/
public class TestId {
    public static Long originalStartId = 10000000L;

    public synchronized Long generateId() {
        LocalDate localDate = LocalDate.now();
        String yearMonth = localDate.toString().replaceAll("-", "");
        String id = yearMonth + incrementId();
        return Long.valueOf(id);
    }

    private long incrementId() {
      /*  if (original0) {
            LocalDateTime now = LocalDateTime.now();
            Long time = now.toEpochSecond(ZoneOffset.of("+8"));
            original = time% originalStartId;
        } else {
            original++;
        }*/
        return original.incrementAndGet();
    }

    private static AtomicLong original = new AtomicLong(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"))% originalStartId);
}
