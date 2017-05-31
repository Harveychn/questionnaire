/*$().ready(function()
{
	$("button").click(function()
	{
		$("#test").validate({
			rules:{
				usercheck:{
					required:true,
					minlength:5
				
				},
				passwordcheck:{
					required:true,
					minlength:8
				},
			},
			message:{
				usercheck:{
					required:"请输入用户名"
					minlength:"用户名由五个以上字符组成"
				},
				passwordcheck:{
					required:"请输入密码"
					minlength:"密码由8位以上字符组成"
				}
			}
		})
		
	})
})*/

$(document).ready(function(){
	
	$('.usercheck1').hide();
	$('.usercheck2').hide();
	$('.usercheck3').hide();
	$('.passwordcheck1').hide();
	$('.passwordcheck2').hide();
	
	$('.buttonsubmite').click(function()
	{
		$('.usercheck1').hide();
		$('.usercheck2').hide();
		$('.usercheck3').hide();
		$('.passwordcheck1').hide();
		$('.passwordcheck2').hide();
		var user = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		
		if(user == 0)
		{
			$('.userblock').hide();
			$('.usercheck1').show();
		}
		else
		{
			if(!user.match(/^\w+$/))
			{
				$('.userblock').hide();
				$('.usercheck2').show();
			}
			else
			{
				$('.userblock').show();
			}
		}
		
		if(password == 0)
		{
			$('.passwordblock').hide();
			$('.passwordcheck1').show();
		}
		else
		{
			if(user == 0)
			{
				$('.userblock').hide();
				$('.usercheck1').hide();
				$('.usercheck3').show();
			}
			else
			{
				if(!password.match(/^[a-zA-Z]\w{5,17}$/))
					{
						$('.passwordblock').hide();
						$('.passwordcheck2').show();
					}
				else
					{
						$('.passwordblock').show();
					}
			}
			
		}
		
		
	})
	
})
