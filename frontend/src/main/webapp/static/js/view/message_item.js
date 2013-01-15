var app = app || {};

(function($) {

    // Message View
    // --------------

    app.MessageItemView = Backbone.View.extend({

        tagName: 'li',

        events: {
            'click'  : 'openMessage'
        },

        template: _.template($('#message-item-template').html()),

        initialize: function() {
            console.log('init message item view');
            this.model.on('change', this.render, this );
        },

        render: function() {
            console.log('render message');
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        openMessage: function() {
            console.log('Click on: ' + this.model.get('messageId'));

            var target = '!/messages/' + this.model.get('messageId');
            if (!this.model.get('sendDate')) {
                target += '/edit';
            }

            app.GepsRouter.navigate(target, true);
        }

    });
})(jQuery);
