# gc-log-profiler
A HotSpot GC Log profiler.

## 界面截图(完善中...)
![界面](http://bill.nexusy.com/nba/gc-log-web.png)

## 使用条件
* 仅支持HotSpot VM
* 仅支持JDK1.7, JDK1.8
* GC日志命令行参数：-XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC
* GC日志命令行参数：-XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -XX:+UseSerialGC
* GC日志命令行参数：-XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -XX:+UseParallelOldGC

## 支持的GC组合
|   Young Generation   |   Old Generation   |
|:--------------------:|:------------------:|
|          G1          |        G1          |
|        ParNew        |        CMS         |
|   Parallel Scavenge  |    Parallel Old    |
|   Parallel Scavenge  |     Serial Old     |
|        DefNew        |     Serial Old     |

## 本项目不支持以下GC组合日志的分析
* ~~-XX:+UseParNewGC = ParNew + Serial old，该组合已不建议使用，未来版本可能被删除~~
* ~~-XX:+UseConcMarkSweepGC -XX:-UseParNewGC = DefNew + CMS，该组合已不建议使用，未来版本可能被删除~~
