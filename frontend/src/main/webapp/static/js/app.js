var app = app || {};

(function($) {
    console.log('Start application...');

    app.MainView = Backbone.View.extend({

        el: '#gepsapp',

        events: {
            'click #select-all': 'selectAll'
        },

        initialize: function() {
            console.log('init main view');
            this.allCheckbox = $('#select-all')[0];
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
            $(this.content).html((new app.MessageListView()).render().el);
        },

        selectAll: function() {
            console.log("select all messages");
            var selected = this.allCheckbox.checked;

            app.Messages.each(function(message) {
                message.setSelected(selected);
            });
        }
    });



})($);