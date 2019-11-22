var app = app || {}

app = (()=>{
	
	let _, js
	
	let run =x=>{
		$.getScript(x+'/resources/js/cmm/router.js',()=>{
				$.extend(new Session(x))
				onCreate()
		})
		/*$.getScript(x+'/resources/js/cmm/rounter.js',
				()=>{$.extend(new Session(x))})*/
	}
	
	let init =()=>{
		_ = $.ctx()
		js = $.js()
	}
	
	let onCreate =()=>{
		init()
		setContentView()
	}
	
	let setContentView =()=>{
		/*<style>
		#tab {
		  font-family: arial, sans-serif;
		  border-collapse: collapse;
		  width: 100%;
		}

		#tab td, th {
		  border: 1px solid #dddddd;
		  text-align: left;
		  padding: 8px;
		}

		#tab tr:nth-child(even) {
		  background-color: #dddddd;
		}
		</style>*/
		
		$('<table id="tab"><tr></tr></table>')
		.css({
			width : '80%',
			height : '800px',
			border : '1px solid black',
			margin : '0 auto'
			})
		.appendTo('body')
		
		$('<td/>',{
			id : 'left'
		}).css({
			width : '20%',
			height : '100%',
			border : '1px solid black',
			'vertical-align' : 'top'
			})
		.appendTo('tr')
		$('<td/>',{
			id : 'right'
		}).css({
			width : '80%',
			height : '100%',
			border : '1px solid black'
			})
		.appendTo('tr')
		$.each(['Naver','CGV','Bugs'],(i,j)=>{
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
				alert($(this).text()+'클릭')
				switch($(this).text()){
				case 'Naver':
					$.getJSON(_+'/crawler/naver',d=>{
						alert(d.msg)
					})
					break
				case 'CGV':
					$.getJSON(_+'/crawler/cgv',d=>{
						alert(d.msg)
					})
					break
				case 'Bugs':
					$.getJSON(_+'/crawler/bugs',d=>{
						alert(d.msg)
					})
					break
				}
			})
		})
	}
	
	return {run}
})();