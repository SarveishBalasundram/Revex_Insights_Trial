package com.example.demoKotlin.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demoKotlin.model.DonutChartData;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class cumalativeRevenueService {

    @Autowired
	private JdbcTemplate jdbcTemplate;

    public List<DonutChartData> getDepartmentCumulativeRevenueAndExpenses(int department, String year) {
		List<DonutChartData> req = new ArrayList<>();
		//req = getRequest(department,dateFrom,dateTo);
		req = getRequestYearly(department,year);
        return req;
    }
    
    private List<DonutChartData> getRequestYearly(int department, String year) {
    	System.out.println("test "+ year);
        List<DonutChartData> allReq;
        Double revenue;
        Double expenses;
        String sql = null;
        List<Object> params = new ArrayList<>();
        if(year.equals("")==false) {
            sql = "SELECT department_id, " +
                    "SUM(CASE WHEN amount >= 0 THEN amount ELSE 0 END) / NULLIF(COUNT(CASE WHEN amount >= 0 THEN amount ELSE 0 END), 0) AS avg_positive_amount, " +
                    "SUM(ABS(CASE WHEN amount < 0 THEN amount ELSE 0 END)) / NULLIF(COUNT(CASE WHEN amount < 0 THEN amount ELSE 0 END), 0) AS avg_absolute_negative_amount " +
                    "FROM ledger WHERE YEAR(general_ledger_date) = ?";

            params.add(year.isEmpty() ? "1000" : year);
            if (department != 0) {
                sql += " AND department_id = ?";
            }
        }else {
            sql = "SELECT department_id, " +
                    "SUM(CASE WHEN amount >= 0 THEN amount ELSE 0 END) / NULLIF(COUNT(CASE WHEN amount >= 0 THEN amount ELSE 0 END), 0) AS avg_positive_amount, " +
                    "SUM(ABS(CASE WHEN amount < 0 THEN amount ELSE 0 END)) / NULLIF(COUNT(CASE WHEN amount < 0 THEN amount ELSE 0 END), 0) AS avg_absolute_negative_amount " +
                    "FROM ledger";
            if (department != 0) {
                sql += " WHERE department_id = ?";
            }
        }

        if (department != 0) {
            params.add(department);
        }

        // Convert parameters list to an array
        Object[] paramsArray = params.toArray();

        // Debug print SQL and parameters
        System.out.println("SQL: " + sql);
        System.out.println("Parameters: " + Arrays.toString(paramsArray));

        // Execute the query
        allReq = jdbcTemplate.query(
            sql,
            paramsArray,
            (rs, rowNum) -> new DonutChartData(
                rs.getInt("department_id"),
                rs.getDouble("avg_positive_amount"),
                rs.getDouble("avg_absolute_negative_amount")
            )
        );
        
        for (DonutChartData data : allReq) {
        	System.out.println("data "+data.getAvg_positive_amount()+ " "+ data.getAvg_absolute_negative_amount());
            double totalAmount = data.getAvg_positive_amount() + data.getAvg_absolute_negative_amount();

            double revenuePercentage = (totalAmount > 0) ? ((data.getAvg_positive_amount()) / totalAmount) * 100 : 0;
            data.setAvg_positive_amount(revenuePercentage);
            
            double expensesPercentage = (totalAmount > 0) ? ((data.getAvg_absolute_negative_amount()) / totalAmount) * 100 : 0;
            data.setAvg_absolute_negative_amount(expensesPercentage);
        }
        //Revenue positive-negative/(positive+negative)*100

        return allReq;
	}

	public List<DonutChartData> getRequest(int department, String dateFrom, String dateTo) {
        List<DonutChartData> allReq;
        Double revenue;
        Double expenses;
        // Initial SQL query
        String sql = "SELECT department_id, " +
                     "SUM(CASE WHEN amount >= 0 THEN amount ELSE 0 END) / NULLIF(COUNT(CASE WHEN amount >= 0 THEN amount ELSE 0 END), 0) AS avg_positive_amount, " +
                     "SUM(ABS(CASE WHEN amount < 0 THEN amount ELSE 0 END)) / NULLIF(COUNT(CASE WHEN amount < 0 THEN amount ELSE 0 END), 0) AS avg_absolute_negative_amount " +
                     "FROM ledger WHERE general_ledger_date >= ? AND general_ledger_date <= ?";

        // Add department filter if department is not 0
        if (department != 0) {
            sql += " AND department_id = ?";
        }

        // Prepare parameters based on conditions
        List<Object> params = new ArrayList<>();
        
        // Add date parameters
        params.add(dateFrom.isEmpty() ? "1900-01-01" : dateFrom);
        params.add(dateTo.isEmpty() ? "9999-12-31" : dateTo);
        
        // Add department parameter if applicable
        if (department != 0) {
            params.add(department);
        }

        // Convert parameters list to an array
        Object[] paramsArray = params.toArray();

        // Debug print SQL and parameters
        System.out.println("SQL: " + sql);
        System.out.println("Parameters: " + Arrays.toString(paramsArray));

        // Execute the query
        allReq = jdbcTemplate.query(
            sql,
            paramsArray,
            (rs, rowNum) -> new DonutChartData(
                rs.getInt("department_id"),
                rs.getDouble("avg_positive_amount"),
                rs.getDouble("avg_absolute_negative_amount")
            )
        );
        
        for (DonutChartData data : allReq) {
            double totalAmount = data.getAvg_positive_amount() + data.getAvg_absolute_negative_amount();

            double revenuePercentage = (totalAmount > 0) ? ((data.getAvg_positive_amount() - data.getAvg_absolute_negative_amount()) / totalAmount) * 100 : 0;
            data.setAvg_positive_amount(revenuePercentage);
            
            double expensesPercentage = (totalAmount > 0) ? ((data.getAvg_absolute_negative_amount() - data.getAvg_positive_amount()) / totalAmount) * 100 : 0;
            data.setAvg_absolute_negative_amount(expensesPercentage);
        }


        return allReq;
    }


             
}
