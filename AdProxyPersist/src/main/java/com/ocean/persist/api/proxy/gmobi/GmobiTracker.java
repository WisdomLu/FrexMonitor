package com.ocean.persist.api.proxy.gmobi;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月16日 
      @version 1.0 
 */
public class GmobiTracker {
	   private	List<String> impression;
	   private	List<String>  click;
	   private	List<String>   install;
	   
	   
	   //video
	   private	List<String>   start;
	   private	List<String>   midpoint;
	   private	List<String>   complete;
	   private	List<String>  skip;
	   private	List<String>   pause;
	   private	List<String>   replay;
		public List<String> getImpression() {
			return impression;
		}
		public void setImpression(List<String> impression) {
			this.impression = impression;
		}
		public List<String> getClick() {
			return click;
		}
		public void setClick(List<String> click) {
			this.click = click;
		}
		public List<String> getInstall() {
			return install;
		}
		public void setInstall(List<String> install) {
			this.install = install;
		}
		public List<String> getStart() {
			return start;
		}
		public void setStart(List<String> start) {
			this.start = start;
		}
		public List<String> getMidpoint() {
			return midpoint;
		}
		public void setMidpoint(List<String> midpoint) {
			this.midpoint = midpoint;
		}
		public List<String> getComplete() {
			return complete;
		}
		public void setComplete(List<String> complete) {
			this.complete = complete;
		}
		public List<String> getSkip() {
			return skip;
		}
		public void setSkip(List<String> skip) {
			this.skip = skip;
		}
		public List<String> getPause() {
			return pause;
		}
		public void setPause(List<String> pause) {
			this.pause = pause;
		}
		public List<String> getReplay() {
			return replay;
		}
		public void setReplay(List<String> replay) {
			this.replay = replay;
		}
}
