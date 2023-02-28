package icu.easyj.spring.boot.sample.logging.elk;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketServer {

	public static void start() throws Throwable {
		try {
			ServerSocket serverSocket = new ServerSocket(8081);

			Socket socket;

			while (true) {
				socket = serverSocket.accept();

				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(isr);

				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}

				socket.shutdownInput();
				socket.close();
			}
		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}
	}

}
