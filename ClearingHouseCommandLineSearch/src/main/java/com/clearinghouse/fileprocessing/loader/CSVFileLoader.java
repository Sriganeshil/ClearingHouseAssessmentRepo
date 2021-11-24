package com.clearinghouse.fileprocessing.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.clearinghouse.fileprocessing.vo.BankInfo;
import com.clearinghouse.fileprocessing.vo.RowData;

public class CSVFileLoader {

	
	private ArrayList<BankInfo> bankInfoList = new ArrayList<BankInfo>();
	
	public void loadFile() throws Exception {
		Path currentDirectory = Paths.get(".");
		Predicate<Path> predicate = path -> String.valueOf(path).contains(".csv");
		List<Path> files  =Files.walk(currentDirectory, 3).filter(predicate).collect(Collectors.toList());

		
		
		files.forEach(v -> {
		
			 
			try {
				File file = v.toFile();
				System.out.println(v);
				Scanner sc = new Scanner(file);
			
			ArrayList<RowData> list = new ArrayList<RowData>();
			ArrayList<BankInfo> fieldLevelDataListLocal = new ArrayList<BankInfo>();
//				sc.useDelimiter(" ");   //sets the delimiter pattern  
				int row = 0 ;
				while (sc.hasNextLine())  //returns a boolean value  
				{  
					if(row ==0 ) {
						row++ ;
						sc.nextLine();
						continue ;
					}
				String next = sc.nextLine();
				
				RowData rowData = new RowData();
				rowData.setRowDataStr(next);
				
				
					list.add(rowData);
					
					String[] rowDataArray = next.split(",");
					
					List<String> asList = Arrays.asList(rowDataArray);
					BankInfo bInfo = new BankInfo();
					bInfo.setBankName(asList.get(1));
					bInfo.setType(asList.get(2));
					bInfo.setCity(asList.get(3));
					bInfo.setState(asList.get(4));
					bInfo.setZipCode(asList.get(5));
					bInfo.setRowDataStr(next);
					fieldLevelDataListLocal.add(bInfo);
					
				}
				
				;
				setBankInfoList(fieldLevelDataListLocal);
				
				sc.close();  //closes the scanner  
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		});
		
		 
		
		 
	
	}

	

	public ArrayList<BankInfo> getBankInfoList() {
		return bankInfoList;
	}

	public void setBankInfoList(ArrayList<BankInfo> fieldLevelDataList) {
		this.bankInfoList = fieldLevelDataList;
	}
}
