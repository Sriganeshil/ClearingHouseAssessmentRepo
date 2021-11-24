package com.clearinghouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clearinghouse.fileprocessing.CSVFileLoader;
import com.clearinghouse.service.DataSearchService;

@Controller
@RequestMapping("/clearinghouse")
public class ClearingHouseController {

	@Autowired
	DataSearchService dataSearchService;
	
	@Autowired
	CSVFileLoader loader ;
	
    @GetMapping("/search")
    public String getResult(@RequestParam(name = "searchData", required = false, defaultValue = "") String searchData, Model model){
    	List result = null;
    	try {
    		result = dataSearchService.fileSearch(loader.getSearchData(), searchData);
		} catch (Exception e) {
				e.printStackTrace();
		}
    
        model.addAttribute("searchData", result);
        return "search";
    }
}
