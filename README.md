# gc-log-profiler
A HotSpot GC Log profiler.

## 使用条件
* 仅支持HotSpot VM
* 仅支持JDK1.7, JDK1.8
* GC日志命令行参数：-XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC
* GC日志命令行参数：-XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -XX:+UseSerialGC
* GC日志命令行参数：-XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -XX:+UseParallelOldGC
