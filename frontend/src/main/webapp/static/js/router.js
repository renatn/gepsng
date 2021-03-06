var app = app || {};

$(function ($) {

    // GEPS Router
    // ----------

    var Workspace = Backbone.Router.extend({

        routes: {
            '': 'index',
            '!/': 'index',
            '!/messages/edit': 'showEdit',
            '!/messages/:id/edit': 'showEdit',
            '!/messages/:id' : 'showView'
        },

        index: function () {
            console.log('route - default');
            app.LayoutManager.show(new app.MessageListView());
        },

        showEdit: function (messageId) {
            console.log('route - edit - params:' + messageId);

            if (messageId) {
                var message = new app.Message({'messageId':messageId});

                message.fetch({success : function(){
                    console.log('message fetched');
                    app.LayoutManager.show(new app.MessageEditView({model: message}));
                    $("#gepsapp").html(app.currentView.render().el);
                }});

            } else {
                var user = new app.User({userId:'me'});
                user.fetch({success: function() {
                    var message = new app.Message({sender: user.toJSON()});
                    app.LayoutManager.show(new app.MessageEditView({model: message}));
                    $("#gepsapp").html(app.currentView.render().el);
                }});
            }
        },

        showView: function(messageId) {
            console.log('route - view - params:' + messageId);
            var message = new app.Message({'messageId':messageId});
            message.fetch({success : function() {
                console.log('message fetched');
                app.LayoutManager.show(new app.MessageView({model: message}));
            }});
        }
    });

    app.GepsRouter = new Workspace();
    Backbone.history.start();

});