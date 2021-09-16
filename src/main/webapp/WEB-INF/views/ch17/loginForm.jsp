<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
   <div class="card-header">
      로그인 폼
   </div>
   <div class="card-body">
      <form method="post" action="${pageContext.request.contextPath}/login">
         <div class="form-group">
             <label for="mid">Member ID</label>
             <input type="text" class="form-control" id="mid" name="mid">
         </div>
         <div class="form-group">
            <label for="mpassword">Member Password</label>
            <input type="password" class="form-control" id="mpassword" name="mpassword">
         </div>
         <button type="submit" class="btn btn-info btn-sm mt-2">로그인</button>
      </form>   
   </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>