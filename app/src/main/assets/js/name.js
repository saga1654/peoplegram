	function nameView(myname, youname)
	{
		
		var inputTxt1 = myname+"님";
		var inputTxt2 = youname+"님";
		
		$(".Name1").text(inputTxt1);
		$(".Name2").text(inputTxt2);

	}


	$(function() {
		nameView("ME", "YOU");
	});