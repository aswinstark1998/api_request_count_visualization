package com.wellsfargo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wellsfargo.util.Constants;

@RestController
public class ApiRequestHitController {

	public static Long total_count = 0l;
	public static Long mobile_count = 0l;
	public static Long web_count = 0l;
	public static Long tab_count = 0l;

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
				synchronized (mobile_count) {
					mobile_count++;
				}
				
				//Enable the service requested through mobile
				break;
			case Constants.WEB_APP:
				synchronized (mobile_count) {
					web_count++;
				}
				
				//Enable the service requested through web
				break;
			case Constants.TAB_APP:
				synchronized (mobile_count) {
					tab_count++;
				}
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
