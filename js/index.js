
const postForm = function(){

    let text = document.querySelector('.form .text');
    let textData = text.value;
    $.post("http://localhost:8080/ToDoList_war/ToDoList",
        {"text": textData,"method":"ADD"},
        function (data) {
            console.log("success" + data);
        },
        "json"
    );
    }

const getTextDefault = function(){
        let header = '<div class="text show"><div class="text-item">';

        let last = '<button class="button check"><i class="fa fa-check"></i></button>' +
                        '<button class="button close"><i class="fa fa-close"></i></button>'+
                    '</div></div>';
        $.get("http://localhost:8080/ToDoList_war/ToDoList", {state:0},
            function (data) {
                console.log("data"+data);
                $(".text-show .text:first").empty();
                for(let i=0;i < data.length;i++){
                    let text = $('<div class = "item-text"></div>>').text(data[i].text);
                    $(".text-show .text:first").append(header + text +last);
                }
            },
            "json"
        );
        
    };
const getText = function(){
    $.get("http://localhost:8080/ToDoList_war/ToDoList", {state:1},
        function (data) {
            $(".text-show .text:last").empty();
            for(let i=0;i < data.length;i++){
                let text = $("<h2></h2>").text(data[i].text);
                $(".text-show .text:last").append(text);

            }
        },
        "json"
    );
}

$(document).ready(function () {
    // 创建类
    let click = new Click();
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