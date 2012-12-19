var app = app || {};

(function() {

    // User Model
    // ----------

    app.User = Backbone.Model.extend({

        url: function() {
            var base = '/geps/api/v1/users';
            if (this.isNew()) {
                return base;
            }
            return base + '/' + this.id;
        },

        idAttribute: "userId",

        defaults: {
            userId: null,
            name: ''
        }

    });

}());
