package com.wengyingjian.kylin.util;

import com.thoughtworks.xstream.XStream;

/**
 * Created by wengyingjian on 16/2/17.
 */
public class XmlUtil {
    /**
     * 通过指定class模版将xml转化成java对象<br/>
     * <p>
     * <p>
     * 要求:
     *
     * @param xmlContent xml的内容
     * @param clazz      java对象的类
     */
    public static <T> T fromXml(String xmlContent, Class<T> clazz) {
        XStream xStream = new XStream();
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.processAnnotations(new Class[]{clazz});
        return clazz.cast(xStream.fromXML(xmlContent));
    }

    /**
     * 将对象转化成XML
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String toXml(T t) {
        XStream xStream = new XStream();
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.processAnnotations(t.getClass());
        return xStream.toXML(t);
    }
}
