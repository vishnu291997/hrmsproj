/**
 * 
 */

app.controller('NotificationCtrl',function(NotificationService,NotificationCtrl,$routeParams,$location,rootScope,$controller){
	})
app.controller('LeavesInDetailCtrl',function($scope,$rootScope,$location,LeaveService,$routeParams,$sce){
	
	var id=$routeParams.id
	
	//statement //select * from blogpost where id=?
	LeaveService.getLeaves(id).then(function(response){
		$scope.leaves=response.data//BlogPost object
		$scope.content=$sce.trustAsHtml($scope.leaves.leaveContent)
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})


/*app.controller('LeavesInDetailCtrl',function($scope,$routeParams,LeaveService,$location,rootScope,$sce){
	var id=$routeParams.id
	$scope.isRejected=false;
	LeaveService.getLeaves(id).then(function(response){
		$scope.leaves=response.data
		$scope.subject=$sce.trustAsHtml($scope.leaves.leaveSubject)
		$scope.content=$sce.trustAsHtml($scope.leaves.leaveContent)
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})

*/

	$scope.approveLeaves=function(leave){
		LeaveService.approveLeaves(leave).then(function(response){
			NotificationCtrl.getNotificationNotViewed();
			$location.path('/leaveswaitingforapproval')
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	$scope.rejectLeaves=function(leave){
		console.log(leave)
		LeaveService.rejectLeaves(leave,$scope.rejectionReason).then(function(response){
			NotificationCtrl.getNotificationNotViewed();
			$location.path('/leaveswaitingforapproval')
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.showTextArea=function(){
		$scope.isRejected=!$scope.isRejected
	}
	$scope.addComment=function(leaves,commentTxt){
		if(commentTxt==undefined || commentTxt=="")
			$scope.error='please enter some comments.. '
			else 
				LeaveService.addComment(leaves,commentTxt).then(function(response){
			$scope.commentTxt=""
			$scope.error=""
			$scope.leavesComment=response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.getLeavesComments=function(leavesId){
		LeaveService.getLeavesComments(leavesId).then(
				function(response){
					$scope.leavesComments=response.data//result of query[select * from blogcomment where blogpost_id=?]
				},function(response){
					if(response.status==401)
						$location.path('/login')
				})
	}
})

