package jibun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Conn;

public class JibunAddress {
	
	public static void main(String[] args) {
		String keyStr = "dong_code\r\n" + "sido\r\n" + "gugun\r\n" + "dong_name\r\n" + "lee_name\r\n" + 
				"is_mnt\r\n" + "jibun\r\n" + "sub_jibun\r\n" + "road_code\r\n" + "is_base\r\n" + 
				"build_num\r\n" + "sub_build_num\r\n" + "jibun_num" ;
		String keys[] = keyStr.split("\r\n");
	
		try {
			System.out.println("프로그램 시작");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\java_study\\address\\jibun_jeju.txt")), "MS949"));
			List<Map<String,String>> list = new ArrayList<>();
			String str;
			while ((str = br.readLine()) != null) {
				String[] values = str.split("\\|");
				Map<String,String> map = new HashMap<>();
				for (int j=0;j<keys.length;j++) {
					map.put(keys[j], values[j]);
				}
				list.add(map);
			}
			long sTime = System.currentTimeMillis();
			String sql = "insert into jibun_address (";
			String value = " values(";
			for(String key:keys) {
				sql += key + ",";
				value += "?,";
			}
			sql = sql.substring(0,sql.length()-1) + ")\r\n";
			value = value.substring(0,value.length()-1) + ")";
			sql += value;
			Connection con = Conn.open();
			PreparedStatement ps = con.prepareStatement(sql);
			for(Map<String,String> row:list) {
				for(int i=0;i<keys.length;i++) {
					ps.setString((i+1), row.get(keys[i]));
				}
				ps.executeUpdate();
			}
			con.commit();
			long eTime = System.currentTimeMillis();
			System.out.println("끝!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

