package com.achatenligne.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.achatenligne.model.Commande;
import com.achatenligne.model.Produit;
import com.achatenligne.model.ProduitService;

@WebServlet("/produits")
public class ListeProduitsControleurServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProduitService service = new ProduitService();
		List<Produit> all = service.getAll();
		req.setAttribute("produits", all);
		req.getRequestDispatcher("/WEB-INF/jsp/produits.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idProduit = req.getParameter("idProduit");
		Commande commande = getCommandeFromSession(req);
		ProduitService produitService = new ProduitService();
		Produit produit = produitService.ajouter(commande, Integer.valueOf(idProduit));
		if (produit != null) {
			req.setAttribute("message", "Le produit " + produit.getLibelle() + " a bien été ajouté à votre panier");
		}
		doGet(req, resp);
	}

	private Commande getCommandeFromSession(HttpServletRequest req) {
		Commande commande = (Commande) req.getSession().getAttribute("commande");
		if (commande == null) {
			commande = new Commande();
			req.getSession().setAttribute("commande", commande);
		}
		return commande;
	}
}
