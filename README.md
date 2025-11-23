一、实验目的
（1）理解并掌握服务器一客户一线程通用技术框架。
（2）理解并掌握 SwingWorker<T,V>后台线程技术。
二．实验内容
（1）重温本章完成的 Echo 一客户一线程服务器设计。
（2）学习和理解 SwingWorker<T,V>这个类的用法。
三．实验方法与步骤
1）重温 Echo 一客户一线程服务器设计
修改客户机设计，在客户机界面上增加一个文本框，用于指定向服务器发送消息的条数，界面如下图所示。客户机可以向服务器批量发送消息，例如每次 30 000 条，消息之间间隔 10ms，启动 5 个、10 个、15 个客户机，测试服务器的性能表现。
2）用 SwingWorker<T,V>替代 Thread 类定义 ClientThread 类 SwingWorker<T,V>类是一个泛型类， javax.swing 包中定义，在实现了 Runnable、Future<T>、RunnableFuture<T>接口，因此 SwingWorker<T,V>是一个线程类，通过将用户界面线程与后台任务线程分离，可以有效提升界面线程的交互体验，后台线程 也可以更新界面线程。
计算机网络与编程实验指导书 第 7 页
SwingWorker<T,V>类适合需要较长时间的后台任务，例如较多的 I/O 数据交换等。
请查找资料， SwingWorker<T,V>类重新定义本章程序 2.3 和程序 2.4 中的 ClientThread。
提示如下：
//创建任务线程 worker，实现一客户一线程
SwingWorker<List<String>,String> worker=new ClientThread(toClientSocket,clientCounts);
worker.execute(); //启动任务线程
让 ClientThread 继承 SwingWorker<List<String>,String>，重写 SwingWorker 的doInBackground()方法。
四、边实验边思考
（1）为什么服务器端的“toClientSocket=listenSocket.accept();”语句需要放在一个无限循环里面？为什么这个无限循环需要单独定义为一个线程？
（2）查找资料，理解 SwingWorker<T,V>类的工作原理，理解类型 T、类型 V 的含义，掌握函数 doInBackground()、publish()、process()、done()、get()的用法。
五、实验总结
根据实验情况，撰写实验报告,简明扼要记录实验过程、实验结果，提出实验问题，做出实验分析。根据实验情况认真回答上述实验思考题。