package com.appspot.JavaGameAPCSServer.server;

import java.util.ArrayList;

public class GameSingleStore {
	private String gameMap;
	private String player1Name,player2Name;
	private String player1Data,player2Data;
	private ArrayList<String> updatesFromPlayer1 = new ArrayList<String>();
	private ArrayList<String> updatesFromPlayer2 = new ArrayList<String>();
	
	
	GameSingleStore(String gameMap, String player1Name, String player2Name)
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
			return (player2Name.equals(this.player1Name) && player1Name.equals(this.player2Name));
		}
	}
	
	public String updateAndGetPlayerData(String gameMap, String playerName, String otherPlayerName, String playerData)
	{
		if (!isCorrectGame(gameMap, playerName, otherPlayerName))
		{
			System.out.println("Whoah! Somehow you are trying to access the wrong game save!");
			return null;
		}
		String[] playerDataSplit = playerData.split(":");//split portions of data, everything after a ":" is a message for the other player. 
		if (this.player1Name.equals(playerName))//this is player one who is making the request, return data about player 2.
		{
			player1Data = playerDataSplit[0];
			if (playerDataSplit.length>0)
				for (int i = 1; i<playerDataSplit.length; i++)
					updatesFromPlayer1.add(playerDataSplit[i]);
			
			if (updatesFromPlayer2.size()>0)
				return player2Data + ":" + updatesFromPlayer2.remove(0);
			return player2Data;
		}
		else//this is player2 making the request. Return player1's data plus updates made by them. 
		{
			player2Data = playerDataSplit[0];
			
			if (playerDataSplit.length>0)
				for (int i = 1; i<playerDataSplit.length; i++)
					updatesFromPlayer1.add(playerDataSplit[i]);
			
			
			return player1Data + generateUpdateString(updatesFromPlayer1);
		}
	}
	
	private String generateUpdateString(ArrayList<String> updates)
	{
		String ret = "";
		if (updates.size()>1)
		{
			for (int i = 0; i<updates.size(); i++)
				ret += ":" + updates.remove(i);
		}
		return ret;
	}
	
	
}
