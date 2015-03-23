package com.jptiancai.read_write;

public class ReaderTHread extends Thread {

	private final Data data;

	public ReaderTHread(Data data) {
		this.data = data;
	}

	@Override
	public void run() {
		try {
			while (true) {
				char[] readbuf = data.read();
				System.out.println(Thread.currentThread().getName() + " reads "
						+ String.valueOf(readbuf));
			}
		} catch (InterruptedException e) {
		}
	}

}
