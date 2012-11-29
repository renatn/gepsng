var app = app || {};

(function($) {
    console.log('Start application...');

    app.OrganizationsView = Backbone.View.extend({

        el: '#selectOrganizationDialog',

        events: {
            'click .organization' : 'organizationSelected'
        },

        initialize: function() {
            console.log('init dialog organization view');
        },

        show: function(callback) {
            console.log('show dialog organization');
            this.onSelectedItem = callback;
            this.$el.modal();
        },

        organizationSelected: function(event) {
            console.log('selected: ' + event.target.text);
            this.$el.modal('toggle');
            this.onSelectedItem(event.target.text);
        }

    });



})($);