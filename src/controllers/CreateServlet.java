package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**--doGetではなくdoPostなのはトークンを送っているから、直接CreateServlet(edit)にアクセスされないように*/
        String _token = request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();
            /**新しいメッセージのインスタンス*/
            Message m = new Message();
            /**getParameter()名前を引数にすると値を受け取る*/
            String title = request.getParameter("title");
            m.setTitle(title);

            String content = request.getParameter("content");
            m.setContent(content);

            /*System.currentTimeMillis()現在の時間をミリ秒で返す*/
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            m.setCreated_at(currentTime);
            m.setUpdated_at(currentTime);

            em.getTransaction().begin();
            em.persist(m); /*新規追加*/
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/index"); /*受け取ってDBにセーブしたらindexへリダイレクト*/

        }

    }
}
