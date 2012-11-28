var app = app || {};

(function( $ ) {

    // The Application
    // ---------------

    app.MessageListView = Backbone.View.extend({

        el: '#gepsapp',

        events: {
            'click #select-all': 'selectAll'
        },

        initialize: function() {
            console.log('init list view');
            this.table = $('#messages tbody');
            this.allCheckbox = $('#select-all')[0];

            app.Messages.on('add', this.addOne, this );
            app.Messages.on('reset', this.addAll, this );

            app.Messages.fetch();
        },

        render: function() {
            console.log('render list view');
            return this;
        },

        addOne: function(message) {
            console.log('add one');
            this.table.append((new app.MessageRowView({model: message})).render().el);
        },

        addAll: function() {
            console.log('add all');
            this.table.empty();
            app.Messages.each(this.addOne, this);
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