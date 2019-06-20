/**
 * 
 */
app.controller('LeavesCtrl',function($scope,$location,LeaveService,$rootScope){
	//View To BlogPostCtrl
	$scope.addleaves=function(leave){
		LeaveService.addleaves(leave).then(function(response){
			alert('leaves inserted successfully and it is waiting for approval')
			$location.path('/home')
		},function(response){
			//Not Authenticated
			if(response.status==401)
				$location.path('/login')
			$scope.error=response.data
			//Exception while inserting blogpost object
		})
	}
	//LIST OF BLOGS APPROVED
	function getLeavesApproved(){
    LeaveService.getLeavesApproved().then(function(response){
    	//response.data? -> List of blogs approved
    	$scope.leavesApproved=response.data
    },function(response){
    	if(response.status==401)
    		$location.path('/login')
    })
	}
	
	function getLeavesWaitingForApproval(){
		LeaveService.getLeavesWaitingForApproval().then(function(response){
			$scope.leavesWaitingForApproval=response.data
		},function(response){
			if(response.status==401)
	    		$location.path('/login')
		})
	}	
	
	getLeavesApproved()
	//call the function only for ADMIN role
	if($rootScope.user.role=='ADMIN')
	getLeavesWaitingForApproval()
	
})
