namespace java com.ocean.thrift.entity
enum ErrorCode {
    RC_SUCCESS       = 0,      	  // �ɹ�
    RC_PARAM_ERROR   = -1,     	  //�����������
    RC_INTER_ERROR   = -2,     	  //ϵͳ�ڲ�����
	RC_UNKNOW_ERROR  = -3,     	  //δ֪����
}

enum CoorType {
	COOR_BD09LL = 0, 	//Ϊһ�ִ������ϵ��Ҳ��Ŀǰ�㷺ʹ�õ�GPSȫ�����Ƕ�λϵͳʹ�õ�����ϵ�� 
	COOR_GCJ02 = 1, 	//��ʾ��������ּ��ܵ�����
	COOR_WGS84 = 2, 	//Ϊһ�ִ������ϵ��Ҳ��Ŀǰ�㷺ʹ�õ�GPSȫ�����Ƕ�λϵͳʹ�õ�����ϵ��

}

struct LBSByAddressReq {
    1: required string address,	//����
    2: optional string city,	//��������
    3: optional CoorType  coor,	//��������
}
struct LBSByAddressResp{
    1: required ErrorCode errorCode,	//������
    2: optional string errorMsg,	//������Ϣ
    3: optional LBSByAddressContent content, //��ѯ����
}
struct LBSByAddressContent{
    1: required string lat,		//ά��
    2: required string lng,		//����
    3: optional string level,	//��ַ����
    
}


struct LBSByIPReq {
    1: required string ip,
    2: optional CoorType  coor,	//��������
}

struct LBSByIPResp {
    1: required ErrorCode errorCode,	//������
    2: optional string errorMsg,	//������Ϣ
    3: optional  LBSByIPContent content,
}

struct LBSByIPContent{
    1: optional string		 	address,	//����
    2: optional string          province,	//ʡ��  
    3: required string 			city,    	//��������
    4: optional string 			cityCode,	//���д���
    5: optional string          district,	//����  

    6: optional string          street,  	//�ֵ�  
    7: optional string          streetNo,	//��ַ  
    8: required string 			lat,		//ά��
    9: required string 			lng,		//����
    
}
service LBSServer {
    //  ����״̬
    void ping(),
    LBSByIPResp searchByIP(1:LBSByIPReq req),
    LBSByAddressResp searchByAddress(1:LBSByAddressReq req),
}