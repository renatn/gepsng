var app = app || {};

$(function() {

    // Message View
    // --------------

    app.MessageView = Backbone.View.extend({

        tagName: 'tr',

        template: _.template($('#message-template').html()),

        initialize: function() {
            this.model.on( 'change', this.render, this );
        },

        render: function() {
            console.log('render message');
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }

    });
});
