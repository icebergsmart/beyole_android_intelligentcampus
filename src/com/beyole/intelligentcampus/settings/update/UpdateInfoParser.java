package com.beyole.intelligentcampus.settings.update;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

/**
 * 更新信息解释器
 * 
 * @author Iceberg
 * 
 */
public class UpdateInfoParser {
	public static UpdateInfo getUpdataInfo(InputStream is) throws Exception {
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "utf-8");
		int type = parser.getEventType();
		UpdateInfo info = new UpdateInfo();
		while (type != XmlPullParser.END_DOCUMENT) {
			switch (type) {
			case XmlPullParser.START_TAG:
				if ("version".equals(parser.getName())) {
					info.setVersion(parser.nextText());
				} else if ("url".equals(parser.getName())) {
					info.setUrl(parser.nextText());
				} else if ("description".equals(parser.getName())) {
					info.setDescription(parser.nextText());
				} else if ("date".equals(parser.getName())) {
					info.setDate(parser.nextText());
				}
				break;
			}
			type = parser.next();
		}
		return info;
	}
}
