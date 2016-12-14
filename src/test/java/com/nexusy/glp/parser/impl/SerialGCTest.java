package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.SerialGCData;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * @author lanhuidong
 * @since 2016-12-08
 */
public class SerialGCTest extends GCLogLineParserTest {

    private SerialGCParser parser = new SerialGCParser();

    @Test
    public void testSerialMinorGCLogJDK8() {
        String line = "2016-12-07T14:50:54.719-0800: 0.092: [GC (Allocation Failure) 0.092: [DefNew: 5656K->0K(6144K),"
                + " 0.0078575 secs] 9752K->9644K(19840K), 0.0079072 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] ";
        SerialGCData data = parser.parse(line);
        assertNotNull(data);
        LocalDateTime dateTime = LocalDateTime.parse("2016-12-07T14:50:54.719-0800", formatter);
        assertEquals(dateTime, data.getDateTime());
        assertEquals(92L, data.getUptime());
        assertEquals("GC", data.getFlag());
        assertEquals("Allocation Failure", data.getCause());
        assertEquals(5656L * 1024, data.getYoungGenUsageBfGC());
        assertEquals(0L, data.getYoungGenUsageAfGC());
        assertEquals(6144L * 1024, data.getYongGenSize());
        assertEquals(0.0078575, data.getMinorGCDuration(), 0.00000001);
        assertEquals(9752L * 1024, data.getHeapUsageBfGC());
        assertEquals(9644L * 1024, data.getHeadUsageAfGC());
        assertEquals(19840L * 1024, data.getHeapSize());
        assertEquals(0.0079072, data.getTotalDuration(), 0.00000001);
        assertEquals(0.01, data.getUserTime(), 0.001);
        assertEquals(0.00, data.getSysTime(), 0.001);
        assertEquals(0.01, data.getRealTime(), 0.001);
    }

    @Test
    public void testSerialMajorGCLogJDK8() {
        String line = "2016-12-07T14:50:54.729-0800: 0.102: [GC (Allocation Failure) 0.102: "
                + "[DefNew: 5219K->5219K(6144K), 0.0000334 secs]0.102: [Tenured: 9644K->4523K(13696K), 0.0032665 secs] "
                + "14864K->4523K(19840K), [Metaspace: 2731K->2731K(1056768K)], 0.0033682 secs] "
                + "[Times: user=0.00 sys=0.00, real=0.01 secs] ";
        SerialGCData data = parser.parse(line);
        assertNotNull(data);
        LocalDateTime dateTime = LocalDateTime.parse("2016-12-07T14:50:54.729-0800", formatter);
        assertEquals(dateTime, data.getDateTime());
        assertEquals(102L, data.getUptime());
        assertEquals("GC", data.getFlag());
        assertEquals("Allocation Failure", data.getCause());
        assertEquals(5219L * 1024, data.getYoungGenUsageBfGC());
        assertEquals(5219L * 1024, data.getYoungGenUsageAfGC());
        assertEquals(6144L * 1024, data.getYongGenSize());
        assertEquals(0.0000334, data.getMinorGCDuration(), 0.00000001);
        assertEquals(9644L * 1024, data.getOldGenUsageBfGC());
        assertEquals(4523L * 1024, data.getOldGenUsageAfGC());
        assertEquals(13696L * 1024, data.getOldSize());
        assertEquals(0.0032665, data.getFullGCDuration(), 0.00000001);
        assertEquals(14864L * 1024, data.getHeapUsageBfGC());
        assertEquals(4523L * 1024, data.getHeadUsageAfGC());
        assertEquals(19840L * 1024, data.getHeapSize());
        assertEquals(2731L * 1024, data.getMetaspaceUsageBfGC());
        assertEquals(2731L * 1024, data.getMetaspaceUsageAfGC());
        assertEquals(1056768L * 1024, data.getMetaspaceSize());
        assertEquals(0.0033682, data.getTotalDuration(), 0.00000001);
        assertEquals(0.00, data.getUserTime(), 0.001);
        assertEquals(0.00, data.getSysTime(), 0.001);
        assertEquals(0.01, data.getRealTime(), 0.001);
    }

