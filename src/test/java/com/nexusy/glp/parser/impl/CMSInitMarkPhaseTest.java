package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.CMSInitialMarkData;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lanhuidong
 * @since 2016-12-13
 */
public class CMSInitMarkPhaseTest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private CMSInitMarkParser parser = new CMSInitMarkParser();

    @Test
    public void testInitMarkJDK8() {
        String line = "2016-12-13T15:43:51.496+0800: 0.417: [GC (CMS Initial Mark) [1 CMS-initial-mark: 6790K(20480K)]"
                + " 7814K(29696K), 0.0000744 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] ";
        CMSInitialMarkData data = parser.parse(line);
        LocalDateTime dateTime = LocalDateTime.parse("2016-12-13T15:43:51.496+0800", formatter);
        Assert.assertEquals(dateTime, data.getDateTime());
        Assert.assertEquals(417, data.getUptime());
        Assert.assertEquals("CMS Initial Mark", data.getPhase());
        Assert.assertEquals(6790L, data.getOldGenUsage());
        Assert.assertEquals(20480L, data.getOldGenSize());
        Assert.assertEquals(7814L, data.getHeapUsage());
        Assert.assertEquals(29696L, data.getHeapSize());
        Assert.assertEquals(0.0000744, data.getDuration(), 0.00000001);
        Assert.assertEquals(0.01, data.getUserTime(), 0.001);
        Assert.assertEquals(0.01, data.getSysTime(), 0.001);
        Assert.assertEquals(0.01, data.getRealTime(), 0.001);
    }
}
