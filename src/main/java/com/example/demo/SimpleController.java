package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SimpleController {


	/**
	 * http://www.masterspringboot.com/web/rest-services/how-to-return-json-objects-as-response-in-spring-boot/
	 * 
	 * @return
	 */
	@GetMapping("/kai/")
	public ResponseEntity<Object> getEmployee() {
		Map<Integer, Map<String, String>> Emps = new HashMap<>();

		DBInteraction db = new DBInteraction();
		ResultSet rsc = db.Retrieve("select * from employee");
		int counter = 0;
		try {
			while (rsc.next()) {
				Map<String, String> emp = new HashMap<>();
				// System.out.println("step2:"+ emp);
				emp.put("first", rsc.getString("first"));
				emp.put("last", rsc.getString("last"));
				emp.put("age", rsc.getString("age"));
				emp.put("address", rsc.getString("address"));
				emp.put("city", rsc.getString("city"));
				emp.put("state", rsc.getString("state"));
				// System.out.println("current record:"+ emp);
				Emps.put(counter, emp);
				// System.out.println("current collection:"+ Emps);
				counter++;
			}
			rsc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		db.closeConn();

		return new ResponseEntity<>(Emps, HttpStatus.OK);
	}

	/**
	 * 
	 * @param fn
	 * @param emp
	 * @return
	 * https://www.appsdeveloperblog.com/putmapping-spring-mvc/
	 */
	@PutMapping("/age/{fn}")
	public Integer age(
			@PathVariable String fn, 
			@RequestBody Employee emp) {
		
		
		DBInteraction db = new DBInteraction();
		String usql = String.format("UPDATE employee SET age = %s WHERE first in ('%s')",emp.age.toString(),fn);
		int result = db.Update(usql);
		db.closeConn();
		
		return result;
	}
	

	/***
	 * https://howtodoinjava.com/spring-mvc/controller-getmapping-postmapping/
	 * @param newEmployee
	 * @return
	 */
	@PostMapping("/createEmp/")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee newEmployee){
		Map<Integer, Map<String, String>> Emps = new HashMap<>();
		DBInteraction db = new DBInteraction();
		
		String csql = String.format("insert into employee "
				+ "(first, last, age, address, city, state) "
				+ "values ('%s', '%s', %s, '%s', '%s', '%s')", 
				newEmployee.first, newEmployee.last, newEmployee.age.toString(),
				newEmployee.address, newEmployee.city, newEmployee.state);
		
		db.Create(csql);
		
		String rsql = String.format("select * from employee WHERE first in ('%s') ",newEmployee.first);
		ResultSet rsc = db.Retrieve(rsql);
		int counter = 0;
		try {
			while (rsc.next()) {
				Map<String, String> emp = new HashMap<>();
				// System.out.println("step2:"+ emp);
				emp.put("first", rsc.getString("first"));
				emp.put("last", rsc.getString("last"));
				emp.put("age", rsc.getString("age"));
				emp.put("address", rsc.getString("address"));
				emp.put("city", rsc.getString("city"));
				emp.put("state", rsc.getString("state"));
				// System.out.println("current record:"+ emp);
				Emps.put(counter, emp);
				// System.out.println("current collection:"+ Emps);
				counter++;
			}
			rsc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		db.closeConn();
		return new ResponseEntity<>(Emps, HttpStatus.OK);
	}

}