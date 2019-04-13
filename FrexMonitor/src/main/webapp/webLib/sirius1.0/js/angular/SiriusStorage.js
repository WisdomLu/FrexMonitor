/**
 * Created by Administrator on 2016/3/28.
 */
var SiriusStorageService = angular.module('Sirius.Storage', []);

SiriusStorageService.service('localStorage', function () {
    return {
        set: function (key,obj) {
            window.localStorage.setItem(key,angular.toJson(obj))
        },
        get: function (key) {
            return angular.fromJson(window.localStorage.getItem(key));

        },
        remove: function (key) {
            window.localStorage.removeItem(key);
        },
        clear: function () {
            window.localStorage.clear();
        }
    }
}).service('sessionStorage', function () {
    return {
        set: function (key,obj) {
            window.sessionStorage.setItem(key,angular.toJson(obj))
        },
        get: function (key) {
            return angular.fromJson(window.sessionStorage.getItem(key));

        },
        remove: function (key) {
            window.sessionStorage.removeItem(key);
        },
        clear: function() {
            window.sessionStorage.clear();
        }
    }
});