//json返回数据的code的状态码
const GET_OK = 20011,POST_OK = 20021,PUT_OK = 20031,DELETE_OK = 20041,
        GET_ERR = 20010,POST_ERR = 20020,PUT_ERR = 20030,DELETE_ERR = 20040;

// 添加文本
const postForm = function(){

    let text = document.querySelector('.form .text');
    let textData = text.value;
    $.post("http://localhost:8080/Test02_war_exploded/ToDoList",
        {"text": textData},
        function (Data) {
            if(Data.code === GET_OK){
                joinHtml(Data.data)
            }
        },
        "json"
    );
};
const joinHtml = function (data){
    let header = '<div class="text-item">';
    let last = '<button class="button check"><i class="fa fa-check"></i></button>' +
        '<button class="button close"><i class="fa fa-close"></i></button>'+
        '</div>';
    let text = '<div class = "item-text">'+ data.text +'</div>';
    $(".text-show .text:first").append(header + text +last);
    blingUpData(data.id);
    blingDelete(data.id);
}
const  joinColse = function (data){
    let header = '<div class="text-item">';

    let last = '<button class="button close"><i class="fa fa-close"></i></button>'+
        '</div>';
    let text = '<div class = "item-text">'+ data.text +'</div>';
    $(".text-show .text:last").append(header + text + last);
    blingDelete(data.id);
}

// 查询所有state为0 即所有未办事项
const getTextDefault = function(){

        $.get("http://localhost:8080/Test02_war_exploded/ToDoList/0",
            function (Data) {
                $(".text-show .text:first").empty();
                for(let i=0;i < Data.data.length;i++) {
                    joinHtml(Data.data[i])
                }
            },
        );
        
    };
// 查询所有state为 1 即所有已完成事项
const getText = function(){

    $.get("http://localhost:8080/Test02_war_exploded/ToDoList/1",
        function (Data) {
            $(".text-show .text:last").empty();
            for(let i=0;i < Data.data.length;i++){
                joinColse(Data.data[i])
            }
        },
    );
};
// 更新state 函数 传入参数 id
const upData = function(id){
    $.ajax({
        url : "http://localhost:8080/Test02_war_exploded/ToDoList/"+id,
        type : "PUT",
        async: false,
        success : function(result){

        }
        })
};

// 删除事项函数,传入参数 id
 const deleteData = function(id){
    $.ajax({
        url : "http://localhost:8080/Test02_war_exploded/ToDoList/"+id,
        type : "DELETE",
        async: false,
        success : function(result){

        },
        })
};

// 给按钮绑定事件
// 给state更新按钮绑定事件，传入id 表示主键
const blingUpData = function(id){
    let checkBtn = $(".check");
    checkBtn.last().click(function(){
        upData(id)
        getTextDefault()
    });
};
// 给删除按钮绑定事件，传入id 表示主键
const blingDelete = function(id){
    let closeBtn = $(".show .text-item .close");
    closeBtn.last().click(function(){
            deleteData(id)
            getTextDefault()
            getText()
    });
}
// 未办事项和已完成事项的切换
const unToDoList = function(){
    // 导航栏
    $(".item:last").removeClass("show");
    $(".item:first").addClass("show");
    // 显示框
    $(".text-show .text:last").removeClass("show");
    $(".text-show .text:first").addClass("show");
    // 请求数据
    getTextDefault();
}
const DoList = function(){
    // 导航栏
    $(".item:first").removeClass("show");
    $(".item:last").addClass("show");
    // 显示框
    $(".text-show .text:first").removeClass("show");
    $(".text-show .text:last").addClass("show");
    // 请求数据
    getText();
}
// js函数执行
$(document).ready(function () {

    getTextDefault();
    getText();
    // 添加按钮点击事件
    $(".form .button").click(function(){
        // 函数调用
        postForm()
    });
    // 未办事项点击事件
    $(".item:first").click(function(){
        unToDoList();
    });
    // 已完成点击事件
    $(".item:last").click(function(){
        DoList();
    });
});