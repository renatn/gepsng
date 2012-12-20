<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         session="false" %>

<script type="text/template" id="message-view-template">
    <p>
        <a class="btn btn-primary" href="#!/">Назад</a>
        <a class="btn" href="#!/">Удалить</a>
        <a class="btn" href="#!/">Версия для печати</a>
    </p>

    <div class="accordion" id="accordion2">
        <div class="accordion-group">
            <div class="accordion-heading">
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                    {{ messageId }} : {{ recipient.name }} <span class="pull-right">{{ sendDate }}</span>
                </a>
            </div>
            <div id="collapseOne" class="accordion-body collapse in">
                <div class="accordion-inner">
                    <p><strong>{{ subject }}</strong></p>
                    <p>{{ text }}</p>
                </div>
            </div>
        </div>
    </div>

    <p>
        <a class="btn btn-primary" href="#">Назад</a>
        <a class="btn" href="#">Удалить</a>
        <a class="btn" href="#">Версия для печати</a>
    </p>
</script>
