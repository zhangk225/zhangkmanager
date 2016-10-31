package com.zhangkssh.baseframe.hibernate;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 *******************************************************
 * <p>Title: DBManager.java</p>
 * <p>Description: 数据库连接基类</p>
 * <p>Copyright: Copyright (c) 2013-12-1</p>
 * <p>Company www.vsit.com</p>
 * @author zhangkaifeng(cxianf@gmail.com)
 * @version 1.0
 *******************************************************
 * 
 */
@Repository
public class DBManager {
	
	private static final Log log = LogFactory.getLog(DBManager.class);
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	public  DataSource dataSource = null;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/** 
     * 获取系统的数据源 
     * 
     * @return DataSource 
     */ 
//    public static DataSource getDataSource() { 
//        DataSource dataSource = null; 
//        try { 
//        	 ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        	 dataSource = (DataSource)ctx.getBean("dataSource");
//        } catch (Exception e) { 
//            log.error("获取数据源出错，请检查Spring数据源配置！"); 
//        } 
//        return dataSource; 
//    } 
	/**
	 * 取得数据库连接
	 * @return
	 */
	public  synchronized Connection getConnection(){
		try { 
          //  conn = getDataSource().getConnection(); //加载上下文的获取方法
            conn = dataSource.getConnection();//注入的获取方法
        } catch (SQLException e) { 
            log.error("通过数据源获取数据库连接发生异常！"); 
            e.printStackTrace(); 
        } 
		return conn;
	}
	
	/**
	 * 连接对象的事务提交
	 * @param conn
	 */
	public  synchronized void commit(){
		try{
			if(conn != null)
				conn.commit();
		}catch(SQLException ex){
			log.error(ex.getMessage());
		}
	}
	
	/**
	 * 连接对象的事务回滚
	 * @param conn
	 */
	public  synchronized void rollback(){
		try{
			if(conn != null)
				conn.rollback();
		}catch(SQLException ex){
			log.error(ex.getMessage());
		}
	}
	
    /**
     * 连接对象的自动提交设置
     * 
     * @param blnFlag
     */
    public  synchronized void setAutoCommit(boolean blnFlag) {
        if (conn != null) {
            try {
                conn.setAutoCommit(blnFlag);
            } catch (SQLException ex) {
            	log.error(ex.getMessage());
            }
        }
    }
	
	/**
	 * 连接对象的释放
	 * @param conn
	 */
	public  synchronized void close(){
		try{
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(conn != null)
				conn.close();
		}catch(SQLException ex){
			log.error(ex.getMessage());
		}finally{
			rs = null;
			pstmt = null;
			conn = null;
		}
	}
	
	/**
	 * 获取查询结果的记录集
	 * @param sql
	 * @return
	 */
    public  ResultSet executeSQLQuery(String sql){
        try{
        	if(conn != null)
        		pstmt = conn.prepareStatement(sql);
        	else
        		pstmt = getConnection().prepareStatement(sql);
	        if (pstmt == null) {
	            throw new SQLException("Haven't Get The PreparedStatment.");
	        }
	        rs = pstmt.executeQuery();
        }catch(SQLException ex){
        	log.error(ex.getMessage());
        }
        return rs;
    }
    
    /**
     * 获取查询结果的记录集(带查询参数)
     * @param sql
     * @param param
     * @return
     */
    public  ResultSet executeSQLQuery(String sql,Object[] params){
        try{
        	if(conn != null)
        		pstmt = conn.prepareStatement(sql);
        	else
        		pstmt = getConnection().prepareStatement(sql);
	        if (pstmt == null) {
	            throw new SQLException("Haven't Get The PreparedStatment.");
	        }
	        if (params != null) {
	            for (int i = 0; i < params.length; i++) {
	                pstmt.setObject(i + 1, params[i]);
	            }
	        }
	        rs = pstmt.executeQuery();
        }catch(SQLException ex){
        	log.error(ex.getMessage());
        }   	
    	return rs;
    }
    
    /**
     * 数据表更新操作
     * @param sql
     * @param params
     * @return
     */
    public  int executeSQLUpdate(String sql,Object[] params){
    	List list = new ArrayList();
    	list.add(params);
    	return executeSQLUpdate(sql,list,false);
    }
    
    /**
     * 数据表更新操作(默认不执行批处理)
     * @param sql
     * @param list
     * @param isBatch
     * @return
     */
    public  int executeSQLUpdate(String sql,List list,boolean isBatch){
    	boolean dbError=false;
        int retValue = 0;
        try{
        	if(conn != null)
        		pstmt = conn.prepareStatement(sql);
        	else
        		pstmt = getConnection().prepareStatement(sql);
            Iterator it = list.iterator();
            int count = 1;
            while (it.hasNext()) {
            	System.out.println("次数：" + (count++));
                Object[] params = (Object[]) it.next();
                if (params != null) {
                    for (int i = 0; i < params.length; i++) {
                        pstmt.setObject(i + 1, params[i]);
                    }
                }
                if (isBatch) {
                    pstmt.addBatch();
                    if (count%3000==0) {
	                   	pstmt.executeBatch();
	                    pstmt.clearBatch();
                    }
                } else {
                    retValue += pstmt.executeUpdate();
                }
            }
            if (isBatch) {
                int[] ret = pstmt.executeBatch();
                pstmt.clearBatch();
                for (int i = 0; i < ret.length; i++) {
                    retValue += ret[i];
                }
            }
        }catch(SQLException ex) {
        	log.error(ex.getMessage());
        	dbError=true;
        }finally {
        	if (dbError)
        		rollback();
        	else
        		commit();
        	close();
        }
        return retValue;
    }
    public  synchronized long getSequence(String seq){
    	String seqSql="select nextval ('"+seq+"')";
    	long newSeq=0;
    	try{
        	if(conn != null)
        		pstmt = conn.prepareStatement(seqSql);
        	else
        		pstmt = getConnection().prepareStatement(seqSql);
	        if (pstmt == null) {
	            throw new SQLException("Haven't Get The PreparedStatment.");
	        }
	        
	        rs = pstmt.executeQuery();
	        while(rs.next()){
	        	newSeq=rs.getLong(1);
	        }
        }catch(SQLException ex){
        	log.error(ex.getMessage());
        }   	
    	return newSeq;
	}
}
