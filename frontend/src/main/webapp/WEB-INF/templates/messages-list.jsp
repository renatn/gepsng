<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         session="false" %>

<script type="text/template" id="message-item-template">
    <div class="pull-left">
        <input type="checkbox">
    </div>
    <div class="pull-left overview">
        <span class="muted">{{ messageId }}</span>
    </div>
    <div class="pull-right">
        <span class="label label-success">{{ sendDate ? sendDate : 'Черновик' }}</span>
    </div>
    <div>
        <p><strong>{{ recipient.name }}</strong> <small class="muted">{{updateDate}}</small></p>
        <p>{{ subject }}</p>
    </div>
</script>

<script type="text/template" id="messages-table-template">

    <div class="toolbar">
        <a id="createThread" class="btn btn-primary" href="${pageContext.request.contextPath}/#!/messages/edit"><i class="icon-plus"></i>Создать обращение</a>
        <div class="btn-group">
            <a class="btn dropdown-toggle" data-toggle="dropdown">
                Создать из шаблона
                <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
                <li><a href="#!/templates/1">Статус обращения</a></li>
                <li><a href="#!/templates/2">Нет реакции на обращение</a></li>
                <li><a href="#!/templates/3">Использование интернет-портала</a></li>
            </ul>
        </div>

        <form class="form-search pull-right" style="display:inline">
            <div class="input-append">
                <input type="text" class="span3 search-query" placeholder="Поиск по госпочте">
                <button type="submit" class="btn">Search</button>
            </div>
        </form>

    </div>
    <br>

    <div class="alert loading">
        <strong>Загрузка...</strong>
    </div>

    <ul class="message-list"></ul>

    <div class="pagination pull-right">
        <ul>
            <li class="disabled"><a href="#">Пред</a></li>
            <li class="active"><a href="#">1</a></li>
            <li><a href="#?page=2">2</a></li>
            <li><a href="#?page=3">3</a></li>
            <li><a href="#?page=4">4</a></li>
            <li><a href="#?page=2">След</a></li>
        </ul>
    </div>

</script>