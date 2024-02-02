package com.hao.test.year.demo2023.demo8;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.*;

/**
 * @author xu.liang
 * @since 2023/8/1 13:40
 */
@Slf4j
public class XmlTest {

    public static void main(String[] args) throws DocumentException {
        String strParamXml = gtdowntrans1;
//        String strParamXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <esb><route><sender>gp_noc</sender><receiver>cthk_eoms</receiver><time>2023-07-21 17:00:48</time><service_name>sa_nf_wp_uwp</service_name><msg_type>request</msg_type><msg_id>JT-SJ-FT-202307210003420230721170422279334</msg_id></route> <interfacemsg><JMSType>JMSNewReactionInfo</JMSType><changed_Data><attachInfoList/><reactionInfo><WSID>JT-SJ-FT-2023072100034</WSID><CONTACT>尚直玉</CONTACT><CONTACTTEL>15311702070</CONTACTTEL><DEALFEEDBACK>反馈信息：国际公司-处理说明 姓名工号：尚直玉 联系电话：15311702070</DEALFEEDBACK><DEALER>sysadmin</DEALER><OPPROV>gp_noc</OPPROV></reactionInfo></changed_Data></interfacemsg></esb>";
        Document doc = DocumentHelper.parseText(strParamXml);
        Element root = doc.getRootElement();
        Node privateInfo = root.selectSingleNode("//interfacemsg");//私有信息
        Node routeInfo = root.selectSingleNode("//route");//路由信息
        log.info("==========================解析xml开始==========================");
//        Node reactionInfo = privateInfo.selectSingleNode("//changed_Data/reactionInfo");
        Node jmsStatusChangeInfo = privateInfo.selectSingleNode("//changed_Data/jmsStatusChangeInfo");
//        System.out.println("status = " + status);
        String status = jmsStatusChangeInfo.selectSingleNode("Status").getText();
        System.out.println("status1 = " + status);
//        log.info("==========================reactionInfo: " + reactionInfo);
//        List<Node> attachInfoListNode = privateInfo.selectNodes("//changed_Data/attachInfoList");

//        log.info("==========================attachInfoList: " + attachInfoListNode);
//        log.info("==========================WSID: " + reactionInfo.selectSingleNode("WSID").getText());
//        log.info("==========================CONTACT: " + reactionInfo.selectSingleNode("CONTACT").getText());
//        log.info("==========================CONTACTTEL: " + reactionInfo.selectSingleNode("CONTACTTEL").getText());

//        if (CollectionUtil.isNotEmpty(attachInfoListNode)) {
//            System.out.println(" 11111111111111 ");
//        }
//
//        for (int i = 0; i < attachInfoListNode.size(); i++) {
//            Node node = attachInfoListNode.get(i);
//            Node fileNode = node.selectSingleNode("FILE");
//            if (null == fileNode) {
//                System.out.println("fileNode = " + fileNode);
//                break;
//            }
//            System.out.println(" =11121313 ");
//            String fileName = fileNode.selectSingleNode("FILE_NAME").getText();
//            String filePath = fileNode.selectSingleNode("FILE_PATH").getText();
//        }

    }

    public static String gtdowntrans = "<esb>\n" +
            "  <route>\n" +
            "    <sender>gp_noc</sender>\n" +
            "    <receiver>cthk_eoms</receiver>\n" +
            "    <time>2023-04-07 08:30:58</time>\n" +
            "    <service_name>sa_nf_wp_nwp</service_name>\n" +
            "    <msg_type/>\n" +
            "    <msg_id>2023040700180_34dfb468-b947-4b54-bae5-3fd96951fa40</msg_id>\n" +
            "  </route>\n" +
            "  <interfacemsg>\n" +
            "    <JMSType>JMSNewDistribute</JMSType>\n" +
            "    <sheetTask>\n" +
            "      <WSID>2023040700180</WSID>\n" +
            "      <WSINFOHINT>链路故障  163  广州法兰克福FRK/CT-GZU/CT 100G004*IP  IP电路中断  </WSINFOHINT>\n" +
            "      <WSTYPECODE>传输故障单</WSTYPECODE>\n" +
            "      <WSMARKER/>\n" +
            "      <URGEDEGREE>一般</URGEDEGREE>\n" +
            "      <TASKNAME>故障处理</TASKNAME>\n" +
            "      <HANDLEPERSON>SYSTEM</HANDLEPERSON>\n" +
            "      <BEGINTIME>2023-04-07 08:17:29</BEGINTIME>\n" +
            "      <NETWORK/>\n" +
            "      <REJECT_NUMBER>0</REJECT_NUMBER>\n" +
            "      <OPPROV>shgp_noc</OPPROV>\n" +
            "      <STARTPROV>shgp_noc</STARTPROV>\n" +
            "      <LOCALWSID>集团NOC</LOCALWSID>\n" +
            "      <WSDEALFLAG>i</WSDEALFLAG>\n" +
            "    </sheetTask>\n" +
            "  </interfacemsg>\n" +
            "</esb>";

    public static String gtdowntrans1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<esb>\n" +
            "    <route>\n" +
            "        <sender>gp_noc</sender>\n" +
            "        <receiver>cthk_eoms</receiver>\n" +
            "        <time>2023-08-01 11:10:35</time>\n" +
            "        <service_name>sa_nf_wp_uwp</service_name>\n" +
            "        <msg_type>StatusChange</msg_type>\n" +
            "        <msg_id>JT-SJ-FT-202308010000220230801111417067568</msg_id>\n" +
            "    </route>\n" +
            "    <interfacemsg>\n" +
            "        <JMSType>JMSStatusChange</JMSType>\n" +
            "        <changed_Data>\n" +
            "            <jmsStatusChangeInfo>\n" +
            "                <WSID>JT-SJ-FT-2023080100002</WSID>\n" +
            "                <Status>结单</Status>\n" +
            "            </jmsStatusChangeInfo>\n" +
            "        </changed_Data>\n" +
            "    </interfacemsg>\n" +
            "</esb>";



}
