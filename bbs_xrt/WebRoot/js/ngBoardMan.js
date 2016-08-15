var app = angular.module('MyApp', [ 'ngRoute' ]);

app.config([ '$routeProvider', function(rp) {
	rp.when('/add', {
		templateUrl : 'tmps/addBoard.html'
	}).when('/list', {
		template : '<h1>列表</h1>'
	}).when('/modify', {
		template : '<h1>修改</h1>'
	}).otherwise({
		template : "<h1>版块管理首页</h1>"
	});
} ]);

app.directive(
				'menuGroup',
				function() {
					return {
						restrict : 'AE', // E = Element, A = Attribute, C =
											// Class, M = Comment
						template : '<div class="list-group">'
								+ '<a class="list-group-item active">{{title}}</a>'
								+ '<a ng-repeat="i in menus" href="{{base}}#/{{links[$index]}}" class="list-group-item">{{i}}</a>'
								+ '</div>',
						replace : true,
						link : function(sc, iElm, iAttrs, controller) {
							sc.title = iAttrs.title;
							sc.menus = iAttrs.menus.split(",");
							sc.links = iAttrs.links.split(",");
							sc.base = iAttrs.base;
						}
					};
				});

app.controller('BoardController', ['$scope','$http', function(sc,ht){
	ht.get('getParentBoard'
	).then(function(resp){
		console.log(resp.data);
		sc.boardsP=resp.data;
	});
}]);
