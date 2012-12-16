var app = app || {};

(function() {

    // Message Model
    // ----------

    app.Message = Backbone.Model.extend({

        url: function() {

            var target = '/geps/api/v1/messages';

            // Send message
            if (this.get('action') == 'send') {
                console.log('send action url');
                return '/geps/api/v1/users/10050';
            }

            // Create draft
            if (this.isNew() ) {
                return target;
            }

            // Update draft
            return target  + '/' + this.id;
        },

        idAttribute: "messageId",

        defaults: {
            messageId: null,
            subject: '',
            text: '',
            sender: null,
            recipient: null,
            sendDate: null,
            updateDate: null,
            selected: false,
            action: null
        },

        validate: function(attrs) {

            if (!attrs.action) {
                return false;
            }

            var errors = [];

            if (!attrs.recipient) {
                errors.push({name:'recipient', message:'Не выбран получатель'});
            }

            if (!attrs.subject) {
                errors.push({name:'subject', message:'У обращения отсутствует тема'});
            }

            if (!attrs.text) {
                errors.push({name:'text', message:'У обращения отсутствует текст'});
            }

            return errors.length > 0 ? errors : false;
        },

        setSelected: function(selected) {
            this.set({'selected':selected});
        }

    });

}());
