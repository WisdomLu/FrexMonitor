package com.ocean.persist.api.proxy.paerjiate_online;

public class PaerjiateOLAdslot {
	private String mimes;//": "jpg,gif,icon,png,",
    private int slotWidth;//": 640,
    private int slotHeight;//": 300,
    private int minDuration;//": 15,
    private int maxDuration;//": 15
	public String getMimes() {
		return mimes;
	}
	public void setMimes(String mimes) {
		this.mimes = mimes;
	}
	public int getSlotWidth() {
		return slotWidth;
	}
	public void setSlotWidth(int slotWidth) {
		this.slotWidth = slotWidth;
	}
	public int getSlotHeight() {
		return slotHeight;
	}
	public void setSlotHeight(int slotHeight) {
		this.slotHeight = slotHeight;
	}
	public int getMinDuration() {
		return minDuration;
	}
	public void setMinDuration(int minDuration) {
		this.minDuration = minDuration;
	}
	public int getMaxDuration() {
		return maxDuration;
	}
	public void setMaxDuration(int maxDuration) {
		this.maxDuration = maxDuration;
	}

}
