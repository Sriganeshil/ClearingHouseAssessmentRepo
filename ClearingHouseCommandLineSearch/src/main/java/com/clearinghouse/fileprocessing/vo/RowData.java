package com.clearinghouse.fileprocessing.vo;

public class RowData {

	private String rowDataStr ;

	public String getRowDataStr() {
		
		return rowDataStr;
	}

	public void setRowDataStr(String rowDataStr) {
		this.rowDataStr = rowDataStr;
	}
	
	public String getRowDataStrFormattedForSearch() {
//		String lowerCase = rowDataStr.toLowerCase();
		String updateValue = rowDataStr.replace(" ","");
		
		return updateValue.toLowerCase() ;
	}
	
	public String toString() {		
		return rowDataStr ;
	}
}
