/**
 * 
 */
app.factory('LeaveService',function($http){
	var leaveService={}
	var BASE_URL="http://localhost:8086/middleware"
		leaveService.addleaves=function(leave){
		var url=BASE_URL + "/addleaves"
		return $http.post(url,leave)
	}
	
	//QUERY - select * from blogpost where approvalstatus=true - IN DAO
	leaveService.getLeavesApproved=function(){
		return $http.get(BASE_URL + "/approvedleaves")
	}
	
	leaveService.getLeaves=function(id){
		return $http.get(BASE_URL + "/getleaves/"+id)
	}
	
	leaveService.getLeavesWaitingForApproval=function(){
		return $http.get(BASE_URL + "/leaveswaitingforapproval")
	}
	
	leaveService.approveLeaves=function(leave){
		return $http.put(BASE_URL + "/approveleaves",leave)
	}
	
	leaveService.rejectLeaves=function(leave,rejectionReason){
    	console.log(leave);
    	return $http.put(BASE_URL+"/rejectleaves?rejectionReason="+rejectionReason,leave)
    }	
    
	leaveService.getNotificationNotViewed=function()
    {
    	return $http.get(BASE_URL + "/notifications")
    }
	
	leaveService.addComment=function(leave,commentTxt){
		return $http.post(BASE_URL + "/addcomment?commentTxt="+commentTxt,leave)
	}
	
	leaveService.getLeavesComments=function(leavesId){
		return $http.get(BASE_URL + "/getcomments/"+leavesId)
	}
	
	return leaveService;
	
})