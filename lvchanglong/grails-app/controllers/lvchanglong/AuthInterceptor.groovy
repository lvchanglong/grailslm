package lvchanglong


class AuthInterceptor {

    AuthInterceptor() {
        matchAll().excludes(controller:"guest")
    }

    boolean before() {
//        if(!session.uid) {
//            redirect(url:"/")
//            return false
//        }
        return true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }

}
