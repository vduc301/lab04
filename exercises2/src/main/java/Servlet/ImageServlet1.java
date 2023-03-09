package Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ImageServlet1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String imagePath = "/tdtu.jpg";
//        Path path = Paths.get(imagePath);
//        InputStream in = Files.newInputStream(path);
		InputStream in = getClass().getClassLoader().getResourceAsStream("/tdtu.jpg");
        
        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "inline; filename=tdtu.jpg");
        
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        
        in.close();
        out.flush();
        out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
