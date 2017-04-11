'use strict';
	
	var app = angular.module('conteudoSC', ['ui.bootstrap','superSC.service', 'ngResource']);
	
	// Clear browser cache (in development mode)
	// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
	app.run(function ($rootScope, $templateCache) {
	    $rootScope.$on('$viewContentLoaded', function () {
	        $templateCache.removeAll();
	    });
	});
	
	app.filter('iif', function () {
		   return function(input, trueValue, falseValue) {
		        return input ? trueValue : falseValue;
		   };
	});
	
	app.controller('conteudoCtrl', ['$scope', 'ConteudoSetorFactory', '$window', '$location',
   		function ($scope, ConteudoSetorFactory, $window, $location) {
			$scope.idMateria = 1244;
			$scope.setor = { idSetor:setor };
			$scope.conteudo = {};
			$scope.conteudo = ConteudoSetorFactory.query($scope.setor);
			
			$scope.filtroAssunto = function(codAssunto){
				$scope.search = { assunto: { idAssunto:codAssunto.idAssunto } };
				return $scope.search;
			};
			$scope.filtroTipo = function(codTipo){
				$scope.search = { idTipo:codTipo };
				return $scope.search;
			};
			
			$scope.acessaMateria = function(codMateria){
				$window.location.href = 'ExibirMateria.do?idMateria=' + codMateria;
			};	
			
			$scope.ativa = function(index){
				return index == 0 ? true : false;
			}
	}]);
	
	app.controller('materiaCtrl', ['$scope', 'UsersFactory', 'MateriaSetorFactory', '$window', '$location', '$sce',
   		function ($scope, UsersFactory, MateriaSetorFactory, $window, $location, $sce) {

			$scope.conteudoMateria = {};
			MateriaSetorFactory.query().$promise.then(function(data){
				 $scope.conteudoMateria = data;
				 $scope.conteudoTexto = $sce.trustAsHtml($scope.conteudoMateria.texto);
			});
			
	}]);
	
	app.directive('bindHtmlCompile', ['$compile', function ($compile) {
		return {
			restrict: 'A',
			link: function (scope, element, attrs) {
				scope.$watch(function () {
					return scope.$eval(attrs.bindHtmlCompile);
				}, function (value) {
					element.html(value && value.toString());
					var compileScope = scope;
					if (attrs.bindHtmlScope) {
						compileScope = scope.$eval(attrs.bindHtmlScope);
					}
					$compile(element.contents())(compileScope);
				});
			}
		};	
	}]);
