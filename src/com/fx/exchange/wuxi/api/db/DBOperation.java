package com.fx.exchange.wuxi.api.db;

import java.io.Reader;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
public class DBOperation {
    private static final SqlMapClient sqlMap;
    //在静态区块中初试化返回
    static {
        try {
            //声明配置文件的名称（映射文件被定义在其中）
            String resource = "SqlMapConfig.xml";
            //利用工具类Resources来读取到配置文件
            Reader reader = Resources.getResourceAsReader(resource);
            //创建SqlMapClient接口的变量实例
            sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(
                    "Error initializing MyAppSqlConfig class. Cause: " + e);
        }
    }
 
    public static SqlMapClient getSqlMapInstance() {
        //提供静态方法返回静态区块中得到的SqlMapClient
        return sqlMap;
    }
}
//public class DBOperation {
//	//private String resource = "/SqlMapConfig.xml";//
//    private SqlMapClient sqlMap = null;
//    private static DBOperation dbOperation;
//    public DBOperation() {
//        Reader reader = null;
//        String resource = getResource();
//        try {
//            reader = Resources.getResourceAsReader(resource);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            
//        }
//        sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
//    }
//    public static DBOperation getDBInstence(){
//    	if(dbOperation == null){
//    		 dbOperation = new DBOperation(); 
//    	}
//    	
//    	return dbOperation;
//    	
//    }
//    private String getResource(){
//    	String resource = "/SqlMapConfig.xml";
//    	
//    	return resource;
//    	
//    }
//    public Object selectObj(String sqlID,Object obj) throws SQLException{
//        Object result = null;
//        try {
//            sqlMap.startTransaction();
//            
//            result = sqlMap.queryForObject(sqlID,obj);
//            sqlMap.commitTransaction();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            throw ex;
//        }
//        finally{
//        	sqlMap.endTransaction();
//        }
//        return result;
//    }
//    public void startTransaction(){
//    	try {
//			sqlMap.startTransaction();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
//    public void endTransaction(){
//    	try {
//			sqlMap.endTransaction();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
//    
////    public int insertObj(String sqlID,Object obj) throws SQLException{
////    	
////    	try {
////			sqlMap.startTransaction();			
////			sqlMap.insert(sqlID,obj);
////	        sqlMap.commitTransaction();
////		} catch (SQLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////			throw e;
////		}  
////    	finally{
////        	sqlMap.endTransaction();
////        }
////		return 1;
////    	
////    }
//    public Object insertObjReturn(String sqlID,Object obj) throws SQLException{
//    	Object result = null;
//    	try {
//			sqlMap.startTransaction();	
//			result = sqlMap.insert(sqlID, obj);
//			
//			//sqlMap.insert(sqlID,obj);
//	        sqlMap.commitTransaction();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw e;
//		}  
//    	finally{
//        	sqlMap.endTransaction();
//        }
//		return result;
//    	
//    }
//    public int updateObj(String sqlID,Object obj) throws SQLException{
//    	int result = 0;
//    	try {
//			sqlMap.startTransaction();			
//			result = sqlMap.update(sqlID, obj);
//	        sqlMap.commitTransaction();
//	        
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw e;
//		}  
//    	finally{
//        	sqlMap.endTransaction();
//        }
//		return result;
//    }
//    public List<?> selectObjList(String sqlID,Object obj) throws SQLException{
//    	List<?> result = null;
//    	try{
//    		sqlMap.startTransaction();
//    		
//            result = sqlMap.queryForList(sqlID, obj);
//            sqlMap.commitTransaction();
//    	} catch (SQLException ex){
//    		ex.printStackTrace();
//    		throw ex;
//    	}
//    	finally{
//        	sqlMap.endTransaction();
//        }
//    	
//		return result;
//    	
//    }
//}


