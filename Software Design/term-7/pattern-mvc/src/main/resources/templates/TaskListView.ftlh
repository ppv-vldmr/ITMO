<#import "common.ftl" as c>

<@c.page>
    <#if taskList??>
        <div class="centered-title-wrapper">
            <h2 class="title">${taskList.name}</h2>
            <input onclick="runAction('DELETE', '/task-list/${taskList.id}')" type="image" src="/remove.png"
                   alt="Delete task list" class="icon-input delete-task-list">
        </div>
        <@c.boardView board=board createLink='/task-list/${taskList.id}/task/'></@c.boardView>
        <div class="date">Created: ${taskList.creationDate?date}</div>
    <#else>
        <div class="error">No such task list</div>
    </#if>
</@c.page>
