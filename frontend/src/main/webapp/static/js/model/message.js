var app = app || {};

(function() {
    'use strict';

    // Message Model
    // ----------

    app.Message = Backbone.Model.extend({

        defaults: {
            messageId: '',
            subject: '',
            text: ''
        }

    });

}());
