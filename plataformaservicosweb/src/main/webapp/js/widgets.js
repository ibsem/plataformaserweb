'use strict';

/* Widgets */

/*
 * slider
 * destaque
 * mobilizacao
 * materias
 * pauta
 * noticia
 * agasalhometro
 * boneco
 * videomp4
 * 
 * icon(TODO)
 * testeira
 * 
 */

var app = angular.module('superSC.widget', []);

app.component('slider', {
	bindings: {
		origin: '=',
		interval: '=',
		header: '=',
	},
	controller: ['DestaqueFactory', '$window', function (Factory, $window) {
		var ctrl = this;
		ctrl.slides = null;
		var orig = { tipo: 3 };
		ctrl.active = 0;
		
		Factory.query(orig).$promise.then(function(data){
			ctrl.slides = data;
		});
		
		ctrl.acessaMateria = function(codMateria){
			if(codMateria.indexOf("#") > -1){
				codMateria = '/ExibirMateria.do?idMateria=' + codMateria.replace("#", "");
			}
			$window.location.href = codMateria;
		};
	}],
	templateUrl: 'widgets/slider/slider.html'
});

app.component('destaque', {
	bindings: {
		origin: '='
	},
	controller: ['DestaqueFactory', '$window', 'orderByFilter', function (Factory, $window, orderBy) {
		var ctrl = this;
		ctrl.destaque = null;
		
		Factory.query({ tipo: 2 }).$promise.then(function(data){
			ctrl.destaque = montaConteudoLinhas(data);
		});
		
		ctrl.acessaLink = function(link){
			$window.location.href = link;
		};
		
		ctrl.splitLinha = function(string, nb) {
		    var array = string.split('@@');
		    return array[nb];
		};
		
		ctrl.spanDinamico = function(tamanho){
			var span = "span";
			return span.concat(tamanho);
		};
		
		var montaConteudoLinhas = function(itens){
			itens = orderBy(itens, 'titulo', false);
			var itensSize = itens.length;
			var retorno = [];
			if(itensSize > 0){
				if(itensSize > 1){
					var tamanho = parseInt(itensSize / 2);
					var linha1 = [];
					var linha2 = [];
					for(var index=0; index < tamanho; index++){
						linha1.push({ titulo : itens[index].titulo, 
							 		  image  : itens[index].destaquePortal.urlImagem, 
							 		  link   : itens[index].link,
							 		  idTipo : itens[index].idTipo
							 		  });
					};				
					for(var index=tamanho; index < itensSize; index++){
						linha2.push({ titulo: itens[index].titulo, 
									  image : itens[index].destaquePortal.urlImagem, 
									  link  : itens[index].link,
							 		  idTipo : itens[index].idTipo });
					};
					retorno.push(linha1);
					retorno.push(linha2);
				}else{
					var linha1 = [];
					linha1.push({ titulo: itens[0].titulo, 
								  image : itens[0].destaquePortal.urlImagem, 
								  link  : itens[0].link,
						 		  idTipo : itens[index].idTipo });
					retorno.push(linha1);
				}
			};
			return retorno;
		};
		
		
		ctrl.textoDinamico = function(tamanho, linha){
			if(linha == false){
				tamanho = tamanho/2;
			}
			var span = "texto";
			var tam = 0;
			if(tamanho < 5){
				tam = 3;
			}else if (tamanho < 10){
				tam = 2;
			}else if (tamanho < 15){
				tam = 2;
			}else{
				tam = 1;
			}
			return span.concat(tam);
		};
	}],
	templateUrl: 'widgets/destaque/destaque.html'
});

