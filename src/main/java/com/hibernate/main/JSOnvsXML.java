package com.hibernate.main;

import java.io.ByteArrayOutputStream;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

public class JSOnvsXML {
	private static final Random random = new Random();

	private static final char[] letters = new char[26];

	static {
		for (int i = 0; i < 26; ++i) {
			letters[i] = (char) ('a' + i);
		}
	}

	private static int getId() {
		return random.nextInt(99999);
	}

	private static String getName(int length) {
		char[] chars = new char[length];

		for (int i = 0; i < length; ++i) {
			chars[i] = letters[random.nextInt(letters.length)];
		}

		return new String(chars);
	}

	private static User[] getUsers(int count) {
		User[] users = new User[count];

		for (int i = 0; i < count; ++i) {
			users[i] = new User(getId(), getName(6));
		}

		return users;
	}

	private static final String xmlTemplate = "<user><id>%d</id><name>%s</name></user>";
	private static final String jsonTemplate = "{id:%d,name:\"%s\"}";

	private static String getXML(User[] users) {
		StringBuilder builder = new StringBuilder();
		builder.append("<?xml version=\"1.0\" ?>");
		builder.append("<users>");

		for (User user : users) {
			builder.append(String.format(xmlTemplate, user.getId(),
					user.getName()));
		}

		builder.append("</users>");
		return builder.toString();
	}

	private static String getJSON(User[] users) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");

		for (User user : users) {
			builder.append(String.format(jsonTemplate + ",", user.getId(),
					user.getName()));
		}
		builder.deleteCharAt(builder.length() - 1); // delete the last ","

		builder.append("]");
		return builder.toString();
	}

	private static byte[] zip(String string) throws Exception {
		ByteArrayOutputStream memory = new ByteArrayOutputStream();

		GZIPOutputStream zip = new GZIPOutputStream(memory);
		zip.write(string.getBytes());
		zip.close();

		return memory.toByteArray();
	}

	public static void main(String[] args) throws Exception {
		User[] users = getUsers(2000000);
		String xml = getXML(users);
		String json = getJSON(users);
		System.out.println(String.format("xml(%d)/json(%d): %f", xml.length(),
				json.length(), 1.0 * xml.length() / json.length()));
		long t1 = System.currentTimeMillis();
		byte[] xmlZip = zip(xml);
		long t2 = System.currentTimeMillis();
		byte[] jsonZip = zip(json);
		long t3 = System.currentTimeMillis();
		System.out.println(String.format(
				"xmlDuration(%d)/jsonDuration(%d): %f", t2 - t1, t3 - t2, 1.0
						* (t2 - t1) / (t3 - t2)));
		System.out.println(String.format("xmlZip(%d)/jsonZip(%d): %f",
				xmlZip.length, jsonZip.length, 1.0 * xmlZip.length
						/ jsonZip.length));

	}

}

class User {
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}
}