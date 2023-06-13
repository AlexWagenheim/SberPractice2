<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Ваше системное время</title>
</head>
<style>
    .send-button {
      text-decoration: none;
      display: inline-block;
      padding: 10px 30px;
      margin: 10px 20px;
      position: relative;
      overflow: hidden;
      border: 2px solid #fe6637;
      background: #fff;
      border-radius: 8px;
      font-family: 'Montserrat', sans-serif;
      font-size: 20pt;
      color: #fe6637;
      transition: .2s ease-in-out;
    }
    .send-button:before {
      content: "";
      background: linear-gradient(90deg, rgba(255, 255, 255, .1), rgba(255, 255, 255, .5));
      height: 70px;
      width: 50px;
      position: absolute;
      top: -8px;
      left: -75px;
      transform: skewX(-45deg);
    }
    .send-button:hover {
      background: #fe6637;
      color: #fff;
    }
    .send-button:hover:before {
      left: 150px;
      transition: .5s ease-in-out;
    }
</style>
<body>
    <div style="width: 700px; margin-left: auto; margin-right: auto; text-align: center; margin-top: 300px;">
        <h1>Узнайте Ваше системное время!</h1>
        <form action="/time" method="post">
            <button class="send-button">Узнать!</button>
        </form>
    </div>
</body>
</html>