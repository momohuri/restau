require.config({
    paths: {
        'jquery': 'vendor/jquery/jquery.min',
        'underscore': 'vendor/underscore-amd/underscore',
        'Backbone': 'vendor/backbone',
        'namespace': 'namespace',
        'helpers': 'helpers/mainHelper',
        'backboneRouteFilter': 'vendor/backbone.routefilter',
        'bootstrap': 'vendor/bootstrap.min'
    },
    shim: {
        'Backbone': {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
        },
        'bootstrap': {
            deps: ['jquery'],
            exports: 'bootstrap'
        },
        'backboneRouteFilter': {
            deps: ['Backbone'],
            exports: 'backboneRouteFilter'
        }
    }
});

//require(['jquery', './templates', 'bootstrap','helpers']);

define(['namespace', 'Backbone', 'cockpit/routers/router','client/routers/router','./templates'], function (App, Backbone, cockpitRouter,clientRouter, undefined) {
    $(function () {
        App.cockpit.router = new cockpitRouter();
        App.client.router = new clientRouter();
        Backbone.history.start();
    });
});