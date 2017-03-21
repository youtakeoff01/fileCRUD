<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- styles -->
<link rel="stylesheet" href="mycss/select.dataTables.css" />
<link rel="stylesheet" href="css/dataTables.min.css" />

<!-- script -->
<script type="text/javascript" src="myjs/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="myjs/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="myjs/jquery.dataTables.bootstrap.js"></script>
<script type="text/javascript" src="myjs/dataTables.sort.plungin.js"></script>

<title></title>

</head>
<body>
	<div id="busSelectDiv" class="table-responsive">
		<table id="samp-table"
			class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>表头1</th>
					<th>表头2</th>
					<th>表头3</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>7</td>
					<td>A</td>
					<td>1</td>
					<td>已拒绝</td>
				</tr>
				<tr>
					<td>2</td>
					<td>G</td>
					<td>2</td>
					<td>已审批</td>
				</tr>
				<tr>
					<td>1</td>
					<td>B</td>
					<td>3</td>
					<td>审核中</td>
				</tr>
				<tr>
					<td>4</td>
					<td>C</td>
					<td>4</td>
					<td>未确认</td>
				</tr>
				<tr>
					<td>5</td>
					<td>h</td>
					<td>6</td>
					<td>已归档</td>
				</tr>
			</tbody>
		</table>
	</div>

<script type="text/javascript">
	/* jQuery.fn.dataTableExt.oSort['string-case-asc']  = function(x,y) {
		alert("dfdfdfd");
	  	var orderArr = ["未确认","审核中","已审批","已拒绝","已归档"];
		var io1 = orderArr.indexOf(x);
		var io2 = orderArr.indexOf(y);
		return io1 - io2;
	};
	   
	jQuery.fn.dataTableExt.oSort['string-case-desc'] = function(x,y) {
		alert("dsfdsf");
	  	var orderArr = ["未确认","审核中","已审批","已拒绝","已归档"];
		var io1 = orderArr.indexOf(x);
		var io2 = orderArr.indexOf(y);
		return io2 - io1;
	}; */
	
	$(document).ready(function() {
		dataTable = $('#myDataTables').DataTable({
			"dom" : "<<'toolbar'>rt<'rightDiv' p><'rightDiv' i>>",
			/* "columnDefs" : [ {"targets" : [ 0 ],"visible" : false,"searchable" : false} ], */
			/* "order" : [ [ 5, "desc" ] ], */
			"aoColumnDefs": [{ "sType": "string-case", "aTargets": [4] }]   //指定列号使用自定义排序
		});
	});
</script>

</body>
</html>
