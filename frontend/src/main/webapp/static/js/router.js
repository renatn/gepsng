var app = app || {};

$(function ($) {

    // GEPS Router
    // ----------

    var Workspace = Backbone.Router.extend({

        routes: {
            '': 'default',
            'threads/edit': 'showEdit'
        },

        default: function () {
            console.log('route - default');
            new app.MessageListView();
        },

        showEdit: function (param) {
            console.log('route - edit - params:' + param);
            var message = new app.Message();
            $("#gepsapp").html(new app.MessageEditView({model: message}).render().el);
        }
    });

    app.GepsRouter = new Workspace();
    Backbone.history.start();

});