<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">Login Form</div>
	<div class="card-body">
		<c:if test="${error != null}">
			<div class="alert alert-danger mb-2" role="alert">${error}</div>
		</c:if>

		<form method="post" action="login">
			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">mid</span>
				</div>
				<input type="text" name="mid" class="form-control">
			</div>
			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">mpassword</span>
				</div>
				<input type="password" name="mpassword" class="form-control">
			</div>
			<div class="mt-2">
				<input class="btn btn-info btn-sm" type="submit" value="로그인" /> <input
					class="btn btn-info btn-sm" type="reset" value="다시 작성" /> <a
					class="btn btn-info btn-sm" href="content">취소</a>
			</div>
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>