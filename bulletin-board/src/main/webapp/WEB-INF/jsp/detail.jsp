<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>公告詳細內容</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <div class="card">
            <div class="card-header">
                <h2>${bulletinBoard.title}</h2>
            </div>
            <div class="card-body">
                <div class="row mb-3">
                    <div class="col-md-6">
                        <p class="text-muted">
                            <strong>發布者：</strong> ${bulletinBoard.publisher}
                        </p>
                    </div>
                    
                </div>
                
                <div class="row mb-3">
                    <div class="col-md-6">
                        <p class="text-muted">
                            <strong>開始日期：</strong> ${bulletinBoard.startDate}
                        </p>
                    </div>
                    <div class="col-md-6">
                        <p class="text-muted">
                            <strong>結束日期：</strong> ${bulletinBoard.endDate}
                        </p>
                    </div>
                </div>

                <div class="mb-4">
                    <h5>公告內容：</h5>
                    <p style="white-space: pre-line;">${bulletinBoard.content}</p>
                </div>

                <div class="mt-4">
                    <a href="/ROOT/home" class="btn btn-secondary">返回列表</a>
                    <a href="/board/edit/${bulletinBoard.id}" class="btn btn-primary">編輯公告</a>
                    <button class="btn btn-danger" onclick="confirmDelete(${bulletinBoard.id})">刪除公告</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        function confirmDelete(id) {
            if (confirm('確定要刪除這則公告嗎？')) {
                window.location.href = '/board/delete/' + id;
            }
        }
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>