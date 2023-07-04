/**
 * reply module
 */
console.log("Reply Module ..");

let replyService = {
	add : (reply, callback, error) => {
		$.ajax({
			type : "POST",
			url : "/replies/new",
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : callback,
			error : error
		});
	},
	getList : (param, callback, error) => {
		let bno = param.bno;
		// var v = a || b	: a에 값이 있으면 a, 없으면 b를 v에 할당
		let page = param.page || 1;
		
		$.getJSON("/replies/pages/" + bno + "/" + page,
			// getJSON으로 나온 데이터를 callback에게 줌
			function(list){
				if(callback){
					callback(list);
				}
			}
		);
	},
	remove : (rno, callback, error) => {
		$.ajax({
			type : "DELETE",
			url : "/replies/" + rno,
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			}
		});
	},
	update : (reply, callback, error) => {
		$.ajax({
			type : "PATCH",
			url : "/replies/" + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			}
		});
	},
	get : (rno, callback, error) => {
		$.get("/replies/" + rno,
			function(result){
				if(callback){
					callback(result);
				}
		});
	}
};























