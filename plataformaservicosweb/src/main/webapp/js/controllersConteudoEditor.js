'use strict';
	
	var app = angular.module('conteudoSC', ['ui.bootstrap',
	                                        'superSC.service', 
	                                        'superSC.widget',
	                                        'ngResource', 
	                                        'ui.tinymce']);
	
	// Clear browser cache (in development mode)
	// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
	app.run(function ($rootScope, $templateCache) {
	    $rootScope.$on('$viewContentLoaded', function () {
	        $templateCache.removeAll();
	    });
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
	
	app.filter('iif', function () {
		   return function(input, trueValue, falseValue) {
		        return input ? trueValue : falseValue;
		   };
	});
	
	app.directive('dateInput', function(){
	    return {
	        restrict : 'A',
	        scope : {
	            ngModel : '='
	        },
	        link: function (scope) {
	            if (scope.ngModel) scope.ngModel = new Date(scope.ngModel);
	        }
	    }
	});
	
	app.directive('fileModel', ['$parse', function ($parse) {
	    return {
	        restrict: 'A',
	        link: function(scope, element, attrs) {
	            var model = $parse(attrs.fileModel);
	            var modelSetter = model.assign;
	            
	            element.bind('change', function(){
	                scope.$apply(function(){
	                    modelSetter(scope, element[0].files[0]);
	                });
	            });
	        }
	    };
	}]);
	
	app.service('FileUpload', ['$http', function ($http) {
	    this.uploadFileToUrl = function(file, uploadUrl){
	        var fd = new FormData();
	        fd.append('file', file);
	        return $http.post(uploadUrl, fd, {
	            transformRequest: angular.identity,
	            headers: {'Content-Type': undefined }
	        });
	        /**.success(function(data){
	        	return data;
	        })
	        .error(function(dataError){
	        	return data;
	        });*/
	    }
	}]);
	
	app.controller('conteudoCtrl', function ($scope, ConteudoSetorFactory, $window, $location, $http) {
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
			};
			
	});
	
	app.controller('materiaCtrl', function ($scope, MateriaSetorFactory, EditorMateriaFactory, $window, FileUpload) {
			$scope.alerts = [];
			$scope.editor = {};
			$scope.conteudoMateria = {};
			$scope.idSetorFiltro = {};
			$scope.loadingFileHome   = false;
			$scope.loadingFilePortal = false;
			$scope.loadingFile       = false;
			
			EditorMateriaFactory.query().$promise.then(function(dataEditor){
				$scope.editor = dataEditor;
				$scope.editor.tipo = [ { name: "Conteúdo" , value:0 } , { name: "Arquivo" ,  value: 1 }];
				EditorMateriaFactory.query().$promise.then(function(data){
					 $scope.conteudoMateria = data;
					 if(data.materia.setor){
						 $scope.idSetorFiltro = data.materia.setor.idSetor; 
					 };
				});
			});
			
			$scope.tinymceOptionsContent = {
					inline: true,
					valid_elements : '*[*]',
					extend_valid_elements : '*[*]',
					plugins: 'advlist autolink link image lists charmap preview table',
					toolbar: 'insertfile undo redo | fontselect fontsizeselect | bold italic underline | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | table | link image',
					language: 'pt_BR'
			};
			
			$scope.salvar = function(liberar){
				if($scope.conteudoMateria.materia.idTipo != 1){
					if(liberar){
						$scope.conteudoMateria.materia.visivel=true;
					}else{
						$scope.conteudoMateria.materia.visivel=false;
					}
				};
				if($scope.formProduto.$valid){
					if($scope.conteudoMateria.materia.idTipo == 1 && $scope.conteudoMateria.materia.id == 0){
						if(!$scope.conteudoMateria.materia.visivel){
							$scope.conteudoMateria.materia.visivel = false;
						}
						var file = $scope.arquivo;
						var uploadUrl = 'Upload.do';
						FileUpload.uploadFileToUrl(file, uploadUrl).then(function(upload){
							if(upload.data.codResposta == 1){
								$scope.alerts.push({ type: 'danger' , msg: "Problemas no Upload do Arquivo!: " + upload.textoErro});
							}else if(upload.data.codResposta == 0){
								$scope.conteudoMateria.materia.linkConteudo = upload.data.item;
								$scope.editorFactorySave();
							}
						}, function(error){
							$scope.alerts.push({ type: 'danger' , msg: "Problemas no Upload do Arquivo!: " + error.textoErro});
						});
					}else{
						$scope.editorFactorySave();
					}
				}else{
					$scope.alerts.push({ type: 'danger' , msg: 'Preencha os campos marcados em vermelho!'});
				}
			};
			
			$scope.load_file = function(file){
				$scope.fileTesting = file;
//				if(file){
//					$scope.loadingFile = true;
//					var uploadUrl = 'Upload.do';
//					FileUpload.uploadFileToUrl(file, uploadUrl).then(function(upload){
//						if(upload.data.codResposta == 1){
//							$scope.alerts.push({ type: 'danger' , msg: "Problemas no Upload do Arquivo!: " + upload.textoErro});
//						}else if(upload.data.codResposta == 0){
//							returnLink = upload.data.item;
//						}
//					}, function(error){
//						$scope.alerts.push({ type: 'danger' , msg: "Problemas no Upload do Arquivo!: " + error.textoErro});
//					});
//				}
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
		    
		    $scope.filtraAssunto = function(){
		    	if($scope.conteudoMateria.materia.setor){
		    		$scope.idSetorFiltro = $scope.conteudoMateria.materia.setor.idSetor;
		    	}else{
		    		$scope.idSetorFiltro = "";
		    	}
		    };
		    
		    var replaceString = function(text, older, newer){
				return text.replace(new RegExp(older, 'g'), newer);
			};
		    
		    $scope.cleanHtml = function(text){
		    	return replaceString(replaceString(text, "&lt;", "<"), "&gt;", ">");
		    };
	});
	
	app.controller('listCtrl', function ($scope, EditorFactory, EditorMateriaFactory, $window) {
			$scope.alerts = [];	
			$scope.editor = {};
   			$scope.searchInput = {};
   			$scope.materias = [];
   			$scope.qntLimite = 20;
   			$scope.idSetorFiltro = {};
   			
   			$scope.list = function(){
   				EditorFactory.load().$promise.then(function(dataEditor){
   	   				$scope.editor = dataEditor;
   	   				$scope.editor.tipo = [ { name: "Conteúdo" , value:0 } , { name: "Arquivo" ,  value: 1 }];
   	   				EditorMateriaFactory.list().$promise.then(function(data){
   	   					 $scope.materias = data;
   	   				});
   	   			});
   			};
   			
   			$scope.remove = function(idItem){
   				var remove = { id:idItem };
   				EditorMateriaFactory.remove(remove).$promise.then(function(data){
   					if(data != null){						
						if(data.codResposta != null){
							if(data.codResposta == 1){
			    				$scope.alerts.push({ type: 'danger' , msg: "Problemas na remoção do item! Favor tentar novamente.: " + data.textoErro});
			    			}else if(data.codResposta == 0){
			    				$scope.alerts.push({ type: 'success' , msg: "Item removido com sucesso!."});
			    				$scope.list();
			    			}
						}
					}
   				}, function(error){
   					if(error){
    					$scope.alerts.push({ type: 'danger', msg: "Erro na requisição: " + error.status + " - " + error.statusText, paginaErro:error.data});
    				}
   				});
   			};
   			
   			$scope.criarMateria = function(){
   				$scope.acessaMateria(0);
   			};
   			
   			$scope.exibirLink = function(materia){
   				var textoMsg = "";
   				if(materia){
   					if(materia.idTipo == 0){
   						textoMsg = "Conteudo: " + materia.titulo + " Link: /ExibirMateria.do?idMateria=" + materia.id;
   					}else{
   						textoMsg = "Conteudo: " + materia.titulo + " Link: /conteudo/arquivos/" + materia.linkConteudo;
   					};
   				};
   				$scope.alerts.push({ type: 'success' , msg: textoMsg });
   			};

   			$scope.acessaMateria = function(link){
				$window.location.href = 'ExibirMateria.do?editar=editar&idMateria=' + link;
			};
			
			$scope.aumentaLimiteMaterias = function(){
				$scope.qntLimite += 10;
			};
			
			$scope.closeAlert = function(index) {
		    	$scope.alerts.splice(index, 1);
		    };
			
			$scope.list();
			
			$scope.filtraAssunto = function(){
			   	if($scope.searchInput.setor.idSetor){
			   		$scope.idSetorFiltro = $scope.searchInput.setor.idSetor;
			   	}else{
			   		$scope.idSetorFiltro = "";
				}
			};
   	});
		
