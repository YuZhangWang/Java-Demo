package com.wbhz.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class BaseDao {

	private static String DRIVER;
	private static String URL;
	private static String USER_NAME;
	private static String PASSWORD;
	static{
		//读取配置文件给4个属性复制
		InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");//获得配置文件流
		Properties p = new Properties();
		try {
			p.load(is);//加载配置文件进行解析
			DRIVER = p.getProperty("db.mysql.driver");
			USER_NAME = p.getProperty("db.mysql.username");
			PASSWORD = p.getProperty("db.mysql.password");
			URL = p.getProperty("db.mysql.url");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//获得连接
	public static Connection getConnection(){
		//（1）加载驱动
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//（2）获得连接
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	//关闭连接
	public static void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// 因为增删改的语句一致，抽象出相同的方法
	//传入sql语句与参数（动态参数，如何确定数据类型?）
	//因为数据类型不确定（int，小数，日期，字符串，使用的类型是Object）
	public static int execute(String sql,Object ...objects ){
		int count = 0;//执行sql语句后影响的行数
		//获得Connection
		Connection conn = getConnection();
		PreparedStatement pstmt =null;
		//获得数据库操作对象
		try {
			pstmt = conn.prepareStatement(sql);
			//判断是否有参数
			if(objects==null || objects.length==0){
				//没有参数，不需要做参数的添加
			}else{
				for (int i = 0; i < objects.length; i++) {
					pstmt.setObject((i+1), objects[i]);
				}
			}
			//执行sql语句（增删改）
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll(conn,pstmt,null);//关闭连接
		}
		return count;
	}
	//返回一个集合
	/**
	 * 
	 * @param sql 要执行的sql语句
	 * @param clazz 要反射的类对象
	 * @param objects where条件对应的参数值的集合
	 * @return 返回对应类型的查询后的集合List
	 */
	public static <T> List<T> getList(String sql,Class<T> clazz, Object ...objects ){
		List<T> list = new ArrayList<T>();
		//获得Connection
		Connection conn = getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs = null;//结果集
		try {
			pstmt = conn.prepareStatement(sql);
			//判断是否有参数
			if(objects==null || objects.length==0){
				//没有参数，不需要做参数的添加
			}else{
				for (int i = 0; i < objects.length; i++) {
					pstmt.setObject((i+1), objects[i]);
				}
			}
			//执行sql语句（查询）
			rs = pstmt.executeQuery();
			//数据封装
			ResultSetMetaData rsmd = rs.getMetaData();//获得列的相关属性
			int count = rsmd.getColumnCount();//获得有多少列
			String[] fieldNames = new String[count];
			//获得列名转化
			for (int i = 0;i<count;i++) {
				String colName = rsmd.getColumnName(i+1);
				String[] colNameTemp = colName.split("_");
				//字符串拼接
				StringBuffer sb= new StringBuffer(colNameTemp[0]);
				for (int j = 1; j < colNameTemp.length; j++) {
					sb.append(colNameTemp[j].substring(0,1).toUpperCase());//第一个字母变大写
					sb.append(colNameTemp[j].substring(1));//其他字母固定不变
				}
				fieldNames[i] = sb.toString();
			}
			//(5)读取结果集，由于结果集的函数不知道，只能使用while循环读取,
			//结果集的读取，默认是只进只读的，不能回头读取
			//rs.next() 可以读取到下一行
			while(rs.next()){
				T obj = clazz.newInstance();//表面是 T，实际上是传入的类型
				// 获得属性，获得set方法进行赋值
				for(int i=0;i<fieldNames.length;i++){
					//对每一个属性，通过属性名，获得属性的类型，并且获得对应set方法
					String methodName = "set"+fieldNames[i].substring(0,1).toUpperCase()+fieldNames[i].substring(1);//set方法名
					//System.out.println(methodName);
					//获得属性的类型
					//clazz.getDeclaredField(fieldNames[i]);//通过名字获得属性
					Class fieldType = clazz.getDeclaredField(fieldNames[i]).getType();//获得属性的类型
					//有了方法名，有了数据类型，就可以获得对应的方法
					Method method = clazz.getDeclaredMethod(methodName, fieldType);//成功获得了方法
					//调用方法给属性赋值，通过类型判断，调用ResultSet的哪一个方法
					String fieldTypeName = fieldType.getName();//
					if(fieldTypeName.toLowerCase().contains("int")||fieldTypeName.toLowerCase().contains("integer")){
						method.invoke(obj, rs.getInt(i+1));
					}else if(fieldTypeName.toLowerCase().contains("string")){
						method.invoke(obj, rs.getString(i+1));
					}else if(fieldTypeName.toLowerCase().contains("double")){
						method.invoke(obj, rs.getDouble(i+1));
					}else if(fieldTypeName.toLowerCase().contains("float")){
						method.invoke(obj, rs.getFloat(i+1));
					}else if(fieldTypeName.toLowerCase().contains("short")){
						method.invoke(obj, rs.getShort(i+1));
					}else if(fieldTypeName.toLowerCase().contains("byte")){
						method.invoke(obj, rs.getByte(i+1));
					}else if(fieldTypeName.toLowerCase().contains("boolean")||fieldTypeName.toLowerCase().contains("bool")){
						method.invoke(obj, rs.getBoolean(i+1));
					}else if(fieldTypeName.toLowerCase().contains("char")||fieldTypeName.toLowerCase().contains("character")){
						//字符
						method.invoke(obj, rs.getCharacterStream(i+1));
					}else if(fieldTypeName.toLowerCase().contains("date")){
						//日期  rs.getDate(i+1) 返回的java.sql.Date,不是常用的java.util.Date,需要转换
						method.invoke(obj, new Date(rs.getTimestamp(i+1).getTime()));
					}
				}
				list.add(obj);			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll(conn,pstmt,rs);//关闭连接
		}
		return list;
	}
	
}
