<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">AOP(관점 지향 프로그래밍)</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">Advice 테스트</div>
			<div class="card-body">
				<a class="btn btn-info btn-sm" href="before">@Before</a> <a
					class="btn btn-info btn-sm" href="after">@After</a> <a
					class="btn btn-info btn-sm" href="afterReturning">@AfterReturning</a>
				<a class="btn btn-info btn-sm" href="afterThrowing">@AfterThrowing</a>
				<a class="btn btn-info btn-sm" href="around">@Around</a>
			</div>
		</div>

		<div class="card">
			<div class="card-header">AOP 예제</div>
			<div class="card-body">
				<a class="btn btn-info btn-sm" href="runtimeCheck">요청 처리 시간 측정</a> <a
					class="btn btn-info btn-sm" href="javascript:boardList()">인증 여부
					확인</a>
				<hr />
				<div>${methodName} 실행 시간: ${howLong}ns</div>
				<hr />
				<div id="boardList"></div>
			</div>
			<script>
			function boardList() {
				$.ajax({
					url: "boardList"
				}).done((data) => {
					if(data.result === "loginNeed") {
						// $("#boardList").html("로그인 필요");
						window.location.href = "login";
					} else {
						$("#boardList").html(data);
					}
				});
			}
			</script>
		</div>

		<div class="card mt-2">
			<div class="card-header">form을 통한 login 처리</div>
			<div class="card-body">
				<c:if test="${sessionMid == null}">
					<a href="login" class="btn btn-success btn-sm">로그인 폼 요청</a>
				</c:if>
				<c:if test="${sessionMid != null}">
					<a href="logout" class="btn btn-warning btn-sm">로그아웃</a>
				</c:if>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>