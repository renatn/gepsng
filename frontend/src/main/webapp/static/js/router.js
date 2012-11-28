var app = app || {};

$(function ($) {

    // GEPS Router
    // ----------

    var Workspace = Backbone.Router.extend({

        routes: {
            '': 'default',
            'messages/edit': 'showEdit',
            'messages/:id' : 'showView'
        },

        default: function () {
            console.log('route - default');
            new app.MainView();
        },

        showEdit: function (param) {
            console.log('route - edit - params:' + param);
            var message = new app.Message();
            $("#gepsapp").html(new app.MessageEditView({model: message}).render().el);
        },

        showView: function(messageId) {
            console.log('route - view - params:' + messageId);
            var message = new app.Message({'messageId':messageId});
            message.fetch({success : function() {
                console.log('message fetched');
                $("#gepsapp").html(new app.MessageView({model: message}).render().el);
            }});
        }
    });

    app.GepsRouter = new Workspace();
    Backbone.history.start();

});