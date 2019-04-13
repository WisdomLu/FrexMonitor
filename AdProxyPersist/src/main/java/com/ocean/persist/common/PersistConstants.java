package com.ocean.persist.common;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月16日 
      @version 1.0 
 */
public interface PersistConstants {
    String CACHE_POSITION_PREFIX="ocean::cache::position::";
    String CACHE_POSITION_FLAG_PREFIX="ocean::cache::flag::position::";
    String CACHE_POSITION_TARGET_PREFIX="ocean::cache::position::target::";
	//redis config
	String  CACHE_EXPIRE="cache.expire";
	
	//lbs
	String  CACHE_LBS_IP="ocean::cache::lbs::ip::";
	String  CACHE_LBS_CITY="ocean::cache::lbs::city::";
	
	//log open switch
	String OMIT_CONTENT="content  omit";
    String LOG_IS_ALL_WRITE="log_is_all_write";
    
	String  HTTP_TIME_OUT="http.time.out";
    
    //test
    String TEST_ADSPACE_ID="test.adSpace.id";
}
