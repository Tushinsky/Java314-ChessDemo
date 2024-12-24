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
          <li class="menuitem1"><a href="#">Отложенные партии</a></li>
      </ul>
  </nav>
  <br /><br />
  <header>
  <h1>Добро пожаловать, ${chessman.name}!</h1>
  </header>
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
  <section class="two_">
      <#if !gameApp??>
          <h3>Оппоненты: чтобы видеть других Вам необходимо подать заявку на участие в играх.</h3>
          <a href="<@spring.url '/gameApp/${chessman.id}'/>">Подать заявку</a>
      <#else>
          <a href="<@spring.url '/gameApp/${chessman.id}'/>">Посмотреть заявку</a>
          <#if appList??>
              <div>
                  <h3>Оппоненты</h3>
              </div>
              <div class="opponents-list">

                  <table>
                      <tr>
                          <th width="200">Ник</th>
                          <th width="100">Время</th>
                          <th width="100">Цвет</th>
                          <th width="100">Занят</th>
                          <th width="100">Вызов</th>
                      </tr>

                          <#list appList as app>
                              <tr>
                                  <td>${app.nic}</td>
                                  <td>${app.gameTime}</td>
                                  <td>${app.color}</td>
                                  <td>${app.busy?string('yes','no')}</td>
                                  <td><a href="<@spring.url '/challenge/${app.id}'/>">Вызвать</a></td>
                              </tr>
                          </#list>

                  </table>

              </div>
          </#if>
          <hr>
          <div class="opponents-list">
              <#if whomChallenge??>
                  <h3>Приглашаете сыграть</h3>
                  <table>
                      <tr>
                          <th>Ник</th>
                          <th>Вызов принят</th>
                          <th>Отменить</th>
                      </tr>
                      <#list whomChallenge as whom>
                          <tr>
                              <td>${whom.opponentName}</td>
                              <td>${whom.takeIt?string('yes','no')}</td>
                              <td><a href="<@spring.url '/cancel/${whom.id}'/>" >Отменить</a></td>
                          </tr>
                      </#list>
                  </table>
              </#if>
          </div>
          <hr>
          <div class="opponents-list">
              <#if whoChallenge??>
                  <h3>Приглашают с ними сыграть</h3>
                  <table>
                      <tr>
                          <th>Ник</th>
                          <th>Вызов принят</th>
                          <th>Действие</th>
                      </tr>
                      <#list whoChallenge as who>
                          <tr>
                              <td>${who.chessManName}</td>
                              <td>${who.takeIt?string('yes','no')}</td>
                              <#if who.takeIt??>
                                <td><a href="<@spring.url '/cancel/${who.id}&true'/>">Принять</a></td>
                              <#else>
                                <td><a href="<@spring.url '/cancel/${who.id}&false'/>">Отказаться</a></td>
                              </#if>
                          </tr>
                      </#list>
                  </table>
              </#if>
          </div>
      </#if>

  </section>
</body>
</html>