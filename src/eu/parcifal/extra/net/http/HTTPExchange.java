package eu.parcifal.extra.net.http;

import java.util.ArrayList;
import java.util.Collection;

public class HTTPExchange {

	private Collection<HTTPHeader> headers = new ArrayList<HTTPHeader>();

	public void header(String name, String value) {
		for (HTTPHeader header : this.headers) {
			if (header.name().equals(name)) {
				header.value(value);

				return;
			}
		}

		this.headers.add(new HTTPHeader(name, value));
	}

	public HTTPHeader header(String name) {
		for (HTTPHeader header : this.headers) {
			if (header.name().equals(name)) {
				return header;
			}
		}

		return null;
	}

	@Override
	public String toString() {
		String exchange = "";

		for (HTTPHeader header : this.headers) {
			exchange += header.toString() + "\n";
		}

		return exchange;
	}

}
