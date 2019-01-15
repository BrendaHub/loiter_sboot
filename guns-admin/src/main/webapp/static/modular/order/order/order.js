/**
 * 订单管理管理初始化
 */
var Order = {
    id: "OrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Order.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '商品名称', field: 'goods_name', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '下单地点', field: 'place', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '下单时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '下单用户名称', field: 'user_name', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '下单用户电话', field: 'user_phone', visible: true, align: 'center', valign: 'middle',sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Order.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Order.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加订单管理
 */
Order.openAddOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加订单管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/order/order_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看订单管理详情
 */
Order.openOrderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '订单管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/order/order_update/' + Order.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除订单管理
 */
Order.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/order/delete", function (data) {
            Feng.success("删除成功!");
            Order.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("orderId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
Order.formParams = function() {
    var queryData = {};

    queryData['goodsName'] = $("#goodsName").val();

    return queryData;
};

/**
 * 查询订单管理列表
 */
Order.search = function () {
    var queryData = {};
    queryData['goodsName'] = $("#goodsName").val();
    Order.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Order.initColumn();
    var table = new BSTable(Order.id, "/order/list", defaultColunms);
    /**
     * 设置分页方式：server 或者 client
     */
    table.setPaginationType("server");
    table.setQueryParams(Order.formParams());
    Order.table = table.init();
});
