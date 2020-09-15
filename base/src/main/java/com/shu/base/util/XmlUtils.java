package com.shu.base.util;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: XmlUtils
 * @Desctiption: xml工具
 * @Author: XWY
 * @Date: 2019/9/10 17:18
 * @Version: 1.0
 */
@Slf4j
public class XmlUtils {
    public static Document parseXmlString(String xmlStr){

        try{
            InputSource is = new InputSource(new StringReader(xmlStr));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();
            Document doc = builder.parse(is);
            return doc;
        }catch(Exception e){
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static Map<String,Object> getXmlBodyContext(String bodyXml){

        Map<String,Object> dataMap = new HashMap<String,Object>();

        Document doc = parseXmlString(bodyXml);
        if(null != doc){
            NodeList rootNode = doc.getElementsByTagName("xml");
            if(rootNode != null){

                Node root = rootNode.item(0);
                NodeList nodes = root.getChildNodes();
                for(int i = 0;i < nodes.getLength(); i++){
                    Node node = nodes.item(i);
                    dataMap.put(node.getNodeName(), node.getTextContent());
                }
            }
        }
        return dataMap;
    }
}
