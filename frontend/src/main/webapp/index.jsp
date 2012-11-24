<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>GEPS New Generation</title>
    <link href="static/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
    <h1>Hello World!!</h1>
    <div id="gepsapp">
        <table id="messages" class="table table-bordered">
            <thead>
                <tr>
                    <th>Номер</th>
                    <th>Отправитель</th>
                    <th>Тема</th>
                    <th>Дата</th>
                </tr>
            </thead>
            <tr>
                <td>100500</td>
                <td>ГИБДД</td>
                <td>Уведомление о штрафе</td>
                <td>24-11-2012</td>
            </tr>
        </table>
    </div>

    <script src="static/js/jquery-1.8.3.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
    <script src="static/js/underscore-min.js"></script>
    <script src="static/js/backbone-min.js"></script>
    <script src="static/js/model/message.js"></script>
    <script src="static/js/collection/messages.js"></script>
    <script src="static/js/view/app.js"></script>
    <script src="static/js/app.js"></script>
</body>
</html>