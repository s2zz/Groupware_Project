<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
	<link rel="stylesheet" href="/css/commons/body_form/left_form/body_form_default.css" />
	<link rel="stylesheet" href="/css/commons/topForm.css" />
    <link rel="stylesheet" href="/css/board/board_contents_write.css" />
    <script src="js/commons/body_form/body_form_default.js"></script>
</head>
<body>
	 <div class="top">TOP</div>
    <div class="body_form">
        <div class="left_item" id="left_item">

        </div>
        <div class="right_item">
            <div class="content_tab">
                <form>
                    <div class="board_title_box">
                        <div class="board_title_text">게시판</div>
                        <div>
                            <select>
                                <option selected>게시판선택</option>
                                <option>자유게시판</option>
                                <option>사내게시판</option>
                            </select>
                        </div>
                        <div>
                            <select>
                                <option selected>말머리 선택</option>
                                <option>[중요]</option>
                            </select>
                        </div>
                        <div></div>
                    </div>
                    <div class="board_contents_title_box">
                        <div class="info_text_title">제목</div>
                        <div>
                            <input type="text" placeholder="제목을 입력하세요">
                        </div>
                        <div>
                            <input type="checkbox"> &nbsp;공지로 등록
                        </div>
                    </div>
                    <div class="board_contents_file_box">
                        <div class="file_box_text">파일 첨부</div>
                        <div class="file_box_input_file">
                            <div><input type="file">X</div>
                            <div><input type="file">X</div>
                        </div>
                    </div>
                    <div class="board_contents_box">
                        <textarea id="summernote"></textarea>
                    </div>
                    <div class="board_servey">
                        <div>설문 여부</div>
                        <div>
                            <div><input type="radio"> 사용</div>
                            <div><input type="radio"> 사용 안 함</div>
                        </div>
                    </div>
                    <div class="buttons">
                        <button>취소</button>
                        <button>등록</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

	<script>
    	$(document).ready(function() {
			$("#left_item").load("/board/sideBar");
			
			$("#summernote").summernote({
	            width: 1500,
	            height: null,
	            minHeight: 380,
	            maxHeight: null,
	            focus: true,
	            lang: "ko-KR",
	            placeholder: '내용을 입력하세요 ( 1000 글자 이하 )',
	            callbacks: {
	                onImageUpload: function (files) {
	                    let formData = new FormData();
	                    for (let i = 0; i < files.length; i++) {
	                        formData.append("fileList", files[i]);
	                    }

	                    $.ajax({
	                        url: "/file/upload",
	                        method: "post",
	                        data: formData,
	                        processData: false, // 인코딩 처리 금지 ( processData, contentType : false = multipart/form-data)
	                        contentType: false, // 파일 타입 그대로 유지

	                    }).done(function (resp) {
	                        console.log(resp);
	                        for (let i = 0; i < files.length; i++) {
	                            let img = $("<img>");
	                            img.attr("type", "file");
	                            img.attr("src", resp[i]);
	                            $("#summernote").summernote('insertNode', img[0]);
	                        }

	                    })

	                }
	            }
	        });
		});
    </script>
</body>
</html>