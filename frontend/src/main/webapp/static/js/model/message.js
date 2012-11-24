var app = app || {};

(function() {
    'use strict';

    // Message Model
    // ----------

    app.Message = Backbone.Model.extend({

        defaults: {
            id: '',
            from: '',
            subject: '',
            text: '',
            send_date: ''
        }

    });

}());
