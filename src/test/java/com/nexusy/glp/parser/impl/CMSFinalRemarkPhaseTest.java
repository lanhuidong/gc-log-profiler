package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.CMSFinalRemarkData;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * @author lanhuidong
 * @since 2016-12-13
 */
public class CMSFinalRemarkPhaseTest extends GCLogLineParserTest {

    private CMSFinalRemarkParser parser = new CMSFinalRemarkParser();

    @Test
    public void testFinalRemarkJDK8() {
        String line = "2016-12-13T15:43:51.700+0800: 0.621: [GC (CMS Final Remark) "
                + "[YG occupancy: 1024 K (9216 K)]2016-12-13T15:43:51.700+0800: 0.622: "
                + "[Rescan (parallel) , 0.0002099 secs]2016-12-13T15:43:51.701+0800: 0.622: "
                + "[weak refs processing, 0.0000288 secs]2016-12-13T15:43:51.701+0800: 0.622: "
                + "[class unloading, 0.0003655 secs]2016-12-13T15:43:51.701+0800: 0.622: "
                + "[scrub symbol table, 0.0008772 secs]2016-12-13T15:43:51.702+0800: 0.623: "
                + "[scrub string table, 0.0003322 secs][1 CMS-remark: 9862K(20480K)] 10886K(29696K), 0.0019949 secs] "
                + "[Times: user=0.01 sys=0.01, real=0.01 secs] ";
        CMSFinalRemarkData data = parser.parse(line);
        LocalDateTime dateTime = LocalDateTime.parse("2016-12-13T15:43:51.700+0800", formatter);
        assertEquals(dateTime, data.getDateTime());
        assertEquals(621, data.getUptime());
        assertEquals("CMS Final Remark", data.getPhase().toString());
        assertEquals(1024L, data.getYoungGenUsage());
        assertEquals(9216L, data.getYoungGenSize());
        assertEquals(0.0002099, data.getRescanDuration(), 0.00000001);
        assertEquals(0.0000288, data.getWeakRefsProcessing(), 0.00000001);
        assertEquals(0.0003655, data.getClassUnloading(), 0.00000001);
        assertEquals(0.0008772, data.getScrubSymbolTable(), 0.00000001);
        assertEquals(0.0003322, data.getScrubStringTable(), 0.00000001);
        assertEquals(9862L, data.getOldGenUsage());
        assertEquals(20480L, data.getOldGenSize());
        assertEquals(10886L, data.getHeapUsage());
        assertEquals(29696L, data.getHeapSize());
        assertEquals(0.0019949, data.getDuration(), 0.00000001);
        assertEquals(0.01, data.getUserTime(), 0.001);
        assertEquals(0.01, data.getSysTime(), 0.001);
        assertEquals(0.01, data.getRealTime(), 0.001);
    }
}
