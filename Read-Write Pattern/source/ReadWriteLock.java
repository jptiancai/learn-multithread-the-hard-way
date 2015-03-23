package com.jptiancai.read_write;

public class ReadWriteLock {

	//(A)... ʵ�����ڶ�ȡ���߳�����
	private int readingReaders=0;
	//(B)... ���ڵȴ�д����߳�����
	private int waitingWriters=0;
	//(A)... ʵ������д����߳�����
	private int writingWriters=0;
	//д�����ȵĻ�,ֵΪtrue
	private boolean preferWriter=true;
	
	public synchronized void readLock() throws InterruptedException {
		//û���߳�����д��
		while (writingWriters>0||(preferWriter&&waitingWriters>0)) {
			wait();
		}
		//(A) ʵ�����ڶ�ȡ���߳�������1
		readingReaders++;
	}

	public synchronized void readUnlock() {
		//(A) ʵ�����ڶ�ȡ���߳�������1
		readingReaders--;
		preferWriter=true;
		notifyAll();
		
	}

	public synchronized void writeLock() throws InterruptedException {
		//(B) ���ڵȴ�д����߳�������1
		waitingWriters++;
		try {
			//û���߳�����ִ�ж�ȡ��д��Ĳ���
			while (readingReaders>0||writingWriters>0) {
				wait();
			}
		} finally {
			//(B) ���ڵȴ�д����߳�������1
			waitingWriters--;
		}
		//(C) ʵ������д����߳�������1
		writingWriters++;
		
	}

	public synchronized void writeUnlock() {
		//(C) ʵ������д����߳�������1
		writingWriters--;
		preferWriter=false;
		notifyAll();
	}

}
