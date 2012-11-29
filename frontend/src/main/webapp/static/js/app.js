var app = app || {};

(function($) {

    app.MainView = Backbone.View.extend({

        el: '#gepsapp',

        initialize: function() {
            console.log('init main view');
            this.content = $('.geps-content')[0];
            app.Messages.on('reset', this.addAll, this );
            app.Messages.fetch();
        },

        render: function() {
            console.log('render app');
            return this;
        },

        addAll: function() {
            console.log('add all');
            this.$el.html((new app.MessageListView()).render().el);
        }

    });



})($);