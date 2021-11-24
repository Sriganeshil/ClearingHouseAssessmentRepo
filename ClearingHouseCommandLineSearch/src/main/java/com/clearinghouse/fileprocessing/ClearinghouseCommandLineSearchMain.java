package com.clearinghouse.fileprocessing;

import com.clearinghouse.fileprocessing.loader.CSVFileLoader;
import com.clearinghouse.fileprocessing.service.DataSearchService;
public class ClearinghouseCommandLineSearchMain {

	public static void main(String[] args) {
		
		try {
			/**
			 * Loader
			 */
			CSVFileLoader loader = new CSVFileLoader();
			loader.loadFile();
			DataSearchService dataSearch = new DataSearchService();
			dataSearch.fileSearch(loader.getBankInfoList());
		} catch (Exception e) {
		
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
}
