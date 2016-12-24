package com.nexusy.glp.parser.impl;

import com.nexusy.glp.data.basic.CommandLineData;
import com.nexusy.glp.data.basic.GCName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author lanhuidong
 * @since 2016-12-24
 */
public class CommandLineParserTest {

    private CommandLineParser parser = new CommandLineParser();

    @Test
    public void testParNewCMSJDK8() {
        String line = "CommandLine flags: -XX:CICompilerCount=2 -XX:GCLogFileSize=1048576 -XX:InitialHeapSize=62914560 "
                + "-XX:MaxGCPauseMillis=500 -XX:MaxHeapSize=1073741824 -XX:MaxNewSize=174456832 "
                + "-XX:MaxTenuringThreshold=6 -XX:MinHeapDeltaBytes=196608 -XX:NewSize=20971520 "
                + "-XX:NumberOfGCLogFiles=2 -XX:OldPLABSize=16 -XX:OldSize=41943040 -XX:+PrintGC -XX:+PrintGCDetails "
                + "-XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops "
                + "-XX:+UseConcMarkSweepGC -XX:+UseGCLogFileRotation -XX:+UseParNewGC ";
        CommandLineData data = parser.parse(line);
        assertEquals(data.getYoungGCName(), GCName.ParNew);
        assertEquals(data.getOldGCName(), GCName.CMS);
    }

    @Test
    public void testDefNewSerialOldJDK8() {
        String line = "CommandLine flags: -XX:GCLogFileSize=8388608 -XX:InitialHeapSize=30143744 "
                + "-XX:MaxHeapSize=482299904 -XX:NumberOfGCLogFiles=2 -XX:+PrintGC -XX:+PrintGCDateStamps "
                + "-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops "
                + "-XX:+UseGCLogFileRotation -XX:+UseSerialGC ";
        CommandLineData data = parser.parse(line);
        assertEquals(data.getYoungGCName(), GCName.DefNew);
        assertEquals(data.getOldGCName(), GCName.SerialOld);
    }

    @Test
    public void testParallelScavengeSerialOldJDK8() {
        String line = "CommandLine flags: -XX:GCLogFileSize=8388608 -XX:InitialHeapSize=134217728 "
                + "-XX:MaxHeapSize=2147483648 -XX:NumberOfGCLogFiles=2 -XX:+PrintGC -XX:+PrintGCDateStamps "
                + "-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops "
                + "-XX:+UseGCLogFileRotation -XX:+UseParallelGC ";
        CommandLineData data = parser.parse(line);
        assertEquals(data.getYoungGCName(), GCName.ParallelScavenge);
        assertEquals(data.getOldGCName(), GCName.SerialOld);
    }

    @Test
    public void testParallelScavengeParallelOldJDK8() {
        String line = "CommandLine flags: -XX:GCLogFileSize=8388608 -XX:InitialHeapSize=134217728 "
                + "-XX:MaxHeapSize=2147483648 -XX:NumberOfGCLogFiles=2 -XX:+PrintGC -XX:+PrintGCDateStamps "
                + "-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops "
                + "-XX:+UseGCLogFileRotation -XX:+UseParallelOldGC ";
        CommandLineData data = parser.parse(line);
        assertEquals(data.getYoungGCName(), GCName.ParallelScavenge);
        assertEquals(data.getOldGCName(), GCName.ParallelOld);
    }

    @Test
    public void testG1G1JDK8() {
        String line = "CommandLine flags: -XX:GCLogFileSize=8388608 -XX:InitialHeapSize=134217728 "
                + "-XX:MaxHeapSize=2147483648 -XX:NumberOfGCLogFiles=2 -XX:+PrintGC -XX:+PrintGCDateStamps "
                + "-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops "
                + "-XX:+UseG1GC -XX:+UseGCLogFileRotation ";
        CommandLineData data = parser.parse(line);
        assertEquals(data.getYoungGCName(), GCName.G1);
        assertEquals(data.getOldGCName(), GCName.G1);
    }
}
