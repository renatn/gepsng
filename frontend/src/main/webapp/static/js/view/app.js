var app = app || {};

$(function( $ ) {

    // The Application
    // ---------------

    // Our overall **AppView** is the top-level piece of UI.
    app.AppView = Backbone.View.extend({

        el: '#gepsapp',

        events: {
            'click #createThread': 'createThread',
            'click #select-all': 'selectAll'
        },

        initialize: function() {
            console.log('init app view');
            this.table = $('#messages tbody');
            this.allCheckbox = $('#select-all')[0];

            app.Messages.on('add', this.addOne, this );
            app.Messages.on('reset', this.addAll, this );

            app.Messages.fetch();
        },

        render: function() {
            console.log('render app view');
            return this;
        },

        addOne: function(message) {
            console.log('add one');
            var view = new app.MessageView({model: message});
            this.table.append(view.render().el);
        },

        addAll: function() {
            console.log('add all');
            this.table.empty();
            app.Messages.each(this.addOne, this);
        },

        selectAll: function() {
            console.log("Select all");
            var selected = this.allCheckbox.checked;

            app.Messages.each(function(message) {
                message.setSelected(selected);
            });
        },

        createThread: function() {
            console.log('Create thread');
        }

    });
});