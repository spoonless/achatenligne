package com.achatenligne.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.achatenligne.model.ProduitService;

@WebServlet("/produits")
public class ListeProduitsControleurServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProduitService service = new ProduitService();
		req.setAttribute("produits", service.getAll());
		req.getRequestDispatcher("/WEB-INF/jsp/produits.jsp").forward(req, resp);
	}

}
