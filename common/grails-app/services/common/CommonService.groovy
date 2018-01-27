package common

import grails.gorm.transactions.Transactional

@Transactional
class CommonService {

    /**
     * 初始化系统数据
     */
    void initSystem() {
        def platform = Platform.first()
        if(!platform) {
            platform = new Platform([name:"快速开发", state:"开启"])
            platform.save(flush: true)
        }

        def function = Function.first()
        if(!function) {
            def activePlatform = Platform.getActive()
            if(activePlatform) {
                ArrayList array = new ArrayList()
                array.add(new HashMap(["name":"功能管理", "controllerName":"function", "actionName":"index", "zIndex":10]))
                array.add(new HashMap(["name":"功能分组管理", "controllerName":"funGroup", "actionName":"index", "zIndex":20]))
                array.add(new HashMap(["name":"平台管理", "controllerName":"platform", "actionName":"index", "zIndex":30]))

                array.add(new HashMap(["name":"账号管理", "controllerName":"user", "actionName":"index", "zIndex":110]))
                array.add(new HashMap(["name":"角色管理", "controllerName":"role", "actionName":"index", "zIndex":120]))
                array.add(new HashMap(["name":"文件管理", "controllerName":"mimeFile", "actionName":"index", "zIndex":130]))
                array.add(new HashMap(["name":"幻灯片管理", "controllerName":"slide", "actionName":"index", "zIndex":140]))
                array.add(new HashMap(["name":"用户反馈", "controllerName":"feedback", "actionName":"index", "zIndex":150]))
                array.add(new HashMap(["name":"登录日志", "controllerName":"loginLog", "actionName":"index", "zIndex":160]))
                array.add(new HashMap(["name":"检索日志", "controllerName":"searchLog", "actionName":"index", "zIndex":170]))
                array.each {params->
                    platform.addToFunctions(new Function(params))
                }
                activePlatform.save(flush: true)
            }
        }

        def funGroup = FunGroup.first()
        if(!funGroup) {
            def funGroup1 = new FunGroup([name:"资源管理", "zIndex":10])
            Function.findAllByNameInList(["文件管理"]).each {fun->
                funGroup1.addToFunctions(fun)
            }
            funGroup1.save()

            def funGroup2 = new FunGroup([name:"人员管理", "zIndex":20])
            Function.findAllByNameInList(["账号管理", "角色管理"]).each {fun->
                funGroup2.addToFunctions(fun)
            }
            funGroup2.save()

            def funGroup3 = new FunGroup([name:"系统设置", "zIndex":30])
            Function.findAllByNameInList(["幻灯片管理", "用户反馈"]).each {fun->
                funGroup3.addToFunctions(fun)
            }
            funGroup3.save()

            def funGroup4 = new FunGroup([name:"日志管理", "zIndex":40])
            Function.findAllByNameInList(["登录日志", "检索日志"]).each {fun->
                funGroup4.addToFunctions(fun)
            }
            funGroup4.save()

            def funGroupZ = new FunGroup([name:"开发人员", "zIndex":1000])
            Function.findAllByNameInList(["功能管理", "功能分组管理", "平台管理"]).each {fun->
                funGroupZ.addToFunctions(fun)
            }
            funGroupZ.save(flush: true)
        }

        def role = Role.first()
        if(!role) {
            ["管理员", "普通用户"].each {name->
                def instance = new Role([name:name])
                instance.save(flush:true)
            }
        }

        def user = User.first()
        if(!user) {
            def instance = new User(["username":"admin", "password":"123456", "nickname":"管理员", role:Role.getAdmin()])
            instance.save()
            def superman = new User(["username":"superman", "password":"123456", "nickname":"开发人员"])
            superman.save(flush:true)
        }
    }

}
