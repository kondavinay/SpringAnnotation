package com.rabo.statementprocessor.filereader;

import java.io.IOException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.rabo.statementprocessor.validation.ValidationClass;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.rabo.statementprocessor.filereader.XmlReader;
import com.rabo.statementprocessor.pojo.XmlPojoClass;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.rabo.statementprocessorInterface.FileProcessor;
@Component
@Primary
public class XmlReader extends DefaultHandler implements FileProcessor {
	
	
	private XmlPojoClass read;
	private String temp;
	private ArrayList<XmlPojoClass> accList = new ArrayList<XmlPojoClass>();
	
	public void process() throws IOException, SAXException, ParserConfigurationException, ClassNotFoundException {
		
	
		
		SAXParserFactory spfac = SAXParserFactory.newInstance();

		SAXParser sp = spfac.newSAXParser();

		XmlReader handler = new XmlReader();

		sp.parse("C:\\Users\\Vinay Konda\\Desktop\\New folder (3)\\1\\filecode.XML", handler);

		handler.readList();
	}

	public void characters(char[] buffer, int start, int length) {
		temp = new String(buffer, start, length);
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		temp = "";
		if (qName.equalsIgnoreCase("record")) {
			read = new XmlPojoClass();
			read.setReference(attributes.getValue("reference"));

		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("record")) {
			// add it to the list
			accList.add(read);

		} else if (qName.equalsIgnoreCase("accountNumber")) {
			read.setAccountNumber((temp));
		} else if (qName.equalsIgnoreCase("Description")) {
			read.setDescription((temp));
		} else if (qName.equalsIgnoreCase("startBalance")) {
			read.setStartBal((temp));
		} else if (qName.equalsIgnoreCase("mutation")) {
			read.setMutation((temp));
		}

		else if (qName.equalsIgnoreCase("endBalance")) {
			read.setEndBal((temp));
		}

	}
	
	private void readList() throws ClassNotFoundException, IOException {


		XmlPojoClass[] use = accList.toArray(new XmlPojoClass[accList.size()]);

		String[] input = new String[use.length];

		for (int i = 0; i < use.length; i++) {
			input[i] = use[i].getReference() + "," + use[i].getAccountNumber() + "," + use[i].getDescription() + ","
					+ use[i].getStartBal() + "," + use[i].getMutation() + "," + use[i].getEndBal();
		}
		
		
			
			ValidationClass.method(input);

	}

	
		
		
	
}
