package com.appspot.JavaGameAPCSServer.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GameServiceImpl extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String method = getServletConfig().getInitParameter("action");
		if ("end".equals(method))
		{
			endGame(req, resp);
		}
		else if ("update".equals(method))
		{
			gameUpdate(req, resp);
		}
		else
		{
			System.out.println("Method Failure: invalid method. Received \""+method+"\"");
		}	
		
	}
	
	public void gameUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		PrintWriter out = resp.getWriter();
		String gameMap = req.getParameter("game");
		String myUName = req.getParameter("me");
		String myData = req.getParameter("meData");
		String itUName = req.getParameter("it");

		//System.out.println(gameMap+myUName+myData+itUName);
		//out.write("Game = "+gameMap);
		//out.write("me = "+myUName);
		//out.write("it = "+itUName);
		//out.write("meData = "+myData);
		GameSingleStore game = GamesStore.getGame(gameMap, myUName,  itUName);
		
		if (game == null)
		{	//generate a new game
			game = GamesStore.newGame(gameMap, myUName, itUName, myData);
			
		}
		else //update game data and return it.
		{
			String responseDat = game.updateAndGetPlayerData(gameMap, myUName, itUName, myData);
			out.write(responseDat);
			/*out.write("Game = "+gameMap);
			out.write("me = "+myUName);
			out.write("it = "+itUName);
			out.write("meData = "+myData);
			out.write("GS Game = "+game);//*/
		}
	}

	public void endGame(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		PrintWriter out = resp.getWriter();
		String gameMap = req.getParameter("game");
		String myUName = req.getParameter("me");
		String itUName = req.getParameter("it");
		
		GamesStore.removeGame(gameMap, myUName, itUName);
		
		out.println("REMOVED GAME SUCESS!");
	}
}
