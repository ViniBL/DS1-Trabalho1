package br.ufscar.dc.dsw.controller;

//import br.ufscar.dc.dsw.dao.ClienteDAO;
//import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
//import br.ufscar.dc.dsw.domain.cliente;
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

@WebServlet(urlPatterns = "/admin/clientes/*")
public class ClienteController extends HttpServlet {

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
        List<Usuario> listaClientes = new ArrayList<>();
        listaClientes = dao.getAllCliente();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/lista.jsp");
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id_cliente"));
        Usuario cliente = dao.getClienteByID(id);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String nome = request.getParameter("nome");
        String email = request.getParameter("login");
        String senha = request.getParameter("senha");
        String papel = "USER";
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String data_nascimento = request.getParameter("data_nascimento");

        Usuario cliente = new Usuario(nome, email, senha, papel, cpf, telefone, sexo, data_nascimento);
        //daoUsuario.insert(Usuario);

        //Long UsuarioId = Long.parseLong(request.getParameter("UsuarioId"));
        //Usuario UsuarioEnd = new UsuarioDAO().getbyLogin(email);
        
        //cliente cliente = new cliente(cpf, telefone, sexo, data_nascimento, UsuarioEnd);
        dao.insertCliente(cliente);
        response.sendRedirect("lista.jsp");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id_cliente = Long.parseLong(request.getParameter("id_cliente"));
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String papel = "USER";
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String data_nascimento = request.getParameter("data_nascimento");
        
        
        Usuario cliente = new Usuario(id_cliente, nome, login, senha, papel, cpf, telefone, sexo, data_nascimento);
        dao.updateCliente(cliente);
        response.sendRedirect("lista.jsp");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id_Cliente = Long.parseLong(request.getParameter("id_cliente"));
        //Long id_usuario = Long.parseLong(request.getParameter("id_usuario"));
       
        Usuario Cliente = new Usuario(id_Cliente);
   
        //Long id_usuario = Cliente.getUsuario().getId();
        //Usuario usuario = new Usuario(id_usuario);

        //daoUsuario.delete(usuario);
        dao.delete(Cliente);
        //daoUsuario.delete(Cliente.getUsuario());
        response.sendRedirect("lista.jsp");
    }
    
}
