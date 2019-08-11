package com.zhuguozhu.framework.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.Connection;
import com.zhuguozhu.framework.util.CollectionUtil;
import com.zhuguozhu.framework.util.PropsUtil;
import com.zhuguozhu.framework.util.StreamUtil;

/**
 * 数据库助手类
 * @author Guozhu Zhu
 * @date 2019/07/01
 * @version 1.0
 *
 */
public final class DatabaseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
	private static final String driver = ConfigHelper.getJdbcDriver();
	private static final String url = ConfigHelper.getJdbcUrl();
	private static final String username = ConfigHelper.getJdbcUsername();
	private static final String password = ConfigHelper.getJdbcPassword();
	
	// 定义一个用于放置数据库连接的局部线程变量（使每个线程都拥有自己的连接)
	private  static ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();
    // 获取连接
	public static Connection getConnection() {
		Connection conn = CONNECTION_HOLDER.get();
		    try {
			if (conn == null) {
				Class.forName(driver);
				conn = (Connection) DriverManager.getConnection(url, username, password);
			}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
			return conn;
	}
	
	/**
	 * 开启事务
	 */
	public static void beginTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				LOGGER.error("begin transaction failure!!!", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
		}
	}
	
	/**
	 * 提交事务
	 */
	public static void commitTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				LOGGER.error("commit transaction failure!!!", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}
	
	/**
	 * 回滚事务
	 */
	public static void rollbackTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e) {
				LOGGER.error("rollback transaction failure!!!");
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}

}
