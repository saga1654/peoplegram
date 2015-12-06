	function nameView(myname, youname)
	{
		
		var inputTxt1 = myname;
		var inputTxt2 = youname;

		//console.log(inputTxt1 + ":::" inputTxt2);

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

    function type_chart(my_speed, my_control, people_speed, people_control, gubun1, viewType)
    	{

    		var my_total = Math.abs(my_speed * my_control);
    		var my_per = parseInt((my_total * 4));
    		var my_type = "";




    		if(my_speed > 0 && my_control > 0) {
    			//$("#type1_bg_color").css({"background-color" : "#ff8a55"});

				//$("#type2_per").css({"color":"#ff8a55"});
				$("#type1_text").css({"color":"#ff8a55"});

				if(my_speed <= 5 && my_control <= 1){
					if(my_speed <= 1 && my_control <= 1){
						$("#type1_per").html("분석형,표출형 특징을 가진");
						$("#type1_text").html("주도형");
						my_type ="D4";
					}else{
						$("#type1_per").html("표출형 특징을 가진");
						$("#type1_text").html("주도형");
						my_type ="D3";
					}
				}else if(my_speed <= 1 && my_control <= 5){
					$("#type1_per").html("분석형 특성을 가진");
					$("#type1_text").html("주도형");
					my_type ="D2";
				}else{
					$("#type1_text").html("주도형");
					my_type ="D1";
				}
			}
			if(my_speed > 0 && my_control < 0) {
				//$("#type1_bg_color").css({"background-color" : "#37afec"});

				//$("#type2_per").css({"color":"#aa64f8"});
				$("#type1_text").css({"color":"#aa64f8"});


				if(my_speed <= 5 && my_control >= -1){
					if(my_speed <= 1 && my_control >= -1){
						$("#type1_per").html("주도형,우호형 특징을 가진");
						$("#type1_text").html("표출형");
						my_type ="E2";
					}else{
						$("#type1_per").html("주도형 특징을 가진");
						$("#type1_text").html("표출형");
						my_type ="E1";
					}
				}else if(my_speed <= 1 && my_control >= -5){
					$("#type1_per").html("우호형 특징을 가진");
					$("#type1_text").html("표출형");
					my_type ="E4";
				}else{
					$("#type1_text").html("표출형");
					my_type ="E3";
				}
			}
			if(my_speed < 0 && my_control > 0) {
				//$("#type1_bg_color").css({"background-color" : "#52d935"});

				//$("#type2_per").css({"color":"#37afec"});
				$("#type1_text").css({"color":"#37afec"});

				if(my_speed >= -5 && my_control <= 1){
					if(my_speed >= -1 && my_control <= 1){
						$("#type1_per").html("주도형,우호형 특징을 가진");
						$("#type1_text").html("분석형");
						my_type ="A4";
					}else{
						$("#type1_per").html("우호형 특징을 가진");
						$("#type1_text").html("분석형");
						my_type ="A3";
					}
				}else if(my_speed >= -1 && my_control <= 5){
					$("#type1_per").html("주도형 특징을 가진");
					$("#type1_text").html("분석형");
					my_type ="A2";
				}else{
					$("#type1_text").html("분석형");
					my_type ="A1";
				}
			}
			if(my_speed < 0 && my_control < 0) {
				//$("#type1_bg_color").css({"background-color" : "#aa64f8"});

				//$("#type2_per").css({"color":"#52d935"});
				$("#type1_text").css({"color":"#52d935"});

				if(my_speed >= -5 && my_control >= -1){
					if(my_speed >= -1 && my_control >= -1){
						$("#type1_per").html("분석형,표출형 특징을 가진");
						$("#type1_text").html("우호형");
						my_type ="I2";
					}else{
						$("#type1_per").html("분석형 특징을 가진");
						$("#type1_text").html("우호형");
						my_type ="I1";
					}
				}else if(my_speed >= -1 && my_control >= -5){
					$("#type1_per").html("표출형 특징을 가진");
					$("#type1_text").html("우호형");
					my_type ="I4";
				}else{
					$("#type1_text").html("우호형");
					my_type ="I3";
				}
    		}

			
			var people_total = Math.abs(people_speed * people_control);
    		var people_per = parseInt((people_total * 4));
    		var people_type = "";


				
    		
    			if(people_speed > 0 && people_control > 0) {
					//$("#type1_bg_color").css({"background-color" : "#ff8a55"});

					//$("#type2_per").css({"color":"#ff8a55"});
					$("#type2_text").css({"color":"#ff8a55"});

					if(people_speed <= 5 && people_control <= 1){
						if(people_speed <= 1 && people_control <= 1){
							$("#type2_per").html("분석형,표출형 특징을 가진");
							$("#type2_text").html("주도형");
							people_type ="D4";
						}else{
							$("#type2_per").html("표출형 특징을 가진");
							$("#type2_text").html("주도형");
							people_type ="D3";
						}
					}else if(people_speed <= 1 && people_control <= 5){
						$("#type2_per").html("분석형 특성을 가진");
						$("#type2_text").html("주도형");
						people_type ="D2";
					}else{
						$("#type2_text").html("주도형");
						people_type ="D1";
					}
				}
				if(people_speed > 0 && people_control < 0) {
					//$("#type1_bg_color").css({"background-color" : "#37afec"});

					//$("#type2_per").css({"color":"#aa64f8"});
					$("#type2_text").css({"color":"#aa64f8"});


					if(people_speed <= 5 && people_control >= -1){
						if(people_speed <= 1 && people_control >= -1){
							$("#type2_per").html("주도형,우호형 특징을 가진");
							$("#type2_text").html("표출형");
							people_type ="E2";
						}else{
							$("#type2_per").html("주도형 특징을 가진");
							$("#type2_text").html("표출형");
							people_type ="E1";
						}
					}else if(people_speed <= 1 && people_control >= -5){
						$("#type2_per").html("우호형 특징을 가진");
						$("#type2_text").html("표출형");
						people_type ="E4";
					}else{
						$("#type2_text").html("표출형");
						people_type ="E3";
					}
				}
				if(people_speed < 0 && people_control > 0) {
					//$("#type1_bg_color").css({"background-color" : "#52d935"});

					//$("#type2_per").css({"color":"#37afec"});
					$("#type2_text").css({"color":"#37afec"});

					if(people_speed >= -5 && people_control <= 1){
						if(people_speed >= -1 && people_control <= 1){
							$("#type2_per").html("주도형,우호형 특징을 가진");
							$("#type2_text").html("분석형");
							people_type ="A4";
						}else{
							$("#type2_per").html("우호형 특징을 가진");
							$("#type2_text").html("분석형");
							people_type ="A3";
						}
					}else if(people_speed >= -1 && people_control <= 5){
						$("#type2_per").html("주도형 특징을 가진");
						$("#type2_text").html("분석형");
						people_type ="A2";
					}else{
						$("#type2_text").html("분석형");
						people_type ="A1";
					}
				}
				if(people_speed < 0 && people_control < 0) {
					//$("#type1_bg_color").css({"background-color" : "#aa64f8"});

					//$("#type2_per").css({"color":"#52d935"});
					$("#type2_text").css({"color":"#52d935"});

					if(people_speed >= -5 && people_control >= -1){
						if(people_speed >= -1 && people_control >= -1){
							$("#type2_per").html("분석형,표출형 특징을 가진");
							$("#type2_text").html("우호형");
							people_type ="I2";
						}else{
							$("#type2_per").html("분석형 특징을 가진");
							$("#type2_text").html("우호형");
							people_type ="I1";
						}
					}else if(people_speed >= -1 && people_control >= -5){
						$("#type2_per").html("표출형 특징을 가진");
						$("#type2_text").html("우호형");
						people_type ="I4";
					}else{
						$("#type2_text").html("우호형");
						people_type ="I3";
					}
				}



    		var my_contents="";
    		var people_contents="";

    		var _type1_per = $("#type1_per").html();
    		var _type1_text = $("#type1_text").html();

    		var _type2_per = $("#type2_per").html();
            var _type2_text = $("#type2_text").html();


		    if((my_type == "I1" && people_type == "I1") || (my_type == "I1" && people_type == "I2") || (my_type == "I1" && people_type == "I3") || (my_type == "I1" && people_type == "I4") || (my_type == "I2" && people_type == "I1") || (my_type == "I2" && people_type == "I2") || (my_type == "I2" && people_type == "I3") || (my_type == "I2" && people_type == "I4")|| (my_type == "I3" && people_type == "I1") || (my_type == "I3" && people_type == "I2") || (my_type == "I3" && people_type == "I3") || (my_type == "I3" && people_type == "I4") || (my_type == "I4" && people_type == "I1") || (my_type == "I4" && people_type == "I2") || (my_type == "I4" && people_type == "I3") || (my_type == "I4" && people_type == "I4") || (my_type == "D1" && people_type == "D1") || (my_type == "D1" && people_type == "D2") || (my_type == "D1" && people_type == "D3") || (my_type == "D1" && people_type == "D4") || (my_type == "D2" && people_type == "D1") || (my_type == "D2" && people_type == "D2") || (my_type == "D2" && people_type == "D3") || (my_type == "D2" && people_type == "D4")|| (my_type == "D3" && people_type == "D1") || (my_type == "D3" && people_type == "D2") || (my_type == "D3" && people_type == "D3") || (my_type == "D3" && people_type == "D4") || (my_type == "D4" && people_type == "D1") || (my_type == "D4" && people_type == "D2") || (my_type == "D4" && people_type == "D3") || (my_type == "D4" && people_type == "D4") || (my_type == "E1" && people_type == "E1") || (my_type == "E1" && people_type == "E2") || (my_type == "E1" && people_type == "E3") || (my_type == "E1" && people_type == "E4") || (my_type == "E2" && people_type == "E1") || (my_type == "E2" && people_type == "E2") || (my_type == "E2" && people_type == "E3") || (my_type == "E2" && people_type == "E4")|| (my_type == "E3" && people_type == "E1") || (my_type == "E3" && people_type == "E2") || (my_type == "E3" && people_type == "E3") || (my_type == "E3" && people_type == "E4") || (my_type == "E4" && people_type == "E1") || (my_type == "E4" && people_type == "E2") || (my_type == "E4" && people_type == "E3") || (my_type == "E4" && people_type == "E4") || (my_type == "A1" && people_type == "A1") || (my_type == "A1" && people_type == "A2") || (my_type == "A1" && people_type == "A3") || (my_type == "A1" && people_type == "A4") || (my_type == "A2" && people_type == "A1") || (my_type == "A2" && people_type == "A2") || (my_type == "A2" && people_type == "A3") || (my_type == "A2" && people_type == "A4")|| (my_type == "A3" && people_type == "A1") || (my_type == "A3" && people_type == "A2") || (my_type == "A3" && people_type == "A3") || (my_type == "A3" && people_type == "A4") || (my_type == "A4" && people_type == "A1") || (my_type == "A4" && people_type == "A2") || (my_type == "A4" && people_type == "A3") || (my_type == "A4" && people_type == "A4") ){
				if(viewType == "my") {
					my_contents  =  "<b class = 'Name1'></b>님 자신이 바라보는 모습과 타인이 <b class = 'Name1'></b>님을 바라보는 모습이 일치합니다. <br><br> 평소 <b class = 'Name1'></b></b>님은 <b class='gubun1'></b> 관계에서 <b class='my_type'></b>의 일관된 행동 모습을 보여 주고 있습니다. <br><br>또한 다른 사람들도 <b class = 'Name1'></b>님의 행동을 일관되게 <b class='my_type'></b>으로 파악하고 예측하고 있어 <b class='gubun1'></b> 관계에서 상호 간의 소통과 행동에 어려움이 없습니다. <br><br> <b class = 'Name1'></b>님은 평소 <b class='gubun1'></b> 관계에서 자신이 어떤 행동을 하고 있는지 잘 알고 있습니다.<br><br> 다른 사람 또한 <b class = 'Name1'></b>님이 예상에 어긋난 행동을 거의 보이지 않기 때문에 <b class = 'Name1'></b>님을 매우 잘 알고 있다고 생각합니다.<br><br> 즉 <b class = 'Name1'></b>님이나 주변 사람들 모두 <b class='gubun1'></b> 관계에서 <b class = 'Name1'></b>님과 어떻게 소통하고 행동하는 것이 훌륭한 인간 관계를 맺고 지속할 수 있는지 잘 알고 있습니다. <br><br> 따라서 <b class = 'Name1'></b>님은 <b class='gubun1'></b> 관계 내에서 인간 관계에 따른 스트레스는 상대적으로 적을 가능성이 높습니다.<br><br>";
				} else {
					my_contents = "당신이 알고 있는 그 분이 맞습니다.<br><br><b class = 'Name1'></b>님이 <b class = 'Name2'></b>님을 바라보는 모습과 타인이 <b class = 'Name2'></b>님을 바라보는 모습이 일치합니다. <br><br><b class = 'Name1'></b>님 뿐만 아리나 다른 사람들도 <b class = 'gubun1'></b> 관계에서 <b class = 'Name2'></b>님의 행동을 일관되게 <b class = 'my_type'></b>으로 파악하고 있어 <b class = 'Name2'></b>님이 <b class = 'Name1'></b>님을 대하는 행동이나 다른 사람을 대하는 행동이 다르지 않습니다.<br><br>따라서 <b class = 'Name1'></b>님은 <b class = 'gubun1'></b>관계에서 <b class = 'Name2'></b>님의 행동에 대해 <b class = 'my_type'></b>의 성향을 정확히 알고 대응한다면 <b class = 'Name2'></b>님과 훌륭한 인간 관계를 맺고 지속할 수 있습니다.  <br><br>자세한 사항은 관계Tip을 참조하세요.<br><br>";
				}
			  }else if((my_type == "D1" && people_type == "I2") || (my_type == "D2" && people_type == "I2") || (my_type == "D3" && people_type == "I2") || (my_type == "D4" && people_type == "I2") || (my_type == "I1" && people_type == "D4") || (my_type == "I2" && people_type == "D4") || (my_type == "I3" && people_type == "D4") || (my_type == "I4" && people_type == "D4") || (my_type == "I1" && people_type == "E2") || (my_type == "I2" && people_type == "E2") || (my_type == "I3" && people_type == "E2") || (my_type == "I4" && people_type == "E2") || (my_type == "I1" && people_type == "E4") || (my_type == "I2" && people_type == "E4") || (my_type == "I3" && people_type == "E4") || (my_type == "I4" && people_type == "E4") || (my_type == "I1" && people_type == "A3") || (my_type == "I2" && people_type == "A3") || (my_type == "I3" && people_type == "A3") || (my_type == "I4" && people_type == "A3") || (my_type == "I1" && people_type == "A4") || (my_type == "I2" && people_type == "A4") || (my_type == "I3" && people_type == "A4") || (my_type == "I4" && people_type == "A4") || (my_type == "D1" && people_type == "E1") || (my_type == "D2" && people_type == "E1") || (my_type == "D3" && people_type == "E1") || (my_type == "D4" && people_type == "E1") || (my_type == "D1" && people_type == "E2") || (my_type == "D2" && people_type == "E2") || (my_type == "D3" && people_type == "E2") || (my_type == "D4" && people_type == "E2") || (my_type == "D1" && people_type == "A2") || (my_type == "D2" && people_type == "A2") || (my_type == "D3" && people_type == "A2") || (my_type == "D4" && people_type == "A2") || (my_type == "D1" && people_type == "A4") || (my_type == "D2" && people_type == "A4") || (my_type == "D3" && people_type == "A4") || (my_type == "D4" && people_type == "A4") || (my_type == "E1" && people_type == "I2") || (my_type == "E2" && people_type == "I2") || (my_type == "E3" && people_type == "I2") || (my_type == "E4" && people_type == "I2") || (my_type == "E1" && people_type == "I4") || (my_type == "E2" && people_type == "I4") || (my_type == "E3" && people_type == "I4") || (my_type == "E4" && people_type == "I4") || (my_type == "E1" && people_type == "D3") || (my_type == "E2" && people_type == "D3") || (my_type == "E3" && people_type == "D3") || (my_type == "E4" && people_type == "D3")  || (my_type == "E1" && people_type == "D4") || (my_type == "E2" && people_type == "D4") || (my_type == "E3" && people_type == "D4") || (my_type == "E4" && people_type == "D4") || (my_type == "E1" && people_type == "A4") || (my_type == "E2" && people_type == "A4") || (my_type == "E3" && people_type == "A4") || (my_type == "E4" && people_type == "A4")   || (my_type == "A1" && people_type == "I1") || (my_type == "A2" && people_type == "I1") || (my_type == "A3" && people_type == "I1") || (my_type == "A4" && people_type == "I1") || (my_type == "A1" && people_type == "I2") || (my_type == "A2" && people_type == "I2") || (my_type == "A3" && people_type == "I2") || (my_type == "A4" && people_type == "I2") || (my_type == "A1" && people_type == "D2") || (my_type == "A2" && people_type == "D2") || (my_type == "A3" && people_type == "D2") || (my_type == "A4" && people_type == "D2") || (my_type == "A1" && people_type == "D4") || (my_type == "A2" && people_type == "D4") || (my_type == "A3" && people_type == "D4") || (my_type == "A4" && people_type == "D4") || (my_type == "A1" && people_type == "E2") || (my_type == "A2" && people_type == "E2") || (my_type == "A3" && people_type == "E2") || (my_type == "A4" & people_type == "E2")  ){
				if(viewType == "my") {
					my_contents  =  "<b class = 'Name1'></b>님은 인간 관계 스트레스가 어느 정도 있음<br><b class = 'Name1'></b>자신이 바라보는 모습과 타인이 <b class = 'Name1'></b>님을 바라보는 모습이 다소 불일치합니다. <br><br>평소 <b class = 'Name1'></b></b>님은 <b class='gubun1'></b> 관계에서 <b class='my_type'></b>의 행동을 보여주고 있다고 생각하지만, 다른 사람들은 약간의 차이가 있는 <b class='people_type'></b>의 행동을 하고 있다고 생각합니다.<br><br>이로 인해 다른 사람들은 <b class = 'Name1'></b>님을 대할 때 <b class='people_type'></b>에 맞춰 소통하고 행동하지만 간혹 예상과 다른 <b class='my_type'></b>의 행동이 나타나 당황스러울 수 있을 것입니다. <br><br><b class = 'Name1'></b>님은 평소 <b class='gubun1'></b> 관계에서 자신이 일관되게 어떤 행동을 하고 있는지 대부분은 잘 알고 있으나, 간혹 자신도 몰랐던 행동을 하거나 본인이 드러내고 싶지 않은 행동을 하는 경우도 있습니다.<br><br>다른 사람들은 <b class = 'Name1'></b>님이 예상에 어긋난 다른 행동을 보일 경우 평소 알고 있던 <b class = 'Name1'></b>님이 아니기 때문에 당황할 수 있어 <b class = 'Name1'></b>님과 인간 관계에서 거리를 둘 수 있습니다.<br><br>이로 인해 <b class = 'Name1'></b>님은 <b class='gubun1'></b> 관계 내에서 인간 관계에 따른 스트레스를 받을 수 있습니다.<br><br>훌륭한 인간 관계를 맺기 위해서 <b class = 'Name1'></b>님은 <b class='gubun1'></b> 관계에서 자신의 행동을 일관되게 할 필요가 있습니다. <br><br>그러기 위해서는 자신이 미처 몰랐던 자신의 행동은 타인의 판단을 존중하고 수용하여 자신의 모습으로 인정할 수 있어야 합니다. <br><br>또한 자신의 처한 환경과 역할, 관계에 따라 통제하고 숨겨왔던 자신의 모습은 조금씩 드러내어 솔직한 모습 그대로 사람들과 관계를 맺을 필요가 있습니다.<br><br>";
				} else {
					my_contents = "당신이 알고 있는 그 분이 거의 맞습니다.<br><br><b class = 'Name1'></b>님이 <b class = 'Name2'></b>님을 바라보는 모습과 타인이 <b class = 'Name2'></b>님을 바라보는 모습이 다소 불일치합니다. <br><br>평소 <b class = 'Name1'></b>님은 <b class='gubun1'></b> 관계에서 <b class = 'Name2'></b>님이 <b class='my_type'></b>의 행동을 보여주고 있다고 생각하지만, 다른 사람들은 약간의 차이가 있는 <b class='people_type'></b>의 행동을 하고 있다고 생각합니다.<br><br>이로 인해 <b class = 'Name1'></b>님은 <b class = 'Name2'></b>님을 대할 때 <b class='my_type'></b>에 맞춰 소통하고 행동하지만 간혹 예상과 다른 <b class='people_type'></b>의 행동이 나타나 당황스러웠을 것입니다. 이로 인해 <b class = 'Name1'></b>님은 <b class = 'Name2'></b>님과 가까워 지기 어려워 약간의 거리를 두고 있을 수 있습니다.<br><br>이는 <b class = 'Name1'></b>님이 <b class = 'Name2'></b>님의 모습을 일부 잘못 파악하고 있기 때문입니다.<br><br>따라서 <b class = 'Name1'></b>님은 다른 사람들이 파악하고 있는 <b class = 'Name2'></b>님의 <b class='people_type'></b> 행동에 주목하고, <b class='people_type'></b> 성향을 정확히 알고 대응한다면 <b class = 'Name2'></b>님과 더욱 훌륭한 인간 관계를 맺고 지속할 수 있습니다.<br><br>그러나 간혹 <b class = 'Name2'></b>님이 <b class = 'Name1'></b>님과 다른 형태의 인간 관계를 맺기 위하여 <b class = 'Name1'></b>님에게 <b class='my_type'></b>의 행동을 하고 있을 수 있습니다. <br><br> 즉, <b class = 'Name2'></b>님은 <b class = 'Name1'></b>님과의 관계에서 숨겨진 자신의 모습을 더 많이 보여주고 있거나, 다른 사람에게 보여준 행동의 일부를 <b class = 'Name1'></b>님에게는 숨기고 있을 수 있습니다.<br><br>이 경우에는 <b class = 'Name2'></b>님이 <b class = 'Name1'></b>님과 왜 다른 형태의 인간 관계를 맺고자 하는 지 곰곰이 고민해 볼 필요가 있습니다.<br><br>자세한 사항은 관계Tip을 참조하세요.<br><br>";
				}

			  }else if((my_type == "I1" && people_type == "E1") || (my_type == "I2" && people_type == "E1") || (my_type == "I3" && people_type == "E1") || (my_type == "I4" && people_type == "E1") || (my_type == "I1" && people_type == "E3") || (my_type == "I2" && people_type == "E3") || (my_type == "I3" && people_type == "E3") || (my_type == "I4" && people_type == "E3") || (my_type == "I1" && people_type == "A1") || (my_type == "I2" && people_type == "A1") || (my_type == "I3" && people_type == "A1") || (my_type == "I4" && people_type == "A1") || (my_type == "I1" && people_type == "A2") || (my_type == "I2" && people_type == "A2") || (my_type == "I3" && people_type == "A2") || (my_type == "I4" && people_type == "A2")  || (my_type == "D1" && people_type == "E3") || (my_type == "D2" && people_type == "E3") || (my_type == "D3" && people_type == "E3") || (my_type == "D4" && people_type == "E3") || (my_type == "D1" && people_type == "E4") || (my_type == "D2" && people_type == "E4") || (my_type == "D3" && people_type == "E4") || (my_type == "D4" && people_type == "E4") || (my_type == "D1" && people_type == "A1") || (my_type == "D2" && people_type == "A1") || (my_type == "D3" && people_type == "A1") || (my_type == "D4" && people_type == "A1") || (my_type == "D1" && people_type == "A3") || (my_type == "D2" && people_type == "A3") || (my_type == "D3" && people_type == "A3") || (my_type == "D4" && people_type == "A3") || (my_type == "E1" && people_type == "I1") || (my_type == "E2" && people_type == "I1") || (my_type == "E3" && people_type == "I1") || (my_type == "E4" && people_type == "I1") || (my_type == "E1" && people_type == "I3") || (my_type == "E2" && people_type == "I3") || (my_type == "E3" && people_type == "I3") || (my_type == "E4" && people_type == "I3") || (my_type == "E1" && people_type == "D1") || (my_type == "E2" && people_type == "D1") || (my_type == "E3" && people_type == "D1") || (my_type == "E4" && people_type == "D1") || (my_type == "E1" && people_type == "D2") || (my_type == "E2" && people_type == "D2") || (my_type == "E3" && people_type == "D2") || (my_type == "E4" && people_type == "D2") || (my_type == "A1" && people_type == "I3") || (my_type == "A2" && people_type == "I3") || (my_type == "A3" && people_type == "I3") || (my_type == "A4" && people_type == "I3") || (my_type == "A1" && people_type == "I4") || (my_type == "A2" && people_type == "I4") || (my_type == "A3" && people_type == "I4") || (my_type == "A4" && people_type == "I4") || (my_type == "A1" && people_type == "D1") || (my_type == "A2" && people_type == "D1") || (my_type == "A3" && people_type == "D1") || (my_type == "A4" && people_type == "D1") || (my_type == "A1" && people_type == "D3") || (my_type == "A2" && people_type == "D3") || (my_type == "A3" && people_type == "D3") || (my_type == "A4" && people_type == "D3") ){
				if(viewType == "my") {
					my_contents  = "<b class = 'Name1'></b>님 자신이 바라보는 모습과 타인이 <b class = 'Name1'></b>님을 바라보는 모습이 불일치합니다.<br><br>평소 <b class = 'Name1'></b>님은 <b class='gubun1'></b> 관계에서 <b class='my_type'></b>의 행동을 보여주고 있다고 생각하지만, 다른 사람들은 ><b class = 'Name1'></b>님의 생각과 차이가 큰 <b class='people_type'></b>의 행동을 하고 있다고 생각합니다.<br><br>이로 인해 다른 사람들은 <b class = 'Name1'></b>님을 대할 때 <b class='people_type'></b>에 맞춰 소통하고 행동하려 하지만 <b class = 'Name1'></b>님은 예상과 다른 <b class='my_type'></b>의 행동을 보여 당황스러울 것입니다. <br><br><b class = 'Name1'></b>님은 평소 <b class='gubun1'></b> 관계에서 자신이 어떤 행동을 하고 있는지 잘 알고 있지 못하며, <b class='gubun1'></b> 관계에서 자신도 몰랐던 행동을 하거나 본인이 드러내고 싶지 않은 행동을 자주 하는 편입니다.<br><br>다른 사람들은 <b class = 'Name1'></b>님이 예상에 어긋난 다른 행동을 보일 경우 평소 알고 있던 <b class = 'Name1'></b>님이 아니기 때문에 당황할 수 있어 <b class = 'Name1'></b>님과 인간 관계에서 거리를 둘 수 있습니다.<br><br>이로 인해 <b class = 'Name1'></b>님은 <b class='gubun1'></b> 관계 내에서 인간 관계를 잘 풀어가기 어려워 그에 따른 강한 스트레스를 받을 수 있습니다.<br><br>훌륭한 인간 관계를 맺기 위해서 <b class = 'Name1'></b>님은 <b class='gubun1'></b> 관계에서 자신의 행동을 일관되게 할 필요가 있습니다. <br><br>그러기 위해서는 자신이 미처 몰랐던 자신의 행동은 타인의 판단을 존중하고 수용하여 자신의 모습으로 인정할 수 있어야 합니다.<br><br> 또한 자신의 처한 환경과 역할, 관계에 따라 통제하고 숨겨왔던 자신의 모습은 조금씩 드러내어 솔직한 모습 그대로 사람들과 관계를 맺을 필요가 있습니다.<br><br>";
				} else {
					my_contents = "당신이 알고 있는 그 분이 아닙니다.<br><br><b class ='Name1'></b>님이 <b class = 'Name2'></b>님을 바라보는 모습과 타인이 <b class = 'Name2'></b>님을 바라보는 모습이 불일치합니다. <br><br>평소 <b class = 'Name1'></b>님은 <b class = 'gubun1'></b> 관계에서 <b class = 'Name2'></b>님이 <b class = 'my_type'></b>의 행동을 보여주고 있다고 생각하지만, 다른 사람들은 <b class = 'Name1'></b>님의 생각과 차이가 큰 <b class = 'people_type'></b>의 행동을 하고 있다고 생각합니다.<br><br>이로 인해 <b class = 'Name1'></b>님은 <b class = 'Name2'></b>님을 대할 때 <b class = 'my_type'></b>에 맞춰 소통하고 행동하지만, <b class = 'Name2'></b>님이 예상과 다른 <b class = 'people_type'></b>의 행동이 나타나 당황스러웠을 것입니다. 이로 인해 <b class = 'Name1'></b>님은 <b class = 'Name2'></b>님과 가까워 지기 어려워 거리를 두고 있을 수 있습니다.<br><br>이는 <b class = 'Name1'></b>님이 <b class = 'Name2'></b>님의 모습을 잘못 파악하고 있다는 의미일 수 있습니다.<br><br>따라서 <b class = 'Name1'></b>님은 다른 사람들이 파악하고 있는 <b class = 'Name2'></b>님의 <b class = 'people_type'></b> 행동에 주목하고, <b class = 'people_type'></b>성향을 정확히 알고 대응한다면 <b class = 'Name2'></b>님과 더욱 훌륭한 인간 관계를 맺고 지속할 수 있습니다.<br><br>그러나 간혹 <b class = 'Name2'></b>님이 <b class = 'Name1'></b>님과 다른 형태의 인간 관계를 맺기 위하여 <b class = 'Name1'></b>님에게 <b class = 'my_type'></b>의 행동을 하고 있을 수 있습니다. <br><br>즉, <b class = 'Name2'></b>님은 <b class = 'Name1'></b>님과의 관계에서 숨겨진 자신의 모습을 더 많이 보여주고 있거나, 다른 사람에게 보여준 행동의 일부를 <b class = 'Name1'></b>님에게는 숨기고 있을 수 있습니다.<br><br>이 경우에는 <b class = 'Name2'></b>님이 <b class = 'Name1'></b>님과 왜 다른 형태의 인간 관계를 맺고자 하는 지 곰곰이 고민해 볼 필요가 있습니다.<br><br>자세한 사항은 관계Tip을 참조하세요.<br><br>";
				}

			  }else{
				if(viewType == "my") {
					my_contents  ="<b class = 'Name1'></b>님 자신이 바라보는 모습과 타인이 <b class = 'Name1'></b>님을 바라보는 모습이 전혀 다릅니다. <br><br>평소 <b class = 'Name1'></b>님은 <b class='gubun1'></b> 관계에서 <b class='my_type'></b>의 행동을 보여주고 있다고 생각하지만, 다른 사람들은 <b class = 'Name1'></b>님의 생각과 전혀 다른 <b class='people_type'></b>의 행동을 하고 있다고 생각합니다.<br><br>이로 인해 다른 사람들은 <b class = 'Name1'></b>님을 대할 때 <b class='people_type'></b>에 맞춰 소통하고 행동하려 하지만 <b class = 'Name1'></b>님은 예상과 전혀 다른 <b class='my_type'></b>의 행동을 보여 매우 당황스러울 것입니다. <br><br><b class = 'Name1'></b>님은 평소 <b class='gubun1'></b> 관계에서 자신이 어떤 행동을 하고 있는지 전혀 알고 있지 못하며, <b class='gubun1'></b> 관계에서 자신도 전혀 몰랐던 행동을 하거나 본인의 원래 모습을 꽁꽁 숨겨 두고 있을 가능성이 높습니다.<br><br>다른 사람들은 <b class = 'Name1'></b>님이 예상에 어긋난 다른 행동을 보일 경우 평소 알고 있던 <b class = 'Name1'></b>님이 전혀 아니기 때문에 매우 당황할 수 있어 <b class = 'Name1'></b>님과 인간 관계에서 많은 거리를 둘 수 있습니다.<br><br>이로 인해 <b class = 'Name1'></b>님은 <b class='gubun1'></b> 관계 내에서 인간 관계를 잘 풀어가기 어려워 그에 따른 매우 강한 스트레스를 받을 수 있습니다.<br><br><br><br>훌륭한 인간 관계를 맺기 위해서 <b class = 'Name1'></b>님은 <b class='gubun1'></b> 관계에서 자신의 행동을 일관되게 할 필요가 있습니다.<br><br> 그러기 위해서는 자신이 미처 몰랐던 자신의 행동은 타인의 판단을 존중하고 수용하여 자신의 모습으로 인정할 수 있어야 합니다.<br><br> 또한 자신의 처한 환경과 역할, 관계에 따라 강하게 통제하고 숨겨왔던 자신의 모습은 조금씩 드러내어 솔직한 모습 그대로 사람들과 관계를 맺을 필요가 있습니다.<br><br>";
				} else {
					my_contents = "당신이 알고 있는 그 분이 전혀 아닙니다.<br><br><b class = 'Name1'></b>이 <b class = 'Name2'></b>님을 바라보는 모습과 타인이 <b class = 'Name2'></b>님을 바라보는 모습이 전혀 다릅니다. <br><br>평소 <b class = 'Name1'></b>님은 <b class = 'gubun1'></b> 관계에서 <b class = 'Name2'></b>님이 <b class = 'my_type'></b>의 행동을 보여주고 있다고 생각하지만, 다른 사람들은 <b class = 'Name1'></b>님의 생각과 전혀 다른 <b class = 'people_type'></b>의 행동을 하고 있다고 생각합니다.<br><br>이로 인해 <b class = 'Name1'></b>님은 <b class = 'Name2'></b>님을 대할 때 <b class = 'my_type'></b>에 맞춰 소통하고 행동하지만, <b class = 'Name2'></b>님이 예상과 전혀 다른 <b class = 'people_type'></b>의 행동이 나타나 매우 당황스러웠을 것입니다.<br><br> 이로 인해 <b class = 'Name1'></b>님은 <b class = 'Name2'></b>님과 가까워 지기 매우 어려워 거리를 두고 있을 수 있습니다.<br><br>이는 <b class = 'Name1'></b>님이 <b class = 'Name2'></b>님의 모습을 전혀 잘못 파악하고 있다는 의미일 수 있습니다.<br><br>따라서 <b class = 'Name1'></b>님은 다른 사람들이 파악하고 있는 <b class = 'Name2'></b>님의 <b class = 'people_type'></b> 행동에 주목하고, <b class = 'people_type'></b> 성향을 정확히 알고 대응한다면 <b class = 'Name2'></b>님과 더욱 훌륭한 인간 관계를 맺고 지속할 수 있습니다.<br><br>그러나 간혹 <b class = 'Name2'></b>님이 <b class = 'Name1'></b>님과 다른 형태의 인간 관계를 맺기 위하여 <b class = 'Name1'></b>님에게 <b class = 'my_type'></b>의 행동을 하고 있을 수 있습니다. <br><br>즉, <b class = 'Name2'></b>님은 <b class = 'Name1'></b>님과의 관계에서 숨겨진 자신의 모습을 더 많이 보여주고 있거나, 다른 사람에게 보여준 행동의 일부를 <b class = 'Name1'></b>님에게는 숨기고 있을 수 있습니다.<br><br>이 경우에는 <b class = 'Name2'></b>님이 <b class = 'Name1'></b>님과 왜 다른 형태의 인간 관계를 맺고자 하는 지 곰곰이 고민해 볼 필요가 있습니다.<br><br>자세한 사항은 관계Tip을 참조하세요.<br><br>";
				}

			  }

            $("#my_contents").html(my_contents);

            $(".my_type").html(_type1_per + " " + _type1_text);
            $(".people_type").html(_type2_per + " " + _type2_text);
            $(".gubun1").html(gubun1);


            $(".Name1").html(myname);
            $(".Name2").html(youname);



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

        			var left_title = "";
        			var right_title = "";


        			per_right = Math.ceil((you_data / you_all_data) * 100);
        			per_left = 100 - per_right;

					if(id == "1"){
						left_title = "느린편";
						right_title = "빠른편";
					}
					if(id == "2"){
						left_title  = "바꾸는편";
						right_title = "안바꾸는편";
					}
					if(id == "3"){
						left_title  = "듣는편";
						right_title = "말하는편";
					}
					if(id == "4"){
						left_title = "표현하는";
						right_title = "표현안하는";
					}

					if(id == "5"){
						left_title = "느린편";
						right_title = "빠른편";
					}

					if(id == "6"){
						left_title = "따뜻하다";
						right_title = "냉정하다";
					}

					if(id == "7"){
						left_title = "따라가는";
						right_title = "주도하는";
					}

					if(id == "8"){
						left_title = "다양편";
						right_title = "없는편";
					}

					if(id == "9"){
						left_title = "신중한";
						right_title = "행동적";
					}

					if(id == "10"){
						left_title = "간혹어기는";
						right_title = "엄격한";
					}

        			$("#left"+id).css({"width":per_left+"%"});
        			$("#right"+id).css({"width":per_right+"%"});

        			$("#left"+id+"_title").html(left_title+"("+per_left+"%)");
        			$("#right"+id+"_title").html(right_title+"("+per_right+"%)");

        		}


        function uid_view(uid)
        {
	        $("#uid").val(uid);
        }