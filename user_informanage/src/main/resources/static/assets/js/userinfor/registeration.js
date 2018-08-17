$(function() {       /* 文档加载，执行一个函数*/
    // spring security 验证 ajax 需要添加token，跨站防护
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });


    $('#registerationForm').bootstrapValidator({
        message: '此值无效',
        feedbackIcons: {        /*input状态样式图片*/
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },

        locale: 'zh_CN',

        fields: {       /*验证：规则*/

            username: {//验证input项：验证规则
                message: 'The username is not valid',

                validators: {
                    notEmpty: {//非空验证：提示消息
                        message: '用户名不能为空'
                    },

                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '用户名长度必须在6到30之间'
                    },

                    threshold: 6, //有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
                    remote: {       //ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                        url: '/checkName',       //验证地址
                        delay: 2000,      //每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST',        //请求方式
                        message: '用户名已被注册'      //提示消息
                    },

                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '用户名由数字字母下划线和.组成'
                    }

                }

                //threshold: 6, //有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）

            },

            realname: {
                message:'昵称无效',
                validators: {
                    notEmpty: {
                        message: '昵称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 30,
                        message: '昵称长度必须在1到30之间'
                    }
                }

            },

            password: {
                message:'密码无效',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '密码长度必须在6到30之间'
                    },
                    identical: {//相同
                        field: 'password', //需要进行比较的input name值
                        message: '两次密码不一致'
                    },
                    different: {//不能和用户名相同
                        field: 'username',//需要进行比较的input name值
                        message: '不能和用户名相同'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '密码由数字字母下划线和.组成'
                    }
                }
            },

            repassword: {
                message: '密码无效',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '密码长度必须在6到30之间'
                    },
                    identical: {//相同
                        field: 'password',
                        message: '两次密码不一致'
                    },
                    different: {//不能和用户名相同
                        field: 'username',
                        message: '不能和用户名相同'
                    },
                    regexp: {//匹配规则
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '密码由数字字母下划线和.组成'
                    }
                }
            },

            email: {
                validators: {
                    notEmpty: {
                        message: '邮件不能为空'
                    },
                    emailAddress: {
                        message: '请输入正确的邮件地址如：123@163.com'
                    }
                }
            }

        }



    }).on('success.form.bv', function(e) {      //点击提交之后

        // 防止表单提交
        //e.preventDefault();

        // 获取表单实例
        var $form = $(e.target);

        // 获取表单实例
        var bv = $form.data('bootstrapValidator');

        // Use Ajax to submit form data 提交至form标签中的action，result自定义
        $.post($form.attr('action'), $form.serialize(), function(result) {

            // do something...
        });

    });

    //$("#registerationForm").submit(function(ev){ev.preventDefault();});

    $("#submit").on("click", function(){
        var bootstrapValidator = $("#registerationForm").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid())
            $("#registerationForm").submit();
        else return;
    });

});