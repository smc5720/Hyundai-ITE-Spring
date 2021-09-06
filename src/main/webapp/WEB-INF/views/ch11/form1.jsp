<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">DTO 객체(Command Object)와 폼 연결</div>
	<div class="card-body">
		<form:form method="post" modelAttribute="member">
			<div class="form-group">
				<label for="mid">ID</label>
				<form:input type="text" class="form-control" id="mid" path="mid"></form:input>
			</div>
			<div class="form-group">
				<label for="mname">Name</label>
				<form:input type="text" class="form-control" id="mname" path="mname"></form:input>
			</div>
			<div class="form-group">
				<label for="mpassword">Password</label>
				<form:password class="form-control" id="mpassword" path="mpassword"></form:password>
			</div>
			<div class="form-group">
				<label for="mnation">Nation</label>
				<form:input type="text" class="form-control" id="mnation"
					path="mnation"></form:input>
			</div>
			<button type="submit" class="btn btn-primary btn-sm">Submit</button>
		</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>