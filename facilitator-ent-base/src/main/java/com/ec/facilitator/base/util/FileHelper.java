package com.ec.facilitator.base.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 从Disk中读取或者写入文本
 * @author 张荣英
 * @date 2017年4月7日 下午10:37:40
 */
public class FileHelper {

	/**
	 * 写文本到Disk中
	 * 
	 * @param path
	 * @param text
	 * @throws Exception
	 */
	public static void writeAllText(String path, String text) throws Exception {
		File file = FileHelper.createNewFile(path);
		BufferedWriter bufferWriter = null;
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file);
			bufferWriter = new BufferedWriter(fileWriter);
			bufferWriter.write(text);

		} finally {
			if (bufferWriter != null) {
				bufferWriter.close();
			}
			if (fileWriter != null) {
				fileWriter.close();
			}
		}
	}

	/**
	 * 检查文件路径是否存在，如果不存在则创建
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static File createNewFile(String path) throws Exception {
		if (path.lastIndexOf("/") > -1) {
			File dir = new File(path.substring(0, path.lastIndexOf("/")));
			if (dir.exists() == false) {
				dir.mkdirs();
			}
		}
		File file = new File(path);
		if (file.exists() == false) {
			file.createNewFile();
		}
		return file;
	}

	/**
	 * 从disk读取指定路径的文本
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String readAllText(String path) throws Exception {
		File file = new File(path);
		if (file.exists()) {
			BufferedReader bufferReader = null;
			FileReader fileReader = null;
			try {
				fileReader = new FileReader(file);
				bufferReader = new BufferedReader(fileReader);

				StringBuilder sb = new StringBuilder();
				String line = bufferReader.readLine();
				while (line != null) {
					sb.append(line);
					line = bufferReader.readLine();
					if (line != null) {
						sb.append(System.lineSeparator());
					}
				}
				return sb.toString();

			} finally {
				if (fileReader != null) {
					fileReader.close();
				}
				if (bufferReader != null) {
					bufferReader.close();
				}
			}
		} else {
			return null;
		}
	}
}
