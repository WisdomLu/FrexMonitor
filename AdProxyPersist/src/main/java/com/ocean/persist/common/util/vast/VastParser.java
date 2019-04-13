package com.ocean.persist.common.util.vast;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

public class VastParser {

	@SuppressWarnings("unchecked")
	public static VastAd parse(String text) throws DocumentException{
		
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new InputSource(new StringReader(text)));
		Element root = doc.getRootElement();
		
		VastAd data = new VastAd();
		data.setId(root.attributeValue("id"));
		
		String sequence = root.attributeValue("sequence");
		if(!StringUtils.isEmpty(sequence)){
			data.setSequence(Integer.valueOf(sequence));
		}
		Element element = root.element("InLine");
		List<Element> elements = element.elements();
		List<String> impr = new ArrayList<String>(); 
		for (Element e : elements) {
			
			String name = e.getName();
			if(name.equals("AdSystem")){
				data.setTitle(e.getText());
			} 
			else if(name.equals("Description")){
				data.setDesc(e.getText());
			}
			else if(name.equals("Impression")){
				impr.add(e.getTextTrim());
			}
			else if(name.equals("Creatives")){
				data.setCreatives(parseVastCreatives(e));
			}
		}
		data.setImpr(impr);
		return data;
	}

	@SuppressWarnings("unchecked")
	private static List<VastCreative> parseVastCreatives(Element creativeElement){
		
		List<Element> creatives = creativeElement.elements();
		List<VastCreative> vastCreatives = new ArrayList<VastCreative>();
		for (Element creative : creatives) {
			
			VastCreative vastCreative = new VastCreative();
			Element linear = creative.element("Linear");
			
			Element duration = linear.element("Duration");
			vastCreative.setDuration(duration.getText());
			
			// 落地页地址
			List<String> urls = new ArrayList<String>();
			Element clicksElement = linear.element("VideoClicks");
			List<Element> clicks = clicksElement.elements();
			for (Element click : clicks) {
				urls.add(click.getTextTrim());
			}
			vastCreative.setLinkurls(urls);
			
			List<VastMedia> medias = parseVastMedias(linear.element("MediaFiles"));
			vastCreative.setMedias(medias);
			
			vastCreatives.add(vastCreative);
		}
		return vastCreatives;
	}
	
	@SuppressWarnings("unchecked")
	private static List<VastMedia> parseVastMedias(Element mediasElement){
		
		List<Element> mes = mediasElement.elements();
		List<VastMedia> medias = new ArrayList<VastMedia>();
		for (Element element : mes) {
			
			if(element.getName().equals("MediaFile")){
				
				VastMedia media = new VastMedia();
				media.setDelivery(element.attributeValue("delivery"));
				media.setType(element.attributeValue("type"));
				media.setUrl(element.getText());
				
				String width = element.attributeValue("width");
				if(!StringUtils.isEmpty(width)){
					media.setWidth(Integer.valueOf(width));
				}
				String height = element.attributeValue("height");
				if(!StringUtils.isEmpty(height)){
					media.setHeight(Integer.valueOf(width));
				}
				medias.add(media);
			}
		}
		return medias;
	}
	
	public static void main(String[] args) 
			throws UnsupportedEncodingException, DocumentException {
		
		String text = 
				"<Ad id=\"VamVideo\" sequence=\"1\">" + 
				"<InLine>" + 
				"<AdSystem>VALUEMAKER MOBILE</AdSystem>" + 
				"<AdTitle>Value Maker MOBILE VAST</AdTitle>" + 
				"<Description>http://www.baidu.com</Description>" + 
				"<Impression id=\"0\">http://www.baidu.com</Impression>" + 
				"<Impression id=\"1\">http://www.qq.com</Impression>" + 
				"<Creatives>" + 
				"<Creative>" + 
				"<Linear>" + 
				"<Duration>00:00:15</Duration>" + 
				"<MediaFiles>" + 
				"<MediaFile delivery=\"progressive\" type=\"video/x-flv\" width=\"640\" height=\"480\">http://www.baidu.com</MediaFile>" + 
				"</MediaFiles>" + 
				"<VideoClicks>" + 
				"<ClickThrough>http://www.baidu.com</ClickThrough>" + 
				"</VideoClicks>" + 
				"<TrackingEvents/>" + 
				"</Linear>" + 
				"</Creative>" + 
				"</Creatives>" + 
				"</InLine>" + 
				"</Ad>";		
		System.out.println(parse(text));
	}
}
