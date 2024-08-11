package com.example.demoKotlin.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoKotlin.model.DonutChartData;
import com.example.demoKotlin.service.cumalativeRevenueService;

@CrossOrigin
@RestController
public class RexExRestController {
	@Autowired
	private cumalativeRevenueService cumalativeRevService;
	
	@RequestMapping(method = RequestMethod.POST, path = "/getRevenueExpenseData")
	public  List<DonutChartData> getPendingRequest(@RequestBody Map<String, String> payload)
	{
        String departmentStr = payload.get("department");
        String year = payload.get("year");

        Integer department = null;
        department = Integer.parseInt(departmentStr);
		List<DonutChartData> result = cumalativeRevService.getDepartmentCumulativeRevenueAndExpenses(department,year);
		return result;
	}
}