<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/9
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>区域管理</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript">
        $(function () {
            //使用datagrid绑定数据库
            $('#dg').datagrid({
                url: 'pageShow',
                title: "区域信息",
                /* singleSelect:true,只允许选中一行 */
                pagination: true, //开启分页
                pageSize: 3,  //设置每页多少条
                toolbar: '#ToolBar',//toolbar属性 指定工具栏  增删改功能
                pageList: [3, 6, 10, 20],  //设置选择分页大小的条数
                columns: [[
                    {field: 'cb', checkbox: true},// 开启复选框 给个默认名字就行
                    {field: 'id', title: '编号', width: 100},
                    {field: 'name', title: '区域名称', width: 100},
                    {
                        field: 's', title: '操作', width: 200,
                        formatter: function (value, row, index) {
                            var str = "<a href='javascript:deleteDis(" + row.id + ");'>删除</a>|<a href='javascript:updateDialog()'>修改</a>|<a href='javascript:showStreet()'>查看街道</a>"
                            return str;
                        }
                    }
                ]]
            });
        });

        //1 显示街道的对话框
        function showStreet() {
            //判断用户选择
            //1.获取dagagrid的选中行
            var SelectRows = $("#dg").datagrid('getSelections');
            //返回数组
            if (SelectRows.length == 1) {
                $('#updateDialog').window('setTitle', "编辑区域");
                $('#updateDialog').window('open');
                //获取当前行的数据:获取主键，查询数据库获取单行数据
                //如果当显示的数据在dagagrid中存在，无需查询数据库，如果当显示的数据在datagrid中不全，需要查询数据库获单条
                //发异步请求查询数据库
                $.post("seleteOne", {"id": SelectRows[0].id}, function (data) {
                    $("#form2").form('load', data); //将对象还原表单
                }, "json");
                //将信息还原到表单中.
                //$("#form1").form('load',json对象);
                //$("#form2").form('load',{"name":"默认值"});  //name表示表单对象名称
                //$("#form2").form('load',SelectRows[0]);  //{"id":1001,"name":"东城"}
            } else {
                $.messager.alert('>>提示', '请选择一行!', 'warn');  //提示框
            }
        }


        //删除单行
        function deleteDis(id) {  //参数表示接收id
            //确认消息框
            $.messager.confirm('>>提示', '确定删除吗?', function (r) {
                if (r) {  //实现删除
                    //删除数据库
                    $.post("deleteDis", {"id": id}, function (data) {
                        if (data.result > 0) {
                            //刷新datagrid
                            $('#dg').datagrid("reload");
                        } else {
                            $.messager.alert('>>提示', '删除失败！', 'error');
                        }
                    }, "json");
                }else { //取消删除 清空所选行
                    $("#dg").datagrid("clearSelections");//清空选中行
                }
            });
        }

        //1 打开添加对话框
        function addDialog() {
            /*    $('#AddDialog').window('setTitle','添加信息');  */
            $('#AddDialog').window('open');  // open a window
        }

        //关闭对话框  统一关闭
        function CloseDialog1(id) {
            $('#' + id).window('close'); //关闭
            $("#dg").datagrid("clearSelections");//清空选中行
            /*$("#Table").datagrid("clearChecked");*/
        }

        //2 异步请求-- 实现添加功能
        function SaveDistrict() {
            //使用easuyi插件  以异步方式提交表单
            $('#form1').form('submit', {
                url: "addDis",
                success: function (data) {
                    // data获取的是字符串
                    // 必须 将返回的json字符串转化为json对象
                    data = $.parseJSON(data);
                    if (data.result > 0) {
                        $('#dg').datagrid("reload");//刷新表格数据
                        $.messager.alert('>>提示', '添加成功！', 'info');  //提示框
                        $('#AddDialog').window('close'); //关闭
                    } else {
                        $.messager.alert('>>提示', '添加失败！', 'error');
                        $('#AddDialog').window('close'); //关闭
                    }
                }
            });
        }

        //1 显示修改的对话框
        function updateDialog() {
            //判断用户选择
            //1.获取dagagrid的选中行
            var SelectRows = $("#dg").datagrid('getSelections');
            //返回数组
            if (SelectRows.length == 1) {
                /*$('#updateDialog').window('setTitle', "编辑区域"); 设置窗口名*/
                $('#updateDialog').window('open');
                //获取当前行的数据:获取主键，查询数据库获取单行数据
                //如果当显示的数据在dagagrid中存在，无需查询数据库，如果当显示的数据在datagrid中不全，需要查询数据库获单条
                //发异步请求查询数据库
                $.post("seleteOne", {"id": SelectRows[0].id}, function (data) {
                    $("#form2").form('load', data); // 将对象还原表单
                }, "json");
                //将信息还原到表单中.
                //$("#form1").form('load',json对象);
                //$("#form2").form('load',{"name":"默认值"});  //name表示表单对象名称
                //$("#form2").form('load',SelectRows[0]);  //{"id":1001,"name":"东城"}
            } else {
                $.messager.alert('>>提示', '请选择一行!', 'warn');  //提示框
            }
        }

        //更新的保存
        function upSaveDistrict() {
            $('#form2').form('submit', {
                url: "updateDis",
                success: function (data) {  //data表示的字符串
                    //将返回的json字符串转化为json对象
                    data = $.parseJSON(data);
                    if (data.result > 0) {
                        $('#dg').datagrid("reload");//刷新表格数据
                        $.messager.alert('>>提示', '更新成功！', 'info');  //提示框
                        $('#updateDialog').window('close'); //关闭
                    } else {
                        $.messager.alert('>>提示', '更新失败！', 'error');
                        $('#updateDialog').window('close'); //关闭
                    }
                }
            });
        }
        //批量删除区域
        function deleteMoreDistrict() {
            //1.获取dagagrid的选中行
            var SelectRows = $("#dg").datagrid('getSelections');  //返回数组
            //2.判断是否选择的行
            if (SelectRows.length == 0) {
                $.messager.alert('>>提示', '请选择后再进行删除.', 'warn');
            } else {
                //确认删除
                $.messager.confirm('>>提示', '确定删除吗?想好在点!', function (r) {
                    if (r) {  //删除
                        //3.获取选中项的值,拼接字符串 格式:1,2,3
                        var val = "";
                        for (var i = 0; i < SelectRows.length; i++) {
                            val = val + SelectRows[i].id + ",";
                        }
                        val = val.substring(0, val.length - 1);
                        //4.发送异步请求到服务器实现删除
                        $.post("delMoreDistrict", {"ids": val}, function (data) {
                            if (data.result > 0) {
                                //刷新datagrid
                                $('#dg').datagrid("reload");
                            } else {
                                $.messager.alert('>>提示', '删除多项失败！', 'error');
                            }
                        }, "json");
                    }
                });
            }
        }

    </script>
