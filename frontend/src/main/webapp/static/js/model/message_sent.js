var app = app || {};

(function() {

    // Message Model
    // ----------

    app.Message = Backbone.Model.extend({

        url: function() {
            var base = '/geps/api/v1/users/'+this.toId;
            if (this.isNew()) {
                return base;
            }
            return base  + '/' + this.id;
        },

        idAttribute: "messageId",

        defaults: {
            messageId: null,
            subject: '',
            text: '',
            sender: 'Борцов Дмитрий',
            recipient: '',
            sendDate: null,
            updateDate: null,
            selected: false,
            toId: 555
        },

        validate: function(attrs) {

            if (!attrs.action) {
                return false;
            }

            var errors = [];

            if (!attrs.recipient) {
                errors.push({name:'recipient', message:'Не выбран получатель'});
            }

            if (!attrs.subject) {
                errors.push({name:'subject', message:'У обращения отсутствует тема'});
            }

            if (!attrs.text) {
                errors.push({name:'text', message:'У обращения отсутствует текст'});
            }

            return errors.length > 0 ? errors : false;
        }


    });

}());
