package com.ocean.forex.service.dataAsyn.fallowme.persist;

import java.util.List;

public class FallowmeData {
    private List<Integer> t;
    private List<Double> c;
    private List<Double> h;
    private List<Double> l;
    private List<Double> v;
    private List<Double> o;
    private String s;
    private int st;
    private int digits;
	public List<Integer> getT() {
		return t;
	}
	public List<Double> getC() {
		return c;
	}
	public List<Double> getH() {
		return h;
	}
	public List<Double> getL() {
		return l;
	}
	public List<Double> getV() {
		return v;
	}
	public List<Double> getO() {
		return o;
	}
	public String getS() {
		return s;
	}
	public int getSt() {
		return st;
	}
	public int getDigits() {
		return digits;
	}
	public void setT(List<Integer> t) {
		this.t = t;
	}
	public void setC(List<Double> c) {
		this.c = c;
	}
	public void setH(List<Double> h) {
		this.h = h;
	}
	public void setL(List<Double> l) {
		this.l = l;
	}
	public void setV(List<Double> v) {
		this.v = v;
	}
	public void setO(List<Double> o) {
		this.o = o;
	}
	public void setS(String s) {
		this.s = s;
	}
	public void setSt(int st) {
		this.st = st;
	}
	public void setDigits(int digits) {
		this.digits = digits;
	}
}
