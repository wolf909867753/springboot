<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<header>
    <meta charset="UTF-8">
    <script type="text/javascript" src="/js/jquery-1.11.2.js"></script>
    <script type="text/javascript">
        $(function(){
            $('#btn').on('click',function(){
                $.ajax({
                    type:'POST',
                    url:'/api/city/'+$('#id').val(),
                    data:$('#form').serialize(),
                    success : function(msg){
                        msg = eval(msg);
                        if(0 == msg.code){
                            $('#error').text(msg.lists[0].id+'===='+msg.lists[0].provinceId+'===='+msg.lists[0].cityName+'===='+msg.lists[0].description);
                        }else{
                            $('#error').text(msg.message);
                        }
                    }
                });
            });
        });
    </script>
</header>
<body>
<form action="" method="post" id="form">
    <input id="id" name="id" value="">
    <button type="button" id="btn" value="确定">确定</button>
    <p id="error" style="color: #f00"></p>
</form>
</body>
</html>
