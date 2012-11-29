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
            send_date: 'None',
            selected: false
        },


        setSelected: function(selected) {
            this.set({'selected':selected});
        }

    });

}());
