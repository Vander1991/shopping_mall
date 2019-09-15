//服务层
app.service('brandService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../brand/findAll');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../brand/findPage?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../brand/findOne?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../brand/add',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../brand/update',entity );
	}
	//删除
	this.del=function(ids){
		return $http.get('../brand/delete?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('../brand/search?page='+page+"&rows="+rows, searchEntity);
	}
	//下拉列表数据
	this.selectOptionList=function(){
		return $http.get('../brand/selectOptionList');
	}
	
});
