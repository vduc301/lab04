package Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ImageServlet2() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/tdtu1.jpg");
		if (inputStream == null) {
			response.getWriter().println("File not found");
			return;
		}
		response.setHeader("Content-Disposition", "attachment; filename=tdtu1.jpg");
		response.setContentType("application/octet-stream");
		OutputStream outputStreamDownload = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStreamDownload.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		outputStreamDownload.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
