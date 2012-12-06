var app = app || {};

(function($) {

    // Message View
    // --------------

    app.MessageRowView = Backbone.View.extend({

        tagName: 'tr',

        events: {
            'click'  : 'viewMessage'
        },

        template: _.template($('#message-row-template').html()),

        initialize: function() {
            console.log('init message row view');
            this.model.on( 'change', this.render, this );
        },

        render: function() {
            console.log('render message');
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        viewMessage: function() {
            console.log('Click on: ' + this.model.get('messageId'));

            var target = '/messages/' + this.model.get('messageId');
            if (!this.model.get('sendDate')) {
                target += '/edit';
            }

            app.GepsRouter.navigate(target, true);
        }

    });
})(jQuery);
