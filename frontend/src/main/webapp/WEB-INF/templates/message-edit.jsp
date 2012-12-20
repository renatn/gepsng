<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         session="false" %>

<script type="text/template" id="select-organization-dialog-template">
    <div class="modal hide fade selectOrganizationDialog" tabindex="-1"  role="dialog">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4>Выбор получателя обращения</h4>
        </div>
        <div class="modal-body">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#">Ведомства</a>
                </li>
                <li><a href="#">Частные организации</a></li>
                <li><a href="#">Группы</a></li>
            </ul>

            <ul class="nav nav-list">
                <li class="nav-header">Популярные</li>
                <li class="nav-header">Все</li>
            </ul>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal" aria-hidden="true">Закрыть</button>
        </div>
    </div>
</script>

<script type="text/template" id="message-edit-template">

    <a class="btn btn-primary send disabled">Отправить</a>
    <a class="btn sendSigned disabled">Подписать и отправить</a>
    <a class="btn save">В черновики</a>
    <a class="btn delete">Удалить</a>
    <a class="btn pull-right back">Назад</a>

    <form class="form-horizontal">
        <fieldset>
            <legend>Создать обращение</legend>
            <div class="control-group">
                <label class="control-label">Отправитель</label>
                <div class="controls">
                    <input type="text" class="input-xlarge" value="{{ sender.name }}" disabled>
                </div>
            </div>
            <div id="field-recipient" class="control-group">
                <label class="control-label">Кому</label>
                <div class="controls">
                    <input id="recipient"
                           class="input-xlarge"
                           type="text"
                           value="{{ recipient && recipient.name }}"
                           placeholder="Выбрать получателя…" >
                    <span class="help-inline"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">Тип обращения</label>
                <div class="controls">
                    <select class="input-xlarge">
                        <option>Предложение</option>
                        <option>Заявление</option>
                        <option>Жалоба</option>
                    </select>
                </div>
            </div>
            <div id="field-subject" class="control-group" >
                <label class="control-label">Тема обращения</label>
                <div class="controls">
                    <input type="text"
                           id="subject"
                           name="subject"
                           class="input-xlarge"
                           value="{{ subject }}"
                           placeholder="Заполните тему…">
                    <span class="help-inline"></span>
                </div>
            </div>
            <div id="field-text" class="control-group">
                <label class="control-label">Обращение</label>
                <div class="controls">
                    <textarea id="text"
                              name="text"
                              rows="10"
                              class="input-xlarge">{{ text }}</textarea>
                    <span class="help-inline"></span>
                </div>
            </div>
        </fieldset>
    </form>

    <a class="btn btn-primary send disabled">Отправить</a>
    <a class="btn sendSigned disabled">Подписать и отправить</a>
    <a class="btn save">В черновики</a>
    <a class="btn delete">Удалить</a>
    <a class="btn pull-right back">Назад</a>

    <div class="dialogHolder"></div>
</script>
