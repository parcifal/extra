package eu.parcifal.extra;

import eu.parcifal.extra.net.http.HttpListener;
import eu.parcifal.extra.print.Console;

public class Test {

	public static void main(String[] args) {
		Console.debug(true);
		new Thread(new HttpListener()).start();
	}

}
