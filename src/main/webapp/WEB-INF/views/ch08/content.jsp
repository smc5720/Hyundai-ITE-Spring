<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">Session Support</div>
	<div class="card-body">
		<div class="card mt-2">
			<div class="card-header">세션 원리 : JSESSIONID</div>
			<div class="card-body">
				<p>서버 : 세션 객체 생성 -> JSESSIONID 쿠키 발행</p>
				<p>브라우저: JSESSIONID 쿠키 전송 -> 세션 객체 찾음 -> 세션 이용</p>
				<a href="javascript:saveData()" class="btn btn-info">세션에 데이터 저장</a>
				<a href="javascript:readData()" class="btn btn-secondary">세션에
					데이터 읽기</a>
			</div>
		</div>
		<script type="text/javascript">
         function saveData(){
            $.ajax({
               url: "saveData",
               data: {name: "홍길동"},
            })
            .done((data) => {
               console.log(data);
            })
         } 
         
         function readData(){
            $.ajax({
               url: "readData",
            })
            .done((data) => {
               console.log(data.name);               
            })
         } 
      </script>
		<div class="card mt-2">
			<div class="card-header">for을 통한 login 처리</div>
			<div class="card-body">
				<c:if test="${sessionMid == null}">
					<a href="login" class="btn btn-success">로그인 폼 요청</a>
				</c:if>
				<c:if test="${sessionMid != null}">
					<a href="logout" class="btn btn-warning">로그아웃</a>
				</c:if>
			</div>
		</div>

		<div class="card mt-2">
			<div class="card-header">AJAX을 통한 login 처리</div>
			<div class="card-body">
				<c:if test="${sessionMid == null}">
					<form>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">mid</span>
							</div>
							<input id="mid" type="text" name="mid" class="form-control">
							<span id="mid-error" class="error"></span>
						</div>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">mpassword</span>
							</div>
							<input id="mpassword" type="password" name="mpassword"
								class="form-control"> <span id="mpassword-error"
								class="error"></span>
						</div>
					</form>
				</c:if>
				<div class="input-group mt-2">
					<c:if test="${sessionMid == null}">
						<a href="javascript:login()" class="btn btn-success">로그인</a>
					</c:if>
					<c:if test="${sessionMid != null}">
						<a href="javascript:logout()" class="btn btn-warning">로그아웃</a>
					</c:if>
				</div>
				<script type="text/javascript">
               function login(){
                  const mid = $("#mid").val();
                  const mpassword = $("#mpassword").val();
                  $.ajax({
                     url: "loginAjax",
                     data: {mid, mpassword},
                     method: "post",
                  })
                  .done((data)=>{
                     //data = {result: "success"}
                     //data = {result: "wrongMid"}
                     //data = {result: "wrongMpassword"}
                     
                     const midErr = $("#mid-error");
                     const mpwdErr = $("#mpassword-error");
                     
                     midErr.html("");
                     mpwdErr.html("");
                     
                     if(data.result === "success"){               
                        location.reload();
                     }else if(data.result === "wrongMid"){
                        midErr.html("아이디가 잘못됨");
                     }else if(data.result === "wrongMpassword"){
                        mpwdErr.html("비밀번호가 잘못됨");
                     }
                  })
               }
               
               function logout(){
                   $.ajax({
                      url: "logoutAjax"
                   })
                   .done((data)=>{            
                      location.reload();
                   })
                }
            </script>
			</div>
		</div>
		<div class="card mt-2">
			<div class="card-header">@SessionAttributes를 이용한 다단계 입력 처리</div>
			<div class="card-body">
				<a href="inputStep1" class="btn btn-info btn-sm">1단계 입력</a>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>