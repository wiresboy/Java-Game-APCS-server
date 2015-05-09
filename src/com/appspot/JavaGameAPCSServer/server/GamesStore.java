package com.appspot.JavaGameAPCSServer.server;

import java.util.ArrayList;

public class GamesStore {
	private static ArrayList<GameSingleStore> currentGames = new ArrayList<GameSingleStore>();
	
	public static GameSingleStore getGame(String gameMap, String player1Name, String player2Name)
	{
		for (GameSingleStore t : currentGames)
			if (t.isCorrectGame(gameMap, player1Name, player2Name))
				return t;
		return null;//maybe make the game here instead?
	}
	
	public static void removeGame(String gameMap, String player1Name, String player2Name)
	{
		for (int i = currentGames.size(); i >= 0; i--)//loop backward so that when we remove an old game, it doesn't skip checking the next one because every index shifted down one.
		{
			if (currentGames.get(i).isCorrectGame(gameMap, player1Name, player2Name))
			{
				currentGames.remove(i);
				return;
			}
		}
	}
	
	public static GameSingleStore newGame(String gameMap, String player1Name, String player2Name, String player1Data)
	{
		GameSingleStore game = new GameSingleStore(gameMap, player1Name, player2Name, player1Data);
		currentGames.add(game);
		return game;
	}
	
	public static void clearOldGames()
	{
		for (int i = currentGames.size(); i >= 0; i--)//loop backward so that when we remove an old game, it doesn't skip checking the next one because every index shifted down one.
		{
			if (currentGames.get(i).isToOld())//does that game feel it is too old?
				currentGames.remove(i);//then remove it!
		}
	}
}
