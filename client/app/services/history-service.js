angular.module ('myapp.services.history', ['ngResource', 'myapp.config'])

    .provider ('HistoryService', [function HistoryServiceProvider()
    {
        var apiUrlPrefix;

        function HistoryService ($resource) {
            "use strict";

            this.getActiveCourses = function ()
            {
                return $resource (apiUrlPrefix + "/userHistory/activeCourses");
            };

            this.getCourseTableOfContents = function ()
            {
                return $resource (apiUrlPrefix + "/userHistory/courses/:courseId/entryHistory", {courseId: '@courseId'});
            };

        }

        this.$get = ['$resource', 'Configuration', function ($resource, Configuration)
        {
            apiUrlPrefix = Configuration.getApiUrl ();
            return new HistoryService ($resource);
        }];
    }]);
