<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Home Page</title>
  <style>
      <#include "css/style.css">
      <#include "css/home.css">
      <#include "css/tooltip.css">
  </style>
</head>
<body>
    <nav class="nav-bar">
      <ul class="menu">
          <li class="menuitem1"><a href="#">Оппоненты</a>
              <ul>
                  <li><a href="<@spring.url '/list'/>">Показать</a></li>
                  <li><a href="#">Сортировать</a>
                      <ul>
                          <li><a href="#">По времени</a></li>
                          <li><a href="#">По цвету фигур</a>
                            <ul>
                                <li><a href="#">BLACK</a></li>
                                <li><a href="#">WHITE</a></li>
                            </ul>
                          </li>
                          <li><a href="#">Home Page 3</a></li>
                      </ul>
                  </li>
                  <li><a href="<@spring.url '/whomChallenge'/>">Вы пригласили сыграть</a></li>
                  <li><a href="<@spring.url '/whoChallenge'/>">Вас пригласили сыграть</a></li>
              </ul>
          </li>
          <li class="menuitem1"><a href="<@spring.url '/history'/>">Проведённые партии</a></li>
          <li class="menuitem1"><a href="<@spring.url '/put_aside'/>">Отложенные партии</a></li>
          <li class="menuitem1"><a href="<@spring.url '/exit'/>">Выход</a></li>
      </ul>
    </nav>
    <br /><br />
    <header>
        <h1>Добро пожаловать, ${chessman.name}!</h1>
    </header>
    <br />
    <section class="two_">
        <#if !gameApp??>
            <h3>Оппоненты: чтобы видеть других Вам необходимо подать заявку на участие в играх.</h3>
            <a href="<@spring.url '/gameApp/${chessman.id}'/>">Подать заявку</a>
        <#else>
            <a href="<@spring.url '/gameApp/${chessman.id}'/>">Посмотреть заявку</a>
        </#if>

    </section>
    <br />
    <section class="one_">
        <#if headerMessage??>
            <h3>${headerMessage}</h3>
        </#if>
        <#if tableData??>
            <div class="history_">

              <table>

                  ${tableData}
              </table>
            </div>
        </#if>
    </section>
    <hr>
    <section class="three_">
        <div class="list_">
            <div class="list_unit" data-tooltip="it's a user_one">
                <span>user_one</span>
            </div>
            <div class="list_unit" data-tooltip="it's a user_two">
                <span>user_two</span>
            </div>
            <div class="list_unit" data-tooltip="it's a user_three">
                <span>user_three</span>
            </div>
            <div class="list_unit" data-tooltip="it's a user_four">
                <span>user_four</span>
            </div>
        </div>
    </section>
</body>
</html>