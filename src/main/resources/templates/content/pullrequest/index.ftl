<#import "/spring.ftl" as spring />

<div class="row">
    <form action="" method="get">
        <div class="input-field col l6">
            <@spring.bind "repoMap" />
            <@spring.formSingleSelect "gitHubPullRequestForm.repo", repoMap, "class='blue-text'" />
        </div>
        <div class="input-field col l2">
            <@spring.bind "pullRequestStateMap" />
            <@spring.formSingleSelect "gitHubPullRequestForm.state", pullRequestStateMap, "class='blue-text'" />
        </div>

        <div class="input-field col l4">
            <button class="btn waves-effect waves-light col l12" type="submit">
                View
                <i class="mdi-action-search right"></i>
            </button>
        </div>
    </form>
</div>

<div class="row">
    <div class="col l12">
        <#if (pullRequestPaginationModel.pullRequestModelList)??>
            <div class="card white">
                <div class="card-content">
                    <table class="striped blue-text">
                        <thead>
                        <tr>
                            <th>Number</th>
                            <th>Title</th>
                            <th>Status</th>
                            <th>Time</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list pullRequestPaginationModel.pullRequestModelList as pullRequestModel>
                            <tr>
                                <td>
                                    <a href="${gitHubPullRequestUrl}${pullRequestModel.pullRequest.number}">
                                    ${pullRequestModel.pullRequest.number}
                                    </a>
                                </td>
                                <td>${pullRequestModel.pullRequest.title}</td>
                                <#if pullRequestModel.pullRequest.state == 'open'>
                                    <td class="green-text" style="text-transform: capitalize">${pullRequestModel.pullRequest.state}</td>
                                <#else>
                                    <td class="red-text accent-2" style="text-transform: capitalize">${pullRequestModel.pullRequest.state}</td>
                                </#if>
                                <td>
                                    <span title="${pullRequestModel.startDate} - ${pullRequestModel.endDate}">
                                        ${pullRequestModel.diffTime}
                                    </span>
                                </td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
                <#if (pullRequestPaginationModel.previousPage)?? || (pullRequestPaginationModel.nextPage)??>
                    <div class="card-action">
                        <div class="row">
                            <div class="col l12 center">
                                <#if (pullRequestPaginationModel.previousPage)??>
                                    <a class="btn waves-effect waves-light white-text"
                                       href="?repo=${gitHubPullRequestForm.repo}&state=${gitHubPullRequestForm.state}&page=${pullRequestPaginationModel.previousPage}">
                                        <i class="mdi-image-navigate-before left"></i>
                                        Prev
                                    </a>
                                </#if>
                                <#if (pullRequestPaginationModel.nextPage)??>
                                    <a class="btn waves-effect waves-light white-text"
                                       href="?repo=${gitHubPullRequestForm.repo}&state=${gitHubPullRequestForm.state}&page=${pullRequestPaginationModel.nextPage}">
                                        Next
                                        <i class="mdi-image-navigate-next right"></i>
                                    </a>
                                </#if>
                            </div>
                        </div>
                    </div>
                </#if>
            </div>
        </#if>
    </div>
</div>
