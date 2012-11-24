var app = app || {};

(function() {
    'use strict';

    // Message Collection
    // ---------------

    var MessageList = Backbone.Collection.extend({

        url: '/geps/api/messages',

        // Reference to this collection's model.
        model: app.Message,

        initialize: function() {
        }

    });

    // Create our global collection of **Todos**.
    app.Messages = new MessageList();

}());
