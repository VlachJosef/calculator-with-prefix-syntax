package vlach.calculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import vlach.calculator.api.Calculator;  

class CalculatorService {
	public static void main(String[] args) throws IOException {
		String fileName = null;
		if(args.length == 1) {
			fileName = args[0];
		}
		Path path = Paths.get(fileName);
		if(!path.toFile().exists()) {
			System.out.println("File not found: " + path.toFile().getAbsolutePath());
			return; 
		}

		String input = new String(Files.readAllBytes(path));
		Optional<Double> result = Calculator.process(input);
		if(result.isPresent()) {
			System.out.println(result.get()); 
		} else {
			System.out.println("Invalid input:"); 
			System.out.println("***************"); 
			System.out.println(input ); 
			System.out.println("***************"); 
		}
	}
}