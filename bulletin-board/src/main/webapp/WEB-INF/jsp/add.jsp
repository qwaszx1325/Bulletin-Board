<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新增公告</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.tiny.cloud/1/6st0qrkyk3zq5hfpjm3p00vv2hqbit50u96ez82udc5s9ob3/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
    <script src="https://cdn.jsdelivr.net/npm/tinymce-i18n@23.7.24/langs5/zh_TW.min.js"></script>
    <style>
        .error-message {
            color: red;
            font-size: 0.875rem;
            margin-top: 0.25rem;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <div class="card">
                <div class="card-header">
                    <h2 class="mb-0">新增公告</h2>
                </div>
                <div class="card-body">
                    <form action="/ROOT/createBoard" method="post" id="bulletinForm" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label for="title" class="form-label">標題</label>
                            <input type="text" class="form-control" id="title" name="title" required>
                            <div class="error-message" id="titleError"></div>
                        </div>
                        
                        <div class="mb-3">
                            <label for="publisher" class="form-label">發布者</label>
                            <input type="text" class="form-control" id="publisher" name="publisher" required>
                            <div class="error-message" id="publisherError"></div>
                        </div>
                        
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="startDate" class="form-label">開始日期</label>
                                <input type="date" class="form-control" id="startDate" name="startDate" required>
                                <div class="error-message" id="startDateError"></div>
                            </div>
                            
                            <div class="col-md-6 mb-3">
                                <label for="endDate" class="form-label">結束日期</label>
                                <input type="date" class="form-control" id="endDate" name="endDate" required>
                                <div class="error-message" id="endDateError"></div>
                            </div>
                        </div>
                        
                        <div class="mb-3">
                            <label for="content" class="form-label">內容</label>
                            <textarea id="content" name="content"></textarea>
                            <div class="error-message" id="contentError"></div>
                        </div>
                        
                        <div class="text-center mt-4">
                            <button type="submit" class="btn btn-primary mx-2">送出</button>
                            <a href="/ROOT/home" class="btn btn-secondary mx-2">返回</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    tinymce.init({
        selector: '#content',
        language: 'zh_TW',
        plugins: [
            'advlist', 'autolink', 'lists', 'link', 'image', 'charmap', 'preview',
            'anchor', 'searchreplace', 'visualblocks', 'code', 'fullscreen',
            'insertdatetime', 'media', 'table', 'help', 'wordcount'
        ],
        toolbar: 'undo redo | blocks | ' +
                'bold italic forecolor | alignleft aligncenter ' +
                'alignright alignjustify | bullist numlist outdent indent | ' +
                'removeformat | image | help',
        menubar: 'file edit view insert format tools table help',
        content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }',
        height: 500,
        images_upload_base64: true,
        images_upload_handler: function (blobInfo, progress) {
            return new Promise((resolve, reject) => {
                const reader = new FileReader();
                reader.readAsDataURL(blobInfo.blob());
                reader.onload = () => {
                    resolve(reader.result);
                };
                reader.onerror = (error) => reject(error);
            });
        },
        setup: function(editor) {
            editor.on('change', function() {
                editor.save();
            });
        }
    });

    document.getElementById('bulletinForm').addEventListener('submit', function(e) {
        e.preventDefault();
        
        // 清除之前的錯誤訊息
        document.querySelectorAll('.error-message').forEach(el => el.textContent = '');
        
        let hasError = false;
        
        // 驗證標題
        const title = document.getElementById('title').value.trim();
        if (!title) {
            document.getElementById('titleError').textContent = '請輸入標題';
            hasError = true;
        }
        
        // 驗證發布者
        const publisher = document.getElementById('publisher').value.trim();
        if (!publisher) {
            document.getElementById('publisherError').textContent = '請輸入發布者';
            hasError = true;
        }
        
        // 驗證日期
        const startDate = document.getElementById('startDate').value;
        const endDate = document.getElementById('endDate').value;
        
        if (!startDate) {
            document.getElementById('startDateError').textContent = '請選擇開始日期';
            hasError = true;
        }
        
        if (!endDate) {
            document.getElementById('endDateError').textContent = '請選擇結束日期';
            hasError = true;
        }
        
        if (startDate && endDate && startDate > endDate) {
            document.getElementById('endDateError').textContent = '結束日期不能早於開始日期';
            hasError = true;
        }
        
        // 驗證內容
        const content = tinymce.get('content').getContent().trim();
        if (!content) {
            document.getElementById('contentError').textContent = '請輸入內容';
            hasError = true;
        }
        
        if (!hasError) {
            this.submit();
        }
    });

    // 設定開始日期的最小值為今天
    const today = new Date().toISOString().split('T')[0];
    document.getElementById('startDate').setAttribute('min', today);
    
    // 當開始日期改變時，設定結束日期的最小值
    document.getElementById('startDate').addEventListener('change', function() {
        document.getElementById('endDate').setAttribute('min', this.value);
    });
</script>
</body>
</html>