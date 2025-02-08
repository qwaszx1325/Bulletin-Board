<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>公告欄管理系統</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-4">
		<h2 class="mb-4">公告欄列表</h2>

		<!-- 新增公告按鈕 -->
		<div class="mb-3">
			<a href="/bulletin-board/add" class="btn btn-success">新增公告</a>
		</div>

		<!-- 公告列表 -->
		<table class="table table-striped">
			<thead>
				<tr>
					<th>標題</th>
					<th>發布者</th>
					<th>發布日期</th>
					<th>開始日期</th>
					<th>結束日期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bulletinBoard}" var="board">
					<tr>
						<td><a href="/bulletin-board/detail/${board.id}"
							class="text-decoration-none text-primary"> ${board.title} </a></td>
						<td>${board.publisher}</td>
						<td>${board.publishDate}</td>
						<td>${board.startDate}</td>
						<td>${board.endDate}</td>
						<td><a href="/bulletin-board/edit/${board.id}"
							class="btn btn-primary btn-sm">修改</a> <a
							href="/bulletin-board/deleteBoard/${board.id}" class="btn btn-danger btn-sm"
							onclick="return confirm('確定要刪除這則公告嗎？')">刪除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- 分頁 -->
		<nav>
			<ul class="pagination justify-content-center">
				<c:if test="${currentPage > 1}">
					<li class="page-item"><a class="page-link"
						href="/bulletin-board/home?pageNumber=${currentPage-1}">上一頁</a></li>
				</c:if>

				<c:forEach begin="1" end="${totalPage}" var="i">
					<li class="page-item ${currentPage == i ? 'active' : ''}"><a
						class="page-link" href="/bulletin-board/home?pageNumber=${i}">${i}</a></li>
				</c:forEach>

				<c:if test="${currentPage < totalPage}">
					<li class="page-item"><a class="page-link"
						href="/bulletin-board/home?pageNumber=${currentPage+1}">下一頁</a></li>
				</c:if>
			</ul>
		</nav>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>