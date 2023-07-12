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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SimpleController {

	@GetMapping("/single/")
	public String helloWorld() {
		System.out.println("Going to print Hello World");
		return "helloWorld";

	}

	@GetMapping("/getAge/")
	public int helloTara() {
		return 3;

	}

	@GetMapping("/centersByName/")
	public LocalDateTime helloTarak() {
		return java.time.LocalDateTime.now();

	}

	@GetMapping("/float/")
	public float helloTaras() {
		Float f1 = new Float(34);
		return f1;

	}

	@GetMapping("/double/")
	public double helloTarask() {
		double d1 = 12.3;
		return d1;

	}

	@GetMapping("/boolean/")
	public boolean helloT() {
		boolean one = false;
		return one;

	}

	@GetMapping("/byte/")
	public byte helloTa() {
		byte a = 20;
		byte b = -1;
		if (b < a) {
			return b;
		} else {
			return a;
		}

	}

	@GetMapping("/Long Data/")
	public Object helloTar(@RequestParam int b) {
		long a = 100000L;
		if (b > 0) {
			return a;
		} else {
			return 8;
		}

	}

	@GetMapping("/char/")
	public char helloTaraB() {
		char A = 'A';
		return A;

	}

	@GetMapping("/CRUD/")
	public String CRUD() {
		String name = "";
		String lastName = "";

		DBInteraction db = new DBInteraction();

		db.Create(
				"insert into employee (first, last, age, address, city, state) values ('ashkon', 'b', 7, '78 rat Nest','Hazard', 'ajax');");
		db.Update("UPDATE employee SET age = 60 WHERE first in ('gt')");
		ResultSet rsc = db.Retrieve("select * from employee");
		try {
			while (rsc.next()) {
				name = rsc.getString("first");
				lastName = rsc.getString("last");
				System.out.println(name + " " + lastName);
				// do something with the extracted data...
			}
			rsc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeConn();
		return name + " " + lastName;
	}

	/**
	 * http://www.masterspringboot.com/web/rest-services/how-to-return-json-objects-as-response-in-spring-boot/
	 * @return
	 */
	@GetMapping("/kai/")
    public ResponseEntity<Object> getEmployee() {
		
		
		Map<Integer,Map<String, String>> Emps = new HashMap<>();
		
		/*
		 *  | string     |  string   |    
		 *  | ------     |  ------   |
		 *  |  first     |   luke    |    
		 *  |   last     |   duke    | 
		 *       
		 */
		//Create collection obejct - data
		Map<String, String> emp = new HashMap<>();
		//Fill java Collection named data
		emp.put("first", "luke");
        emp.put("last", "duke");
        emp.put("age","5");
		
        
        Emps.put(1, emp);
        
        Map<String, String> emp2  = new HashMap<>();
		//Fill java Collection named data
		emp2.put("first", "tara");
        emp2.put("last", "p");
        emp2.put("age","4");
        
        Emps.put(2, emp2);
        
        //return it
        return new ResponseEntity<>(Emps, HttpStatus.OK);
        
		//DBInteraction db = new DBInteraction();
		//ResultSet rsc = db.Retrieve("select * from employee");
	}

	@GetMapping("/student/")
	public String getStudent() {
		// https://www.baeldung.com/jackson-object-mapper-tutorial
		studentDatabase A = new studentDatabase();
		A.name = "Raj";
		A.age = 5;
		A.DOB = new Date();

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(A);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// https://www.freecodecamp.org/news/what-is-the-correct-content-type-for-json-request-header-mime-type-explained/
		// TODO: return json instead of string
		return jsonString;

	}
}

//@Controller
//public class SimpleController {
//    @Value("${spring.application.name}")
//    String appName;
//
//    @GetMapping("/")
//    public String homePage(Model model) {
//        model.addAttribute("appName", appName);
//        return "home";
//    }
//}
