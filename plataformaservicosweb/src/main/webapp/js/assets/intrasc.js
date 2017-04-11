/**
 * Rotina para navegação no projeto principal 
 */
var xmlhttp=new XMLHttpRequest();

function listarDoc(setor,id){
	xmlhttp.open("POST","ExibirConteudo",false);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send("acao=listar&setor="+setor+"&"+id.split("_")[0]+"="+id.split("_")[1]);
	document.getElementById(id).innerHTML=xmlhttp.responseText;
}

function abreListas(){
	var grupos = document.getElementsByTagName("div");
	var setor = document.getElementById("nomeSetor");
	var pb = $('#pb1').progressbar();
	pb.progressbar('value', 0);
	var inc = 100 / (grupos.length);
	var perc = 0;
	for (i=0;i<grupos.length;i++){
		if (grupos[i].className.substring(0, 7)==setor.value){
			listarDoc((grupos[i].className).replace(" ng-scope", ""),grupos[i].id);
		}
		pb.progressbar('value', perc += inc);
	}
	$('#pb1').remove();
	$('#carregando').remove();
}

function exibirDoc(id, url) {
	if (url.substring(url.length-4)==".xml") {
		if (document.getElementById(id).innerHTML==""){
			xmlhttp.open("POST","ExibirConteudo",false);
			xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xmlhttp.send("acao=abrir&arquivo="+url);
			document.getElementById(id).innerHTML=xmlhttp.responseText;
			document.getElementById("chave"+id).style.display = "block";
		} else {
			document.getElementById(id).innerHTML="";
			document.getElementById("chave"+id).style.display = "none";
		}
	} else {
		window.open(url, "_blank");
	}
}

function fecharDoc(id) {
	document.getElementById(id).innerHTML="";
	document.getElementById("chave"+id).style.display = "none";
}

function logar(setor){
	window.open("/log.jsp?setor="+setor,"_self");
}

function destino(setor) {
	window.open("/ExibirConteudo?setor="+setor, "_self");
}