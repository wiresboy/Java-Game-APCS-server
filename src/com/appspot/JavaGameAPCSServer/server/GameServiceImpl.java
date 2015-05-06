package com.appspot.JavaGameAPCSServer.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GameServiceImpl extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		byte b[] = new byte[1024];
		String input = "";
		while (req.getInputStream().read(b)>0)
			input = input + b.toString();
		req.getMethod();
		PrintWriter out = resp.getWriter();
		out.println("TEST11111");
		out.println("Method: "+req.getMethod());
		out.print("Cookies: ");
		for (Cookie c : req.getCookies())
			out.println(c.getName()+": "+c.getValue());
		out.println("Remote Address: "+req.getRemoteAddr());
		out.println("game: "+req.getParameter("game"));
		out.println("me: "+req.getParameter("me"));
		out.println("it: "+req.getParameter("it"));
		out.print(input);
		
	}


}
