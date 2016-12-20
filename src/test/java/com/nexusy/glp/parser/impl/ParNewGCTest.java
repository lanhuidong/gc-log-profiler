package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.basic.ParNewData;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author lanhuidong
 * @since 2016-12-15
 */
public class ParNewGCTest extends GCLogLineParserTest {

    private ParNewParser parser = new ParNewParser();

    @Test
    public void testJDK8() {
        String log = "2016-12-07T13:59:16.080-0800: 0.118: [GC (Allocation Failure) 0.118: "
                + "[ParNew: 5001K->455K(6144K), 0.0008393 secs] 5001K->455K(19840K), 0.0009109 secs] "
                + "[Times: user=0.00 sys=0.00, real=0.00 secs] ";
        ParNewData data = parser.parse(log);
        LocalDateTime dateTime = LocalDateTime.parse("2016-12-07T13:59:16.080-0800", formatter);
        assertEquals(dateTime, data.getDateTime());
        assertEquals(118, data.getUptime());
        assertEquals("GC", data.getFlag());
        assertEquals("Allocation Failure", data.getCause());
        assertEquals(5001L * 1024, data.getYoungGenUsageBfGC());
        assertEquals(455L * 1024, data.getYoungGenUsageAfGC());
        assertEquals(6144L * 1024, data.getYongGenSize());
        assertEquals(0.0008393, data.getMinorGCDuration(), 0.00000001);
        assertEquals(5001L * 1024, data.getHeapUsageBfGC());
        assertEquals(455L * 1024, data.getHeadUsageAfGC());
        assertEquals(19840L * 1024, data.getHeapSize());
        assertEquals(0.0009109, data.getStwDuration(), 0.00000001);
        assertEquals(0.00, data.getUserTime(), 0.001);
        assertEquals(0.00, data.getSysTime(), 0.001);
        assertEquals(0.00, data.getRealTime(), 0.001);
    }

    @Test
    public void testJDK7() {
        String log = "2016-12-15T09:40:42.510+0800: 0.178: [GC2016-12-15T09:40:42.510+0800: 0.178: "
                + "[ParNew: 8004K->488K(9216K), 0.0104046 secs] 8004K->7658K(29696K), 0.0106560 secs] "
                + "[Times: user=0.00 sys=0.00, real=0.00 secs] ";
        ParNewData data = parser.parse(log);
        LocalDateTime dateTime = LocalDateTime.parse("2016-12-15T09:40:42.510+0800", formatter);
        assertEquals(dateTime, data.getDateTime());
        assertEquals(178, data.getUptime());
        assertEquals("GC", data.getFlag());
        assertNull(data.getCause());
        assertEquals(8004L * 1024, data.getYoungGenUsageBfGC());
        assertEquals(488L * 1024, data.getYoungGenUsageAfGC());
        assertEquals(9216L * 1024, data.getYongGenSize());
        assertEquals(0.0104046, data.getMinorGCDuration(), 0.00000001);
        assertEquals(8004L * 1024, data.getHeapUsageBfGC());
        assertEquals(7658L * 1024, data.getHeadUsageAfGC());
        assertEquals(29696L * 1024, data.getHeapSize());
        assertEquals(0.0106560, data.getStwDuration(), 0.00000001);
        assertEquals(0.00, data.getUserTime(), 0.001);
        assertEquals(0.00, data.getSysTime(), 0.001);
        assertEquals(0.00, data.getRealTime(), 0.001);
    }
}
