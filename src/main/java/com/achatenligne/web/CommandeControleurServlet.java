package com.achatenligne.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.achatenligne.model.Commande;
import com.achatenligne.model.CommandeService;
import com.achatenligne.model.CommandeVideException;

@WebServlet("/commande")
public class CommandeControleurServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/commande.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Commande commande = (Commande) req.getSession().getAttribute("commande");
		CommandeService commandeService = new CommandeService();
		try {
			commandeService.valider(commande);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/commandeValidee.jsp").forward(req, resp);
		} catch (CommandeVideException e) {
			doGet(req, resp);
		}
	}
}
