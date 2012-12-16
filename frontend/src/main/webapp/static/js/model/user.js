var app = app || {};

(function() {

    // User Model
    // ----------

    app.User = Backbone.Model.extend({

        url: '',

        idAttribute: "userId",

        defaults: {
            userId: null,
            name: ''
        }

    });

}());
