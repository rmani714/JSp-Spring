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

	private static final String LoadPlayer = "select id,name,rank from player where id=?";

	private DataSource dataSource;

	private static String Add_PLAYER = "insert into player(id,name,rank) values (?,?,?)";

	private static String UPDATE_PLAYER = "Update player set id=?,name=?,rank=? where id=?";

	public PlayerUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Player> getPlayerList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Player> players = new ArrayList<>();
		Connection myConnection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			myConnection = dataSource.getConnection();

			statement = myConnection.createStatement();

			resultSet = statement.executeQuery("select * from Player");

			while (resultSet.next()) {
				Player player = new Player(resultSet.getInt("id"), resultSet.getString("name"),
						resultSet.getInt("rank"));
				players.add(player);
			}

			close(myConnection, statement, resultSet);

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
		ResultSet resultSet = null;
		boolean result = false;

		try {
			myConnection = dataSource.getConnection();

			statement = myConnection.prepareStatement(Add_PLAYER);
			statement.setString(1, String.valueOf(player.getId()));
			statement.setString(2, player.getName());
			statement.setString(3, String.valueOf(player.getRank()));

			result = statement.execute();

			close(myConnection, statement, resultSet);

		} catch (Exception e) {
			throw new Exception("Getting exception while fetching players from dataBase");
		}
		return result == true ? "Succesfully Added" : "Failed";
	}

	public Player loadPlayer(String playerId) throws Exception {

		Player player = null;
		Connection myConnection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			myConnection = dataSource.getConnection();

			statement = myConnection.prepareStatement(LoadPlayer);
			statement.setInt(1, Integer.parseInt(playerId));
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				player = new Player(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("rank"));
			}
			close(myConnection, statement, resultSet);

		} catch (Exception e) {
			throw new Exception("Getting exception while fetching players from dataBase");
		}
		return player;

	}

	public String updatePlayer(Player player) {
		Connection myConnection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean result = false;

		try {
			myConnection = dataSource.getConnection();

			statement = myConnection.prepareStatement(UPDATE_PLAYER);
			statement.setInt(1, player.getId());
			statement.setString(2, player.getName());
			statement.setInt(3, player.getRank());
			statement.setInt(4, player.getId());
			result = statement.execute();
			close(myConnection, statement, resultSet);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result == true ? "Succesfully Update" : "Failed";
	}

}
