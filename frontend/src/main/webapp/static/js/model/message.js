var app = app || {};

(function() {

    // Message Model
    // ----------

    app.Message = Backbone.Model.extend({

        url: '/geps/api/messages',

        defaults: {
            messageId: '',
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
