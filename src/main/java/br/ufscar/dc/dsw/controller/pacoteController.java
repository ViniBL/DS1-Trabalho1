package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.pacoteDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.DestinoDAO;
import br.ufscar.dc.dsw.domain.pacote;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.destino;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/agente/pacotes/*")
public class pacoteController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private pacoteDAO dao;

    @Override
    public void init() {
        dao = new pacoteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
                
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }
        
        try {
            
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

   
    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<pacote> listaPacotes = new ArrayList<>();
        listaPacotes = dao.getAll();
        request.setAttribute("listaPacotes", listaPacotes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agente/lista.jsp");
        dispatcher.forward(request, response);
        
    }

    private Map<Long, String> getAgencias() {
        Map <Long,String> agencias = new HashMap<>();
        for (Usuario agencia: new UsuarioDAO().getAllAgencia()) {
            agencias.put(agencia.getId(), agencia.getNome());
        }
        return agencias;
    }
    /*
    private Map<Long, String> getDestinos() {
        Map <Long,String> destinos = new HashMap<>();
        for (destino destino: new DestinoDAO().getAll()) {
            destinos.put(destino.getId_destino(), destino.getCidade());
        }
        return destinos;
    }
    */
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("agencias", getAgencias());
        //request.setAttribute(name: "destinos", getDestinos());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pacote/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id_pacote"));
        pacote pacote = dao.get(id);
        request.setAttribute("pacote", pacote);
        request.setAttribute("agencias", getAgencias());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pacote/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String data_partida = request.getParameter("data_partida");
        String descricao = request.getParameter("descricao");
        Integer duracao = Integer.parseInt(request.getParameter("duracao"));
        Float valor = Float.parseFloat(request.getParameter("valor"));
        
        Long agenciaId = Long.parseLong(request.getParameter("id_agencia"));
        Usuario agencia = new UsuarioDAO().getAgenciaByID(agenciaId);
        Long destinoId = Long.parseLong(request.getParameter("id_destino"));
        destino destino = new DestinoDAO().get(destinoId);
        
        pacote pacote = new pacote(data_partida, duracao, valor, descricao, agencia, destino);
        dao.insert(pacote);
        response.sendRedirect("lista.jsp");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id_pacote = Long.parseLong(request.getParameter("id_pacote"));
        String data_partida = request.getParameter("data_partida");
        String descricao = request.getParameter("descricao");
        Integer duracao = Integer.parseInt(request.getParameter("duracao"));
        Float valor = Float.parseFloat(request.getParameter("valor"));
        
        Long agenciaId = Long.parseLong(request.getParameter("id_agencia"));
        Usuario agencia = new UsuarioDAO().getAgenciaByID(agenciaId);
        Long destinoId = Long.parseLong(request.getParameter("id_destino"));
        destino destino = new DestinoDAO().get(destinoId);
        pacote pacote = new pacote(id_pacote, data_partida, duracao, valor, descricao, agencia, destino);
        dao.update(pacote);
        response.sendRedirect("lista.jsp");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id_pacote = Long.parseLong(request.getParameter("id_pacote"));

        pacote pacote = new pacote(id_pacote);
        dao.delete(pacote);
        response.sendRedirect("lista.jsp");
    }
    
}
