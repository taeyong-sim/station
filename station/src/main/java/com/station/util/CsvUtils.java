package com.station.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;


public class CsvUtils {

private static Log logger = LogFactory.getLog(CsvUtils.class);
	
	public static List<List<String>> readToList(String path) {
		List<List<String>> list = new ArrayList<List<String>>();
		File csv = new File(path);
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new BufferedReader(new InputStreamReader(new FileInputStream(csv), "euc-kr")));
			Charset.forName("UTF-8");
			String line = "";
			
			while((line=br.readLine()) != null) {
				String[] token = csvSplit(line);
				List<String> tempList = new ArrayList<String>(Arrays.asList(token));
				list.add(tempList);
			}
			
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} finally {
			try {
				if(br != null) {br.close();}
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return list;
	}
	
	//csv파일에 맞게 split 해주는 메서드
	public static String[] csvSplit(String str){
		String[] resultStr=null;
		String result="";
		String[] a=str.split(",");
		int cnt=0;
		String temp="";

		for(int i=0;i<a.length;i++){
			if(a[i].indexOf("\"")==0){
				if(a[i].lastIndexOf("\"")==a[i].length()-1){
					result+=a[i].replaceAll("\"","");
				}else{
					cnt++;
					temp+=a[i].replaceAll("\"","");
				}
			}else if(a[i].lastIndexOf("\"")==a[i].length()-1){
				if(cnt>0){
					result+=temp+","+a[i].replaceAll("\"","");
					cnt=0;
					temp="";
				}
			}else{
				if(cnt>0){
					cnt++;
					temp+=","+a[i].replaceAll("\"","");
				}else{
					result+=a[i];
				}
			}
			if(i!=a.length-1 && cnt==0) result+="|,|";
		}

		resultStr=result.split("\\|,\\|");
		String[] resultArr = resultStr;
		for (int i = 0; i < resultStr.length; i++) {
			resultArr[i] = resultStr[i].replace(",", "");
			
		}
		return resultArr;
	}
}