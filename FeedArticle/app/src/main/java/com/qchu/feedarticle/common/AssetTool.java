package com.qchu.feedarticle.common;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by quocdungchu on 21/08/15.
 */
public class AssetTool {
	public static String stringFromAssetFilePath (Context context, String assetFilePath) {
		StringBuilder buf=new StringBuilder();
		InputStream json = null;
		try {
			json = context.getAssets().open(assetFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String str;
		try {
			while ((str=in.readLine()) != null) {
				buf.append(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			if(in != null) in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(buf != null)
			return buf.toString();
		else
			return null;
	}
}
