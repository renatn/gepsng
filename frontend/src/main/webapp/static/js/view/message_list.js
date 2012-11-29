var app = app || {};

(function( $ ) {

    // The Application
    // ---------------

    app.MessageListView = Backbone.View.extend({

        tagName: 'div',

        events: {
            'click #select-all': 'selectAll'
        },

        template: _.template($('#messages-table-template').html()),

        initialize: function() {
            console.log('init list view');
        },

        render: function() {
            console.log('render list view');
            this.$el.html(this.template({}));
            app.Messages.each(this.addMessage, this);
            return this;
        },

        addMessage: function(message) {
            console.log('add one');
            $('tbody', this.el).append((new app.MessageRowView({model: message})).render().el);
        },

        selectAll: function() {
            var selected = this.$el.find('#select-all')[0].checked;

            app.Messages.each(function(message) {
                message.setSelected(selected);
            });
        }

    });
})(jQuery);