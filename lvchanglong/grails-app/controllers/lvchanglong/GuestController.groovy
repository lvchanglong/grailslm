package lvchanglong

import grails.converters.JSON
import grails.transaction.Transactional
import org.hibernate.criterion.CriteriaSpecification

import static org.springframework.http.HttpStatus.*

/**
 * 来宾-未登录状态
 */
@Transactional(readOnly = false)
class GuestController {

    /**
     * 首页
     */
    def index() {

    }

    /**
     * 软件下载
     */
    def software() {
        def groups = Software.list([sort:"zindex", order:"asc"]).groupBy {
            it.grouping
        }.sort()
        [groups:groups]
    }

    /**
     * 参考文档
     */
    def documentation() {
        def groups = Documentation.list([sort:"zindex", order:"asc"]).groupBy {
            it.grouping
        }.sort()
        [groups:groups]
    }

    /**
     * 学习
     */
    def learning() {
        def groups = Article.list([sort:"zindex", order:"asc"]).groupBy {
            it.grouping
        }.sort()
        [groups:groups]
    }

    /**
     * 建议
     */
    def suggest() {

    }

    /**
     * 社区
     */
    def community() {
        render "施工中！！！"
    }

    /**
     * carousel模板
     * @return
     */
    def carousel() {
        def articleList = Article.findAllByTitleLike("%${params.q?:""}%", params)//offset起始位置，max每页显示结果数
        def articleCount = Article.countByTitleLike("%${params.q?:""}%") //符合条件的结果总数
        [slideList: Slide.list(), articleList: articleList, articleCount:articleCount]
    }

    //------------------------------------------------------------------------------------------------------------------------

    /**
     * 用户登录
     */
    def login(String username, String password) {
        def user = User.findByUsernameAndPassword(username, password)
        if(user) {
            session.uid = user.id //保存用户id信息
            render status: OK, text: "登录成功"
            return
        }
        render status: UNAUTHORIZED, text: "登录失败"
    }

    /**
     * 用户注册
     */
    def register(User user) { //def user = new User(params)
        if(user.hasErrors()) {
            render status: INTERNAL_SERVER_ERROR, text: "注册失败"
            return
        }
        user.save(flash: true)
        render status: OK, text: "注册成功"
    }

    /**
     * 用户反馈
     * @param instance
     */
    def feedback(Feedback instance) {
        if(instance.hasErrors()) {
            render status: INTERNAL_SERVER_ERROR, text: "反馈失败"
            return
        }
        instance.save flush:true
        render status: OK, text: "反馈成功，灰常感谢！！！"
    }

}