</head>

<%-- -------------------------------------------------------------------%>
<body>
<!--显示所有区域 表格标签-->
<table id="dg"></table>

<%--制作工具栏--%>
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:addDialog()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a> <a
            href="javascript:updateDialog()" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:deleteMoreDistrict()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">批量删除</a>
        <%-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
            onclick="javascript:alert('easyui')">easyui</a>--%>
    </div>
</div>

<!--1 添加对话框-->
<div id="AddDialog" class="easyui-dialog" title="添加窗口" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form method="post" name="form1" id="form1">
        区域名称:<input type="text" name="name">
    </form>
</div>
<!--2 添加对话框的按钮-->
<div id="AddDialogButtons">
    <a href="javascript:SaveDistrict()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:CloseDialog1('AddDialog')"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>


<!--1 修改对话框-->
<div id="updateDialog" class="easyui-dialog" title="修改窗口" buttons="#updateDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form method="post" name="form2" id="form2">
        区域名称:<input type="text" name="name">
        <input type="hidden" name="id"><%-- 修改时隐藏传入id值--%>
    </form>
</div>
<!--2 修改对话框的按钮-->
<div id="updateDialogButtons">
    <a href="javascript:upSaveDistrict()" class="easyui-linkbutton"
       iconCls="icon-ok">更新</a> <a href="javascript:CloseDialog1('updateDialog')"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

</body>
</html>