app.component('destaquesetor', {
	bindings: {
		origin: '=',
		tipo: '=',
	},
	controller: ['MenuSetorFactory', '$window', function (Factory, $window) {
		var ctrl = this;
		ctrl.destaque = null;
		
		Factory.query({ id: ctrl.origin, 
						destaque:'destaque', 
						subdivisao:'subdivisao', 
						tipo: ctrl.tipo }).$promise.then(function(data){
			ctrl.destaque = montaConteudoLinhas(data);
		});
		
		ctrl.acessaLink = function(link){
			$window.location.href = link;
		};
		
		ctrl.splitLinha = function(string, nb) {
		    var array = string.split('@@');
		    return array[nb];
		};
		
		ctrl.spanDinamico = function(tamanho){
			var span = "span";
			return span.concat(tamanho);
		};
		
		var montaConteudoLinhas = function(itens){
			var itensSize = itens.length;
			var retorno = [];
			if(itensSize > 0){
				if(itensSize > 1){
					var tamanho = parseInt(itensSize / 2);
					var linha1 = [];
					var linha2 = [];
					for(var index=0; index < tamanho; index++){
						linha1.push({ titulo: itens[index].titulo, 
							 		  image : itens[index].destaqueHome.urlImagem, 
							 		  link  : itens[index].link,
							 		  idTipo : itens[index].idTipo });
					};				
					for(var index=tamanho; index < itensSize; index++){
						linha2.push({ titulo: itens[index].titulo, 
									  image : itens[index].destaqueHome.urlImagem, 
									  link  : itens[index].link,
							 		  idTipo : itens[index].idTipo });
					};
					retorno.push(linha1);
					retorno.push(linha2);
				}else{
					var linha1 = [];
					linha1.push({ titulo: itens[0].titulo, 
								  image : itens[0].destaqueHome.urlImagem, 
								  link  : itens[0].link,
						 		  idTipo : itens[index].idTipo });
					retorno.push(linha1);
				}
			};
			return retorno;
		};
		
		
		ctrl.textoDinamico = function(tamanho, linha){
			if(linha == false){
				tamanho = tamanho/2;
			}
			var span = "texto";
			var tam = 0;
			if(tamanho < 5){
				tam = 3;
			}else if (tamanho < 10){
				tam = 2;
			}else if (tamanho < 15){
				tam = 2;
			}else{
				tam = 1;
			}
			return span.concat(tam);
		};
	}],
	templateUrl: 'widgets/destaquesetor/destaquesetor.html'
});



app.component('mobilizacao', {
	bindings: {					
	},
	controller: ['MobilizaFactory', function (Factory) {
		var ctrl = this;
		ctrl.mobilizacoes = null;
		var orig = { origin: ctrl.origin };
		var prefixoMobiliza = "";
		ctrl.oneAtATime = true;
		
		ctrl.intradiaMobiliza = function(valor){
			if(valor == "-"){
				return valor;
			}else{
				return parseFloat(valor);
			}
		}; 
			
		ctrl.atualizaMobilizacao = function(prefixoMobiliza){
			var teste = {prefixo : prefixoMobiliza };
			Factory.show(teste).$promise.then(function(dataMobiliza){
				if(dataMobiliza.dependencia == null){
					$scope.mobilizacoes = "Prefixo não participante de Mobilizações neste Site!";
				}else{
					$scope.mobilizacoes = dataMobiliza;
				}
				
			}, function(error){
				$scope.mobilizacoes = "Prefixo não participante de Mobilizações neste Site!";
			});
		};
		
		Factory.query().$promise.then(function(data){
			 ctrl.mobilizacoes = data;
		});
	}],
	templateUrl: 'widgets/mobilizacao/mobilizacao.html'
});

app.component('materias', {
	bindings: {
		origin: '='
	},
	controller: ['MateriaSetorFactory', '$window', function (Factory, $window) {
		var ctrl = this;
		ctrl.materias = null;
		var orig = { origin: ctrl.origin };
		ctrl.qntLimite = 15;
		
		Factory.loadPrincipal(orig).$promise.then(function(dataMateria){
			ctrl.materias = dataMateria;
		}, function(error){
			$scope.materias = "Erro no carregamento das matérias!";
		});
		
		ctrl.acessaMateria = function(link){
			$window.location.href = 'ExibirMateria.do?idMateria=' + link;
		};
		
		ctrl.aumentaLimiteMaterias = function(){
			ctrl.qntLimite += 10;
		};
	}],
	templateUrl: 'widgets/materia/materia.html'
});

