var app = app || {};

(function() {

    // Message Model
    // ----------

    app.Message = Backbone.Model.extend({

        url: function() {
            return '/geps/api/messages/' + this.get('messageId');
        },

        defaults: {
            messageId: null,
            subject: '',
            text: '',
            send_date: 'None',
            selected: false
        },


        setSelected: function(selected) {
            this.set({'selected':selected});
        }

    });

}());
