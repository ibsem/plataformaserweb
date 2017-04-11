'use strict';

/* Services */

/*
 http://docs.angularjs.org/api/ngResource.$resource

 Default ngResources are defined as

 'get':    {method:'GET'},
 'save':   {method:'POST'},
 'query':  {method:'GET', isArray:true},
 'remove': {method:'DELETE'},
 'delete': {method:'DELETE'}

 */

var services = angular.module('gravaWeb.service', ['ngResource']);

services.factory('InicializaFactory', function ($resource) {
    return $resource('rest/aplic/inicializa', {}, {
        query: { method: 'GET', isArray: false }
    })
});

services.factory('TestaFactory', function ($resource) {
    return $resource('rest/aplic/testa', {}, {
        query: { method: 'GET', isArray: false }
    })
});

services.factory('FinalizaFactory', function ($resource) {
    return $resource('rest/aplic/finaliza', {}, {
    	query: { method: 'GET', isArray: false }
    })
});

services.factory('SetParamConfigFactory', function ($resource) {
    return $resource('rest/aplic/setParamConfig', {}, {
    	query: { method: 'GET', isArray: false },
    	show: { method: 'POST', isArray: false }
    })
});

services.factory('SetPacoteGravacaoFactory', function ($resource) {
    return $resource('rest/aplic/setPacoteGravacao', {}, {
    	query: { method: 'GET', isArray: false },
    	show: { method: 'POST', isArray: false }
    })
});

services.factory('SetPacoteTesteFactory', function ($resource) {
    return $resource('rest/aplic/setPacoteTeste', {}, {
    	query: { method: 'GET', isArray: false },
    	show: { method: 'POST', isArray: false }
    })
});


