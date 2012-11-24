var app = app || {};

(function() {

    // Message Model
    // ----------

    app.Message = Backbone.Model.extend({

        defaults: {
            messageId: '',
            subject: '',
            text: '',
            send_date: 'None',
            selected: false
        },

        setSelected: function(selected) {
            this.selected = selected;
        }

    });

}());
