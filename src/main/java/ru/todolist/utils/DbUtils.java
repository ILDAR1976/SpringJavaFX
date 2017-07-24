package ru.todolist.utils;

import org.apache.derby.drda.NetworkServerControl;
import org.apache.log4j.Logger;
import java.net.InetAddress;

public class DbUtils {
	private static Logger LOG = Logger.getLogger(DbUtils.class);
	private static NetworkServerControl server;

	public static void startDB() throws Exception {
		LOG.info("Start DB");
		System.out.println("Start DB");
		server = new NetworkServerControl(InetAddress.getByName("localhost"), 1527);
		server.start(null);
	}

	public static void stopDB() throws Exception {
		LOG.info("Stop DB");
		System.out.println("Stop DB");
		server.shutdown();
	}
}
