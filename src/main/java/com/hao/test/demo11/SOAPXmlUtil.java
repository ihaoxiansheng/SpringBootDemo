package com.hao.test.demo11;

import cn.hutool.core.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 * SOAPXml工具类
 *
 * @author xu.liang
 * @since 2023/11/28 19:03
 */
@Slf4j
public class SOAPXmlUtil {

    public static String getXmlBody(String xml) {
        try {
            // 创建SOAP消息工厂
            MessageFactory factory = MessageFactory.newInstance();
            // 使用消息工厂创建SOAP消息
            SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes()));
            // 从SOAP消息中获取body
            SOAPBody soapBody = message.getSOAPBody();
            // 获取body内容
            String bodyContent = soapBody.getTextContent().trim();
            log.info("Body Content: " + bodyContent);
            return bodyContent;
        } catch (Exception e) {
            log.error("SOAPXml工具类异常：", e);
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                " <soapenv:Body>\n" +
                "  <ns1:cancelOrderResponse soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:ns1=\"http://server.newwfm.oss.ztesoft.com\">\n" +
                "   <ns1:cancelOrderReturn xsi:type=\"xsd:string\">&lt;Return&gt;&lt;ReturnCode&gt;1&lt;/ReturnCode&gt;&lt;ReturnString&gt;&#27809;&#26377;&#25214;&#21040;BSS&#23450;&#21333;&#21495;&#20026;[GJGL-2023103018080877472]&#30340;&#23450;&#21333;&#65281;&lt;/ReturnString&gt;&lt;/Return&gt;</ns1:cancelOrderReturn>\n" +
                "  </ns1:cancelOrderResponse>\n" +
                " </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String xmlBody = getXmlBody(xml);
        Map<String, Object> returnMap = XmlUtil.xmlToMap(xmlBody);
        System.out.println("returnMap = " + returnMap);
        String returnCode = returnMap.get("ReturnCode").toString();
        System.out.println("returnCode = " + returnCode);

    }

}