    @Test
    public void testSerialFullGCLogJDK8() {
        String line = "2016-12-08T08:40:17.994+0800: 1.030: [Full GC (Allocation Failure) "
                + "2016-12-08T08:40:17.994+0800: 1.030: [Tenured: 3707K->3695K(13696K), 0.0047268 secs] "
                + "3707K->3695K(19840K), [Metaspace: 2581K->2581K(1056768K)], 0.0047817 secs] "
                + "[Times: user=0.00 sys=0.00, real=0.00 secs] ";
        SerialGCData data = parser.parse(line);
        assertNotNull(data);
        LocalDateTime dateTime = LocalDateTime.parse("2016-12-08T08:40:17.994+0800", formatter);
        assertEquals(dateTime, data.getDateTime());
        assertEquals(1030, data.getUptime());
        assertEquals("Full GC", data.getFlag());
        assertEquals("Allocation Failure", data.getCause());
        assertEquals(3707L * 1024, data.getOldGenUsageBfGC());
        assertEquals(3695L * 1024, data.getOldGenUsageAfGC());
        assertEquals(13696L * 1024, data.getOldSize());
        assertEquals(0.0047268, data.getFullGCDuration(), 0.00000001);
        assertEquals(3707L * 1024, data.getHeapUsageBfGC());
        assertEquals(3695L * 1024, data.getHeadUsageAfGC());
        assertEquals(19840L * 1024, data.getHeapSize());
        assertEquals(2581L * 1024, data.getMetaspaceUsageBfGC());
        assertEquals(2581L * 1024, data.getMetaspaceUsageAfGC());
        assertEquals(1056768L * 1024, data.getMetaspaceSize());
        assertEquals(0.0047817, data.getTotalDuration(), 0.00000001);
        assertEquals(0.00, data.getUserTime(), 0.001);
        assertEquals(0.00, data.getSysTime(), 0.001);
        assertEquals(0.00, data.getRealTime(), 0.001);
    }

    @Test
    public void testSerialMinorGCLogJDK7() {
        String line = "2016-12-08T09:50:53.521+0800: 3.596: [GC2016-12-08T09:50:53.521+0800: 3.596: ["
                + "DefNew: 5122K->0K(6144K), 0.0031337 secs] 9675K->9673K(19840K), 0.0033012 secs] "
                + "[Times: user=0.00 sys=0.00, real=0.00 secs] ";
        SerialGCData data = parser.parse(line);
        assertNotNull(data);
        LocalDateTime dateTime = LocalDateTime.parse("2016-12-08T09:50:53.521+0800", formatter);
        assertEquals(dateTime, data.getDateTime());
        assertEquals(3596L, data.getUptime());
        assertEquals("GC", data.getFlag());
        assertNull(data.getCause());
        assertEquals(5122L * 1024, data.getYoungGenUsageBfGC());
        assertEquals(0L, data.getYoungGenUsageAfGC());
        assertEquals(6144L * 1024, data.getYongGenSize());
        assertEquals(0.0031337, data.getMinorGCDuration(), 0.00000001);
        assertEquals(9675L * 1024, data.getHeapUsageBfGC());
        assertEquals(9673L * 1024, data.getHeadUsageAfGC());
        assertEquals(19840L * 1024, data.getHeapSize());
        assertEquals(0.0033012, data.getTotalDuration(), 0.00000001);
        assertEquals(0.00, data.getUserTime(), 0.001);
        assertEquals(0.00, data.getSysTime(), 0.001);
        assertEquals(0.00, data.getRealTime(), 0.001);
    }

