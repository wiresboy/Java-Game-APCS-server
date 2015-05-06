package com.appspot.JavaGameAPCSServer.server;

public class GameStore {
	private String gameMap;
	private String player1Name,player2Name;
	private String player1Data,player2Data;
	
	GameStore(String gameMap, String player1Name, String player2Name)
	{
		this.gameMap = gameMap;
		if (player1Name.compareTo(player2Name)<0)//order names properly
		{
			this.player1Name = player1Name;
			this.player2Name = player2Name;
		}
		else
		{
			this.player1Name = player2Name;
			this.player2Name = player1Name;
		}
	}
	
	public boolean isCorrectGame(String gameMap, String player1Name, String player2Name){
		if (!gameMap.equals(this.gameMap))
			return false;//wrong map, so return not same
		if (player1Name.compareTo(player2Name)<0)
		{
			return (player1Name.equals(this.player1Name) & player2Name.equals(this.player2Name));
		}
		else
		{
			return (player2Name.equals(this.player1Name) & player1Name.equals(this.player2Name));
		}
	}
	
	public String updateAndGetPlayerData(String gameMap, String playerName, String otherPlayerName, String playerData)
	{
		if (!isCorrectGame(gameMap, playerName, otherPlayerName))
		{
			System.out.println("Whoah! Somehow you are trying to access the wrong game save!");
			return null;
		}
		if (this.player1Name.equals(playerName))//this is player one who is making the request.
		{
			player1Data = playerData;
			return player2Data;
		}
		else//this is player2 making the request.
		{
			player2Data = playerData;
			return player1Data;
		}
	}
	
}
