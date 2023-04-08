<#import "common.ftl" as c>

<@c.page>
    <div class="content-wrapper">
        <div class="task-lists">
            <#if taskLists?size == 0>
                No task lists
            <#else>
                <#list taskLists as taskList>
                    <div class="task-list-item">
                        <@c.listLink taskList></@c.listLink>
                    </div>
                </#list>
            </#if>
            <form class="create-task-list-form" method="post" action="/task-list/">
                <label>Create new task list: <input id="task-list-name" name="name" type="text"></label>
                <input class="submit" type="submit">
            </form>
        </div>
        <div class="board-wrapper">
            <@c.boardView board=board createLink='/task/'></@c.boardView>
        </div>
    </div>
</@c.page>
