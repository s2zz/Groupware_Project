$("#left_item").load("/board/sideBar");

$(document).on("click","#toWriteContentsBoardBtn",function(){
	location.href="/board/toWriteContentsBoard";
})

$(document).on("click","#toEditBoardBtn",function(){
	location.href="/board/toMk_board";
});

$(document).on("click","#toFavoriteBoardBtn",function(){
	location.href="/board/toFavoriteBoard";
});

$(document).on("click","#toEditBoardBtn",function(){
	location.href="/board/toEditBoard";
})


