# learn-multithread-the-hard-way


<div id="toc">
### 目录

* [线程模型](#thread-parttern)
  * [读写锁,Read-Write Lock Pattern](#read-write-lock)
  





<div id="read-write-lock">
### 读写锁,Read-Write Lock Pattern [↑](#toc)

- 模型概述

该模型把读取和写入分开来处理。
在读取数据之前，必须获取用来读取的锁；而要写入数据的时候，必须获取用来写入的锁。
一般说来，进行共享互斥会使程序性能变差，但将写入的共享互斥与读取的共享互斥分开来思考，就可以提升程序的性能。


- 模型结构

冲突关系     |读取     | 写入
-------- |-------- | ---
读取|不会冲突 | “读取”和“写入”冲突（read-write conflict）
写入|“读取”和“写入”冲突（read-write conflict）    | “写入”和“写入”冲突（write-write conflict）


- 范例程序

- 相关模型

 - Immutable Pattern
 - Single Threaded Execution Pattern
 - Guarded Suspension Pattern
 - Before/After Pattern
 - Strategized Locking Pattern

- 使用场景

 - 同时读取不会冲突的特性，提高程序的性能
 - 适合读取操作繁重时
 - 适合读取比写入次数频繁时