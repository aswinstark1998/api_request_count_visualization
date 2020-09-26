package com.wellsfargo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wellsfargo.util.Constants;

@RestController
public class ApiRequestHitController {

	public static long total_count = 0;
	public static long mobile_count = 0;
	public static long web_count = 0;
	public static long tab_count = 0;

	@GetMapping("/mobile_app")
	public String helloMobile() {
		return updateCountsAndResult(Constants.MOBILE_APP);
	}

	@GetMapping("/web_app")
	public String helloWeb() {
		return updateCountsAndResult(Constants.WEB_APP);
	}

	@GetMapping("/tab_app")
	public String helloTab() {
		return updateCountsAndResult(Constants.TAB_APP);
	}

	public String updateCountsAndResult(int whoCalled) {
		total_count++;

		String result;

		switch (whoCalled) {
			case Constants.MOBILE_APP:
				mobile_count++;
				//Enable the service requested through mobile
				break;
			case Constants.WEB_APP:
				web_count++;
				//Enable the service requested through web
				break;
			case Constants.TAB_APP:
				tab_count++;
				//Enable the service requested through mobile
				break;				
			default:
				System.out.println("Error in API query");
		}
		result = "Mobile requests: "+mobile_count+"\n"+ "Web Requests: "+web_count+"\n"+"Tab Counts: "+
				tab_count+"\n"+"Total counts: "+total_count;
		return result;
	}

}
