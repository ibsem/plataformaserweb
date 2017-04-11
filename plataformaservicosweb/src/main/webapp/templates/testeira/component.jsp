<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="utf-8">
<!-- Buscador -->
<script type="text/ng-template" id="search.html">
	<div class="typeahead-group-header" ng-show="match.model.firstInGroup">Desc {{match.model.categoriaTitulo}}</div>
	<a>	
		<span>teste</span>
  		<span ng-bind-html="match.label | typeaheadHighlight:query"></span>
	</a>
</script>

<nav class="navbar navbar-default" style="margin-bottom: 0px;">
	<div id="testeira" class="no_print">
		<div class="container">
		<!-- Buscador -->
			<div style="float: left; width: 60%;" class="text-center" >
				<div class="inner-addon right-addon" style="width: 60%;  margin:0 auto; margin-top: 15px;">
				    <i class="iconbb icon-search"></i>
				    <input type="text" class="form-control" ng-model="$ctrl.selected"
				    	typeahead-template-url="search.html" 
				    	typeahead="state as state.titulo for state in $ctrl.search($viewValue) | limitTo:8" />
				</div>
			</div>
			
		</div>
	</div>
	
</nav>