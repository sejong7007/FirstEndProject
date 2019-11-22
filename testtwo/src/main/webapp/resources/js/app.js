var app = app || {}

app = (()=>{
	
	let _, js
	
	let run =x=>{
		
		$.when(
			$.getScript(x+'/resources/js/cmm/router.js',()=>{
				$.extend(new Session(x))
			}),
			$.getScript(x+'/resources/js/cmm/pop.js')
		).done(()=>{
			onCreate()
		}).fail()
		
	}
	
	let init =()=>{
		_ = $.ctx()
		js = _+$.js()
	}
	
	let onCreate =()=>{
		init()
		$(pop.view()).appendTo('#wrapper')
		pop.open()
		setContentView()
	}
	
	
	let setContentView =()=>{
		$('<table id="tab"><tr></tr></table>')
		.css({
			width : '80%',
			height : '800px',
			border : '1px solid black',
			margin : '0 auto'
		})
		.appendTo('body')
		
		$('<td/>',{id : 'left'})
		.css({
			width : '20%',
			height : '100%',
			border : '1px solid black',
			'vertical-align' : 'top'
		})
		.appendTo('tr')
		
		$('<td/>',{id : 'right'})
		.css({
			width : '80%',
			height : '100%',
			border : '1px solid black',
			'vertical-align' : 'top'
		})
		.appendTo('tr')
		
		$.each(['Naver','CGV','Bugs','test'],(i,j)=>{
			$('<div id="selectbtn'+i+'"/>')
			.text(j)
			.css({
				width : '100%',
				height : '100px',
				border : '1px solid black',
				'text-align' : 'center',
				'vertical-align': 'middle'
			})
			.appendTo('#left')
			.click(function(){
				$(this).css({'background-color':'yellow'})
				$(this).siblings().css({'background-color':'white'})
				alert($(this).text()+'클릭')
				switch($(this).text()){
				case 'Naver':
					$.getJSON(_+'/crawler/naver', d=>{
						$('#right').empty()
						$.each(d,(i,j)=>{
							$('<div/>')
							.css({
								width : '40%',
								height : '40%',
								border : '3px solid red',
								float : 'left',
								'text-align': 'center'
							})
							.html('<h1>'+ j.origin +'<h1><h4>'+ j.trans +'</h4>')
							.appendTo('#right')							
						})
					})
					break
				case 'CGV':
					$.getJSON(_+'/crawler/cgv', d=>{
						$('#right').empty()
						$.each(d,(i,j)=>{
							$('<div/>')
							.css({
								width : '30%',
								height : '500px',
								border : '3px solid red',
								float : 'left',
								'text-align': 'center'
							})
							.html('<h1>'+ j.title +'<h1>'+
								  '<img src="'+j.photo+'" width="80%" height="340px">'+
								  '<h4>'+j.info+j.percent+'</h4>')
							.appendTo('#right')							
						})
					})
					break
				case 'Bugs':
					bugsGo({page:1})
					break
				case 'test':
					test()
				}
			})
		})
	}
	
	let bugsGo =x=>{
		$.getJSON(_+'/crawler/bugs/'+x.page, d=>{
			let pager = d.pager
			let list = d.list
			$('#right').empty()
			$('<table id="content"><tr id="head"></tr></table>')
			.css({
				width : '99%',
				border : '3px solid blue',
				margin : 'auto'
			})
			.appendTo('#right')
			$.each(['No','앨범','제목','가수'],(i,j)=>{
				$('<th/>')
				.html('<b>'+j+'</b>')
				.css({
					width : '25%',
					height : '70px',
					border : '3px solid black'
				})
				.appendTo('#head')
			})
			$.each(list , (i,j)=>{
				$('<tr><td>'+j.seq+'</td><td><img src="'+j.photo+'"/></td><td>'+j.title+'</td><td>'+j.content+'</td></tr>')
				.css({
					width : '25%',
					height : '100%',
					border : '3px solid black',
					'text-align' : 'center',
				})
				.appendTo('#content tbody')
			})
			
			$('content tr td').css({border : '1px solid black'})
			
			$('<div/>', {id : 'pagination'})
			.css({
				width : '30%',
				height : '100px',
				margin : '20px auto'
			})
			.appendTo('#right')
			
			if(pager.existPrev){
	               $('<span>Previous</span>')
	               .css({
	            	   display: 'inline-block',
	            	   margin : '0 auto'
	               })
	               .appendTo('#pagination')
	               .click(e=>{
	                   e.preventDefault()
	                   alert('이전 블럭으로 이동'+ pager.prevBlock)
	                   bugsGo({page : pager.prevBlock+1})
	               })
	        	}
			
			let i = 0
            for( i=pager.startPage+1; i<pager.endPage+2 ; i++) {
                  if(i==pager.nowPage+1){
                	  $('<span/>')
						.css({
							display: 'inline-block',
							width : '30px',
							height : '30px',
							border : '1px solid blue',
			            	margin : '0 auto'
						})
						.text(i)
						.appendTo('#pagination')
						.addClass('active')
                  }else {
                	$('<span/>')
					.css({
						display: 'inline-block',
						width : '30px',
						height : '30px',
						border : '3px solid black',
		            	margin : '0 auto'
					})
					.text(i)
					.appendTo('#pagination')
					.click(function(){
						alert($(this).text()+'페이지 이동')
						bugsGo({page : $(this).text()}) 
                     })
              }
            }
			
			if(pager.existNext){
	               $('<span>Next</span>')
	               .css({
	            	   display: 'inline-block',
	            	   margin : '0 auto'
	               })
	               .appendTo('#pagination')
	               .click(e=>{
	                   e.preventDefault()
	                   alert('다음 블럭으로 이동'+ pager.nextBlock)
	                   bugsGo({page : pager.nextBlock+1})              
	               })
	        	}
		})
	}
	
	let test =()=>{
		$('body').empty()
		$('<table id="testTab"></table>')
		.css({
			width : '100%',
			height : '100%',
			margin : '0 auto'
		})
		.appendTo('body')
		$('<tr/>',{id : 'testTop'})
		.css({
			width : '100%',
			height : '80px',
			'background-color' : '#FAFAD2'
		})
		.appendTo('#testTab')
		$('<tr/>',{id : 'testDown'})
		.css({
			width : '100%',
			height : '100%'
		})
		.appendTo('#testTab')
		$('<td/>',{id : 'testLeft'})
		.css({
			width : '20%',
			height : '800px',
			'background-color' : '#D8BFD8'
		})
		.appendTo('#testDown')
		$('<td/>',{id : 'testRight'})
		.css({
			width : '80%',
			height : '800px',
			'background-color' : '#FAFAD2'
		})
		.appendTo('#testDown')
		
	}
	
	return {run}
	
})();