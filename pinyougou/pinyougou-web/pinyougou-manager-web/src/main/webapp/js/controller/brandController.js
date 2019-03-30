/** 添加控制器 */
app.controller("brandController",function ($scope,$controller,baseService) {

    /** 品牌控制器继承基础控制器
     第一个$scope为 $scope
    第二个$scope为 brandController*/
    $controller('baseController', {$scope:$scope});

    ///** 读取列表数据绑定到表格中 */
    //$scope.findAll = function () {
    //    /** 发送异步请求查询数据 */
    //    baseService.get("/brand/findAll").then(function (response){
    //        $scope.dataList = response.data;
    //    });
    //};

    /**  添加或修改品牌*/
    $scope.saveOrUpdate = function () {
        var url = "save";
        // 判断id是否存在
        if($scope.entity.id){
            url = "update";
        }
        /**  发送post请求*/
        baseService.sendPost("/brand/" + url ,$scope.entity).then(function (response){
            if(response.data){
                /**  重新加载品牌数据*/
                $scope.reload();
            }else {
                alert("添加失败");
            }
        });
    };

    $scope.show = function (entity) {
        /**  把entity转化成json字符串*/
        var jsonStr = JSON.stringify(entity);
        /**  把json字符串转化成json对象*/
        $scope.entity = JSON.parse(jsonStr);
    };



    ///** 分页查询品牌信息 */
    //$scope.search = function (page, rows) {
    //    /** 发送异步请求分页查询品牌数据 */
    //    $http.get('/brand/findByPage?page=' + page + '&rows=' + rows).then(function (response){
    //        $scope.dataList = response.data.rows;
    //        /** 更新总记录数 */
    //        $scope.paginationConf.totalItems = response.data.total;
    //	});
    //};

    /** 定义查询条件对象 */
    $scope.searchEntity = {};
    /** 多条件分页查询品牌*/
    $scope.search = function (page, rows) {
        baseService.findByPage("/brand/findByPage",page,rows,$scope.searchEntity).then(function (response){
            $scope.dataList = response.data.rows;
            /** 更新总记录数 */
            $scope.paginationConf.totalItems = response.data.total;
        });
    };


    /** 批量删除  */
    $scope.delete = function () {
        if($scope.ids.length > 0){
            if(confirm("确认删除？")){
                /** 发送异步请求 */
                baseService.deleteById("/brand/delete", $scope.ids).then(function (response){
                    if(response.data){
                        $scope.reload();
                        $scope.ids = [];
                    }else {
                        alert("删除失败")
                    }
                })
            }
        }else {
            alert("请选择要删除的");
        }
    };

});