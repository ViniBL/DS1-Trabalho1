package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/agente/*")
public class AgenteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	Erro erros = new Erro();
    	
    	if (usuario == null) {
    		//response.sendRedirect(request.getContextPath());
			erros.add("Acesso não autorizado!");
    		erros.add("Apenas o agente tem acesso a essa página 👍");
    		request.setAttribute("mensagens", erros);
    		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
    		rd.forward(request, response);
    	} else if (usuario.getPapel().equals("AGENCIA")) {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agente/index.jsp");
            dispatcher.forward(request, response);
    	} else {
    		erros.add("Acesso não autorizado!");
    		erros.add("Apenas Papel [AGENTE] tem acesso a essa página");
    		request.setAttribute("mensagens", erros);
    		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
    		rd.forward(request, response);
    	}    	
    }
}