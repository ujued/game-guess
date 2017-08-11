package com.uoope.game.guess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Data {
	private Grade grade;
	private long date;
	private File dataFile;

	public Data() {
		this.dataFile = new File(this.getClass().getResource("/").getPath() + "data.uf");
	}

	public Data(File dataFile) {
		this.dataFile = dataFile;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public void persist() {
		FileOutputStream fout = null;
		OutputStreamWriter writer = null;
		if (date == 0)
			date = Long.parseLong(getProperty(1));
		try {
			fout = new FileOutputStream(dataFile);
			writer = new OutputStreamWriter(fout);
			writer.write(grade.toString() + "." + date);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
			} finally {
				writer = null;
			}
		}
	}

	public String getProperty(int index) {
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(dataFile);
			byte[] data = new byte[1024];
			int l = fin.read(data);
			String dataStr = new String(data, 0, l, "utf8");
			return dataStr.split("\\.")[index];
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
				} finally {
					fin = null;
				}
			}
		}
		return null;
	}
}
