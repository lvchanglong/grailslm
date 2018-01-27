package common

class BootStrap {

    def commonService //公共服务

    def init = { servletContext ->
        commonService.initSystem() //初始化系统数据
        def platform = Platform.getActive()//获得已开启平台
        if(!platform) {
            platform = Platform.mockActive()//虚拟一个
        }
        servletContext.platform = platform
    }

    def destroy = {
    }

}
