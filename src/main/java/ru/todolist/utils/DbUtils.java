package ru.todolist.utils;

import org.apache.derby.drda.NetworkServerControl;

import java.net.InetAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DbUtils {
	private static Logger LOG = LogManager.getLogger(DbUtils.class);
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
