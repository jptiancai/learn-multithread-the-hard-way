package com.jptiancai.read_write;

public class ReadWriteLock {

	//(A)... 实际正在读取的线程数量
	private int readingReaders=0;
	//(B)... 正在等待写入的线程数量
	private int waitingWriters=0;
	//(A)... 实际正在写入的线程数量
	private int writingWriters=0;
	//写入优先的话,值为true
	private boolean preferWriter=true;
	
	public synchronized void readLock() throws InterruptedException {
		//没有线程正在写入
		while (writingWriters>0||(preferWriter&&waitingWriters>0)) {
			wait();
		}
		//(A) 实际正在读取的线程数量加1
		readingReaders++;
	}

	public synchronized void readUnlock() {
		//(A) 实际正在读取的线程数量减1
		readingReaders--;
		preferWriter=true;
		notifyAll();
		
	}

	public synchronized void writeLock() throws InterruptedException {
		//(B) 正在等待写入的线程数量加1
		waitingWriters++;
		try {
			//没有线程正在执行读取或写入的操作
			while (readingReaders>0||writingWriters>0) {
				wait();
			}
		} finally {
			//(B) 正在等待写入的线程数量减1
			waitingWriters--;
		}
		//(C) 实际正在写入的线程数量加1
		writingWriters++;
		
	}

	public synchronized void writeUnlock() {
		//(C) 实际正在写入的线程数量减1
		writingWriters--;
		preferWriter=false;
		notifyAll();
	}

}
