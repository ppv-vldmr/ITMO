<#import "common.ftl" as c>

<@c.page>
    <#if task??>
        <div class="task">
            <div class="title-wrapper">
                <h2 class="title">${task.name}</h2>
                <#if task.taskList??>
                    <@c.listLink task.taskList></@c.listLink>
                </#if>
                <div class="icon-actions">
                    <input onclick="runAction('DELETE', '/task/${task.id}')" type="image" src="/remove.png"
                           alt="Delete task" class="icon-input task-action">
                    <#if task.status == 'TO_DO'>
                        <input onclick="runAction('POST', '/task/${task.id}/mark?status=IN_PROGRESS', '/task/${task.id}')"
                               type="image" src="/round-play-button.png" alt="Delete task list" class="icon-input task-action">
                    </#if>
                    <#if task.status == 'IN_PROGRESS'>
                        <input onclick="runAction('POST', '/task/${task.id}/mark?status=COMPLETED', '/task/${task.id}')"
                               type="image" src="/check.png" alt="Delete task list" class="icon-input task-action">
                    </#if>
                </div>
            </div>
            <p>${task.description}</p>
            <div class="date">Created: ${task.creationDate?date}</div>
            <div class="status">Status: ${task.status?replace('_', ' ')?lower_case}</div>
        </div>
    <#else>
        <div class="error">No such task</div>
    </#if>
</@c.page>
