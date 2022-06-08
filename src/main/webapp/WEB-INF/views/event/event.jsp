<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Event</title>
<style>
	.event-main{
		width:100%;
		
	}
	.event-table{
		width:60%;
		text-align: center;
		margin:auto;
		margin-top: 30px;
		margin-bottom: 30px;
		 border-collapse: collapse
	}
	.event-table th{
		padding: 10px;
	}
	.event-tr{
		border-bottom: 1px solid black;
	}
</style>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="event-main">
		<table class="event-table">
			<tr>
				<th>TITLE</th>
				<td>무료배송 EVENT</td>
			</tr>
			<tr class="event-tr">
				<th>DATE</th>
				<td>2022-04-15</td>
			</tr>
			<tr>
				<td colspan="2">
						<img src="/upload/event/event.png">
				</td>
			</tr>
		</table>
	</div>
<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>