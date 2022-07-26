package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.pacoteDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.DestinoDAO;
import br.ufscar.dc.dsw.dao.PacotesAdquiridosDAO;
import br.ufscar.dc.dsw.domain.pacote;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.destino;
import br.ufscar.dc.dsw.domain.pacotes_adquiridos;

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
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/usuario/pacotesAdquiridos/*")
public class PacotesAdquiridosController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private pacoteDAO dao;
    private PacotesAdquiridosDAO daoPA;

    @Override
    public void init() {
        dao = new pacoteDAO();
        daoPA = new PacotesAdquiridosDAO();
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
                case "/proposta":
                    apresentaFormCompra(request, response);
                    break;
                case "/compra":
                    compra(request, response);
                    break;
                case "/erro":
                    erroCompra(request, response);
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
            List<pacotes_adquiridos> listaPacotesAdquiridos = new ArrayList<>();
            Usuario cliente = (Usuario) request.getSession().getAttribute("usuarioLogado");
            listaPacotesAdquiridos = daoPA.getAllCliente(cliente.getId());
            List<pacote> listaPacotes = new ArrayList<>();
            for(int i = 0; i < listaPacotesAdquiridos.size(); i++){
                pacote pacote = new pacoteDAO().get(listaPacotesAdquiridos.get(i).getId_pacote_adquirido());
                listaPacotes.add(i, pacote);
            }
            request.setAttribute("listaPacotes", listaPacotes);
            request.setAttribute("listaPacotesAdquiridos", listaPacotesAdquiridos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/usuario/listaAdquiridos.jsp");
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
    private void apresentaFormCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id_pacote"));
        pacote pacote = dao.get(id);
        request.setAttribute("pacote", pacote);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/usuario/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void compra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String status = "ADQUIRIDO";
        Long id_pacote = Long.parseLong(request.getParameter("id_pacote"));
        //Long id_cliente = Long.parseLong(request.getParameter("id_cliente"));
        Usuario cliente = (Usuario) request.getSession().getAttribute("usuarioLogado");
        //Usuario cliente = new UsuarioDAO().getClienteByID(id_cliente);
        pacote pacote = new pacoteDAO().get(id_pacote);

        pacotes_adquiridos pacoteAdquirido = new pacotes_adquiridos(status, cliente, pacote);
        daoPA.insert(pacoteAdquirido);
        response.sendRedirect("lista.jsp");

    }
    
    protected void erroCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Erro erros = new Erro();
        erros.add("Valor de proposta insuficiente!");
        erros.add("Faça uma proposta no mínimo equivalente ao valor do pacote.");
        request.setAttribute("mensagens", erros);
        RequestDispatcher rd = request.getRequestDispatcher("/logado/usuario/erroCompra.jsp");
        rd.forward(request, response); 
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("agencias", getAgencias());
        //request.setAttribute(name: "destinos", getDestinos());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id_pacote"));
        pacote pacote = dao.get(id);
        request.setAttribute("pacote", pacote);
        request.setAttribute("agencias", getAgencias());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/formulario.jsp");
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
