<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<label>タイトル<br />
<!--new、editの時に使う。edit用のc:outタグはnewの時カラだとエラーが出るので、
NewServlet.javaでおまじないで文字数0のデータでインスタンスを作ってリクエストスコープに入れてある -->
<input type="text" name="title" value="${message.title}" />
</label>
<br /><br />
<label>メッセージ<br />
<input type="text" name="content" value="${message.content}" />
</label>
<br /><br />
<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>