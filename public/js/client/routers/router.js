define(['namespace', 'backboneRouteFilter', '../views/menu','../views/qrcode'],
    function (App, Backbonefilter, Menu, QRCode,undefined) {
        App.client.routers.Router = Backbone.Router.extend({

            routes: {
                'client/': 'qrcode',
                'client/checkOrder': 'checkOrder',
                'client/*actions': 'menu'
            },

            initialize: function () {
                function setCookie(c_name, value, exdays) {
                    var exdate = new Date();
                    exdate.setDate(exdate.getDate() + exdays);
                    var c_value = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toUTCString());
                    document.cookie = c_name + "=" + c_value;
                }

                var isUUID = false;
                document.cookie.split(';').forEach(function (item) {
                    if (item.split('=')[0] == ' uuid') isUUID = true
                });
                if (!isUUID) {
                    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
                        var r = Math.random() * 16 | 0, v = c == 'x' ? r : r & 0x3 | 0x8;
                        return v.toString(16);
                    });
                    document.cookie += setCookie('uuid', uuid, 3650)
                }
            },

            before: function () {
                $('*').off();
                //todo this is bad !!!

                $('head').append(' <link rel="stylesheet" type="text/css" media="screen" href="/css/client/theme.css"/>')

            },
            after: function () {
                //the function will put active the menu selected.
                // to use give add class menuBasedOnName or menuBasedOnId to the div with the menu
                // and then add a data-reference with the id or name to each li of the menu
                // the function will try to find the name/id in the url
                var activeNav = function (idOrName) {
                    if ($(idOrName).length !== 0) {
                        _.each($(idOrName + ' .nav li'), function (item) {
                            if (Backbone.history.getHash().split('/').indexOf(item.dataset.reference) !== -1) {
                                item.className = item.className + ' active';
                            }
                        });
                    }
                }
                activeNav('.menuBasedOnName');
                activeNav('.menuBasedOnId')
            },

            menu: function () {


                new Menu();
            },

            qrcode: function () {
                new QRCode();
            },

            checkOrder: function () {

            }

        });
        return App.client.routers.Router;
    });
