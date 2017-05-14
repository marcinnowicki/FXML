/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.text.Text;
import javax.xml.transform.Source;
// import javafx.scene.transform.Transform;

import net.sf.saxon.Transform;

/**
 *
 * @author marcinn
 */
public class MDRSReader {

    public void readThatFile() {
        try {
            long startTime = System.currentTimeMillis();
            String MyBid = "NA";
            File fXmlFile = new File("E:\\MDRS\\eod\\mdrs_out\\MD.IMPORT.BASISSPREAD_answer.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("rtsh:maturity");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.print("Current Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    if (eElement.getElementsByTagName("mp:bid").getLength() > 0) {
                        MyBid = eElement.getElementsByTagName("mp:bid").item(0).getTextContent();
                    } else {
                        MyBid = "NA";
                    }

                    System.out.println(""
                            + "; Currency: " + eElement.getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("xc:value").getTextContent()
                            + "; type: " + eElement.getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("xc:value").getTextContent()
                            + "; matura: " + eElement.getAttributes().getNamedItem("xc:value").getTextContent()
                            + "; mp:status: " + eElement.getElementsByTagName("mp:status").item(0).getTextContent()
                            + "; mp:bid: " + MyBid
                    );
//                    System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
//                    System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
//                    System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
//                    System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

                }
            }
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println(elapsedTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Text readThatFileOut(String InFilename) {
        Text MyReturn = new Text() ;
        try {
            
            long startTime = System.currentTimeMillis();
            Integer lp = 0 ;
            String MyBid = "NA";
            File fXmlFile = new File(InFilename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            MyReturn.setText( "Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("mp:status");

            MyReturn.setText( MyReturn.getText() + "----------------------------\n");

            for (int temp = 0; temp < Math.min(1e5, nList.getLength()); temp++) {

                Node nNode = nList.item(temp);

                // MyReturn.setText( MyReturn.getText() + "Current Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
//                    if (eElement.getParentNode().getChildNodes( getNamedItem("mp:bid").getLength() > 0) {
//                        MyBid = eElement.getParentNode().getElementsByTagName("mp:bid").item(0).getTextContent();
//                    } else {
//                        MyBid = "NA";
//                    }
                    lp++ ;
                    MyReturn.setText( MyReturn.getText() + "" + lp.toString()
                            + "; " + eElement.getParentNode().getParentNode().getParentNode().getParentNode().getNodeName() + ": " + eElement.getParentNode().getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("xc:value").getTextContent()
                            + "; " + eElement.getParentNode().getParentNode().getParentNode().getNodeName() + ": " + eElement.getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("xc:value").getTextContent()
                            + "; " + eElement.getParentNode().getNodeName() + ": " + eElement.getParentNode().getAttributes().getNamedItem("xc:value").getTextContent()
                            + "; mp:status: " + eElement.getTextContent() 
                            + "; mp:bid: " + MyBid
                            + "\n");

                }
            }
            doc = null ;
            dBuilder = null ;
            dbFactory = null ;
            fXmlFile = null ;
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            MyReturn.setText( "\nElapsed time " + elapsedTime + " ms\n" + "Number of nodes processed " + lp.toString() + "\n" + 
                    "Speed " + (1000 * lp / elapsedTime) + "nodes per second\n" +
                    MyReturn.getText());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MyReturn ;
    }
    
    public void testSAXON() {
        // net.sf.saxon MySAXON = new net.sf.saxon() ;
        // Source MySource = new Source() ;
//        Transform MyTrans = new Transform() ;
//        MyTrans.processFile( 
//                "C:\\Users\\marcinn\\Documents\\MDRS\\eod\\mdrs_out\\MD.IMPORT.BONDPRICES_UPD_answer.xml", 
//                "C:\\Users\\marcinn\\Documents\\MDRS\\check.xslt", 
//                "C:\\Users\\marcinn\\Documents\\MDRS\\check.txt", 
//                new ArrayList<String>(),
//                "C:\\Users\\marcinn\\Documents\\MDRS\\check.log") ;
    }
}
