var app = app || {};

(function() {

    // Message Collection
    // ------------------

    app.MessageList = Backbone.Collection.extend({

        url: '/geps/api/v1/messages',

        // Reference to this collection's model.
        model: app.Message,

        initialize: function() {
            console.log('init message list collection')
        }

    });

}());
