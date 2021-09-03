<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">FileUpload & FileDownload</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">Form 태그를 이용한 FileUpload</div>
			<div class="card-body">
				<form method="post" enctype="multipart/form-data"
					action="fileupload">
					<div class="form-group">
						<label for="desc">Title</label> <input type="text"
							class="form-control" id="title" name="title" placeholder="파일 제목">
					</div>
					<div class="form-group">
						<label for="desc">File Description</label> <input type="text"
							class="form-control" id="desc" name="desc" placeholder="파일 설명">
					</div>
					<div class="form-group">
						<label for="attach">Example file input</label> <input type="file"
							class="form-control-file" name="attach" id="attach" multiple>
					</div>
					<button class="btn btn-info btn-sm" type="submit">Form 파일
						업로드</button>
					<a href="javascript:fileupload()" class="btn btn-info btn-sm">AJAX
						파일 업로드</a>
				</form>
			</div>
			<script>
				function fileupload() {
					// 입력된 정보를 얻기
					const title = $("#title").val();
					const desc = $("#desc").val();
					const attach = document.querySelector("#attach").files[0];
					
					// Multipart/form-data
					const formData = new FormData();
					formData.append("title", title);
					formData.append("desc", desc);
					formData.append("attach", attach);
					
					// Ajax로 서버 전송
					$.ajax({
						url: "fileuploadAjax",
						method: "post",
						data: formData,
						cache: false,
						processData: false,
						contentType: false
					}).done((data) => {
						console.log(data);
						if(data.result === "success") {
							window.alert("파일 전송이 성공됨");
						}
					});
				}
			</script>
		</div>
		<div class="card">
			<div class="card-header">File Download</div>
			<div class="card-body">
				<a href="filedownload?fileNo=1" class="btn btn-info btn-sm">파일
					다운로드</a>
				<hr />
				<img src="filedownload?fileNo=1" width="200px" />
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>