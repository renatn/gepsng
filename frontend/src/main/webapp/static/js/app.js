var app = app || {};

(function($) {

    app.MainView = Backbone.View.extend({

        el: '#gepsapp',

        initialize: function() {
            console.log('init main view');
            this.render();
        },

        render: function() {
            console.log('render app');
            this.$el.html((new app.MessageListView()).render().el);
            return this;
        }

    });


})(jQuery);