app.component('materias2', {
	bindings: {
		origin: '='
	},
	controller: ['ConteudoSetorFactory', '$window', function (Factory, $window) {
		var ctrl = this;
		ctrl.data = null;
		var orig = { idSetor: ctrl.origin };
		ctrl.qntLimite = 15;
		
		Factory.query(orig).$promise.then(function(dataMateria){
			ctrl.data = dataMateria;
		}, function(error){
			ctrl.data = "Erro no carregamento das matérias!";
		});
		
		ctrl.acessaMateria = function(link){
			$window.location.href = 'ExibirMateria.do?idMateria=' + link;
		};
		
		ctrl.aumentaLimiteMaterias = function(){
			ctrl.qntLimite += 10;
		};
		
		ctrl.filtroAssunto = function(codAssunto){
			var search = { assunto: { idAssunto:codAssunto.idAssunto } };
			return search;
		};
	}],
	templateUrl: 'widgets/materia2/materia2.html'
});

app.component('materias3', {
	bindings: {
		origin: '='
	},
	controller: ['ConteudoSetorFactory', '$window', function (Factory, $window) {
		var ctrl = this;
		ctrl.data = null;
		var orig = { idSetor: ctrl.origin };
		ctrl.qntLimite = 15;
		
		Factory.query(orig).$promise.then(function(dataMateria){
			ctrl.data = dataMateria;
		}, function(error){
			ctrl.data = "Erro no carregamento das matérias!";
		});
		
		ctrl.acessaMateria = function(link){
			$window.location.href = 'ExibirMateria.do?idMateria=' + link;
		};
		
		ctrl.aumentaLimiteMaterias = function(){
			ctrl.qntLimite += 10;
		};
		
		ctrl.filtroAssunto = function(codAssunto){
			var search = { assunto: {} };
			if(codAssunto){
				search = { assunto: { idAssunto:codAssunto.idAssunto } };
			}
			return search;
		};
	}],
	templateUrl: 'widgets/materia3/materia3.html'
});

app.component('materias4', {
	bindings: {
		origin: '=',
		data: '<',
		materia: '<',
		viewmenu: '=',
	},
	controller: function ($window, filterFilter) {
		var ctrl = this;
		var orig = { idSetor: ctrl.origin };
		ctrl.qntLimite = 15;
		ctrl.search = { assunto: {} };
		ctrl.idMateria = null;
		ctrl.indexMateria = 0;
		ctrl.assunto = { nomeAssunto: "Últimas Matérias" };
		ctrl.viewMenu = ctrl.viewmenu;

		ctrl.acessaMateria = function(index){
			if(ctrl.data.materia){
				ctrl.idMateria = filterFilter(ctrl.data.materia, ctrl.search)[index].id;
				ctrl.indexMateria = index;
			}
		};
		
		ctrl.aumentaLimiteMaterias = function(){
			ctrl.qntLimite += 10;
		};
		
		ctrl.noPrevious = function(){
			if(ctrl.indexMateria > 0){
				return false;
			}
			return true;
		};
		
		ctrl.noNext = function(){
			if(ctrl.data.materia){
				if(ctrl.indexMateria < filterFilter(ctrl.data.materia, ctrl.search).length){
					return false;
				}
			}
			return true;
		};
		
		ctrl.selectMateria = function(index){
			ctrl.acessaMateria(index);
		};
		
		ctrl.filtroAssunto = function(codAssunto){
			ctrl.viewMenu = 2;
			if(codAssunto){
				ctrl.search = { assunto: { idAssunto:codAssunto.idAssunto } };
				ctrl.assunto = codAssunto;
			}else{
				ctrl.search = { assunto: {} };
				ctrl.assunto = { nomeAssunto: "Últimas Matérias" };
			}
		};
		
		ctrl.retornaAssunto = function(){
			ctrl.viewMenu = 1;
		};
		
		ctrl.isSelected = function(idMateria){
			if(idMateria){
				if(ctrl.idMateria == idMateria){
					return "bg-selected";
				}				
			}else{
				return "";
			}
		};
		
		ctrl.isActive = function(assunto){
			if(assunto){
				if(assunto.idAssunto == ctrl.search.assunto.idAssunto){
					return "active";
				}
			}else{
				if(!ctrl.search.assunto.idAssunto){
					return "active";
				}
			}
		};
		
		ctrl.$onChanges = function(changesObj){
			if(changesObj.materia){
				if(changesObj.materia.currentValue){
					if(ctrl.materia){
						if(ctrl.materia > 0){
							ctrl.idMateria = ctrl.materia;
						}else{
							ctrl.acessaMateria(0);
						}
					}else{
						ctrl.acessaMateria(0);
					};
				};
			}else{
				if(ctrl.materia == 0){
					ctrl.acessaMateria(0);
				}
			}
		};
		
		
	},
	templateUrl: 'widgets/materia4/materia4.html'
});

