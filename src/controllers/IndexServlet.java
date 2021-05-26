package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
//DBUtilをインポートする
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        List<Message> messages = em.createNamedQuery("getAllMessages", Message.class).getResultList();

        em.close();

        request.setAttribute("messages", messages);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/index.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    /**protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        //Messageクラスで定義したJPQLをcreateNameQueryの引数にすると、DBへの問い合わせができる。
        //その結果をgetResultList()でリスト形式で取得
        List<Message> messages = em.createNamedQuery("getAllMessages", Message.class).getResultList();

        em.close();
        //messagesリストをスコープに置く、
        request.setAttribute("messages", messages);

       //ビューとなるJSPを指示して表示する
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/index.jsp");
        rd.forward(request, response); //doGetやdoPostの引数と同じインスタンスを引数にする
    }*/
}
