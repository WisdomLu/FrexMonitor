package com.ocean.persist.app.dis.zk;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;

import javax.annotation.Generated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.core.common.base.AbstractBaseEntity;

public class ZKAppContent  extends AbstractBaseEntity{
  /**
	 * 
	 */
		  private static final long serialVersionUID = 7836806800384773367L;
		  private String appId; // optional
		  private String apkId; // optional
		  private String apkMd5; // optional
		  private String signMd5; // optional
		  private List<String> tags; // optional
		  private String apkUrl; // optional
		  private long totalDownloadTimes; // optional
		  private long installedCount; // optional
		  private String categoryId; // optional
		  private String categoryName; // optional
		  private String publicDate; // optional
		  private String updateDate; // optional
		  private ZKAppDetail appInfo; // required
		  private List<ZKAppImg> imglist; // optional
		  private String content; // optional
		  /**
		   * 
		   * @see JoinSource
		   */
		  private int joinSource; // optional
		  /**
		   * 
		   * @see ReportType
		   */
		  private int reportType; // optional
		  private Map<String,List<String>> thirdReportParams; // optional
		  private Map<String,List<String>> thirdReportLinks; // optional
		  private List<ZKExtData> ext; // optional
		  private int isAd;					  //是否为应用广告,默认为0,1为广告
		  private String payMode;  			  //结算方式，取值CPC/CPM/CPA等
		  private String price;  			  //结算价格（单位元）
		public String getAppId() {
			return appId;
		}
		public void setAppId(String appId) {
			this.appId = appId;
		}
		public String getApkId() {
			return apkId;
		}
		public void setApkId(String apkId) {
			this.apkId = apkId;
		}
		public String getApkMd5() {
			return apkMd5;
		}
		public void setApkMd5(String apkMd5) {
			this.apkMd5 = apkMd5;
		}
		public String getSignMd5() {
			return signMd5;
		}
		public void setSignMd5(String signMd5) {
			this.signMd5 = signMd5;
		}
		public List<String> getTags() {
			return tags;
		}
		public void setTags(List<String> tags) {
			this.tags = tags;
		}
		public String getApkUrl() {
			return apkUrl;
		}
		public void setApkUrl(String apkUrl) {
			this.apkUrl = apkUrl;
		}
		public long getTotalDownloadTimes() {
			return totalDownloadTimes;
		}
		public void setTotalDownloadTimes(long totalDownloadTimes) {
			this.totalDownloadTimes = totalDownloadTimes;
		}
		public long getInstalledCount() {
			return installedCount;
		}
		public void setInstalledCount(long installedCount) {
			this.installedCount = installedCount;
		}
		public String getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(String categoryId) {
			this.categoryId = categoryId;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public String getPublicDate() {
			return publicDate;
		}
		public void setPublicDate(String publicDate) {
			this.publicDate = publicDate;
		}
		public String getUpdateDate() {
			return updateDate;
		}
		public void setUpdateDate(String updateDate) {
			this.updateDate = updateDate;
		}
		public ZKAppDetail getAppInfo() {
			return appInfo;
		}
		public void setAppInfo(ZKAppDetail appInfo) {
			this.appInfo = appInfo;
		}
		public List<ZKAppImg> getImglist() {
			return imglist;
		}
		public void setImglist(List<ZKAppImg> imglist) {
			this.imglist = imglist;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public int getJoinSource() {
			return joinSource;
		}
		public void setJoinSource(int joinSource) {
			this.joinSource = joinSource;
		}
		public int getReportType() {
			return reportType;
		}
		public void setReportType(int reportType) {
			this.reportType = reportType;
		}
		public Map<String, List<String>> getThirdReportParams() {
			return thirdReportParams;
		}
		public void setThirdReportParams(Map<String, List<String>> thirdReportParams) {
			this.thirdReportParams = thirdReportParams;
		}
		public Map<String, List<String>> getThirdReportLinks() {
			return thirdReportLinks;
		}
		public void setThirdReportLinks(Map<String, List<String>> thirdReportLinks) {
			this.thirdReportLinks = thirdReportLinks;
		}
		public List<ZKExtData> getExt() {
			return ext;
		}
		public void setExt(List<ZKExtData> ext) {
			this.ext = ext;
		}
		public int getIsAd() {
			return isAd;
		}
		public void setIsAd(int isAd) {
			this.isAd = isAd;
		}
		public String getPayMode() {
			return payMode;
		}
		public void setPayMode(String payMode) {
			this.payMode = payMode;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}

 
}

