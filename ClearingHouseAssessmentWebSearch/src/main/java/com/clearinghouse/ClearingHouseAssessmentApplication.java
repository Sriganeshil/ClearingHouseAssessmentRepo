package com.clearinghouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.clearinghouse.fileprocessing.CSVFileLoader;

@SpringBootApplication
public class ClearingHouseAssessmentApplication implements CommandLineRunner{

	@Autowired
	CSVFileLoader loader ;
	
	public static void main(String[] args) {
		SpringApplication.run(ClearingHouseAssessmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			loader.loadFile();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
