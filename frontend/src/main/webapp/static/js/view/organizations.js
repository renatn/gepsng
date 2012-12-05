var app = app || {};

(function($) {

    app.OrganizationsView = Backbone.View.extend({

        template : _.template($('#select-organization-dialog-template').html()),

        events: {
            'click .organization' : 'organizationSelected'
        },

        initialize: function() {
            console.log('init dialog organization view');
        },

        render: function() {
            this.$el.html(this.template());
            this.$dialog = $('.selectOrganizationDialog', this.el);
            this.$dialog.modal({show:false});
            return this;
        },

        show: function(callback) {
            console.log('show dialog organization');
            this.onSelectedItem = callback;
            this.$dialog.modal('toggle');
        },

        organizationSelected: function(event) {
            console.log('selected: ' + event.target.text);
            this.$dialog.modal('toggle');
            this.onSelectedItem(event.target.text);
        }

    });


})(jQuery);