package common

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class GuestController {

    def userService
    def loginLogService

    def index() {

        render User.getConstrainedProperties()
    }

    /**
     * 用户登录
     */
    def login(String username, String password) {
        if (username && password) {
            def user = User.findByUsernameAndPassword(username, password.encodeAsMD5())
            if (user) {
                if(!user.getStateToBoolean()) {
                    render status: UNAUTHORIZED, text: '已冻结'
                    return
                }
                session.uid = user.id
                session.setMaxInactiveInterval(43200) //失效时间12小时

                if(!user.isSuperman()) {
                    try {
                        loginLogService.save(new LoginLog([username:user.username, nickname:user.nickname, rolename:user.role, ip:CommonHelper.getRealIp(request)]))
                    } catch (ValidationException e) {

                    }
                }
                render status: OK, text: '操作成功，初始化...'
                return
            } else {
                render status: UNAUTHORIZED, text: '认证失败'
                return
            }
        }
        render status: BAD_REQUEST, text: '参数异常'
    }

    /**
     * 用户注册
     */
    def register(User user) {
        try {
            user.role = Role.getVip()
            userService.save(user)
        } catch (ValidationException e) {
            render status: INTERNAL_SERVER_ERROR
            return
        }
        render status: CREATED, text: "注册成功"
    }

    /**
     * 图片显示
     * @return
     */
//    def showSlice(String id) {
//        if(id) {
//            def slice = Slice.get(id)
//            if(slice) {
//                def sliceData = slice.data
//                if(sliceData) {
//                    ServletOutputStream out = response.getOutputStream()
//                    out.write(sliceData.bytes)
//                    out.flush()
//                    out.close()
//                }
//            }
//        } else {
//            /**
//             * http://localhost:8181/assets/home/1.jpg
//             */
//            def url = new URL(createLink(uri:'/', absolute:true) + assetPath(src:'home/111.png').replaceFirst("/", ""))
//            byte[] bytes = url.getBytes()
//            ServletOutputStream out = response.getOutputStream()
//            out.write(bytes)
//            out.flush()
//            out.close()
//        }
//    }
}
