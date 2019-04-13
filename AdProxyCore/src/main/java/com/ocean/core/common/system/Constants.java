package com.ocean.core.common.system;

public interface Constants {
	String ENCODE="UTF-8";
	
	String TOMCAT_HOME = "TOMCAT_HOME";
	String DYNIC_RESOURCE_FILENAME = "dynicResourceFileName";
	String SESSION_USER = "login_user";
	String SECURITY_CODE="security_code";
	
	/*thrift server config*/
	String RPC_PORT="thrift.server.port";
	String RPC_TIME_OUT="prpc.time.out";
	String QUEUE_BUFFER_SIZE_KEY = "queue.buffer.size";// 队列缓冲尺寸(用于线程等待)
	String IO_SELECTOR_COUNT_KEY = "io.selector";
	
	String RPC_REPORT_URL="rpc.report.url";
	String MAX_WORKER_KEY="max.worker.key";
	String MIN_WORKER_KEY="min.worker.key";
	
	
	/*-----BEGIN eth config-------*/
	String GETH_GAS_PRICE="GAS_PRICE";
	/*set gas limit ,This is the total amount of gas you are happy to spend on the transaction execution. 
	#There is an upper limit of how large a single transaction can be in an Ethereum block which restricts
	#this value typically to less then 1,500,000.The current gas limit is visible at https://ethstats.net/.*/
	String GETH_GAS_LIMIT="GAS_LIMIT";
	//set account,generate account by geth or web3J command line
	String GETH_EXTERNAL_ACCOUT="EXTERNAL_ACCOUT";
	//set account password
	String GETH_WALLET_PASSWORD="WALLET_PASSWORD";
	//set account unlock duration
	String GETH_ACCOUNT_UNLOCK_DURATION="ACCOUNT_UNLOCK_DURATION";
	//set transfer sleep duration
	String GETH_SLEEP_DURATION="SLEEP_DURATION";
	//set transaction attemps
	String GETH_ATTEMPTS="ATTEMPTS";
	
	//set private and public key
	String GETH_PRIVATE_KEY="PRIVATE_KEY";
	String GETH_PUBLIC_KEY="PUBLIC_KEY";


	/*-----END eth config----------*/

    //zookeeper address
	String ZOOKEEPER_ADDRESS="zookeeper.address";
	String ZOOKEEPER_LBS_PATH="zookeeper.lbs.path";
	String ZOOKEEPER_TIMEOUT="zookeeper.timeOut";
	
	String ZOOKEEPER_LBS_NODE_RENEW_INTERVAL="zookeeper.lbs.node.renew.interval";
	//log open switch
	String OMIT_CONTENT="content  omit";
    String LOG_IS_ALL_WRITE="log_is_all_write";
    
	public static final String LBS_THRIFT_STATE="lbs.thrift.state";
	
	int MD5_STR_LEN = 32;
	int SHA1_STR_LEN = 40;
	
}
