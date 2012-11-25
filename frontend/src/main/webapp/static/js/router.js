var app = app || {};

(function() {

    // GEPS Router
    // ----------

    var Workspace = Backbone.Router.extend({
        routes:{
            'edit': 'showEdit'
        },

        showEdit: function( param ) {
            console.log('route - edit - params:' + param);
        }
    });

    app.GepsRouter = new Workspace();
    Backbone.history.start();

}());