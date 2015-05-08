package com.appspot.JavaGameAPCSServer.server;

import java.util.ArrayList;

public class GamesStore {
	ArrayList<GameSingleStore> currentGames = new ArrayList<GameSingleStore>();
	
	public GameSingleStore getGame(String gameMap, String player1Name, String player2Name)
	{
		for (GameSingleStore t : currentGames)
			if (t.isCorrectGame(gameMap, player1Name, player2Name))
				return t;
		return null;//maybe make the game here instead?
	}
}
