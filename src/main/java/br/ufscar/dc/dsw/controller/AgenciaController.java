package br.ufscar.dc.dsw.controller;

//import br.ufscar.dc.dsw.dao.agenciaDAO;
//import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
//import br.ufscar.dc.dsw.domain.agencia;
//import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Usuario;
//import br.ufscar.dc.dsw.domain.destino;
//import br.ufscar.dc.dsw.controller.UsuarioController;

import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
//import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/agencias/*")
public class AgenciaController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private UsuarioDAO dao;
    //private UsuarioDAO daoUsuario;

    @Override
    public void init() {
        dao = new UsuarioDAO();
        //daoUsuario = new UsuarioDAO();
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
        List<Usuario> listaAgencias = new ArrayList<>();
        listaAgencias = dao.getAllAgencia();
        request.setAttribute("listaAgencias", listaAgencias);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/listaA.jsp");
        dispatcher.forward(request, response);
        
    }
/*
    private Map<Long, String> getUsuarios() {
        Map <Long, String> usuarios = new HashMap<>();
        for (Usuario usuario: new UsuarioDAO().getAll()) {
            usuarios.put(usuario.getId(), usuario.getNome());
        }
        return usuarios;
    }
    
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
        //request.setAttribute("usuarios", getUsuarios());
        //request.setAttribute(name: "destinos", getDestinos());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/formularioA.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id_agencia"));
        Usuario agencia = dao.getAgenciaByID(id);
        request.setAttribute("agencia", agencia);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/formularioA.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String nome = request.getParameter("nome");
        String email = request.getParameter("login");
        String senha = request.getParameter("senha");
        String papel = "AGENCIA";
        String cnpj = request.getParameter("cnpj");
        String descricao = request.getParameter("descricao");
        

        Usuario agencia = new Usuario(nome, email, senha, papel, cnpj, descricao);
        //daoUsuario.insert(Usuario);

        //Long UsuarioId = Long.parseLong(request.getParameter("UsuarioId"));
        //Usuario UsuarioEnd = new UsuarioDAO().getbyLogin(email);
        
        //agencia agencia = new agencia(cpf, telefone, sexo, data_nascimento, UsuarioEnd);
        dao.insertAgencia(agencia);
        response.sendRedirect("listaA.jsp");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id_agencia = Long.parseLong(request.getParameter("id_agencia"));
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String papel = "USER";
        String cnpj = request.getParameter("cnpj");
        String descricao = request.getParameter("descricao");
                
        
        Usuario agencia = new Usuario(id_agencia, nome, login, senha, papel, cnpj, descricao);
        dao.updateAgencia(agencia);
        response.sendRedirect("listaA.jsp");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id_agencia = Long.parseLong(request.getParameter("id_agencia"));
        //Long id_usuario = Long.parseLong(request.getParameter("id_usuario"));
       
        Usuario agencia = new Usuario(id_agencia);
   
        //Long id_usuario = agencia.getUsuario().getId();
        //Usuario usuario = new Usuario(id_usuario);

        //daoUsuario.delete(usuario);
        dao.delete(agencia);
        //daoUsuario.delete(agencia.getUsuario());
        response.sendRedirect("listaA.jsp");
    }
    
}
