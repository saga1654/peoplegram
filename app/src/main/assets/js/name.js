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

		/*
			$('#type1_per').jQuerySimpleCounter({
            	end:my_per,
            	duration: 2000
            });

    		if(my_speed > 0 && my_control > 0) {
    			//$("#type1_bg_color").css({"background-color" : "#ff8a55"});
    			
				$("#type1_per").css({"color":"#ff8a55"});
				$("#type1_text").css({"color":"#ff8a55"});

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
    			
				$("#type1_per").css({"color":"#aa64f8"});
				$("#type1_text").css({"color":"#aa64f8"});


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
    			
				$("#type1_per").css({"color":"#37afec"});
				$("#type1_text").css({"color":"#37afec"});

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
    			
				$("#type1_per").css({"color":"#52d935"});
				$("#type1_text").css({"color":"#52d935"});

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

			$("#type2_per").html(people_per+"%");

			$('#type2_per').jQuerySimpleCounter({
            	end:people_per,
                duration: 2000
            });
				
    		
    			if(people_speed > 0 && people_control > 0) {
					//$("#type1_bg_color").css({"background-color" : "#ff8a55"});

					$("#type2_per").css({"color":"#ff8a55"});
					$("#type2_text").css({"color":"#ff8a55"});

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

					$("#type2_per").css({"color":"#aa64f8"});
					$("#type2_text").css({"color":"#aa64f8"});


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

					$("#type2_per").css({"color":"#37afec"});
					$("#type2_text").css({"color":"#37afec"});

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

					$("#type2_per").css({"color":"#52d935"});
					$("#type2_text").css({"color":"#52d935"});

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

		function my_1(title)
		{
			var jsondata = [{value:50, color:"#26d004"}, {value:50, color:"#dce0df"}];
			$( "#my_1" ).doughnutit({
				dnData: jsondata,
				dnSize: 130,
				dnInnerCutout: 90,
				dnAnimation: true,
				dnAnimationSteps: 20,
				dnAnimationEasing: 'linear',
				dnStroke: false,
				dnShowText: true,
				dnFontSize: '14px',
				dnFontOffset: 15,
				dnFontColor: "#819596",
				dnText: title,
				dnStartAngle: 90,
				dnCounterClockwise: false
			});// End Doughnut

		}

		function tip_match(my_speed, my_control, you_speed, you_control)
        		{
        			var type_score = 100;

        			if(my_speed * you_speed * my_control * you_control < 0) {
        				type_score = 90;
        			} else {
        				if(my_speed * you_speed < 0 && my_control * you_control < 0) {
        					type_score = 80;
        				} else {
        					type_score = 100;
        				}
        			}



        			var score_temp = Math.pow((my_speed - you_speed),2) + Math.pow((my_control - you_control),2);
        			var score = 4 * Math.sqrt(2) * Math.sqrt(score_temp);

        			var total = Math.round(type_score - score, 2);


        			$("#tip_match").html("일치율 : "+total+"%");
        		}

        		//id, 내데이터, 상대방전체, 상대방+카운트
        		function tip_graph(id, my_data, you_all_data, you_data)
        		{
        			//좌측
        			var per_left = 0;
        			//우측
        			var per_right = 0;


        			per_right = Math.ceil((you_data / you_all_data) * 100);
        			per_left = 100 - per_right;


        			$("#left"+id).css({"width":per_left+"%"});
        			$("#right"+id).css({"width":per_right+"%"});

        			$("#left"+id+"_title").html("느린편:("+per_left+"%)");
        			$("#right"+id+"_title").html("빠른편:("+per_right+"%)");

        		}
