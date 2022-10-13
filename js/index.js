// 添加文本
const postForm = function(){

    let text = document.querySelector('.form .text');
    let textData = text.value;
    $.post("http://localhost:8080/ToDoList_war/ToDoList",
        {"text": textData,"method":"ADD"},
        function (data) {
            // console.log("success" + data);
        },
        "json"
    );
};
// 查询所有state为0 即所有未办事项
const getTextDefault = function(){
        let header = '<div class="text-item">';

        let last = '<button class="button check"><i class="fa fa-check"></i></button>' +
                        '<button class="button close"><i class="fa fa-close"></i></button>'+
                    '</div>';
        $.get("http://localhost:8080/ToDoList_war/ToDoList", {state:0},
            function (data) {
                
                $(".text-show .text:first").empty();
                for(let i=0;i < data.length;i++){
                    let text = '<div class = "item-text">'+ data[i].text +'</div>';
                    $(".text-show .text:first").append(header + text +last);
                    blingUpData(i,data[i].id);
                    blingDelete(i,data[i].id);
                }
            },
            "json"
        );
        
    };
// 查询所有state为 1 即所有已完成事项
const getText = function(){
    let header = '<div class="text-item">';

    let last = '<button class="button close"><i class="fa fa-close"></i></button>'+
                '</div>';

    $.get("http://localhost:8080/ToDoList_war/ToDoList", {state:1},
        function (data) {
            $(".text-show .text:last").empty();
            for(let i=0;i < data.length;i++){
                let text = '<div class = "item-text">'+ data[i].text +'</div>';
                $(".text-show .text:last").append(header + text + last);
                blingDelete(i,data[i].id);
            }
        },
        "json"
    );
};
// 更新state 函数 传入参数 id
const upData = function(id){
    $.post("http://localhost:8080/ToDoList_war/ToDoList",
        {"id": id,"method":"PUT"},
        function (data) {
            // console.log("success" + data);
        },
        "json"
    );
};

// 删除事项函数,传入参数 id
const deleteData = function(id){
    $.post("http://localhost:8080/ToDoList_war/ToDoList",
    {"id": id,"method":"DELETE"},
    function (data) {
        // console.log("success" + data);
    },
    "json"
);
};

// 给按钮绑定事件

// 给state更新按钮绑定事件，传入num 表示第几个元素,id 表示主键
const blingUpData = function(num,id){
    let checkBtn = $(".check");
    checkBtn.eq(num).click(function(){
        upData(id);
    });
};
// 给删除按钮绑定事件，传入num 表示第几个元素,id 表示主键
const blingDelete = function(num,id){
    let closeBtn = $(".close");
    
    closeBtn.eq(num).click(function(){
        deleteData(id);
    });
}

$(document).ready(function () {

    // 按钮点击事件
    $(".form .button").click(function(){
        // 函数调用
        postForm()
        getTextDefault();
    });
   
    // 未办事项点击事件
    $(".item:first").click(function(){
        
        // 导航栏
        $(".item:last").removeClass("show");
        $(".item:first").addClass("show");
        // 显示框
        $(".text-show .text:last").removeClass("show");
        $(".text-show .text:first").addClass("show");
        // 请求数据
        getTextDefault();
    });
    // 已完成点击事件
    $(".item:last").click(function(){
        
        // 导航栏
        $(".item:first").removeClass("show");
        $(".item:last").addClass("show");
        // 显示框
        $(".text-show .text:first").removeClass("show");
        $(".text-show .text:last").addClass("show");
        // 请求数据
        getText();
    });

});