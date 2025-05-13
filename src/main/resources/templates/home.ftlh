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
    <header>
        <nav class="nav-bar">
            <h1>Добро пожаловать, ${chessman.name}!</h1>
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
    </header>
    <br />
    <section class="two_">
        <#if !gameApp??>
            <h3>Оппоненты: чтобы видеть других Вам необходимо подать заявку на участие в играх.</h3>
            <a href="<@spring.url '/gameApp/${chessman.id}'/>">Подать заявку</a>
        <#else>
            <a href="<@spring.url '/gameApp/${chessman.id}'/>">Посмотреть заявку</a>
            <#include "gameApp.ftlh">
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
            <br />
            <hr>
            <section class="three_">
                <div class="list_">
                    <#if list_unitData??>
                        ${list_unitData}
                    </#if>
                </div>
            </section>
        </#if>

    </section>

</body>
</html>