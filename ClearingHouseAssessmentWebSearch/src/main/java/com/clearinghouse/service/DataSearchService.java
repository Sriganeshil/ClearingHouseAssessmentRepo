package com.clearinghouse.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.clearinghouse.fileprocessing.vo.BankInfo;
import com.clearinghouse.fileprocessing.vo.RowData;
@Service
public class DataSearchService {
	
	
	
	
	 public List<BankInfo> fileSearch ( ArrayList<BankInfo> listBankInfo, String searchData) throws Exception{

		 HashSet<BankInfo> consolidatedSet = new HashSet<BankInfo>();	
		 StringBuffer sb = new StringBuffer();
				    if(searchData != null && searchData.length() >0 ) {
			
				    	String noSpaceSearch = searchData.replace(" ", "");
					    RowData searchRowData = new RowData();
					    
					
					    searchRowData.setRowDataStr(noSpaceSearch);			    
					    List<BankInfo> result =searchBankData(searchRowData, listBankInfo);
					    if(result != null) {
					    	consolidatedSet.addAll(result);
					    }
					    
					    if(searchData.contains(",")) {
					    	searchRowData.setRowDataStr(searchData);
					    List<BankInfo> result2 =searchBankData(searchRowData, listBankInfo);
					    if(result2 != null) {
					    	consolidatedSet.addAll(result2);
					    }
					    
					   }
					    
					    
			    }
				    ArrayList<BankInfo> bankInfos = new ArrayList<BankInfo>();	    
				    consolidatedSet.forEach(v -> {
				    	bankInfos.add(v);
			    	});
			    
			  return bankInfos;
		  
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
