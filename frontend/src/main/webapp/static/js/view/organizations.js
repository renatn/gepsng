var app = app || {};

(function($) {

    app.OrganizationsView = Backbone.View.extend({

        template : _.template($('#select-organization-dialog-template').html()),

        events: {
            'click .organization' : 'organizationSelected'
        },

        initialize: function() {
            console.log('init dialog organization view');

            this.organizations = new app.OrganizationList();
            this.organizations.on('reset', this.addAll, this);
            this.organizations.fetch();
        },

        render: function() {
            this.$el.html(this.template());
            this.$dialog = $('.selectOrganizationDialog', this.el);
            this.$dialog.modal({show:false});
            return this;
        },

        addAll: function() {
            console.log('add all organizations');

            var popularOrganizations = '';
            this.organizations.each(function(organization){
                popularOrganizations += '<li><a class="organization">'+organization.get('name')+'</a></li>';
            });
            $('.nav-header').first().after(popularOrganizations);
            $('.nav-header').last().after(popularOrganizations);
        },

        show: function(callback) {
            console.log('show dialog organization');

            this.onSelectedItem = callback;
            this.$dialog.modal('toggle');
        },

        organizationSelected: function(event) {
            console.log('selected: ' + event.target.text);

            var index = $(event.target).parent().prevAll().length-1;
            var organization = this.organizations.at(index);

            this.$dialog.modal('toggle');
            this.onSelectedItem(organization);
        }

    });


})(jQuery);