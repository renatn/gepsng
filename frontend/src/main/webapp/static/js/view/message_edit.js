var app = app || {};

(function($) {

    // Message Edit View
    // --------------

    app.MessageEditView = Backbone.View.extend({

        template: _.template($('#message-edit-template').html()),

        events: {
            'click .save' : 'save',
            'click .back' : function(){app.GepsRouter.navigate('!/', true)},
            'click .send' : 'send',
            'click #recipient' : 'selectTo'
        },

        initialize: function() {
            console.log('init message edit view');
            this.selectOrganizationDialog = new app.OrganizationsView();

            this.model.on('error', this.onValidationError);
            this.model.on('sync', this.onMessageSaved, this);
        },

        render: function() {
            console.log('render message edit');

            this.$el.html(this.template(this.model.toJSON()));
            $('.dialogHolder', this.el).html(this.selectOrganizationDialog.render().el);

            if (this.model.get('messageId')) {
                this.$el.find('.send').removeClass('disabled');
            }

            return this;
        },

        save: function() {
            console.log("save message");

            var change = this.readForm();
            if (this.model.set(change)) {
                this.model.save();
            }
        },

        readForm: function() {
            return  {
                'subject': $("#subject").val(),
                'text': $("#text").val()
            };
        },

        send: function() {
            console.log('send message');
            var change = this.readForm();

            _.extend(change, {action:'send', messageId:null});
            if (this.model.set(change)) {
                this.model.save();
            }
        },

        selectTo: function() {
            console.log('select to');
            var callback = _.bind(this.onOrganizationSelected, this);
            this.selectOrganizationDialog.show(callback);
        },

        onOrganizationSelected: function(organization) {
            console.log('organization selected: ' + organization.get('name'));
            $('#recipient').val(organization.get('name'));
            this.model.set({'recipient': organization.toJSON()});
        },

        onValidationError: function(model, errors) {
            console.log('validation error');
            _.each(errors, function(error){
                var field = $('#field-'+error.name);
                field.addClass("error");
                field.find(".help-inline").text(error.message);
            });
        },

        onMessageSaved: function(event, model) {
            console.log('message saved to server');
            this.hideErrors();
            app.GepsRouter.navigate('!/messages/'+model.messageId+'/edit', false);
            this.$el.find('.send').removeClass('disabled');
        },

        hideErrors: function() {
            this.$('.control-group').removeClass('error');
            this.$('.help-inline').text('');
        },

        finalize: function() {
            console.log('message view finalize');
            this.selectOrganizationDialog.remove();
            this.$el.unbind();
            this.model.unbind();
        }

    });
})(jQuery);