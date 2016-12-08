package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.ParallelGCData;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lanhuidong
 * @since 2016-12-08
 */
public class ParallelGCTest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private ParallelGCParser parser = new ParallelGCParser();

    @Test
    public void testMinorGCLogJDK8() {
        String line = "2016-12-08T11:03:32.470+0800: 3.164: [GC (Allocation Failure) "
                + "[PSYoungGen: 3137K->1024K(5120K)] 7868K->7803K(18944K), 0.0020042 secs] "
                + "[Times: user=0.00 sys=0.00, real=0.00 secs] ";
        ParallelGCData data = parser.parse(line);
        Assert.assertNotNull(data);
        LocalDateTime dateTime = LocalDateTime.parse("2016-12-08T11:03:32.470+0800", formatter);
        Assert.assertEquals(dateTime, data.getDateTime());
        Assert.assertEquals(3164L, data.getUptime());
        Assert.assertEquals("GC", data.getFlag());
        Assert.assertEquals("Allocation Failure", data.getCause());
        Assert.assertEquals(3137L, data.getYoungGenUsageBfGC());
        Assert.assertEquals(1024L, data.getYoungGenUsageAfGC());
        Assert.assertEquals(5120L, data.getYongGenSize());
        Assert.assertEquals(7868L, data.getHeapUsageBfGC());
        Assert.assertEquals(7803L, data.getHeadUsageAfGC());
        Assert.assertEquals(18944L, data.getHeapSize());
        Assert.assertEquals(0.0020042, data.getTotalDuration(), 0.00000001);
        Assert.assertEquals(0.00, data.getUserTime(), 0.001);
        Assert.assertEquals(0.00, data.getSysTime(), 0.001);
        Assert.assertEquals(0.00, data.getRealTime(), 0.001);
    }

    @Test
    public void testFullGCLogJDK8() {
        String line = "2016-12-08T11:03:32.501+0800: 3.191: [Full GC (Ergonomics) "
                + "[PSYoungGen: 1024K->0K(5120K)] [ParOldGen: 12923K->5755K(13824K)] 13947K->5755K(18944K), "
                + "[Metaspace: 2583K->2583K(1056768K)], 0.0076736 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] ";
        ParallelGCData data = parser.parse(line);
        Assert.assertNotNull(data);
        LocalDateTime dateTime = LocalDateTime.parse("2016-12-08T11:03:32.501+0800", formatter);
        Assert.assertEquals(dateTime, data.getDateTime());
        Assert.assertEquals(3191L, data.getUptime());
        Assert.assertEquals("Full GC", data.getFlag());
        Assert.assertEquals("Ergonomics", data.getCause());
        Assert.assertEquals(1024L, data.getYoungGenUsageBfGC());
        Assert.assertEquals(0L, data.getYoungGenUsageAfGC());
        Assert.assertEquals(5120L, data.getYongGenSize());
        Assert.assertEquals(12923L, data.getOldGenUsageBfGC());
        Assert.assertEquals(5755L, data.getOldGenUsageAfGC());
        Assert.assertEquals(13824L, data.getOldSize());
        Assert.assertEquals(13947L, data.getHeapUsageBfGC());
        Assert.assertEquals(5755L, data.getHeadUsageAfGC());
        Assert.assertEquals(18944L, data.getHeapSize());
        Assert.assertEquals(0.0076736, data.getTotalDuration(), 0.00000001);
        Assert.assertEquals(0.00, data.getUserTime(), 0.001);
        Assert.assertEquals(0.00, data.getSysTime(), 0.001);
        Assert.assertEquals(0.00, data.getRealTime(), 0.001);
    }

    @Test
    public void testMinorGCLogJDK7() {
        String line = "2016-12-08T11:08:16.047+0800: 2.136: "
                + "[GC [PSYoungGen: 3074K->1024K(5120K)] 13826K->13824K(18944K), 0.0023574 secs] "
                + "[Times: user=0.00 sys=0.00, real=0.00 secs] ";
        ParallelGCData data = parser.parse(line);
        Assert.assertNotNull(data);
        LocalDateTime dateTime = LocalDateTime.parse("2016-12-08T11:08:16.047+0800", formatter);
        Assert.assertEquals(dateTime, data.getDateTime());
        Assert.assertEquals(2136L, data.getUptime());
        Assert.assertEquals("GC", data.getFlag());
        Assert.assertNull(data.getCause());
        Assert.assertEquals(3074L, data.getYoungGenUsageBfGC());
        Assert.assertEquals(1024L, data.getYoungGenUsageAfGC());
        Assert.assertEquals(5120L, data.getYongGenSize());
        Assert.assertEquals(13826L, data.getHeapUsageBfGC());
        Assert.assertEquals(13824L, data.getHeadUsageAfGC());
        Assert.assertEquals(18944L, data.getHeapSize());
        Assert.assertEquals(0.0023574, data.getTotalDuration(), 0.00000001);
        Assert.assertEquals(0.00, data.getUserTime(), 0.001);
        Assert.assertEquals(0.00, data.getSysTime(), 0.001);
        Assert.assertEquals(0.00, data.getRealTime(), 0.001);
    }

    @Test
    public void testFullGCLogJDK7() {
        String line = "2016-12-08T11:08:16.063+0800: 2.139: [Full GC [PSYoungGen: 1024K->0K(5120K)] "
                + "[ParOldGen: 12800K->6656K(13824K)] 13824K->6656K(18944K) "
                + "[PSPermGen: 2523K->2523K(21504K)], 0.0131346 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] ";
        ParallelGCData data = parser.parse(line);
        Assert.assertNotNull(data);
        LocalDateTime dateTime = LocalDateTime.parse("2016-12-08T11:08:16.063+0800", formatter);
        Assert.assertEquals(dateTime, data.getDateTime());
        Assert.assertEquals(2139L, data.getUptime());
        Assert.assertEquals("Full GC", data.getFlag());
        Assert.assertNull(data.getCause());
        Assert.assertEquals(1024L, data.getYoungGenUsageBfGC());
        Assert.assertEquals(0L, data.getYoungGenUsageAfGC());
        Assert.assertEquals(5120L, data.getYongGenSize());
        Assert.assertEquals(12800L, data.getOldGenUsageBfGC());
        Assert.assertEquals(6656L, data.getOldGenUsageAfGC());
        Assert.assertEquals(13824L, data.getOldSize());
        Assert.assertEquals(13824L, data.getHeapUsageBfGC());
        Assert.assertEquals(6656L, data.getHeadUsageAfGC());
        Assert.assertEquals(18944L, data.getHeapSize());
        Assert.assertEquals(2523L, data.getMetaspaceUsageBfGC());
        Assert.assertEquals(2523L, data.getMetaspaceUsageAfGC());
        Assert.assertEquals(21504L, data.getMetaspaceSize());
        Assert.assertEquals(0.0131346, data.getTotalDuration(), 0.00000001);
        Assert.assertEquals(0.00, data.getUserTime(), 0.001);
        Assert.assertEquals(0.00, data.getSysTime(), 0.001);
        Assert.assertEquals(0.00, data.getRealTime(), 0.001);
    }
}
