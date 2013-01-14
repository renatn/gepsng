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

            this.messages = new app.MessageList();
            this.messages.on('reset', this.addAll, this);
            this.messages.fetch({error: this.errorFetchMessages});
        },

        render: function() {
            console.log('render list view');
            this.$el.html(this.template());
            return this;
        },

        errorFetchMessages: function(messages, response) {
            console.log('error fetch messages');
            console.log(response.status);
        },

        addMessage: function(message) {
            console.log('add one');
            $('.message-list', this.el).append((new app.MessageItemView({model: message})).render().el);
        },

        addAll: function() {
            console.log('add all');
            $('.loading', this.el).hide();
            $('table', this.el).removeClass('hide');
            this.messages.each(this.addMessage, this);
        },

        selectAll: function() {
            var selected = this.$el.find('#select-all')[0].checked;

            this.messages.each(function(message) {
                message.setSelected(selected);
            });
        },

        finalize: function() {
            console.log('message list view finalize');
            this.$el.unbind();
        }

    });
})(jQuery);