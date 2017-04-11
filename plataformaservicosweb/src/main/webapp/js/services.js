'use strict';

/* Services */

var services = angular.module('superSC.service', ['ngResource']);

/*
 * Exibição de Conteúdo
 */

services.factory('ConteudoSetorFactory', function ($resource) {
    return $resource('rest/conteudo/:idSetor/:idMateria', {}, {
        query: { method: 'GET', params: {}, isArray: false }
    });
});

services.factory('MateriaSetorFactory', function ($resource) {
    return $resource('rest/conteudo/materia/:origin', {}, {
        query: { method: 'GET', params: {}, isArray: false },
    	loadPrincipal: { method: 'GET', isArray: true}
    });
});

services.factory('SetoresFactory', function ($resource) {
    return $resource('rest/conteudo/setores', {}, {
        query: { method: 'GET', params: {}, isArray: true }
    });
});

/*
 * Editor Conteudo
 */

services.factory('EditorFactory', function ($resource) {
    return $resource('rest/conteudo/editor', {}, {
    	load:   { method: 'GET',    isArray: false },
    });
});

services.factory('EditorMateriaFactory', function ($resource) {
    return $resource('rest/conteudo/editor/materia/:id', {}, {
    	query:  { method: 'GET',    isArray: true  },
    	load:   { method: 'GET',    isArray: false },
    	save:   { method: 'POST',   isArray: false },
        remove: { method: 'DELETE', isArray: false },
    });
});

services.factory('EditorDestaqueFactory', function ($resource) {
    return $resource('rest/conteudo/editor/destaque', {}, {
    	query:  { method: 'GET',    isArray: true },
    	save:   { method: 'POST',                 },
    });
});

/*
 * Widgets Services 
 */

services.factory('SliderFactory', function ($resource) {
    return $resource('rest/conteudo/widget/slider/:origin', {}, {
        query: { method: 'GET', isArray: true }
    });
});

services.factory('DestaqueFactory', function ($resource) {
    return $resource('rest/conteudo/portal/destaque/:tipo', {}, {
        query: { method: 'GET', isArray: true }
    });
});

services.factory('NoticiaFactory', function ($resource) {
    return $resource('rest/conteudo/widget/noticia/:origin', {}, {
        query: { method: 'GET', params: {}, isArray: false },
    	loadPrincipal: { method: 'GET', isArray: true}
    });
});

services.factory('PautaFactory', function ($resource) {
    return $resource('rest/conteudo/widget/pauta/:origin', {}, {
        query: { method: 'GET', isArray: true }
    });
});

services.factory('AgasalhometroFactory', function ($resource) {
    return $resource('rest/conteudo/widget/agasalhometro', {}, {
        query: { method: 'GET', isArray: true }
    });
});

services.factory('MobilizaFactory', function ($resource) {
    return $resource('rest/conteudo/widget/mobilizacoes/:id', {}, {
        query: { method: 'GET', isArray: false },
    	show: { method: 'POST', params: {id: '@prefixo'} }
    });
});

services.factory('MenusFactory', function ($resource) {
    return $resource('rest/conteudo/testeira/setores', {}, {
        query: { method: 'GET', isArray: false },
    });
});

services.factory('MenuSetorFactory', function ($resource) {
    return $resource('rest/conteudo/setor/:id/:subdivisao/:destaque/:tipo', {}, {
        load: { method: 'GET', isArray: false },
        query: { method: 'GET', isArray: true },
    });
});

services.factory('SubdivisaoFactory', function ($resource) {
    return $resource('rest/conteudo/subdivisao/:id/:destaque/:tipo', {}, {
        load: { method: 'GET', isArray: false },
        query: { method: 'GET', isArray: true },
    });
});

services.factory('AssuntoFactory', function ($resource) {
    return $resource('rest/conteudo/assunto/:id/:info', {}, {
        load: { method: 'GET', isArray: false },
        query: { method: 'GET', isArray: true },
    });
});

services.factory('AssuntoDestaqueFactory', function ($resource) {
    return $resource('rest/conteudo/assunto/:id/:destaque/:tipo', {}, {
        load: { method: 'GET', isArray: false },
        query: { method: 'GET', isArray: true },
    });
});

services.factory('AcessosConteudoFactory', function ($resource) {
    return $resource('rest/conteudo/materia/:id/:acesso', {}, {
        load: { method: 'GET', isArray: false },
        query: { method: 'GET', isArray: true },
    });
});





