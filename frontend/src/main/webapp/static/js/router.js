var app = app || {};

$(function($) {

    // GEPS Router
    // ----------

    var Workspace = Backbone.Router.extend({
        routes:{
            'threads/edit': 'showEdit'
        },

        showEdit: function( param ) {
            console.log('route - edit - params:' + param);
            var message = new app.Message();
            $("#gepsapp").html(new app.MessageEditView({model: message}).el);
        }
    });

    app.GepsRouter = new Workspace();
    Backbone.history.start();

});