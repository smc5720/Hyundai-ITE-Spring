<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">EL을 사용해서 Object 안에 데이터를 출력</div>
	<div class="card-body">
		<p>이름: ${member.name}</p>
		<p>나이: ${member.age}</p>
		<p>직업: ${member.job}</p>
		<p>도시: ${member.city.name}</p>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>