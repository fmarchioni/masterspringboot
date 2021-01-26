<#import "/spring.ftl" as spring>
<html>
<h1>My customers</h1>
<ul>
<#list customers as customer>
    <li>${customer}</li>
</#list>
</ul>
<br>
<a href="/logout">Logout</a>
</html>