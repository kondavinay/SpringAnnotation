package com.rabo.statementprocessor.filereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import com.rabo.statementprocessor.validation.ValidationClass;
import com.rabo.statementprocessorInterface.FileProcessor;

@Component
//@Primary
public class CsvReader  implements FileProcessor{

	
	public void process() throws IOException, SAXException, ParserConfigurationException, ClassNotFoundException{

		String csvFile = "C:\\Users\\Vinay Konda\\Desktop\\New folder (3)\\1\\file.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));

			ArrayList<String> pl = new ArrayList<String>();

			while ((line = br.readLine()) != null) {

				String[] details = line.split(cvsSplitBy);

				pl.add(details[0] + "," + details[1] + "," + details[2] + "," + details[3] + "," + details[4] + ","
						+ details[5]);

			}

			String csv[] = pl.toArray(new String[pl.size()]);

			

			try {
				ValidationClass.method(csv);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	
}