app.component('pauta', {
	bindings: {
		origin: '=',
		materia: '=',
		header: '=',
	},
	controller: ['DestaqueFactory', '$window', function (Factory, $window) {
		var ctrl = this;
		ctrl.menus = null;
		Factory.query({ tipo: 1 }).$promise.then(function(data){
			ctrl.menus = data;
		});
		/** Pauta Antigo **/
		/*
		var ctrl = this;
		ctrl.conteudoPauta = null;
		var orig = { origin: ctrl.origin };
		
		Factory.query(orig).$promise.then(function(data){
			ctrl.conteudoPauta = data;
		}, function(error){
			ctrl.conteudoPauta = "Erro no carregamento das pautas!";
		});
		
		ctrl.acessaMateria = function(codMateria){
			if(codMateria.indexOf("#") > -1){
				ctrl.materia = codMateria.replace("#", "");
			}else{
				$window.location.href = codMateria;
			};
		};*/
	}],
	templateUrl: 'widgets/pauta/pauta.html'
});

app.component('noticia', {
	bindings: {
		materia: '<'
	},
	controller: ['NoticiaFactory', '$window', "$sce", function (Factory, $window, $sce) {
		var ctrl = this;
		ctrl.$onChanges = function(changesObj){
			if(changesObj.materia.currentValue != ""){
				ctrl.conteudoMateria = null;
				ctrl.conteudoTexto = null;
				var orig = { origin: changesObj.materia.currentValue};
				if(orig.origin){
					Factory.query(orig).$promise.then(function(data){
						ctrl.conteudoMateria = data;
						ctrl.conteudoTexto = ctrl.replaceString(ctrl.replaceString(ctrl.conteudoMateria.textoConteudo, "&lt;", "<"), "&gt;", ">");
					}, function(error){
						ctrl.materias = "Erro no carregamento das notícias!";
					});
				}
			}
		};
		
		ctrl.efetuarDownload = function(objectItem){
			var urlArq = '\/conteudo\/arquivos\/' + objectItem.linkConteudo;
			$window.open(urlArq, '_blank');
		};
		
		ctrl.replaceString = function(text, older, newer){
			return text.replace(new RegExp(older, 'g'), newer);
		};
	}],
	templateUrl: 'widgets/noticia/noticia.html'
});

app.component('agasalhometro', {
	bindings: {
		acordo: '=',
	},
	controller: ['AgasalhometroFactory', function (Factory) {
		var ctrl = this;
		ctrl.conteudoAgasalhomentro = null;
		Factory.query().$promise.then(function(data){
			var index = 0;
			ctrl.conteudoAgasalhomentro = [];
			for(index in data){
				if(ctrl.acordo === 0){
					if(data[index].prefixo === 8496){
						ctrl.conteudoAgasalhomentro.push(data[index]);
					}
				}else{
					if(data[index].prefixo !== 8496){
						if(data[index].atg != null){
							ctrl.conteudoAgasalhomentro.push(data[index]);
						}
					}
				}
			}
		}, function(error){
			ctrl.conteudoAgasalhomentro = "Erro no carregamento do agasalhometro!";
		});
	}],
	templateUrl: 'widgets/agasalhometro/agasalhometro.html'
});

