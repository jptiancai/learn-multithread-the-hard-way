package com.jptiancai.read_write;

public class Data {

	private final char[] buffer;
	private final ReadWriteLock lock=new ReadWriteLock();
	
	public Data(int size) {
		this.buffer=new char[size];
		for (int i = 0; i < buffer.length; i++) {
			buffer[i]='*';
		}
	}
	
	public char[] read() throws InterruptedException{
		lock.readLock();
		try {
			return doRead();
		} finally{
			lock.readUnlock();
		}
	}
	
	public void write(char c) throws InterruptedException{
		lock.writeLock();
		try {
			doWrite(c);
		} finally {
			lock.writeUnlock();
		}
	}

	private char[] doRead() {
		char[] newbuf=new char[buffer.length];
		//下面做的事情和arraycopy是一样的,这里是为了方法与doWrite作比较
		//System.arraycopy(buffer, 0, newbuf, 0, buffer.length);
		for (int i = 0; i < buffer.length; i++) {
			newbuf[i]=buffer[i];
		}
		slowly();
		return newbuf;
	}
	
	private void doWrite(char c) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i]=c;
			slowly();
		}
	}


	private void slowly() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
		}
	}

}
