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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            //セッションスコープからメッセージのIDを取得して
            //該当のIDのメッセージ1件のみをDBから取得
            Message m = em.find(Message.class, (Integer)(request.getSession().getAttribute("message_id")));

            //getParameter()名前を引数にすると値を受け取る
            //new.jspで呼び出してる_form.jspで入力した値をMessageのフィールドに代入
            //フォームの内容を各フィールドに上書き
            String title = request.getParameter("title");
            m.setTitle(title);

            String content = request.getParameter("content");
            m.setContent(content);

            //System.currentTimeMillis()現在の時間をミリ秒で返す、それをMessageのフィールドに代入
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            m.setUpdated_at(currentTime);  //更新日時のみ上書き

            //DBを更新
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();

            //セッションスコープ上の不要になったデータを削除
            request.getSession().removeAttribute("message_id");

            response.sendRedirect(request.getContextPath() + "/index"); //indexへリダイレクト
        }
    }
}