    @Test
    public void testSerialMajorGCLogJDK7() {
        String line = "2016-12-08T09:43:00.637+0800: 0.157: [GC2016-12-08T09:43:00.637+0800: 0.157: "
                + "[DefNew: 1147K->1147K(6144K), 0.0000193 secs]2016-12-08T09:43:00.637+0800: 0.157: "
                + "[Tenured: 12745K->3529K(13696K), 0.0076335 secs] 13893K->3529K(19840K), "
                + "[Perm : 2524K->2524K(21248K)], 0.0077254 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] ";
        SerialGCData data = parser.parse(line);
        assertNotNull(data);
        LocalDateTime dateTime = LocalDateTime.parse("2016-12-08T09:43:00.637+0800", formatter);
        assertEquals(dateTime, data.getDateTime());
        assertEquals(157L, data.getUptime());
        assertEquals("GC", data.getFlag());
        assertNull(data.getCause());
        assertEquals(1147L * 1024, data.getYoungGenUsageBfGC());
        assertEquals(1147L * 1024, data.getYoungGenUsageAfGC());
        assertEquals(6144L * 1024, data.getYongGenSize());
        assertEquals(0.0000193, data.getMinorGCDuration(), 0.00000001);
        assertEquals(12745L * 1024, data.getOldGenUsageBfGC());
        assertEquals(3529L * 1024, data.getOldGenUsageAfGC());
        assertEquals(13696L * 1024, data.getOldSize());
        assertEquals(0.0076335, data.getFullGCDuration(), 0.00000001);
        assertEquals(13893L * 1024, data.getHeapUsageBfGC());
        assertEquals(3529L * 1024, data.getHeadUsageAfGC());
        assertEquals(19840L * 1024, data.getHeapSize());
        assertEquals(2524L * 1024, data.getMetaspaceUsageBfGC());
        assertEquals(2524L * 1024, data.getMetaspaceUsageAfGC());
        assertEquals(21248L * 1024, data.getMetaspaceSize());
        assertEquals(0.0077254, data.getTotalDuration(), 0.00000001);
        assertEquals(0.02, data.getUserTime(), 0.001);
        assertEquals(0.00, data.getSysTime(), 0.001);
        assertEquals(0.02, data.getRealTime(), 0.001);
    }

    @Test
    public void testSerialFullGCLogJDK7() {
        String line = "2016-12-08T09:43:00.653+0800: 0.165: [Full GC2016-12-08T09:43:00.653+0800: 0.165: "
                + "[Tenured: 3529K->3518K(13696K), 0.0095194 secs] 3529K->3518K(19840K), "
                + "[Perm : 2524K->2523K(21248K)], 0.0095751 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] ";
        SerialGCData data = parser.parse(line);
        assertNotNull(data);
        LocalDateTime dateTime = LocalDateTime.parse("2016-12-08T09:43:00.653+0800", formatter);
        assertEquals(dateTime, data.getDateTime());
        assertEquals(165, data.getUptime());
        assertEquals("Full GC", data.getFlag());
        assertNull(data.getCause());
        assertEquals(3529L * 1024, data.getOldGenUsageBfGC());
        assertEquals(3518L * 1024, data.getOldGenUsageAfGC());
        assertEquals(13696L * 1024, data.getOldSize());
        assertEquals(0.0095194, data.getFullGCDuration(), 0.00000001);
        assertEquals(3529L * 1024, data.getHeapUsageBfGC());
        assertEquals(3518L * 1024, data.getHeadUsageAfGC());
        assertEquals(19840L * 1024, data.getHeapSize());
        assertEquals(2524L * 1024, data.getMetaspaceUsageBfGC());
        assertEquals(2523L * 1024, data.getMetaspaceUsageAfGC());
        assertEquals(21248L * 1024, data.getMetaspaceSize());
        assertEquals(0.0095751, data.getTotalDuration(), 0.00000001);
        assertEquals(0.02, data.getUserTime(), 0.001);
        assertEquals(0.01, data.getSysTime(), 0.001);
        assertEquals(0.01, data.getRealTime(), 0.001);
    }
}
