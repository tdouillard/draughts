'use strict';
angular.module('draughtsAngularApp', []).controller('MainCtrl',
		[ '$scope', '$http', function($scope, $http) {
			Console.log('TEST');
			$http({
				url : apiEndpoint,
				method : 'GET'
			}).success(function(data) {
				$scope.game = data;
			});
			$scope.oldColumnPosition = null;
			$scope.oldRowPosition = null;
			$scope.play = function(oldColumnPosition,oldRowPosition,newColumnPosition,newRowPosition) {
				$http.post('apiEndpoint',{oldColumnPosition,oldRowPosition,newColumnPosition,newRowPosition}).then(function(data) {
					$scope.game = data;
					$scope.oldColumnPosition = null;
					$scope.oldRowPosition = null;
					console.log(data);
				},function(data){
					console.log(data);
				});
			};
			
			$scope.tryToMove = function(col,cell){
				console.log(col,cell);
				if($scope.oldColumnPosition != null && $scope.oldRowPosition != null){
					$scope.play(oldColumnPosition,oldRowPosition,col,cell);
				}
				$scope.oldColumnPosition = col;
				$scope.oldRowPosition = col;
			};

		} ]);
