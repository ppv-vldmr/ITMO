<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Task Viewer</title>
        <link rel="stylesheet" type="text/css" href="/style.css">
    </head>
    <script>
        function runAction(method, url, redirectUrl = '/') {
            const XHR = new XMLHttpRequest();
            XHR.addEventListener('load', function() {
                location.href = redirectUrl;
            });
            XHR.addEventListener('error', function() {
                location.href = redirectUrl;
            });

            XHR.open(method, url);
            XHR.send();
        }
    </script>
    <body>
    <header class="header">
<#--        <img src="https://img1.picmix.com/output/stamp/normal/2/9/8/3/1793892_62927.gif"-->
<#--             alt="Welcome to Task Viewer" title="Welcome to Task Viewer"/>-->
        <a href="/"><h1>Task Viewer</h1></a>
    </header>
    <main class="content">
        <#nested/>
    </main>
    <footer class="footer">
        <a href="#">Task Viewer</a> &copy; 2023 by Vladimir Popov
    </footer>
    </body>
    </html>
</#macro>

<#macro listLink taskList>
<#-- @ftlvariable name="task" type="org.example.model.TaskList" -->
    <a href="/task-list/${taskList.id}" class="list-link list-link-${taskList.id % 6}">${taskList.name}</a>
</#macro>

<#macro taskView task>
<#-- @ftlvariable name="task" type="org.example.dto.TaskDto" -->
    <a href="/task/${task.id}"><b>${task.name}</b></a>
    <#if task.taskList??>
        <@listLink taskList=task.taskList></@listLink>
    </#if>
    <#if task.status == 'TO_DO'>
        <input onclick="runAction('POST', '/task/${task.id}/mark?status=IN_PROGRESS', location.href)" type="image"
               src="/round-play-button.png" alt="Delete task list" class="icon-input task-action">
    </#if>
    <#if task.status == 'IN_PROGRESS'>
        <input onclick="runAction('POST', '/task/${task.id}/mark?status=COMPLETED', location.href)" type="image"
               src="/check.png" alt="Delete task list" class="icon-input task-action">
    </#if>
    <#if task.status == 'COMPLETED'>
        <input onclick="runAction('DELETE', '/task/${task.id}', location.href)" type="image"
               src="/remove.png" alt="Delete task list" class="icon-input task-action">
    </#if>
    <p>${task.description}</p>
</#macro>

<#macro boardView board createLink>
<#-- @ftlvariable name="board" type="org.example.dto.BoardDto" -->
    <div class="board">
        <div class="to-do column">
            <h3 class="column-name">To Do</h3>
            <#if board.todoTasks?size == 0>
                No tasks
            <#else>
                <ul>
                    <#list board.todoTasks as task>
                        <li><@taskView task></@taskView></li>
                    </#list>
                </ul>
            </#if>
            <form class="create-task-form" method="post" action="${createLink}">
                <label for="task-name">Create new task:</label>
                <input id="task-name" name="name" type="text">
                <label><textarea id="task-description" name="description"></textarea></label>
                <input class="submit" type="submit">
            </form>
        </div>
        <div class="in-progress column">
            <h3 class="column-name">In Progress</h3>
            <#if board.inProgressTasks?size == 0>
                No tasks
            <#else>
                <ul>
                    <#list board.inProgressTasks as task>
                        <li><@taskView task></@taskView></li>
                    </#list>
                </ul>
            </#if>
        </div>
        <div class="completed column">
            <h3 class="column-name">Completed</h3>
            <#if board.completedTasks?size == 0>
                No tasks
            <#else>
                <ul>
                    <#list board.completedTasks as task>
                        <li><@taskView task></@taskView></li>
                    </#list>
                </ul>
            </#if>
        </div>
    </div>
</#macro>
