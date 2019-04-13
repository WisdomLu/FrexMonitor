package com.ocean.persist.lbs.baidu.ip;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月7日 
      @version 1.0 
 */
public class IPLocationContent {

        private String address; //"吉林省长春市"
        private IPLocationAddrDetail address_detail;  
		private IPLocationPoint point;//  #当前城市中心点，注意当前坐标返回类型，
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public IPLocationAddrDetail getAddress_detail() {
			return address_detail;
		}
		public void setAddress_detail(IPLocationAddrDetail address_detail) {
			this.address_detail = address_detail;
		}
		public IPLocationPoint getPoint() {
			return point;
		}
		public void setPoint(IPLocationPoint point) {
			this.point = point;
		}

}
