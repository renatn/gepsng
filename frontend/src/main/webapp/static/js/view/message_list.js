var app = app || {};

(function( $ ) {

    // The Application
    // ---------------

    app.MessageListView = Backbone.View.extend({

        el: '#gepsapp',

        events: {
            'click #select-all': 'selectAll'
        },

        template: _.template($('#messages-table-template').html()),

        initialize: function() {
            console.log('init list view');
            this.render();
            app.Messages.on('reset', this.addAll, this );
            app.Messages.fetch();
        },

        render: function() {
            console.log('render list view');
            this.$el.html(this.template({}));
            return this;
        },

        addMessage: function(message) {
            console.log('add one');
            $('tbody', this.el).append((new app.MessageRowView({model: message})).render().el);
        },

        addAll: function() {
            console.log('add all');
            $('.loading', this.el).hide();
            $('table', this.el).removeClass('hide');
            app.Messages.each(this.addMessage, this);
        },

        selectAll: function() {
            var selected = this.$el.find('#select-all')[0].checked;

            app.Messages.each(function(message) {
                message.setSelected(selected);
            });
        }

    });
})(jQuery);