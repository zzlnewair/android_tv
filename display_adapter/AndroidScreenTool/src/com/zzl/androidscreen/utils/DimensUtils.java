package com.zzl.androidscreen.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class DimensUtils {

	// 800*480
	private static int value_stand = 480;

	public static void outContent(File desFile, String name,int intSrcSize, float scale) throws IOException {

		BufferedWriter bw = null;
		FileOutputStream fos;

		fos = new FileOutputStream(desFile);
		bw = new BufferedWriter(new OutputStreamWriter(fos));

		String hear = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		bw.write(hear + "\n");
		String tag = "<resources>";
		bw.write(tag + "\n");

		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
		for (int i = 1; i < intSrcSize+1; i++) {
			float pxValue = scale * i;

			double d = 3.14159;
			String strPXValue = df.format(pxValue);

			String contentLine = "<dimen name=\"" +name +"_"+ i + "\">"
					+ strPXValue + "px</dimen>" + "\n";
			bw.write(contentLine);
		}
		String end = "</resources>" + "\n";
		bw.write(end);

		bw.close();
		fos.close();
	}

}
