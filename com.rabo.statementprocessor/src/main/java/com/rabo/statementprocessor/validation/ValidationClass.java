package com.rabo.statementprocessor.validation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;



public class ValidationClass {
	
	public static void method(String[] sf) throws IOException, ClassNotFoundException {
		
	   File file = new File("C:\\Users\\Vinay Konda\\Desktop\\New folder (3)\\1\\folder\\myOutput9999.csv");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		ArrayList<String> faliedrecord = new ArrayList<String>(Arrays.asList(sf));

		Arrays.sort(sf);
		for (int i = 1; i < sf.length; i++) {
			if (sf[i].substring(0, 6).equals(sf[i - 1].substring(0, 6))) {
				faliedrecord.remove(sf[i]);

			}
		}
		System.out.println("unique record references");
		for (String fg : faliedrecord) {

			System.out.println(fg);

		}

		ArrayList<String> validFail = new ArrayList<String>();
		String value2[] = faliedrecord.toArray(new String[faliedrecord.size()]);
		System.out.println("\nsuccess validation records");
		oos.writeChars("\n Endbalance matched records");
		for (String mack : value2) {
			String[] value = mack.split(",");
			
			BigDecimal bigDecimalCurrency1 = new BigDecimal(value[3]);
			BigDecimal bigDecimalCurrency2 = new BigDecimal(value[4]);
			BigDecimal bigDecimalCurrency3 = bigDecimalCurrency1.add(bigDecimalCurrency2);
			BigDecimal bigDecimalCurrency4 = new BigDecimal(value[5]);

			if (bigDecimalCurrency3.equals(bigDecimalCurrency4)) {

				System.out.println("\nRecord Transaction Reference : " + value[0]);

				System.out.println("Description: " + value[2]);
				 oos.writeChars("\nRecord \nTransaction Reference : " +
						 value[0]+"\n"+"Description: " + value[2]);

			} else if (bigDecimalCurrency3 != bigDecimalCurrency4) {

				validFail.add("\nRecord\nTransaction Reference : " + value[0] + "\nDescription  : " + value[2]);

			}

		}
		oos.writeObject("\nEndBalancefailed records");
		System.out.println("\nfailed  validation records");
		for (String s1 : validFail) {

			System.out.println(s1);
			oos.writeObject("\n"+s1);
		}
		oos.close();
		fos.close();
	}
}
