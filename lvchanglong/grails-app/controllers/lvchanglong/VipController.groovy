package lvchanglong

import static org.springframework.http.HttpStatus.*

/**
 * 会员-已登录状态
 */
class VipController {

    def index() {
        render "会员"
    }

    /**
     * 退出登录
     */
    def logout() {
        session.invalidate()
        render status: OK, text: "操作成功"
    }

    /**
     * 个人信息修改
     */
    def regenerate(User user) {
        if (user == null) {
            render status: NOT_FOUND, text: "用户不存在"
            return
        }
        if (user.hasErrors()) {
            render status: INTERNAL_SERVER_ERROR, text: "修改失败"
            return
        }
        user.save(flash: true)
        render status: OK, text: "修改成功"
    }

}
