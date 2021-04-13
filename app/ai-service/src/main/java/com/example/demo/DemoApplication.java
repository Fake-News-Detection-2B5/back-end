package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {

		Process p = Runtime.getRuntime().exec("python3 scor1.py");
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String scor1 = in.readLine();

		Process p1 = Runtime.getRuntime().exec("python3 scor2.py");
		BufferedReader in1 = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String scor2 = in1.readLine();

		int scor = (Integer.parseInt(scor1) + Integer.parseInt(scor2)) / 2;
		System.out.println(scor);

		SpringApplication.run(DemoApplication.class, args);
	}


}
