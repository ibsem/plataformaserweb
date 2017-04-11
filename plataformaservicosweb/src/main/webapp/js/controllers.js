'use strict';
	
	var app = angular.module('superSC', ['ui.bootstrap',
	                                     'superSC.service',
	                                     'superSC.widget',
	                                     'ngResource']);
	
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
	
	app.controller('principalCtrl', function ($scope, $window, $modal) {
			$scope.origem = "principal";
			$scope.conteudo = [];
			
			$scope.acessaMateria = function(link){
				$window.location.href = 'ExibirMateria.do?idMateria=' + link;
			};
			
			$scope.acessaLink = function(link){
				$window.location.href = link;
			};

			$scope.animationsEnabled = true;

			$scope.open = function (size) {
				var modalInstance = $modal.open({
					animation: $scope.animationsEnabled,
					ariaLabelledBy: 'modal-title',
					ariaDescribedBy: 'modal-body',
					templateUrl: 'myModalContent.html',
					controller: 'ModalInstanceCtrl',
					controllerAs: '$ctrl',
					size: size,
					resolve: {}
				});
			};		
			
			/* 
			 * Código para abertura do modal 
			 * O conteudo da modal deve ser editado no arquivo principal
			 * */
			// $scope.open('lg');
	});
	
	app.controller('ModalInstanceCtrl', function ($modalInstance) {
		  var $ctrl = this;

		  $ctrl.ok = function () {
		    $modalInstance.close('ok');
		  };

		  $ctrl.cancel = function () {
		    $modalInstance.dismiss('cancel');
		  };
	});
	
	app.controller('setorCtrl', function ($scope, $window, ConteudoSetorFactory) {
		$scope.origem = "setor";
		$scope.conteudo = [];
		$scope.qry = { idSetor: idSetor==null ? 0 : idSetor , idMateria: idMateria==null ? 0: idMateria };
		$scope.idMateria = 0;
		
		if(idMateria > 0){
			$scope.idMateria = idMateria;
		}
		
		ConteudoSetorFactory.query($scope.qry).$promise.then(function(dataConteudo){
			$scope.conteudo = dataConteudo;
		});
});
	
	
	
