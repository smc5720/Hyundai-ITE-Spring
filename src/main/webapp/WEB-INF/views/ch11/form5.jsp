<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">국제화를 적용한 폼</div>
	<div class="card-body">
		<form:form modelAttribute="member" method="post" action="form5">
			<div class="form-group">
				<label for="mid"><spring:message code="join.form.mid" /></label>
				<form:input type="text" class="form-control" path="mid"></form:input>
			</div>
			<div class="form-group">
				<label for="mname"><spring:message code="join.form.mname" /></label>
				<form:input type="text" class="form-control" path="mname"></form:input>
			</div>
			<div class="form-group">
				<label for="mpassword"><spring:message
						code="join.form.mpassword" /></label>
				<form:password class="form-control" path="mpassword"></form:password>
			</div>
			<div class="form-group">
				<label for="mnation"><spring:message
						code="join.form.mnation" /></label>
				<form:input type="text" class="form-control" path="mnation"></form:input>
			</div>
			<button type="submit" class="btn btn-primary btn-sm">
				<spring:message code="join.form.submit" />
			</button>
		</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>