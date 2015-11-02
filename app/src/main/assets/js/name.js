	function nameView(myname, youname)
	{
		
		var inputTxt1 = myname;
		var inputTxt2 = youname;
		
		$(".Name1").text(inputTxt1);
		$(".Name2").text(inputTxt2);

	}


	function htmlWidth()
	{
		var width = Math.floor(document.body.clientWidth * 0.85);
		return width;
	}

	function chartLine1(x, y) {
		var width = htmlWidth();
		var height = width;

		var x_my = 0;
		var y_my = 0;

		if(x < 0){
        	x_my = Math.floor((parseInt(width) / 11) * (5 + parseInt(x)));
        }

        if(x > 0){
        	x_my = Math.floor((parseInt(width) / 11) * (6 + parseInt(x)));
        }

        if(y > 0){
        	y_my = Math.floor((parseInt(height) / 11) * (5 - parseInt(y)));
        }

        if(y < 0){
        	y_my = Math.floor((parseInt(height) / 11) * (6 - parseInt(y)));
        }

        $("#chart_line1").css({
        	"top" : y_my+"px",
        	"left" : x_my+"px"
        });
        //$("#chart_line1").html("내용");

        //document.write(width + ":::" + x + ":::" + y + ":::" + x_my + ":::" + y_my);
	}

	function chartLine2(x, y) {
    	var width = htmlWidth();
        var height = width;

		var x_my = 0;
		var y_my = 0;

		if(x < 0){
        	x_my = Math.floor((parseInt(width) / 11) * (5 + parseInt(x)));
        }

        if(x > 0){
        	x_my = Math.floor((parseInt(width) / 11) * (6 + parseInt(x)));
        }

        if(y > 0){
        	y_my = Math.floor((parseInt(height) / 11) * (5 - parseInt(y)));
        }

        if(y < 0){
        	y_my = Math.floor((parseInt(height) / 11) * (6 - parseInt(y)));
        }

        $("#chart_line2").css({
            "top" : y_my+"px",
        	"left" : x_my+"px"
        });
        //document.write(width + ":::" + x + ":::" + y + ":::" + x_my + ":::" + y_my);

        //$("#chart_line2").html(x_my + ":::" + y_my);
    }

    function type_chart(my_speed, my_control, people_speed, people_control)
    	{
    		var my_total = Math.abs(my_speed * my_control);
    		var my_per_up = parseInt((my_total * 13) / 5);
    		var my_per_down = 65 - my_per_up;
    		var my_type = "";


    		if(my_speed > 0 && my_control > 0) {
    			$("#type1_bg_color").css({"background-color" : "#059be8"});
    			$("#type1_text").css({"color":"#059be8"});
    			$("#type1_text").html("1111");
    		}
    		if(my_speed > 0 && my_control < 0) {
    			$("#type1_bg_color").css({"background-color" : "#923cf2"});
    			$("#type1_text").css({"color":"#923cf2"});
    		}
    		if(my_speed < 0 && my_control > 0) {
    			$("#type1_bg_color").css({"background-color" : "#059be8"});
    			$("#type1_text").css({"color":"#059be8"});
    		}
    		if(my_speed < 0 && my_control < 0) {
    			$("#type1_bg_color").css({"background-color" : "#26ce04"});
    			$("#type1_text").css({"color":"#26ce04"});
    		}


    		if(people_speed > 0 && people_control > 0) {
    			$("#type2_bg_color").css({"background-color" : "#059be8"});
    			$("#type2_text").css({"color":"#059be8"});
    		}
    		if(people_speed > 0 && people_control < 0) {
    			$("#type2_bg_color").css({"background-color" : "#923cf2"});
    			$("#type2_text").css({"color":"#923cf2"});
    		}
    		if(people_speed < 0 && people_control > 0) {
    			$("#type2_bg_color").css({"background-color" : "#059be8"});
    			$("#type2_text").css({"color":"#059be8"});
    		}
    		if(people_speed < 0 && people_control < 0) {
    			$("#type2_bg_color").css({"background-color" : "#26ce04"});
    			$("#type2_text").css({"color":"#26ce04"});
    		}



    		var people_total = Math.abs(people_speed * people_control);
    		var people_per_up = parseInt((people_total * 13) / 5);
    		var people_per_down = 65 - people_per_up;

    		console.log(my_per_up + ":::" + my_per_down + ":::" + people_per_up + ":::" + people_per_down);



    		$("#type1_text").css({"width" : my_per_down+"%"});
    		$("#type1_bg").css({"width" : my_per_up+"%"});

    		$("#type2_text").css({"width" : people_per_down+"%"});
    		$("#type2_bg").css({"width" : "20%"});

    	}
