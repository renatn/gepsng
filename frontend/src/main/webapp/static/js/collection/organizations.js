var app = app || {};

(function() {

    // Organization Collection
    // ------------------

    app.OrganizationList = Backbone.Collection.extend({

        url: '/geps/api/v1/users',

        // Reference to this collection's model.
        model: app.User,

        initialize: function() {
            console.log('init message list collection')
        }

    });

}());
