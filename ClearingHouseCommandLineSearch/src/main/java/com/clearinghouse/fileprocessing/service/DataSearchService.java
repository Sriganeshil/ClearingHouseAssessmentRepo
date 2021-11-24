package com.clearinghouse.fileprocessing.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.clearinghouse.fileprocessing.vo.BankInfo;
import com.clearinghouse.fileprocessing.vo.RowData;

public class DataSearchService {
	
	 public void fileSearch ( ArrayList<BankInfo> listBankInfo) throws Exception{

			
		  BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));  
		  String str;   
		  
		  System.out.println("Enter lines of text.");   
		  System.out.println("Enter 'stop' to quit.");   
		  System.out.println("Search by bankname, type, city, state or zipcode");
		  System.out.println("Ex: TX or ATM or Bank");
		  System.out.println("Ex: New York,NY or Dallas, TX");
		  
		  do {   
			  
			    str = obj.readLine();   
			    
			    if(str != null && str.length() >0 ) {
			    	String noSpaceSearch = str.replace(" ", "");
			    RowData searchRowData = new RowData();
			    HashSet consolidatedSet = new HashSet();
			
			    searchRowData.setRowDataStr(noSpaceSearch);			    
			    List<BankInfo> result =searchBankData(searchRowData, listBankInfo);
			    if(result != null) {
			    	consolidatedSet.addAll(result);
			    }
			    
			    if(str.contains(",")) {
			    	searchRowData.setRowDataStr(str);
			    List<BankInfo> result2 =searchBankData(searchRowData, listBankInfo);
			    if(result2 != null) {
			    	consolidatedSet.addAll(result2);
			    }
			    }
			   
			    consolidatedSet.forEach(System.out :: println);

			    System.out.println("------end result----");
			    }
			    
			  }   while(!str.equals("stop"));   
		  
		}
	 	
	 
	 private List<BankInfo> searchBankData(RowData searchRowData, ArrayList<BankInfo> listBankInfo) {
		 
		   if(searchRowData.getRowDataStr().length() == 2) {
			   
		    	 List<BankInfo> collect = listBankInfo.stream().filter(v ->getPredicateForState(searchRowData.getRowDataStr(), v)).collect(Collectors.toList());
		    	 return collect;
				   
		    }else {
	    List<BankInfo> collect = listBankInfo.stream().filter(v ->getPredicate(searchRowData.getRowDataStrFormattedForSearch(), v)).collect(Collectors.toList());
	    
		   
		    
		    if(collect.isEmpty()) {
		    	List<BankInfo> collectStateCity = listBankInfo.stream().filter(v ->getPredicateForCityState(searchRowData.getRowDataStrFormattedForSearch(), v)).collect(Collectors.toList());
		    	return collectStateCity;
			    
		    }else {
		    	return collect;
		    }
		    
		    } 
		   
		 
	 }
	 
	 
	 private boolean getPredicateForState(String value, BankInfo bankInfo) {
		 if(bankInfo.getState().toLowerCase().equals(value.toLowerCase())) {
	 			return true ;
	 		 }
		 return false;
	 }
	 
	 private boolean getPredicateForCityState(String value, BankInfo bankInfo) {
		 String cityState = bankInfo.getCity() + bankInfo.getState(); 
		 String searchData =value.replaceAll("\\s","");
		 String cityStateNoSpace = cityState.replaceAll("\\s","");

		 if(cityStateNoSpace.toLowerCase().contains(searchData.toLowerCase())) {
	 			return true ;
	 		 }
		 else if(bankInfo.getCity().toLowerCase().contains(searchData)) {
	 			return true ;
	 		 }
		 else if(bankInfo.getState().toLowerCase().contains(searchData)) {
	 			return true ;
	 		 }
		 return false;
	 }
	 
	 
	 	private boolean getPredicate(String value, BankInfo bankInfo) {
	 		
	 		String searchData =value.replaceAll("\\s","");
	 		
	 		 if(bankInfo.getBankName().equals(searchData)) {
	 			return  true ;
	 		 }else if(bankInfo.getType().equals(searchData)) {
	 			return  true ;
	 		 }
	 		else if(bankInfo.getCity().equals(searchData)) {
	 			return true ;
	 		 }	 		
	 		else if(bankInfo.getZipCode().equals(searchData)) {
	 			return true ;
	 		 }
	 		else if(bankInfo.getRowDataStrFormattedForSearch().contains(value)) {
	 			return true ;
	 		 }
	 		 
	 		return false ;
	 	}
	 
	 
	 
}
