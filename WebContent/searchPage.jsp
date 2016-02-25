<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/sortable.css">
<script src="js/sort_table.js" type="text/javascript"></script>


</head>

<body>
	<div class="container" id="wrapper">
		<h1>Crowd-Sourcing Dataset Searching</h1>
		<div class="filter" , id="filter">
			<form method="get" action="getFileList">
				<fieldset>
					<legend>Filters</legend>
					<em>Data Type:</em> all<input type="checkbox" name="data-type"
						value="all"> text <input type="checkbox" name="data-type"
						value="text"> image <input type="checkbox"
						name="data-type" value="image"> text/image <input
						type="checkbox" name="data-type" value="text/image"><br />
					<em>Eventual Goal: </em> all<input type="checkbox" name="goal"
						value="all"> cluster <input type="checkbox" name="goal"
						value="cluster"> classify <input type="checkbox"
						name="goal" value="classify"> regression <input
						type="checkbox" name="goal" value="regression"><br /> <em>
						Keyword: </em> <input type="text" name="keyword"> <input
						type="submit" value="search" >
				</fieldset>
			</form>
		</div>


<%-- 		<span><c:out value="${datasets}.datasets"></c:out></span> --%>
		<div class="component">
			<table id="keywords" class="sortable" cellspacing="0" cellpadding="0">
				<thead class="sortable">
					<tr>
						<th><span>ID</span></th>
						<th><span>FileName</span></th>
						<th><span>Type</span></th>
						<th><span>Goal</span></th>
						<th><span>Size</span></th>
						<th><span>Link</span></th>
					</tr>
				</thead>
				<tbody id = "table-body">
					<script>
						var json = ${datasets};
						var table = document.getElementById("table-body")
							
						for (i = 0; i < json.datasets.length; i++) {
							var row = table.insertRow(0);
							
							var id = row.insertCell(0);
							var file = row.insertCell(1);
							var type = row.insertCell(2);
							var goal = row.insertCell(3);
							var size = row.insertCell(4);
							var link = row.insertCell(5);
							
							id.innerHTML = json.datasets[i].id;
							file.innerHTML = json.datasets[i].name;
							type.innerHTML = json.datasets[i].type;
							goal.innerHTML = json.datasets[i].goal;
							size.innerHTML = json.datasets[i].size;
							link.innerHTML =  '<a href='+ json.datasets[i].link + '>'+"Download" + '</a>';;
							
							}
					</script>
				</tbody>
			</table>
		</div>
	</div>
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-throttle-debounce/1.1/jquery.ba-throttle-debounce.min.js"></script>
	<script src="js/jquery.stickyheader.js"></script>
</body>
</html>