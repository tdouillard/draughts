'use strict';
console.log('test');
angular.module('draughtsAngularApp', []).controller('MainCtrl',
		[ '$scope', '$http', function($scope, $http) {
			$http({
				url : apiEndpoint,
				method : 'GET'
			}).success(function(data) {
				$scope.game = data;
			});
			$scope.play = function(col) {
				$http(col.playAction).success(function(data) {
					$scope.game = data;
				});
			};

		} ]);
