package com.base.mvc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class PlayerServlet
 */
@WebServlet("/PlayerServlet")
public class PlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PlayerUtil playerUtil;
	
	@Resource(name = "jdbc/player")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		try {
			playerUtil = new PlayerUtil(dataSource);
		} catch (Exception e) {
			throw new RuntimeException("error in player datasource");
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//read the parameter
			String action = request.getParameter("action");
			//route the Action
			if(action == null) {
				action = "LIST";
			}
			
			switch (action) {
				case "List": {
					getPlayerList(request, response);
					break;
				}
				case "ADD": {
					addPlayer(request, response);
					break;
				}
				default: {
					getPlayerList(request, response);
					break;
				}
			}
	}

	private void addPlayer(HttpServletRequest request, HttpServletResponse response) {
		try {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String rank = request.getParameter("rank");
		Player player = new Player(Integer.parseInt(id), name, Integer.parseInt(rank));
		String status = playerUtil.addPlayer(player);
		request.setAttribute("addStatus", status);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/add-player.jsp");
		requestDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getPlayerList(HttpServletRequest request, HttpServletResponse response) {
		
		//get Player list
		
		//get All Player
		try {
			List<Player> playerList = playerUtil.getPlayerList(request,response);
			
			request.setAttribute("playerList", playerList);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Player.jsp");
			
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
