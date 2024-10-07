package ru.specialist;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class App {

	public static void main(String[] args) throws IOException, InterruptedException {
		out.println("Starting process...");
		
		// Runtime.getRuntime().exec("explorer.exe c:\\");
		//Process  p = Runtime.getRuntime().exec(new String[] {"explorer.exe", "c:\\"});
		
		Process  p = new ProcessBuilder("cmd.exe", "/C", "dir")
				//.redirectOutput(ProcessBuilder.Redirect.to(new File("result.txt")))
				//.redirectOutput(ProcessBuilder.Redirect.INHERIT)
				//.inheritIO()
				.start();
		
		var reader = new BufferedReader(
				new InputStreamReader(p.getInputStream(), Charset.forName("cp866")));
		String s;
		while ( (s = reader.readLine()) != null)
			out.println(s);
		
		int returnValue = p.waitFor();
		// int returnValue = p.exitValue(); // только после завершения запущенного процесса
		
		out.printf("Return code: %d\n", returnValue);
		
		System.exit(returnValue);

	}

}
