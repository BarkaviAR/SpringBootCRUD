package com.example.demo;

import java.time.LocalDateTime;
import java.util.Date;

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
		if(b<a)
		{return b;
		}
		else
			{return a;
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
	
	@GetMapping("/student/")
	public String getStudent() {
		//https://www.baeldung.com/jackson-object-mapper-tutorial
		studentDatabase A = new studentDatabase();
		A.name = "Raj";
		A.age = 5;
		A.DOB = new Date();
		
		
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString="";
		try {
			 jsonString = mapper.writeValueAsString(A);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//https://www.freecodecamp.org/news/what-is-the-correct-content-type-for-json-request-header-mime-type-explained/
		//TODO: return json instead of string
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
