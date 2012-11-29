var app = app || {};

(function() {

    // Message Model
    // ----------

    app.Message = Backbone.Model.extend({

        url: function() {
            var target = '/geps/api/messages';
            var messageId = this.get('messageId');
            if (messageId) {
                target += '/' + messageId;
            }
            return target;
        },

        defaults: {
            messageId: null,
            subject: '',
            text: '',
            sender: 'Борцов Дмитрий',
            recipient: '',
            send_date: 'None',
            selected: false
        },

        validate: function(attrs) {

            if (!attrs.recipient) {
                return {'field':'recipient','text':'Не выбран получатель'};
            }

            if (!attrs.subject) {
                return {'field':'subject','text':'У обращения отсутствует тема'};
            }

            if (!attrs.text) {
                return {'field':'text','text':'У обращения отсутствует текст'};
            }

        },

        setSelected: function(selected) {
            this.set({'selected':selected});
        }

    });

}());
