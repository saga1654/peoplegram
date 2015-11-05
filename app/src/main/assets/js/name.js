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

		function my_data(data1,data2,data3,data4,data5,data6,data7,data8,data9,data10) {

			var title1 = "";
			var title2 = "";
			var title3 = "";
			var title4 = "";
			var title5 = "";
			var title6 = "";
			var title7 = "";
			var title8 = "";
			var title9 = "";
			var title10 = "";

			var per1 = "";
			var per2 = "";
			var per3 = "";
			var per4 = "";
			var per5 = "";
			var per6 = "";
			var per7 = "";
			var per8 = "";
			var per9 = "";
			var per10 = "";

			$("#my_1_td").html('<div id="my_1" data-startdegree="180" data-dimension="90" data-text="100%" data-title="본인" data-info="'+title1+'" data-width="3" data-fontsize="12" data-percent="100" data-fgcolor="#00a0e9" data-bgcolor="#eee">');
			$("#my_2_td").html('<div id="my_2" data-startdegree="180" data-dimension="90" data-text="100%" data-title="본인" data-info="'+title2+'" data-width="3" data-fontsize="12" data-percent="100" data-fgcolor="#00a0e9" data-bgcolor="#eee">');
			$("#my_3_td").html('<div id="my_3" data-startdegree="180" data-dimension="90" data-text="100%" data-title="본인" data-info="'+title3+'" data-width="3" data-fontsize="12" data-percent="100" data-fgcolor="#00a0e9" data-bgcolor="#eee">');
			$("#my_4_td").html('<div id="my_4" data-startdegree="180" data-dimension="90" data-text="100%" data-title="본인" data-info="'+title4+'" data-width="3" data-fontsize="12" data-percent="100" data-fgcolor="#00a0e9" data-bgcolor="#eee">');
			$("#my_5_td").html('<div id="my_5" data-startdegree="180" data-dimension="90" data-text="100%" data-title="본인" data-info="'+title5+'" data-width="3" data-fontsize="12" data-percent="100" data-fgcolor="#00a0e9" data-bgcolor="#eee">');
			$("#my_6_td").html('<div id="my_6" data-startdegree="180" data-dimension="90" data-text="100%" data-title="본인" data-info="'+title6+'" data-width="3" data-fontsize="12" data-percent="100" data-fgcolor="#00a0e9" data-bgcolor="#eee">');
			$("#my_7_td").html('<div id="my_7" data-startdegree="180" data-dimension="90" data-text="100%" data-title="본인" data-info="'+title7+'" data-width="3" data-fontsize="12" data-percent="100" data-fgcolor="#00a0e9" data-bgcolor="#eee">');
			$("#my_8_td").html('<div id="my_8" data-startdegree="180" data-dimension="90" data-text="100%" data-title="본인" data-info="'+title8+'" data-width="3" data-fontsize="12" data-percent="100" data-fgcolor="#00a0e9" data-bgcolor="#eee">');
			$("#my_9_td").html('<div id="my_9" data-startdegree="180" data-dimension="90" data-text="100%" data-title="본인" data-info="'+title9+'" data-width="3" data-fontsize="12" data-percent="100" data-fgcolor="#00a0e9" data-bgcolor="#eee">');
			$("#my_10_td").html('<div id="my_10" data-startdegree="180" data-dimension="90" data-text="100%" data-title="본인" data-info="'+title10+'" data-width="3" data-fontsize="12" data-percent="100" data-fgcolor="#00a0e9" data-bgcolor="#eee">');
			$('#my_1').circliful();
			$('#my_2').circliful();
			$('#my_3').circliful();
			$('#my_4').circliful();
			$('#my_5').circliful();
			$('#my_6').circliful();
			$('#my_7').circliful();
			$('#my_8').circliful();
			$('#my_9').circliful();
			$('#my_10').circliful();
		}

		function you_data(data1,data2,data3,data4,data5,data6,data7,data8,data9,data10) {

			var title1 = "";
        	var title2 = "";
        	var title3 = "";
        	var title4 = "";
        	var title5 = "";
        	var title6 = "";
        	var title7 = "";
        	var title8 = "";
        	var title9 = "";
        	var title10 = "";

        	var per1 = "0";
        	var per2 = "0";
        	var per3 = "0";
        	var per4 = "0";
        	var per5 = "0";
        	var per6 = "0";
        	var per7 = "0";
        	var per8 = "0";
        	var per9 = "0";
        	var per10 = "0";



        	$("#you_1_td").html('<div id="you_1" data-startdegree="180" data-dimension="90" data-text="'+per1+'%" data-title="본인" data-info="'+title1+'" data-width="4" data-fontsize="14" data-percent="'+per1+'" data-fgcolor="#fc35a6" data-bgcolor="#eee">');
        	$("#you_2_td").html('<div id="you_2" data-startdegree="180" data-dimension="90" data-text="'+per2+'%" data-title="본인" data-info="'+title2+'" data-width="4" data-fontsize="14" data-percent="'+per2+'" data-fgcolor="#fc35a6" data-bgcolor="#eee">');
        	$("#you_3_td").html('<div id="you_3" data-startdegree="180" data-dimension="90" data-text="'+per3+'%" data-title="본인" data-info="'+title3+'" data-width="4" data-fontsize="14" data-percent="'+per3+'" data-fgcolor="#fc35a6" data-bgcolor="#eee">');
        	$("#you_4_td").html('<div id="you_4" data-startdegree="180" data-dimension="90" data-text="'+per4+'%" data-title="본인" data-info="'+title4+'" data-width="4" data-fontsize="14" data-percent="'+per4+'" data-fgcolor="#fc35a6" data-bgcolor="#eee">');
        	$("#you_5_td").html('<div id="you_5" data-startdegree="180" data-dimension="90" data-text="'+per5+'%" data-title="본인" data-info="'+title5+'" data-width="4" data-fontsize="14" data-percent="'+per5+'" data-fgcolor="#fc35a6" data-bgcolor="#eee">');
        	$("#you_6_td").html('<div id="you_6" data-startdegree="180" data-dimension="90" data-text="'+per6+'%" data-title="본인" data-info="'+title6+'" data-width="4" data-fontsize="14" data-percent="'+per6+'" data-fgcolor="#fc35a6" data-bgcolor="#eee">');
        	$("#you_7_td").html('<div id="you_7" data-startdegree="180" data-dimension="90" data-text="'+per7+'%" data-title="본인" data-info="'+title7+'" data-width="4" data-fontsize="14" data-percent="'+per7+'" data-fgcolor="#fc35a6" data-bgcolor="#eee">');
        	$("#you_8_td").html('<div id="you_8" data-startdegree="180" data-dimension="90" data-text="'+per8+'%" data-title="본인" data-info="'+title8+'" data-width="4" data-fontsize="14" data-percent="'+per8+'" data-fgcolor="#fc35a6" data-bgcolor="#eee">');
        	$("#you_9_td").html('<div id="you_9" data-startdegree="180" data-dimension="90" data-text="'+per9+'%" data-title="본인" data-info="'+title9+'" data-width="4" data-fontsize="14" data-percent="'+per9+'" data-fgcolor="#fc35a6" data-bgcolor="#eee">');
        	$("#you_10_td").html('<div id="you_10" data-startdegree="180" data-dimension="90" data-text="'+per10+'%" data-title="본인" data-info="'+title10+'" data-width="4" data-fontsize="14" data-percent="'+per10+'" data-fgcolor="#fc35a6" data-bgcolor="#eee">');

        	$('#you_1').circliful();
        	$('#you_2').circliful();
        	$('#you_3').circliful();
        	$('#you_4').circliful();
        	$('#you_5').circliful();
        	$('#you_6').circliful();
        	$('#you_7').circliful();
        	$('#you_8').circliful();
        	$('#you_9').circliful();
        	$('#you_10').circliful();
        }
