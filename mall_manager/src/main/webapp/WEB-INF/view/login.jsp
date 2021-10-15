<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div style="position: absolute;left: 50%;top: 30%;transform: translate(-50%,-50%)">
    <form action="/ja" method="post" id="dataFrom" enctype="multipart/form-data">
        <h1 align="center">word英中日翻译</h1>
        <table style="border-collapse:separate; border-spacing:10px 10px;">
            <tr>
                <td><label for="startRow">翻译开始读取行数:</label></td>
                <td><input type="text" name="startRow" id="startRow" /></td>
            </tr>
            <tr>
                <td><label for="beTrColumn">需要翻译的列数:</label></td>
                <td><input type="text" name="beTrColumn" id="beTrColumn" /></td>
            </tr>
            <tr>
                <td><label for="beTrLanguage">需要翻译列语言:</label></td>
                <td>
                    <select id="beTrLanguage" name="beTrLanguage">
                        <option value="en">英语</option>
                        <option value="ja">日语</option>
                        <option value="zh-Hans">简体中文</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="trColumn">翻译完成填写列数:</label></td>
                <td>
                    <input type="text" name="trColumn" id="trColumn" />
                </td>
            </tr>
            <tr>
                <td><label for="trLanguage">翻译成什么语言:</label></td>
                <td>
                    <select id="trLanguage" name="trLanguage">
                        <option value="en">英语</option>
                        <option value="ja">日语</option>
                        <option value="zh-Hans">简体中文</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label>上传翻译文件:</label></td>
                <td>
                    <input type="file" name="uploadFile" id="uploadFile" multiple="multiple" >
                </td>
            </tr>
        </table>
        <button type="submit" style="margin-top: 30px;margin-left: 150px">翻译</button>
    </form>
</div>
</body>
</html>