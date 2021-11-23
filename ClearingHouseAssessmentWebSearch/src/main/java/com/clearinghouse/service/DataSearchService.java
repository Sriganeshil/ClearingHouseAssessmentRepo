package com.clearinghouse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.clearinghouse.fileprocessing.vo.BankInfo;
import com.clearinghouse.fileprocessing.vo.RowData;
@Service
public class DataSearchService {
	
	
	
	
	 public String fileSearch ( ArrayList<BankInfo> listBankInfo, String searchData) throws Exception{

				
		 StringBuffer sb = new StringBuffer();
				    if(searchData != null && searchData.length() >0 ) {
			    final RowData searchRowData = new RowData();
			    String search = searchData.replace(" ", "");
			    
			    searchRowData.setRowDataStr(search);
			    
			    if(search.length() == 2) {
			    	 List<BankInfo> collect = listBankInfo.stream().filter(v ->getPredicateForState(searchRowData.getRowDataStr(), v)).collect(Collectors.toList());
					    collect.forEach(v -> {
					    	sb.append(v+"\n");
					    });
					    System.out.println("------end result----");
			    }else {
		    List<BankInfo> collect = listBankInfo.stream().filter(v ->getPredicate(searchRowData.getRowDataStrFormattedForSearch(), v)).collect(Collectors.toList());
		    collect.forEach(v -> {
		    	sb.append(v+"\n");
		    	});
			    System.out.println("------end result----");
			    }

			    
			    }
			    
			  return sb.toString();
		  
		}
	 	
	 	
	 private boolean getPredicateForState(String value, BankInfo bankInfo) {
		 if(bankInfo.getState().toLowerCase().equals(value.toLowerCase())) {
	 			return true ;
	 		 }
		 return false;
	 }
	 
	 	private boolean getPredicate(String value, BankInfo bankInfo) {
	 		
	 		 
	 		 if(bankInfo.getBankName().equals(value)) {
	 			return  true ;
	 		 }else if(bankInfo.getType().equals(value)) {
	 			return  true ;
	 		 }
	 		else if(bankInfo.getCity().equals(value)) {
	 			return true ;
	 		 }
	 		else if(bankInfo.getZipCode().equals(value)) {
	 			return true ;
	 		 }
	 		else if(bankInfo.getRowDataStrFormattedForSearch().contains(value)) {
	 			return true ;
	 		 }
	 		 
	 		return false ;
	 	}


		
	 
	 
	 
}
