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
    		var my_per = parseInt((my_total * 4));
    		var my_type = "";

			$("#type1_title").css({"color":"#fff","background-color":"#32354d","font-size":"0.85em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});	

			$('#type1_per').jQuerySimpleCounter({
            	end:my_per,
            	duration: 2000
            });

    		if(my_speed > 0 && my_control > 0) {
    			//$("#type1_bg_color").css({"background-color" : "#ff8a55"});
    			
				$("#type1_per").css({"color":"#ff8a55","background-color":"#4d4d4d","font-size":"2.0em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});
				$("#type1_text").css({"color":"#ff8a55","background-color":"#4d4d4d","font-size":"0.85em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});

    			if(my_speed <= 5 && my_control <= 1){
    			   	if(my_speed == 1 && my_control == 1){
    			   		$("#type1_text").html("분석형,표출형 특징을 가진 주도형");
    			   	}else{
    					$("#type1_text").html("표출형 특징을 가진 주도형");
    				}
    			}else if(my_speed <= 1 && my_control <= 5){
    				$("#type1_text").html("분석형 특성을 가진 주도형");
    			}else{
    				$("#type1_text").html("확실한 주도형");
    			}
    		}
    		if(my_speed > 0 && my_control < 0) {
    			//$("#type1_bg_color").css({"background-color" : "#37afec"});
    			
				$("#type1_per").css({"color":"#aa64f8","background-color":"#4d4d4d","font-size":"2.0em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});
				$("#type1_text").css({"color":"#aa64f8","background-color":"#4d4d4d","font-size":"0.85em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});


    			if(my_speed <= 5 && my_control >= -1){
    				if(my_speed == 1 && my_control == -1){
						$("#type1_text").html("주도형,우호형 특징을 가진 표출형");
					}else{
						$("#type1_text").html("주도형 특징을 가진 표출형");
					}
				}else if(my_speed <= 1 && my_control >= -5){
					$("#type1_text").html("우호형 특징을 가진 표출형");
				}else{
					$("#type1_text").html("유쾌한 표출형");
				}
    		}
    		if(my_speed < 0 && my_control > 0) {
    			//$("#type1_bg_color").css({"background-color" : "#52d935"});
    			
				$("#type1_per").css({"color":"#37afec","background-color":"#4d4d4d","font-size":"2.0em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});
				$("#type1_text").css({"color":"#37afec","background-color":"#4d4d4d","font-size":"0.85em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});

    			if(my_speed >= -5 && my_control <= 1){
    				if(my_speed == -1 && my_control == 1){
						$("#type1_text").html("주도형,우호형 특징을 숨긴 분석형");
					}else{
						$("#type1_text").html("우호형 특징을 가진 분석형");
					}
				}else if(my_speed >= -1 && my_control <= 5){
					$("#type1_text").html("주도형 특징을 숨긴 분석형");
				}else{
					$("#type1_text").html("철저한 분석형");
				}
    		}
    		if(my_speed < 0 && my_control < 0) {
    			//$("#type1_bg_color").css({"background-color" : "#aa64f8"});
    			
				$("#type1_per").css({"color":"#52d935","background-color":"#4d4d4d","font-size":"2.0em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});
				$("#type1_text").css({"color":"#52d935","background-color":"#4d4d4d","font-size":"0.85em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});

    			if(my_speed >= -5 && my_control >= -1){
					if(my_speed == -1 && my_control == -1){
						$("#type1_text").html("분석형,표출형 특징을 가진 우호형");
					}else{
						$("#type1_text").html("분석형 특징을 가진 우호형");
					}
				}else if(my_speed >= -1 && my_control >= -5){
					$("#type1_text").html("표출형 특징을 가진 우호형");
				}else{
					$("#type1_text").html("스마일 우호형");
				}
    		}

			
			var people_total = Math.abs(people_speed * people_control);
    		var people_per = parseInt((people_total * 4));

			$("#type2_title").css({"color":"#fff","background-color":"#32354d","font-size":"0.85em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});
			$("#type2_per").html(people_per+"%");

			$('#type2_per').jQuerySimpleCounter({
            	end:people_per,
                duration: 2000
            });
				
    		
    			if(people_speed > 0 && people_control > 0) {
					//$("#type1_bg_color").css({"background-color" : "#ff8a55"});

					$("#type2_per").css({"color":"#ff8a55","background-color":"#4d4d4d","font-size":"2.0em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});
					$("#type2_text").css({"color":"#ff8a55","background-color":"#4d4d4d","font-size":"0.85em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});

					if(people_speed <= 5 && people_control <= 1){
						if(people_speed == 1 && people_control == 1){
							$("#type2_text").html("분석형,표출형 특징을 가진 주도형");
						}else{
							$("#type2_text").html("표출형 특징을 가진 주도형");
						}
					}else if(people_speed <= 1 && people_control <= 5){
						$("#type2_text").html("분석형 특성을 가진 주도형");
					}else{
						$("#type2_text").html("확실한 주도형");
					}
				}
				if(people_speed > 0 && people_control < 0) {
					//$("#type1_bg_color").css({"background-color" : "#37afec"});

					$("#type2_per").css({"color":"#aa64f8","background-color":"#4d4d4d","font-size":"2.0em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});
					$("#type2_text").css({"color":"#aa64f8","background-color":"#4d4d4d","font-size":"0.85em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});


					if(people_speed <= 5 && people_control >= -1){
						if(people_speed == 1 && people_control == -1){
							$("#type2_text").html("주도형,우호형 특징을 가진 표출형");
						}else{
							$("#type2_text").html("주도형 특징을 가진 표출형");
						}
					}else if(people_speed <= 1 && people_control >= -5){
						$("#type2_text").html("우호형 특징을 가진 표출형");
					}else{
						$("#type2_text").html("유쾌한 표출형");
					}
				}
				if(people_speed < 0 && people_control > 0) {
					//$("#type1_bg_color").css({"background-color" : "#52d935"});

					$("#type2_per").css({"color":"#37afec","background-color":"#4d4d4d","font-size":"2.0em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});
					$("#type2_text").css({"color":"#37afec","background-color":"#4d4d4d","font-size":"0.85em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});

					if(people_speed >= -5 && people_control <= 1){
						if(people_speed == -1 && people_control == 1){
							$("#type2_text").html("주도형,우호형 특징을 숨긴 분석형");
						}else{
							$("#type2_text").html("우호형 특징을 가진 분석형");
						}
					}else if(people_speed >= -1 && people_control <= 5){
						$("#type2_text").html("주도형 특징을 숨긴 분석형");
					}else{
						$("#type2_text").html("철저한 분석형");
					}
				}
				if(people_speed < 0 && people_control < 0) {
					//$("#type1_bg_color").css({"background-color" : "#aa64f8"});

					$("#type2_per").css({"color":"#52d935","background-color":"#4d4d4d","font-size":"2.0em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});
					$("#type2_text").css({"color":"#52d935","background-color":"#4d4d4d","font-size":"0.85em","padding-top":"1.0em","padding-bottom":"1.0em","text-align":"center"});

					if(people_speed >= -5 && people_control >= -1){
						if(people_speed == -1 && people_control == -1){
							$("#type2_text").html("분석형,표출형 특징을 가진 우호형");
						}else{
							$("#type2_text").html("분석형 특징을 가진 우호형");
						}
					}else if(people_speed >= -1 && people_control >= -5){
						$("#type2_text").html("표출형 특징을 가진 우호형");
					}else{
						$("#type2_text").html("스마일 우호형");
					}
				}



    		

    		//console.log(my_per_up + ":::" + my_per_down + ":::" + people_per_up + ":::" + people_per_down);


			/*
    		$("#type1_text").css({"width" : my_per_down+"%"});
    		$("#type1_bg").css({"width" : my_per_up+"%"});

    		$("#type2_text").css({"width" : people_per_down+"%"});
    		$("#type2_bg").css({"width" : "20%"});
			*/

    	}