app.component('boneco', {
	bindings: {
		prefixo: '=', atg: '=',
	},
	controller: [function () {
		var ctrl = this;
	}],
	templateUrl: 'widgets/boneco/boneco.html'
});

app.component('videomp4', {
	bindings: {
		ref: '=', largura: '=', altura: '='
	},
	controller: [function () {
		var ctrl = this;
	}],
	templateUrl: 'widgets/video/video.html'
});

app.component('testeira', {
	bindings: {		
	},
	controller: ['MenusFactory', '$window', '$http' , function (Factory, $window, $http) {
		var ctrl = this;
		ctrl.menus = null;
		
		ctrl.selected = undefined;
		ctrl.states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 
		               'California', 'Colorado', 'Connecticut', 
		               'Delaware', 'Florida', 'Georgia', 'Hawaii', 
		               'Idaho', 'Illinois', 'Indiana', 'Iowa', 
		               'Kansas', 'Kentucky', 'Louisiana', 'Maine', 
		               'Maryland', 'Massachusetts', 'Michigan', 
		               'Minnesota', 'Mississippi', 'Missouri', 
		               'Montana', 'Nebraska', 'Nevada', 
		               'New Hampshire', 'New Jersey', 'New Mexico', 
		               'New York', 'North Dakota', 'North Carolina', 
		               'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 
		               'Rhode Island', 'South Carolina', 
		               'South Dakota', 'Tennessee', 'Texas', 'Utah', 
		               'Vermont', 'Virginia', 'Washington', 
		               'West Virginia', 'Wisconsin', 'Wyoming'];
		
		Factory.query().$promise.then(function(data){
			ctrl.menus = data;
		});
		
		ctrl.acessaMenuSetor = function(codSetor){
			$window.location.href = 'ExibirConteudo.do?idSetor=' + codSetor;
		};
		
		ctrl.acessaMenuAssunto = function(codAssunto, codSetor){
			$window.location.href = 'ExibirConteudo.do?idAssunto=' + codAssunto + "&idSetor=" + codSetor;
		};
		
		ctrl.search = function(val) {
		    return $http.get('rest/pesquisa/titulo', {
		      params: {
		        q: val,
		      }
		    }).then(function(response){
		    	var results = _(response.data)
		        .groupBy('categoriaTitulo')
		        .map(function (g) {
		          g[0].firstInGroup = true; // the first item in each group
		          return g;
		        })
		        .flatten()
		        .value();
		      return results;
		    });
		  };
	}],
	templateUrl: 'widgets/testeira/component.jsp?v=0.1'
});

app.component('testeiraeditor', {
	bindings: {		
	},
	controller: ['MenusFactory', '$window', function (Factory, $window) {
		var ctrl = this;
		ctrl.menus = null;
	}],
	templateUrl: 'widgets/testeiraeditor/component.jsp?v=0.1'
});

app.component('loading', {
	bindings: {
		hide: '=',
		title: '=',
	},
	controller: function () {
		var ctrl = this;
	},
	templateUrl: 'widgets/loading/component.html?v=0.1'
});

app.component('menulateral', {
	bindings: {		
		setor: '=',
	},
	controller: ['MenuSetorFactory', '$window', function (Factory, $window) {
	var ctrl = this;
	ctrl.menus = null;
	
	Factory.load({ id: ctrl.setor }).$promise.then(function(data){
		ctrl.menus = data;
	});
	
	ctrl.activeTest = function(idAssunto){
		if(idAssuntoItem){
			if(idAssunto == idAssuntoItem){
				return 'active';
			}else{
				return '';
			}
		}
	};
	
	}],
	templateUrl: 'widgets/menulateral/component.html?v=0.1'
});

