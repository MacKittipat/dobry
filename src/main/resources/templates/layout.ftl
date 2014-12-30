<#import "/spring.ftl" as spring />
<html>
    <head>
        <title>Dobry</title>
        <link rel="stylesheet" type="text/css" href="<@spring.url relativeUrl="/assets/materialize/css/materialize.min.css" />" media="screen,projection" />
        <link rel="stylesheet" type="text/css" href="<@spring.url relativeUrl="/assets/css/style.css" />" />
    </head>
    <body>

        <div id="header" class="row z-depth-1">
            <div class="container">
                <div class="col l12">
                    <h1>
                        <a href="<@spring.url relativeUrl="/" />">
                        Dobry
                        </a>
                    </h1>
                </div>
            </div>
        </div>

        <div id="content" class="row">
            <div class="container">
                <#include "${pageContent}.ftl">
            </div>
        </div>

    </body>
</html>