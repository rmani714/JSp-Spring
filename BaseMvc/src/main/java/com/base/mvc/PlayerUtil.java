package com.base.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class PlayerUtil {
	
	private DataSource dataSource;
	
	private static String Add_PLAYER = "insert into player(id,name,rank) values (?,?,?)";
	public PlayerUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Player> getPlayerList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Player> players = new ArrayList<>(); 
		Connection myConnection = null;
		Statement statement = null;
	    ResultSet resultSet =null;
	    
	    try {
			myConnection = dataSource.getConnection();
			
			statement = myConnection.createStatement();
			
			resultSet = statement.executeQuery("select * from Player");
			
			
			while (resultSet.next()) {
				Player player = new Player(resultSet.getInt("id"),
											resultSet.getString("name"), 
											resultSet.getInt("rank"));
				players.add(player);
			}
			
			close(myConnection,statement,resultSet);
			
		} catch (Exception e) {
			throw new Exception("Getting exception while fetching players from dataBase");
		}
		return players;
	}

	private void close(Connection myConnection, Statement statement, ResultSet resultSet) throws SQLException {

		if (myConnection != null) {
			myConnection.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (resultSet != null) {
			resultSet.close();
		}
	}

	public String addPlayer(Player player) throws Exception {
		
		Connection myConnection = null;
		PreparedStatement statement = null;
	    ResultSet resultSet =null;
	    boolean result = false;
		
	    try {
			myConnection = dataSource.getConnection();
			
			statement = myConnection.prepareStatement(Add_PLAYER);
			statement.setString(1, String.valueOf(player.getId()));
			statement.setString(2, player.getName());
			statement.setString(3, String.valueOf(player.getRank()));
			
			result = statement.execute();
			
			close(myConnection,statement,resultSet);
			
			
		} catch (Exception e) {
			throw new Exception("Getting exception while fetching players from dataBase");
		}
	    return result==true ? "Succesfully Added" : "Failed";
	}
	
	

}
