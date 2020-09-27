package com.wellsfargo.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wellsfargo.util.Constants;

@Controller
public class ApiRequestHitController {

	public static Integer total_count = 0;
	public static Integer mobile_count = 0;
	public static Integer web_count = 0;
	public static Integer tab_count = 0;

	@GetMapping("/mobile_app")
	public String helloMobile(Model model) {
		updateCountsAndResult(Constants.MOBILE_APP);
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		surveyMap.put("mobile app", mobile_count);
		surveyMap.put("web app", web_count);
		surveyMap.put("tab app", tab_count);
		model.addAttribute("surveyMap", surveyMap);
		return "barGraph";
	}

	@GetMapping("/web_app")
	public String helloWeb(Model model) {
		updateCountsAndResult(Constants.WEB_APP);
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		surveyMap.put("mobile app", mobile_count);
		surveyMap.put("web app", web_count);
		surveyMap.put("tab app", tab_count);
		model.addAttribute("surveyMap", surveyMap);
		return "barGraph";
	}
	
    @RequestMapping("/foo")
    @ResponseBody
    public String handleFoo() {
        return "home.html";
    }
	
	@GetMapping("/tab_app")
	public String barGraph(Model model) {
		updateCountsAndResult(Constants.TAB_APP);
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		surveyMap.put("mobile app", mobile_count);
		surveyMap.put("web app", web_count);
		surveyMap.put("tab app", tab_count);
		model.addAttribute("surveyMap", surveyMap);
		return "barGraph";
	}

	public void updateCountsAndResult(int whoCalled) {
		total_count++;
		String result;

		switch (whoCalled) {
		case Constants.MOBILE_APP:
			synchronized (mobile_count) {
				mobile_count++;
			}

			// Enable the service requested through mobile
			break;
		case Constants.WEB_APP:
			synchronized (mobile_count) {
				web_count++;
			}

			// Enable the service requested through web
			break;
		case Constants.TAB_APP:
			synchronized (mobile_count) {
				tab_count++;
			}
			// Enable the service requested through tab
			break;
		default:
			System.out.println("Error in API query");
		}
//		result = "Mobile requests: "+mobile_count+"\n"+ "Web Requests: "+web_count+"\n"+"Tab Counts: "+
//				tab_count+"\n"+"Total counts: "+total_count;
//		return result;
	}

}