app.component('assuntodestaque', {
	bindings: {	
		setor: '=',
		tipo: '=',
	},
	controller: ['MenuSetorFactory', '$window', function (Factory, $window) {
	var ctrl = this;
	ctrl.menus = null;
	
	Factory.query({ id: ctrl.setor, destaque:'destaque', subdivisao:'subdivisao', tipo:ctrl.tipo }).$promise.then(function(data){
		ctrl.menus = data;
	});
		
	}],
	templateUrl: 'widgets/assuntodestaque/component.html?v=0.1'
});

app.component('assunto', {
	bindings: {	
		assunto: '=',
	},
	controller: ['SubdivisaoFactory', '$window', function (Factory, $window) {
	var ctrl = this;
	ctrl.menus = null;
	
	Factory.query({ id: ctrl.assunto }).$promise.then(function(data){
		ctrl.menus = data;
	});
		
	}],
	templateUrl: 'widgets/assunto/component.html?v=0.1'
});

app.component('grupoiconeconteudodestaque', {
	bindings: {	
		assunto: '=',
		tipo: '=',
	},
	controller: ['AssuntoDestaqueFactory', '$window', function (Factory, SubFactory ,$window) {
	var ctrl = this;
	ctrl.icones = null;
	
	Factory.query({ id: ctrl.assunto, destaque:'destaque', tipo: ctrl.tipo }).$promise.then(function(data){
		ctrl.icones = data;
	});
	
	}],
	templateUrl: 'widgets/grupoiconeconteudodestaque/component.html?v=0.1'
});

app.component('grupoiconeconteudo', {
	bindings: {	
		conteudo: '=',
	},
	controller: ['SubdivisaoFactory', '$window', function (Factory, SubFactory ,$window) {
	var ctrl = this;
	
	}],
	templateUrl: 'widgets/grupoiconeconteudo/component.html?v=0.1'
});

app.component('iconeconteudo', {
	bindings: {		
		link: '=',
		icone: '=',
		titulo: '=',
		conteudo: '=',
		tipotarget: '=',
	},
	controller: ['MenusFactory', '$window', function (Factory, $window) {
	var ctrl = this;
	}],
	templateUrl: 'widgets/iconeconteudo/component.html?v=0.1'
});

app.component('fileupload', {
	bindings: {		
		fileList : "=",
	},
	controller: ['MenusFactory', '$window', function (Factory, $window) {
	var ctrl = this;
	ctrl.alerts = [];
	ctrl.fileList = [];
	
	ctrl.upload = function(){
		var file = ctrl.arquivo;
		var uploadUrl = 'Upload.do';
		
		FileUpload.uploadFileToUrl(file, uploadUrl).then(function(upload){
			if(upload.data.codResposta == 1){
				ctrl.alerts.push({ type: 'danger' , msg: "Problemas no Upload do Arquivo!: " + upload.textoErro});
			}else if(upload.data.codResposta == 0){
				ctrl.fileList.push(upload.data.item);
			}
		}, function(error){
			ctrl.alerts.push({ type: 'danger' , msg: "Problemas no Upload do Arquivo!: " + error.textoErro});
		});
	};
	
	}],
	templateUrl: 'widgets/fileupload/component.html?v=0.1'
});

app.component('tituloassunto', {
	bindings: {	
		assunto: '=',
	},
	controller: ['AssuntoFactory', '$window', function (Factory, SubFactory ,$window) {
	var ctrl = this;
	ctrl.assuntoTitulo = null;
	
	Factory.load({ id: ctrl.assunto, info:'info' }).$promise.then(function(data){
		ctrl.assuntoTitulo = data;
	});
	}],
	templateUrl: 'widgets/tituloassunto/component.html?v=0.1'
});

app.component('conteudoacessos', {
	bindings: {	
		conteudo: '=',
	},
	controller: ['AcessosConteudoFactory', '$window', function (Factory, SubFactory ,$window) {
	var ctrl = this;
	ctrl.count = null;
	
	Factory.load({ id: ctrl.conteudo, acesso:'acessos' }).$promise.then(function(data){
		ctrl.count = data;
	});
	}],
	templateUrl: 'widgets/conteudoacessos/component.html?v=0.1'
});


