<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>嵌入iframe测试</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<body>
<script type="text/javascript">
// iframe超时处理
// url:iframe(src路径)
// timeOut_callback:超时后执形的操作
// width:宽
// height:高
function iframeTimeOut(url, timeOut_callback, width, height) {    
    var frm = document.createElement("iframe");
    frm.width = width;
    frm.height = height;
    frm.src = url;
    var kill = setTimeout(timeOut_callback, 10);
    if (frm.attachEvent) {
        frm.attachEvent("onload", function () {
            clearTimeout(kill);
            //这里可以执行其它操作
        });
    } else {
        frm.onload = function () {
            clearTimeout(kill);
        };
    }
    document.body.appendChild(frm);
}
</script>
<iframe width="100%" id="mainFrame" frameborder="2" height="100%" style="border: none;padding: 0px;" name="mainIFrame" border="0"> 
<div width="1000"><iframe width="100%" id="aa" frameborder="0" height="400" name="aa" style="border: none; padding: 0px; height: 179px;" 
src="http://localhost:8080/webportal/bpm/workingProcess/hrmsWorking" border="0"></iframe></div>
</iframe>










	<!-- <h2>Hello World!</h2> -->
	<!-- <table width="1200">
		<tr>
			<td>
				<div width="1200">
					<a onload="iframeTimeOut('http://localhost:8080/webportal/bpm/workingProcess/hrmsWorking', '加载失败...', 1200, 600)"></a>
					
					<iframe width="100%" id="aa" frameborder="0" height="600" name="aa"
						src="http://localhost:8080/webportal/bpm/workingProcess/hrmsWorking"
						border="0"></iframe>
				</div>
			</td>
		</tr>
	</table> -->
</body>
</html>
