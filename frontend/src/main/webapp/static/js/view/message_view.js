var app = app || {};

(function ($) {

    // Message View
    // --------------

    app.MessageView = Backbone.View.extend({

        el: '#gepsapp',

        template: _.template($('#message-view-template').html()),

        initialize: function () {
            console.log('init message view');
            this.render();
        },

        render: function () {
            console.log('render view message');
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }

    });
})(jQuery);