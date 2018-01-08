package lvchanglong

import static org.springframework.http.HttpStatus.UNAUTHORIZED


class AdminInterceptor {

    boolean before() {
        if(!session.uid) {
            redirect(url:"/")
            return false
        }
        if(!User.get(session.uid).isAdmin()) {
            render status: UNAUTHORIZED, text:"未授权，非管理员"
            return false
        }
        return true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }

}
