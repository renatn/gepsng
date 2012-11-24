var app = app || {};

$(function( $ ) {
    'use strict';

    // The Application
    // ---------------

    // Our overall **AppView** is the top-level piece of UI.
    app.AppView = Backbone.View.extend({

        el: '#gepsapp',

        initialize: function() {
            console.log('init app view');
            this.table = $('#messages tbody');

            app.Messages.on( 'add', this.addOne, this );
            app.Messages.on( 'reset', this.addAll, this );
            app.Messages.on( 'all', this.render, this );

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
        }


    });
});