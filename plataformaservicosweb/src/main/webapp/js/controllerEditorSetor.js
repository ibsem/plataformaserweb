'use strict';
	
	var app = angular.module('conteudoSC', ['ui.bootstrap','superSC.service', 'ngResource', 'ui.codemirror']);
	
	// Clear browser cache (in development mode)
	// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
	app.run(function ($rootScope, $templateCache) {
	    $rootScope.$on('$viewContentLoaded', function () {
	        $templateCache.removeAll();
	    });
	});
	
	app.controller('materiaCtrl', function ($scope, MateriaSetorFactory, EditorFactory, $window) {
			$scope.alerts = [];
			$scope.editor = {};
			$scope.conteudoMateria = {};
			
			$scope.cmModel = ';; Scheme code in here.\n' +
    '(define (double x)\n\t(* x x))\n\n\n' +
    '<!-- XML code in here. -->\n' +
    '<root>\n\t<foo>\n\t</foo>\n\t<bar/>\n</root>\n\n\n' +
    '// Javascript code in here.\n' +
    'function foo(msg) {\n\tvar r = Math.random();\n\treturn "" + r + " : " + msg;\n}';
			
		 $scope.editorOptions = {
		        lineWrapping : true,
		        theme:'rubyblue',
		        lineNumbers: true,
		        indentWithTabs: true,
		        mode: 'htmlmixed',
		 };
			
			
			EditorFactory.query().$promise.then(function(dataEditor){
				$scope.editor = dataEditor;
				$scope.editor.tipo = [ { name: "Matéria" , value:0 } , { name: "Arquivo" ,  value: 1 }];
				MateriaSetorFactory.query().$promise.then(function(data){
					 $scope.conteudoMateria = data;
				});
			});
			
			
			$scope.salvar = function(){
				if($scope.formProduto.$valid){
					$scope.editorFactorySave();
				}else{
					$scope.alerts.push({ type: 'danger' , msg: 'Preencha os campos marcados em vermelho!'});
				}
			};
			
			$scope.editorFactorySave = function(){
				EditorFactory.save($scope.conteudoMateria).$promise.then(function(save){
					if(save != null){						
						if(save.codResposta != null){
							if(save.codResposta == 1){
			    				$scope.alerts.push({ type: 'danger' , msg: "Problemas no cadastramento da Matéria. Favor tentar novamente!: " + save.textoErro});
			    			}else if(save.codResposta == 0){
			    				$scope.alerts.push({ type: 'success' , msg: "Matéria cadastrada com sucesso!. Código da Matéria " + save.item.materia.id});
			    				$scope.conteudoMateria = save.item;
			    			}
						}
					}
				}, function(dataError){
    				if(dataError){
    					$scope.alerts.push({ type: 'danger', msg: "Erro na requisição: " + dataError.status + " - " + dataError.statusText, paginaErro:dataError.data});
    				}
    			});
			};
			
			$scope.closeAlert = function(index) {
		    	$scope.alerts.splice(index, 1);
		    };
		    
		    $scope.voltar = function(){
		    	$window.location.href = 'ExibirMateria.do?editar=editar';
		    };
	});
