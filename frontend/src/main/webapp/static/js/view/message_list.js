var app = app || {};

(function( $ ) {

    // The Application
    // ---------------

    app.MessageListView = Backbone.View.extend({

        tagName: 'div',

        template: _.template($('#messages-table-template').html()),

        initialize: function() {
            console.log('init list view');
        },

        render: function() {
            console.log('render list view');
            this.$el.html(this.template({}));
            app.Messages.each(this.addOne, this);
            return this;
        },

        addOne: function(message) {
            console.log('add one');
            $('tbody', this.el).append((new app.MessageRowView({model: message})).render().el);
        }

    });
})($);