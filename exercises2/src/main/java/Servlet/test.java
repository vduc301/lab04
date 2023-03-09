package Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class test {
	public static void main(String[] args) throws IOException {
//		ClassLoader classLoader = getClass().getClassLoader();
		String imagePath = "/images/tdtu.jpg";
        Path path = Paths.get(imagePath);
        InputStream in = Files.newInputStream(path);
	}
}
