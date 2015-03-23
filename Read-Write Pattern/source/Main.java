package com.jptiancai.read_write;

public class Main {
	public static void main(String[] args) {
		Data data=new Data(10);
		new ReaderTHread(data).start();
		new ReaderTHread(data).start();
		new ReaderTHread(data).start();
		new ReaderTHread(data).start();
		new ReaderTHread(data).start();
		new ReaderTHread(data).start();
		
		new WriteThread(data,"ABCDEFGHIJKLMNOPQRSTUVWXYZ").start();
		new WriteThread(data,"abcdefghijklmnopqrstuvwxyz").start();
	}

}
